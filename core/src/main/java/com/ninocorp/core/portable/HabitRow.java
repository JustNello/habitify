package com.ninocorp.core.portable;

import com.ninocorp.core.model.habit.Habit;
import com.ninocorp.core.util.StringUtil;
import com.ninocorp.core.util.time.Timestamp;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.ninocorp.core.util.StringUtil.capitalize;

@Value
public class HabitRow implements Habit {

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;

    String habit;
    String description;
    @NonFinal
    int current;
    @NonFinal
    int target;
    List<LocalDate> dates;

    public HabitRow(String raw) {
        this.habit = split(raw)[0];
        this.description = split(raw)[1];
        this.current = Integer.parseInt(split(raw)[2]);
        this.target = Integer.parseInt(split(raw)[3]);
        this.dates = parseDatesThisHabitHasBeenDone(raw);
    }

    private static String[] split(String raw) {
        // no validation done here
        return raw.split(",");
    }

    private static List<LocalDate> parseDatesThisHabitHasBeenDone(String raw) {
        return Arrays
                // get a list of strings representing dates
                .stream(split(raw)[4]
                        // at this point: a string like '[20240405,20240406]'
                        .replace("[", "")
                        .replace("]", "")
                        // at this point: a string like '20240405,20240406'
                        .split(",")
                        // at this point: an array composed of '20240405' and '20240406'
                )
                // drop duplicates
                .distinct()
                // ignore empty strings (this may be the case if there are no dates to be parsed)
                .filter(dateAsString -> !StringUtil.isEmpty(dateAsString))
                // parse string to LocalDate
                .map(dateAsString -> LocalDate.parse(dateAsString, DateTimeFormatter.BASIC_ISO_DATE))
                // collect dates into a modifiable set
                .collect(Collectors.toList());
    }

    public boolean is(String habit) {
        return this.habit.equals(capitalize(habit));
    }

    public List<LocalDate> getDates() {
        return Collections.unmodifiableList(dates);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(capitalize(habit)).append(",");
        result.append(capitalize(description)).append(",");
        result.append(current).append(",");
        result.append(target).append(",");
        // adding dates is a bit more messy
        result.append("[");
        for (int i = 0; i < dates.size(); i++) {
            result.append(DateTimeFormatter.BASIC_ISO_DATE.format(dates.get(i)));
            if (i < dates.size() - 1)
                result.append(",");
        }
        result.append("]");

        return result.toString();
    }

    @Override
    public boolean isCompleted() {
        return current >= target;
    }

    @Override
    public void complete() {
        current = target;
    }

    @Override
    public void reset() {
        current = 0;
    }

    @Override
    public void done(Timestamp timestamp) {
        current++;
        dates.add(timestamp.value().toLocalDate());
    }
}
