package com.ninocorp.core.exception;

public class CompletedHabitException extends RuntimeException {

    public CompletedHabitException() {
        super("All habits in this page are completed");
    }
}
