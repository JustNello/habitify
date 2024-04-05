package com.ninocorp.core;

import com.ninocorp.core.model.habit.DailyHabit;
import com.ninocorp.core.service.NotebooksManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public abstract class AbstractSpringBootTest {

    @SpringBootApplication
    static class TestConfiguration {

        @Bean
        public NotebooksManager notebooksManager() {
            NotebooksManager result = new NotebooksManager();
            result
                    .notebook("Test Suite Notebook")
                    .page("Exercise")
                    .addHabit(new DailyHabit("Exercise at least for five minutes", 5));

            return result;
        }
    }
}
