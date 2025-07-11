package com.tahoo.guides.ai_command_line_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.command.annotation.EnableCommand;


@CommandScan
@SpringBootApplication
public class AiCommandLineClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiCommandLineClientApplication.class, args);
	}

}
