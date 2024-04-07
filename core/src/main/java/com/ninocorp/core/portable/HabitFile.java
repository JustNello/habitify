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

    public void done(String habit, Timestamp timestamp) {
        // assume that habits have been ordered in a way
        // that the ones to be completed first appear earlier
        // in the habit file
        for (HabitRow habitRow : lines) {
            if (habitRow.is(habit)) {
                habitRow.done(timestamp);
                // update the first match only
                break;
            }
        }
    }
}
