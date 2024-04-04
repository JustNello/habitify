package com.ninocorp.core.model;

import lombok.Data;

import java.util.HashMap;

import static com.ninocorp.core.util.StringUtil.capitalize;

@Data
public class Notebook {

    private String title;

    private HashMap<String, Page> pages = new HashMap<>();

    public Notebook(String title) {
        this.title = title;
    }

    /**
     * Either creates or replace a page with the given title
     */
    public Page page(String title) {
        return (pages.containsKey(capitalize(title)))
                ? pages.get(capitalize(title)) : addPage(capitalize(title));
    }

    private Page addPage(String title) {
        Page result = new Page(title);
        pages.put(title, result);
        return result;
    }
}
