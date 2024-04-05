package com.ninocorp.core.util.time;

import java.time.LocalDateTime;
import java.util.Objects;

public record Timestamp(LocalDateTime value) {

    public static Timestamp today() {
        return new Timestamp(LocalDateTime.now());
    }

    public static Timestamp yesterday() {
        return new Timestamp(LocalDateTime.now().minusDays(1));
    }

    public static Timestamp valueOf(OnEnum onEnum) {
        return switch (onEnum) {
            case TODAY -> today();
            case YESTERDAY -> yesterday();
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timestamp timestamp = (Timestamp) o;
        return Objects.equals(value.toLocalDate(), timestamp.value.toLocalDate());
    }
}
