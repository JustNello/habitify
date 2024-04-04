package com.ninocorp.core.model.habit;

import com.ninocorp.core.util.time.Timestamp;

public abstract class Habit {

    protected String description;

    public Habit() { }

    public Habit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean isCompleted();

    public abstract void complete();

    public abstract void reset();

    public abstract void done(Timestamp timestamp);
}
