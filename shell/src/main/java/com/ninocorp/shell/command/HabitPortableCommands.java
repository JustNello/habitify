package com.ninocorp.shell.command;

import com.ninocorp.core.portable.HabitRow;
import com.ninocorp.shell.portable.HabitFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.util.Objects;

import static com.ninocorp.core.util.time.Timestamp.today;
import static com.ninocorp.core.util.time.Timestamp.yesterday;

@Command(command = "hb")
public class HabitPortableCommands {

    @Autowired
    HabitFileStorage storage;

    @Command(command = "do", description = "After performing a habit, record it as accomplished")
    public String hello(
            @Option(description = "Specify the habit title",
                    longNames = "habit",
                    shortNames = 'n',
                    required = true) String habit,
            @Option(description = "Specify that the habit has been completed either yesterday or today",
                    longNames = "today",
                    shortNames = 't',
                    defaultValue = "false") boolean today
    ) {
        HabitRow result = storage.getHabitFile()
                .done(habit, (today) ? today() : yesterday());

        if (Objects.nonNull(result))
            return "+1 on your " + result.getHabit().toLowerCase() + " habit";
        return "No habit found";
    }
}
