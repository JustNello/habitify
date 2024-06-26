package com.ninocorp.core.model.habit;

import com.ninocorp.core.exception.CompletedHabitException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Data
public class Page {

    private String title;

    @Getter(value = AccessLevel.PRIVATE)
    private List<Habit> habits = new LinkedList<>();

    protected Page(String title) {
        this.title = title;
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public Habit getHabit(int index) {
        return habits.get(index);
    }

    public void swapHabits(int indexOne, int indexTwo) {
        if (isWithinBound(indexOne) && isWithinBound(indexTwo) && indexOne != indexTwo) {
            Habit habitOne = getHabit(indexOne);
            Habit habitTwo = getHabit(indexTwo);
            habits.set(indexOne, habitTwo);
            habits.set(indexTwo, habitOne);
        }
    }

    public Habit removeHabit(int index) {
        return habits.remove(index);
    }

    public Habit getCurrentHabit() throws CompletedHabitException {
        return habits.stream()
                .filter(habit -> !habit.isCompleted())
                .findFirst()
                .orElseThrow(CompletedHabitException::new);
    }

    public Page asCurrentHabit() {
        Page result = new Page(title);
        result.addHabit(getCurrentHabit());
        return result;
    }

    public boolean isComplete() {
        try {
            getCurrentHabit();
            return false;
        } catch (CompletedHabitException e) {
            return true;
        }
    }

    public List<Habit> getAllHabits() {
        return Collections.unmodifiableList(habits);
    }

    private boolean isWithinBound(int index) {
        return index >= 0 && index < habits.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\n\n");
        result.append("[").append(title).append("]");
        for (int i = 0; i < habits.size(); i++) {
            result.append("\n").append("[").append(i).append("]")
                    .append("\t")
                    .append(habits.get(i));
        }
        return result.toString();
    }
}
