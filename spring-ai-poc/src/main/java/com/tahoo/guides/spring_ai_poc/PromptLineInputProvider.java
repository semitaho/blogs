package com.tahoo.guides.spring_ai_poc;

import org.jline.reader.LineReader;
import org.springframework.shell.Input;
import org.springframework.shell.jline.InteractiveShellRunner;
import org.springframework.shell.jline.PromptProvider;

public class PromptLineInputProvider extends InteractiveShellRunner.JLineInputProvider {
  public PromptLineInputProvider(final LineReader lineReader, final PromptProvider promptProvider) {
    super(lineReader, promptProvider);
  }

  @Override
  public Input readInput() {
    return super.readInput();
  }


}
