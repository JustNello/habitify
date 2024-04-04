package com.ninocorp.core.service;

import com.ninocorp.core.model.CapitalizedHashMap;
import com.ninocorp.core.model.habit.Notebook;
import org.springframework.stereotype.Service;

@Service
public class NotebooksManager {

    private final CapitalizedHashMap<Notebook> notebooks = new CapitalizedHashMap<>(Notebook::new);

    public Notebook notebook(String title) {
        return notebooks.item(title);
    }
}
