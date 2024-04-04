package com.ninocorp.core.model;

import lombok.NonNull;

import java.util.HashMap;
import java.util.function.Function;

import static com.ninocorp.core.util.StringUtil.capitalize;

public class CapitalizedHashMap<V> {

    protected final HashMap<String, V> items = new HashMap<>();
    private final Function<String, V> onNewItem;

    public CapitalizedHashMap(@NonNull Function<String, V> onNewItem) {
        this.onNewItem = onNewItem;
    }

    public V item(String key) {
        return (items.containsKey(capitalize(key)))
                ? items.get(capitalize(key)) : addItem(capitalize(key));
    }

    private V addItem(String key) {
        V result = onNewItem.apply(key);
        items.put(key, result);
        return result;
    }
}
