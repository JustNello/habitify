package com.ninocorp.core.portable;

import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
                .toList();
    }
}
