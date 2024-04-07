package com.ninocorp.core.portable;

import com.ninocorp.core.portable.io.HabitFileDiskReader;
import com.ninocorp.core.portable.io.HabitFileDiskWriter;
import com.ninocorp.core.portable.io.HabitFileReader;
import com.ninocorp.core.portable.io.HabitFileWriter;
import com.ninocorp.core.util.time.Timestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

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
        HabitFileReader reader = new HabitFileDiskReader(withinResources("habits.txt"));
        // Convert the loaded file's content into an in-memory HabitFile object for manipulation
        HabitFile habitFile = HabitFile.valueOf(reader);

        // PART 2 - Create and write to a new file using the sample's content
        // Prepare a writer to output a new file in a temporary directory
        HabitFileWriter writer = new HabitFileDiskWriter(tempFile(tempDir));
        // Save the in-memory HabitFile object's content to the new file on disk
        writer.save(habitFile);

        // PART 3 - Validate the new file by comparing its content with the original
        // Set up another reader to load the newly created habits file from the temporary directory
        HabitFileReader anotherReader = new HabitFileDiskReader(tempFile(tempDir));
        // Convert the new file's content into another in-memory HabitFile object for comparison
        HabitFile anotherHabitFile = HabitFile.valueOf(anotherReader);

        // Perform the assertion to ensure both the original and the newly created HabitFile objects are identical
        Assertions.assertEquals(habitFile, anotherHabitFile);
    }

    @Test
    void habitFileCanBeAddedHabitsOrUpdated() throws NoSuchFileException {
        // given
        HabitFileReader reader = new HabitFileDiskReader(withinResources("habits.txt"));
        HabitFile habitFile = HabitFile.valueOf(reader);

        // when
        habitFile.add(new HabitRow("Diet,Eating three Paleo meals,0,30,[]"));
        habitFile.done("Diet", new Timestamp(LocalDateTime.of(2024, 4, 6, 10, 0, 0)));

        // then
        Assertions.assertEquals(Arrays.asList(
                "Diet,Eating one paleo meal,3,7,[20240406,20240406]",
                "Diet,Eating two paleo meals,1,7,[20240406]",
                "Sleep,Going to sleep before 20.45,0,7,[]",
                "Diet,Eating three paleo meals,0,30,[]"
                )
                , habitFile.getRowLines());
    }

    private File tempFile(Path tempDir) {
        return new File(Path
                .of(tempDir.toString(), "habits.txt")
                .toUri());
    }
}
