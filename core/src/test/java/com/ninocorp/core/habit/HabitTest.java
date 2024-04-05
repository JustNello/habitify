package com.ninocorp.core.habit;

import com.ninocorp.core.exception.CompletedHabitException;
import com.ninocorp.core.model.habit.DailyHabit;
import com.ninocorp.core.model.habit.Habit;
import com.ninocorp.core.model.habit.Notebook;
import com.ninocorp.core.model.habit.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.ninocorp.core.util.time.Timestamp.today;
import static com.ninocorp.core.util.time.Timestamp.yesterday;

public class HabitTest {

    @Test
    void aNewPageIsCreated() {
        Notebook notebook = new Notebook("2024");

        Page result = notebook.page("Diet");

        Assertions.assertEquals("Diet", result.getTitle());
    }

    @Test
    void aPageCreatedPreviouslyIsReturned() {
        // given
        Notebook notebook = new Notebook("2024");
        Page page = notebook.page("dIet");
        page.addHabit(new DailyHabit("Eating at least one Paleo meal"));

        // when
        Page result = notebook.page("DIET");

        // then
        Assertions.assertEquals(page, result);
    }

    @Test
    void habitsCanBeSwapped() {
        // given
        Notebook notebook = new Notebook("2024");
        Page page = notebook.page("dIet");
        page.addHabit(new DailyHabit("Eating at least one Paleo meal"));
        page.addHabit(new DailyHabit("Eating at least two Paleo meals"));

        // when
        page.swapHabits(0, 1);

        // then
        Assertions.assertEquals("Eating at least one Paleo meal",
                page.getHabit(1).getDescription());
        Assertions.assertEquals("Eating at least two Paleo meals",
                page.getHabit(0).getDescription());
    }

    @Test
    void userCanGetTheCurrentHabitTheyAreWorkingOn() {
        // given
        Notebook notebook = new Notebook("2024");
        Page page = notebook.page("dIet");
        page.addHabit(new DailyHabit("Eating at least one Paleo meal"));
        page.addHabit(new DailyHabit("Eating at least two Paleo meals"));

        // when
        Habit result = page.getCurrentHabit();

        // then
        Assertions.assertEquals(result, page.getHabit(0));
        Assertions.assertEquals("Eating at least one Paleo meal", result.getDescription());
    }

    @Test
    void userCanGetTheCurrentHabitTheyAreWorkingOnAlsoIfIsIsntTheFirstOnThePage() {
        // given
        Notebook notebook = new Notebook("2024");
        Page page = notebook.page("dIet");
        page.addHabit(new DailyHabit("Eating at least one Paleo meal"));
        page.addHabit(new DailyHabit("Eating at least two Paleo meals"));
        page.addHabit(new DailyHabit("Eating three Paleo meals"));
        page.getHabit(0).complete();

        // when
        Habit result = page.getCurrentHabit();

        // then
        Assertions.assertEquals(result, page.getHabit(1));
        Assertions.assertEquals("Eating at least two Paleo meals", result.getDescription());
    }

    @Test
    void userCanGetTheCurrentHabitTheyAreWorkingOnNoMatterWhat() {
        // given
        Notebook notebook = new Notebook("2024");
        Page page = notebook.page("dIet");
        page.addHabit(new DailyHabit("Eating at least one Paleo meal", 1));
        page.addHabit(new DailyHabit("Eating at least two Paleo meals"));
        page.getHabit(0).done(yesterday());

        // when
        Habit result = page.getCurrentHabit();

        // then
        Assertions.assertEquals(result, page.getHabit(1));
        Assertions.assertEquals("Eating at least two Paleo meals", result.getDescription());
        Assertions.assertFalse(page.isComplete());
    }

    @Test
    void anHabitCanBeDoneUntilComplete() {
        // given
        Notebook notebook = new Notebook("2024");
        Page page = notebook.page("dIet");
        page.addHabit(new DailyHabit("Eating at least one Paleo meal", 2));
        page.addHabit(new DailyHabit("Eating at least two Paleo meals", 2));

        // when
        page.getCurrentHabit().done(yesterday());
        page.getCurrentHabit().done(today());
        page.getCurrentHabit().done(yesterday());
        page.getCurrentHabit().done(today());

        Exception result = Assertions.assertThrows(CompletedHabitException.class,
                page::getCurrentHabit);

        // then
        Assertions.assertEquals("All habits in this page are completed",
                result.getMessage());
        Assertions.assertTrue(page.isComplete());
    }
}
