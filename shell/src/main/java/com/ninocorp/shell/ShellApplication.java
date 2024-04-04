package com.ninocorp.shell;

import com.ninocorp.core.service.NotebooksManager;
import com.ninocorp.shell.command.HabitCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.command.annotation.EnableCommand;

@SpringBootApplication(scanBasePackages = {
		"com.ninocorp.shell.config"
})
@CommandScan
public class ShellApplication {

	@Autowired
	NotebooksManager notebooksManager;

	public static void main(String[] args) {
		SpringApplication.run(ShellApplication.class, args);
	}

}
