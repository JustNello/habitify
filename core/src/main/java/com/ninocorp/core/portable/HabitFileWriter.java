package com.ninocorp.core.portable;

import ch.qos.logback.core.util.FileUtil;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class HabitFileWriter {

    private final File fileToBeWritten;

    public HabitFileWriter(File fileToBeWritten) {
        this.fileToBeWritten = fileToBeWritten;
    }

    @SneakyThrows
    public void save(HabitFile habitFile) {
        Files.write(
                fileToBeWritten.toPath(),
                habitFile.getRowLines(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        );
    }
}
