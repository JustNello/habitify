package com.ninocorp.core.model.habit;

import com.ninocorp.core.util.time.Timestamp;

public interface Habit {

    public String getDescription();

    public boolean isCompleted();

    public void complete();

    public void reset();

    public void done(Timestamp timestamp);
}
