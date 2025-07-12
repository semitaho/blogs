package com.tahoo.guides.ai_command_line_client;


import com.tahoo.guides.ai_command_line_client.history.ChatHistoryService;
import com.tahoo.guides.ai_command_line_client.prompt.ChatPromptService;
import org.springframework.ai.chat.messages.Message;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.standard.ShellComponent;

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
  public void prompt(final String promptquestion) {
    chatPromptService.stream(promptquestion).doOnNext(content -> {
      System.out.print(content);
      System.out.flush();
    }).doOnComplete(() -> System.out.println()).blockLast();

    // Here you would typically call your AI service to get a response
    // For now, we just echo the question
  }

  @Command(command = "history", description = "Display the conversion history")
  public void history() {
    chatHistoryService.getHistory()
            .stream()
            .map((final Message message) -> "%s: %s".formatted(message.getMessageType(), message.getText()))
            .forEach(System.out::println);
  }
}
