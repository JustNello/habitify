package com.ninocorp.shell.command;

import com.ninocorp.core.model.habit.Habit;
import com.ninocorp.core.model.habit.Page;
import com.ninocorp.core.service.NotebooksManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

@Command(command = "habit")
@RequiredArgsConstructor
public class HabitCommands {

    @Autowired
    private final NotebooksManager notebooksManager;

    @Command(command = "ps")
    public String listHabits(
            @Option(longNames = "page", required = false) String userPage,
            @Option(longNames = "all", shortNames = 'a', defaultValue = "false") boolean all) {

        StringBuilder output = new StringBuilder();
        List<Habit> habits = new ArrayList<>();

        for (Page page : notebooksManager.mostUsedNotebook().getPages().itemOrSelf(userPage)) {
            // list all habits
            if (all) {
                habits = page.getAllHabits();
            }
            // list habits that are not completed
            else if (!page.isComplete()) {
                habits = Collections.singletonList(page.getCurrentHabit());
            }

            // text to be written on the terminal
            output
                    .append("\n\n")
                    .append(page.getTitle());
            for (var habit : habits) {
                output
                    .append("\n")
                    .append(habit);
            }
        }

        return output.toString();
    }
}
