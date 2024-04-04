package com.ninocorp.core.model.habit;

import com.ninocorp.core.util.time.Timestamp;
import lombok.Value;

import java.util.Objects;

@Value
public class DailyTrack {

    StatusEnum status;

    Timestamp timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyTrack that = (DailyTrack) o;
        return Objects.equals(timestamp, that.timestamp);
    }
}
