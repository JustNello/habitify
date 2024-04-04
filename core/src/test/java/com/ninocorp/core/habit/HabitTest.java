package com.ninocorp.core.habit;

import com.ninocorp.core.model.DailyHabit;
import com.ninocorp.core.model.Habit;
import com.ninocorp.core.model.Notebook;
import com.ninocorp.core.model.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        page.getHabit(0).done();

        // when
        Habit result = page.getCurrentHabit();

        // then
        Assertions.assertEquals(result, page.getHabit(1));
        Assertions.assertEquals("Eating at least two Paleo meals", result.getDescription());
    }
}