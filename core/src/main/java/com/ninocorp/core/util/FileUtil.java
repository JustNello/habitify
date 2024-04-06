package com.ninocorp.core.util;

import com.ninocorp.core.portable.HabitFileReader;

import java.io.File;
import java.nio.file.NoSuchFileException;

public final class FileUtil {

    public static File withinResources(String file) throws NoSuchFileException {
        try {
            return new File(HabitFileReader.class.getClassLoader().getResource(file).getFile());
        } catch (NullPointerException e) {
            throw new NoSuchFileException(file);
        }
    }

}
