package com.ninocorp.core.portable.io;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public final class HabitFileDiskReader implements HabitFileReader {

    private final File file;

    public HabitFileDiskReader(File file) {
        this.file = file;
    }

    @SneakyThrows
    @Override
    public List<String> readAllLines() {
        return Files.readAllLines(file.toPath());
    }
}
