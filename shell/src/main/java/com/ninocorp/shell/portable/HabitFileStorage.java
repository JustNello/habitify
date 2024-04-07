package com.ninocorp.shell.portable;

import com.ninocorp.core.portable.HabitFile;
import com.ninocorp.core.portable.io.HabitFileDiskReader;
import com.ninocorp.core.portable.io.HabitFileDiskWriter;
import com.ninocorp.core.portable.io.HabitFileReader;
import com.ninocorp.core.portable.io.HabitFileWriter;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class HabitFileStorage {

    private final StorageProperties properties;
    private final HabitFileReader reader;
    private final HabitFileWriter writer;
    @Getter
    private final HabitFile habitFile;

    public HabitFileStorage(StorageProperties properties) {
        this.properties = properties;
        this.reader = new HabitFileDiskReader(habitFileOnDisk());
        this.writer = new HabitFileDiskWriter(habitFileOnDisk());
        this.habitFile = HabitFile.valueOf(reader);
    }

    private File habitFileOnDisk() {
        return new File(properties.getFile());
    }

    public void save() {
        writer.save(habitFile);
    }

    @PreDestroy
    public void destroy() {
        save();
    }
}
