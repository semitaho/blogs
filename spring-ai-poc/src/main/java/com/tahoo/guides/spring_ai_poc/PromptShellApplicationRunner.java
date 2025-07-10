package com.tahoo.guides.spring_ai_poc;

import org.jline.reader.LineReader;
import org.springframework.shell.InputProvider;
import org.springframework.shell.Shell;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.context.ShellContext;
import org.springframework.shell.jline.InteractiveShellRunner;
import org.springframework.shell.jline.PromptProvider;


public class PromptShellApplicationRunner extends InteractiveShellRunner {

  private final LineReader lineReader;

  private final PromptProvider promptProvider;

  private final Shell shell;

  private final ShellContext shellContext;


  public PromptShellApplicationRunner(final LineReader lineReader, final PromptProvider promptProvider, final Shell shell, final ShellContext shellContext) {
    super(lineReader, promptProvider, shell, shellContext);
    this.lineReader = lineReader;
    this.promptProvider = promptProvider;
    this.shell = shell;
    this.shellContext = shellContext;
  }

  @Override
  public boolean run(final String[] args) throws Exception {
    this.shellContext.setInteractionMode(InteractionMode.INTERACTIVE);
    InputProvider inputProvider = new PromptLineInputProvider(this.lineReader,this.promptProvider);

    this.shell.run(inputProvider);
    return true;
  }
}
