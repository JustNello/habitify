package com.ninocorp.core.model.habit;

import com.ninocorp.core.util.time.Timestamp;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Data
public class DailyHabit implements Habit {

    private Set<DailyTrack> dailyTracks = new HashSet<>();

    private String description;

    private int current = 0;

    private int target = Integer.MAX_VALUE;

    public DailyHabit(String description) {
        this(description, Integer.MAX_VALUE);
    }

    public DailyHabit(String description, int target) {
        this.description = description;
        this.target = target;
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
        dailyTracks.add(new DailyTrack(StatusEnum.DONE, timestamp));
    }

    @Override
    public String toString() {
        return format("{%d/%d}\t%s", current, target, description);
    }

}
