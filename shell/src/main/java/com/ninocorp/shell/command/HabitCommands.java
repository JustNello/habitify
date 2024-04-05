package com.ninocorp.shell.command;

import com.ninocorp.core.model.habit.Page;
import com.ninocorp.core.service.NotebooksManager;
import com.ninocorp.core.util.time.OnEnum;
import com.ninocorp.core.util.time.Timestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import static com.ninocorp.core.util.time.Timestamp.today;
import static com.ninocorp.core.util.time.Timestamp.yesterday;
import static java.lang.String.format;

@Command(command = "habit", description = "Display and update habits")
@RequiredArgsConstructor
public class HabitCommands {

    @Autowired
    private final NotebooksManager notebooksManager;

    @Command(command = "ps", description = "Display habits")
    public String listHabits(
            @Option(description = "Specifies the page of habits to display. Useful for pagination.",
                    longNames = "page",
                    required = false) String userPage,
            @Option(description = "Toggles between displaying all habits (true) or only incomplete habits (false). Default is 'false'.",
                    longNames = "all",
                    shortNames = 'a',
                    defaultValue = "false") boolean all) {

        StringBuilder output = new StringBuilder();

        for (Page page : notebooksManager.mostUsedNotebook().getPages().itemOrSelf(userPage)) {
            // list all habits
            if (all)
                output.append(page);
            // list habits that are not completed
            else if (!page.isComplete())
                output.append(page.asCurrentHabit());
        }

        return output.toString();
    }

    @Command(command = "do", description = "Mark an habit as done")
    public String doHabit(
            @Option(description = "Specifies the page of habits to mark as done",
                    longNames = "page",
                    required = true) String userPage,
            @Option(description = "Specify that the habit has been completed either yesterday or today",
                    longNames = "yesterday",
                    shortNames = 'y',
                    defaultValue = "true") boolean yesterday
    ) {
        if (!notebooksManager.mostUsedNotebook().getPages().containsItem(userPage))
            return format("No page found with name: '%s'", userPage);

        // mark as done
        notebooksManager
                .mostUsedNotebook()
                .page(userPage)
                .getCurrentHabit()
                .done((yesterday) ? yesterday() : today());

        // terminal text
        return notebooksManager
                .mostUsedNotebook()
                .page(userPage)
                .asCurrentHabit()
                .toString();
    }
}
