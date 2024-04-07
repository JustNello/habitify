package com.ninocorp.core.portable;

import com.ninocorp.core.util.time.Timestamp;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class HabitFile {

    private final List<HabitRow> lines;

    private HabitFile(List<String> lines) {
        this.lines = toHabitRows(lines);
    }

    public List<HabitRow> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public List<String> getRowLines() {
        return lines.stream()
                .map(HabitRow::toString)
                .toList();
    }

    public static HabitFile valueOf(HabitFileReader reader) {
        return new HabitFile(reader.readAllLines());
    }

    private List<HabitRow> toHabitRows(Collection<String> lines) {
        return lines.stream()
                .map(HabitRow::new)
                .collect(Collectors.toList());
    }

    public void add(HabitRow habitRow) {
        lines.add(habitRow);
    }

    /**
     * Marks a specified habit as done by setting a completion timestamp. This method iterates through
     * an ordered collection of habits, where habits scheduled for earlier completion appear first.
     * Upon finding the first occurrence of the specified habit, it marks that habit as done by
     * updating it with the provided timestamp and returns the updated habit. Only the first match is
     * updated to reflect its completion status.
     *
     * @param habit The name of the habit to mark as done. This is a string identifier for the habit.
     * @param timestamp The timestamp when the habit was completed. This timestamp marks the habit as done.
     * @return The updated {@code HabitRow} object that has been marked as done. If the specified habit
     *         is not found within the collection, {@code null} is returned.
     */
    public HabitRow done(String habit, Timestamp timestamp) {
        // assume that habits have been ordered in a way
        // that the ones to be completed first appear earlier
        // in the habit file
        for (HabitRow habitRow : lines) {
            if (habitRow.is(habit)) {
                habitRow.done(timestamp);
                // update the first match only
                return habitRow;
            }
        }
        return null;
    }
}
