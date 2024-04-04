package com.ninocorp.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DailyHabit extends Habit {

    private List<DailyTrack> dailyTracks = new ArrayList<>();

    private int current = 0;

    private int target = Integer.MAX_VALUE;

    public DailyHabit(String description) {
        this(description, Integer.MAX_VALUE);
    }

    public DailyHabit(String description, int target) {
        super(description);
        this.target = target;
    }

    @Override
    public boolean isCompleted() {
        return current >= target;
    }

    @Override
    public void complete() {
        this.current = target;
    }

    @Override
    public void reset() {
        this.current = 0;
    }

    @Override
    public void done() {
        this.current++;
    }

}
