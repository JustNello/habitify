package com.ninocorp.core.model;

import com.ninocorp.core.util.time.Timestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class DailyHabit extends Habit {

    private Set<DailyTrack> dailyTracks = new HashSet<>();

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

}
