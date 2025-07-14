package com.tahoo.guides.ai_command_line_client;


import com.tahoo.guides.ai_command_line_client.history.ChatHistoryService;
import com.tahoo.guides.ai_command_line_client.prompt.ChatPromptService;
import org.springframework.ai.chat.messages.Message;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.time.Duration;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

@Command(group = "AI Commands")
public class AiCommands {

  private static final Integer STREAMING_INTERVAL_MS = 50;
  private final ChatHistoryService chatHistoryService;

  private final ChatPromptService chatPromptService;


  public AiCommands(final ChatPromptService chatPromptService, final ChatHistoryService chatHistoryService) {
    this.chatPromptService = chatPromptService;
    this.chatHistoryService = chatHistoryService;
  }

  @Command(command = "prompt", alias = "p", description = "Ask a question to the AI")
  public void prompt(@Option(required = true, arity = CommandRegistration.OptionArity.ONE_OR_MORE) final List<String> words) {
    final var promptText = words.stream()
            .collect(joining(" "));
    chatPromptService.stream(promptText)
            .delayElements(Duration.ofMillis(STREAMING_INTERVAL_MS))
            .doOnNext(content -> {
              System.out.print(content);
              System.out.flush();
            }).doOnComplete(System.out::println)
            .blockLast();

  }

  @Command(command = "history", alias = "h", description = "Displays the conversation history of last 10 messages")
  public String history() {
    return chatHistoryService.getHistory()
            .stream()
            .map((final Message message) -> "%s: %s".formatted(message.getMessageType(), message.getText()))
            .collect(joining(lineSeparator()));
  }
}
