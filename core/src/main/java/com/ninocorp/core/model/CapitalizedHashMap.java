package com.ninocorp.core.model;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.ninocorp.core.util.StringUtil.capitalize;

public class CapitalizedHashMap <V> implements Iterable<V> {

    protected final HashMap<String, V> items = new HashMap<>();
    private final Function<String, V> onNewItem;

    public CapitalizedHashMap(@NonNull Function<String, V> onNewItem) {
        this.onNewItem = onNewItem;
    }

    /**
     * Retrieves the first item ever added to the map. This method is useful for operations
     * where the insertion order matters or when needing to reference the initial state of the map.
     *
     * Note: The behavior is undefined if the map has been modified (items added or removed) since
     * the first item was added. It's assumed this method returns a reference to the item, not a copy.
     *
     * @return The first item added to the map. Returns null if the map is empty.
     */
    public V findFirst() {
        return items.values()
                .stream()
                .findFirst()
                .orElseThrow();
    }

    /**
     * Attempts to create a new item in the map with the specified key. If the key is new,
     * the provided onNewItem functional interface is executed to initialize the item.
     * This method is primarily used for adding new items with a specific initialization process.
     *
     * Note: The behavior or state change (if any) when the key already exists in the map is not specified,
     * suggesting that existing items are not modified or replaced by this method.
     *
     * @param key The key with which the specified item is to be associated. The key cannot be null.
     */
    public V item(String key) {
        return (items.containsKey(capitalize(key)))
                ? items.get(capitalize(key)) : addItem(capitalize(key));
    }

    /**
     * Searches for an item in the map using the specified key. If the item is found,
     * a new CapitalizedHashMap instance is created containing only the found item and returned.
     * If the item is not found, a copy of the current map is returned instead.
     * This method is useful for isolating specific items or verifying their existence within the map.
     *
     * @param key The key of the item to search for. The key cannot be null.
     * @return A new CapitalizedHashMap instance containing only the found item if the item is found;
     * otherwise, a copy of the current map. Never returns null.
     */

    public CapitalizedHashMap<V> itemOrSelf(String key) {
        if (Objects.isNull(key)) {
            return this;
        }
        else {
            var result = new CapitalizedHashMap<>(onNewItem);
            if (items.containsKey(capitalize(key)))
                result.addItem(key, item(key));
            return result;
        }
    }

    private void addItem(String key, V item) {
        items.put(capitalize(key), item);
    }

    private V addItem(String key) {
        V result = onNewItem.apply(capitalize(key));
        items.put(capitalize(key), result);
        return result;
    }

    public boolean containsItem(String key) {
        return items.containsKey(capitalize(key));
    }

    @Override
    public Iterator<V> iterator() {
        return items.values()
                .stream()
                .iterator();
    }
}
