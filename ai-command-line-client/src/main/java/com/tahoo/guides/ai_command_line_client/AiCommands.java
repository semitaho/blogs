package com.tahoo.guides.ai_command_line_client;


import org.springframework.shell.command.annotation.Command;

public class AiCommands {


  @Command(command = "history", description = "Display the command history")
  public String history() {
    return "Hello, AI Command Line Client!";
  }
}
