package com.ninocorp.core.model.habit;

import com.ninocorp.core.model.CapitalizedHashMap;
import lombok.Data;

@Data
public class Notebook {

    private String title;

    private CapitalizedHashMap<Page> pages = new CapitalizedHashMap<>(Page::new);

    public Notebook(String title) {
        this.title = title;
    }

    /**
     * Either creates or replace a page with the given title
     */
    public Page page(String title) {
        return pages.item(title);
    }

}
