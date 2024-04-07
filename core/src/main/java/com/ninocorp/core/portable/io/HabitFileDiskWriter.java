package com.ninocorp.core.portable.io;

import com.ninocorp.core.portable.HabitFile;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public final class HabitFileDiskWriter implements HabitFileWriter {

    private final File fileToBeWritten;

    public HabitFileDiskWriter(File fileToBeWritten) {
        this.fileToBeWritten = fileToBeWritten;
    }

    @SneakyThrows
    @Override
    public void save(HabitFile habitFile) {
        Files.write(
                fileToBeWritten.toPath(),
                habitFile.getRowLines(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
        );
    }

}
