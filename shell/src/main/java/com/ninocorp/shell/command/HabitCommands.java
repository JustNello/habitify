package com.ninocorp.shell.command;

import com.ninocorp.core.service.NotebooksManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;

@Command(command = "habit")
@RequiredArgsConstructor
public class HabitCommands {

    @Autowired
    private final NotebooksManager notebooksManager;

    @Command(command = "habit")
    public String habit() {
        return notebooksManager.toString();
    }
}
