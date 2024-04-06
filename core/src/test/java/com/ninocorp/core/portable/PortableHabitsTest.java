package com.ninocorp.core.portable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ninocorp.core.util.FileUtil.withinResources;

class PortableHabitsTest {

    @Test
    void habitRowHasGettersForEveryField() {
        HabitRow row = new HabitRow("Diet,Eating two paleo meals,1,7,[20240406]");

        Assertions.assertEquals("Diet", row.getHabit());
        Assertions.assertEquals("Eating two paleo meals", row.getDescription());
        Assertions.assertEquals(1, row.getCurrent());
        Assertions.assertEquals(7, row.getTarget());
        Assertions.assertEquals(1, row.getDates().size());
        Assertions.assertEquals(
                LocalDate.of(2024, 4, 6), row.getDates().get(0));
    }

    @Test
    void habitRowCanBeParsedWithAnEmptyListOfDates() {
        HabitRow row = new HabitRow("Diet,Eating two paleo meals,1,7,[]");

        Assertions.assertEquals(0, row.getDates().size());
    }

    @Test
    void writeFile(@TempDir Path tempDir) throws NoSuchFileException {
        // PART 1 - Load the sample habits file
        // Initialize a reader to load a sample habits file from resources
        HabitFileReader reader = new HabitFileReader(withinResources("habits.txt"));
        // Convert the loaded file's content into an in-memory HabitFile object for manipulation
        HabitFile habitFile = HabitFile.valueOf(reader);

        // PART 2 - Create and write to a new file using the sample's content
        // Prepare a writer to output a new file in a temporary directory
        HabitFileWriter writer = new HabitFileWriter(tempFile(tempDir));
        // Save the in-memory HabitFile object's content to the new file on disk
        writer.save(habitFile);

        // PART 3 - Validate the new file by comparing its content with the original
        // Set up another reader to load the newly created habits file from the temporary directory
        HabitFileReader anotherReader = new HabitFileReader(tempFile(tempDir));
        // Convert the new file's content into another in-memory HabitFile object for comparison
        HabitFile anotherHabitFile = HabitFile.valueOf(anotherReader);

        // Perform the assertion to ensure both the original and the newly created HabitFile objects are identical
        Assertions.assertEquals(habitFile, anotherHabitFile);
    }

    @Test
    void demo() throws NoSuchFileException {
        HabitFileReader reader = new HabitFileReader(withinResources("habits.txt"));

        HabitFile file = HabitFile.valueOf(reader);

        /* methods that should be implemented
        file.add(new HabitRow());
        file.remove(new HabitRow());
        file.done("Diet", yesterday());
        *
         */
    }

    private File tempFile(Path tempDir) {
        return new File(Path
                .of(tempDir.toString(), "habits.txt")
                .toUri());
    }
}
