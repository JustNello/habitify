package com.ninocorp.core.exception;

public class EmptyHabitException extends RuntimeException {

    public EmptyHabitException() {
        super("Empty page");
    }
}
