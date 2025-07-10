package com.tahoo.guides.spring_ai_poc;

import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.shell.Shell;
import org.springframework.shell.ShellRunner;
import org.springframework.shell.boot.CommandRegistrationCustomizer;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.context.ShellContext;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
@EnableCaching
@CommandScan
public class SpringAiPocApplication {


  @Bean
  public PromptProvider myPromptProvider() {
    return () -> new AttributedString("mun shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
  }



  @Bean
  CommandRegistrationCustomizer commandRegistrationCustomizerExample() {
    return builder -> {
      System.out.println("Customizing command registration");
      // customize instance of CommandRegistration.Builder
    };
  }

  @Bean
  @Order(-100)
  public ShellRunner myShellRunner(final LineReader lineReader,
                                   final PromptProvider promptProvider,
                                   final Shell shell,
                                   final ShellContext shellContext) {
    return new PromptShellApplicationRunner(lineReader, promptProvider, shell, shellContext);
  }

  @Bean
  public CommandExceptionResolver exceptionResolver() {
    return ex -> {
      // Handle the exception and return a message
      // For example, you can log the exception or return a custom message
      System.err.println("An error occurred: " + ex.getMessage());
      return null;
    };
  }

  @Bean
  CommandRegistration jeeje(CommandRegistration.BuilderSupplier builder) {
    return builder.get()
            .command("jeeje")
            .description("jeeje command")
            .withTarget().
            consumer(ctx -> {
                // This is where you can handle the command logic
                // For example, you can print a message or perform some action
                // ctx.getWriter().println("jeeje command executed");
              System.out.println("jeeje command executed");
            }).and()
            .build();
  }


  public static void main(String[] args) {
    SpringApplication.run(SpringAiPocApplication.class, args);
  }


}
