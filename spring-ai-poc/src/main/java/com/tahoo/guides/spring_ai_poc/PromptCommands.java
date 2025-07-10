package com.tahoo.guides.spring_ai_poc;

import org.jline.terminal.Terminal;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.component.flow.ComponentFlow;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.standard.AbstractShellComponent;

@Command
public class PromptCommands extends AbstractShellComponent {

  private final ChatClient chatClient;

  public PromptCommands(final ChatClient.Builder clientBuilder) {
    this.chatClient = clientBuilder

            .build();
  }

  @Autowired
  private Terminal terminal;


  @Autowired
  private ComponentFlow.Builder componentFlowBuilder;


  @Command(command = "*", description = "Ask GPT a question or get an audio response.", interactionMode = InteractionMode.INTERACTIVE)
  public void gpt(final String prompt) {
    System.out.println("prompt = " + prompt);

  }

  @CommandAvailability
  public Availability availabilityCheck() {
    return Availability.unavailable("ei kai");

  }
}
