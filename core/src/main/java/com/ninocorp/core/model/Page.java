package com.ninocorp.core.model;

import com.ninocorp.core.exception.CompletedHabitException;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Page {

    private String title;

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

    private boolean isWithinBound(int index) {
        return index >= 0 && index < habits.size();
    }
}
