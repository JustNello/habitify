package com.ninocorp.shell.config;

import com.ninocorp.core.model.habit.DailyHabit;
import com.ninocorp.core.model.habit.Notebook;
import com.ninocorp.core.model.habit.Page;
import com.ninocorp.core.service.NotebooksManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(NotebooksManager.class)
public class DemoConfig {

    @Bean
    public CommandLineRunner initData(NotebooksManager notebooksManager) {
        return args -> {
            Notebook notebook = notebooksManager.notebook("2024 demo");

            Page dietPage = notebook.page("Diet");
            dietPage.addHabit(new DailyHabit("Eating at least one Paleo meal", 7));
            dietPage.addHabit(new DailyHabit("Eating at least two Paleo meals", 10));
            dietPage.addHabit(new DailyHabit("Eating three Paleo meals", 30));

            Page sleepPage = notebook.page("Sleep");
            sleepPage.addHabit(new DailyHabit("Go to sleep at 20.45", 5));
        };
    }

}
