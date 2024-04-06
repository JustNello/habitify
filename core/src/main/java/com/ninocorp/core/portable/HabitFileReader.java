package com.ninocorp.core.portable;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class HabitFileReader {

    private final File file;

    public HabitFileReader(File file) {
        this.file = file;
    }

    @SneakyThrows
    protected List<String> readAllLines() {
        return Files.readAllLines(file.toPath());
    }
}
