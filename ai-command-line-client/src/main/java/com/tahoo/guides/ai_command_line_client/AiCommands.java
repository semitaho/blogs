package com.tahoo.guides.ai_command_line_client;


import com.tahoo.guides.ai_command_line_client.history.ChatHistoryService;
import com.tahoo.guides.ai_command_line_client.prompt.ChatPromptService;
import org.springframework.ai.chat.messages.Message;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;

import java.time.Duration;
import java.util.List;

import static java.util.stream.Collectors.joining;

@ShellComponent
@Command(group = "AI Commands")
public class AiCommands {

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
            .delayElements(Duration.ofMillis(100))
            .doOnNext(content -> {
              System.out.print(content);
              System.out.flush();
            }).doOnComplete(System.out::println)

            .blockLast();

  }

  @Command(command = "history", alias = "h", description = "Displays the conversation history of last 10 messages")
  public void history() {
    chatHistoryService.getHistory()
            .stream()
            .map((final Message message) -> "%s: %s".formatted(message.getMessageType(), message.getText()))
            .forEach(System.out::println);
  }
}
