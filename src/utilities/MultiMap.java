package utilities;

import java.util.*;

/**
 * A Map that can hold multiple values associated to one key.
 *
 * @param <V> The type of the keys
 */
public class MultiMap<V> {

    /**
     * Container allowing for multiple values
     * to be associated to one key
     */
    public static class MapValuesWrapper {
        private final ArrayList<Object> items;

        public MapValuesWrapper() {
            items = new ArrayList<>();
        }

        /**
         * Retrieves items of a specific class
         *
         * @param cls The class of the items to retrieve
         * @return A list of items of type <code>T</code>
         * @param <T> The type of the items to retrieve
         */
        public final <T> ArrayList<T> getValuesByClass(Class<T> cls) {
            ArrayList<T> items = new ArrayList<>();

            for(final Object obj : this.items) {
                if(cls.isAssignableFrom(obj.getClass())) {
                    items.add((T) obj);
                }
            }

            return items;
        }

        /**
         * Adds an item to the wrapper
         *
         * @param item The item to remove
         * @return this <code>MapValuesWrapper</code>
         */
        public MapValuesWrapper addItem(Object item) {
            items.add(item);
            return this;
        }

        /**
         * Removes an item from the wrapper
         *
         * @param item The item to remove
         * @return this <code>MapValuesWrapper</code>
         */
        public MapValuesWrapper removeItem(Object item) {
            items.remove(item);
            return this;
        }

        /**
         * The items in the wrapper
         *
         * @return An <code>ArrayList</code> of the items in the wrapper
         */
        public ArrayList<Object> getItems() {
            return this.items;
        }

        /**
         * Determines if this wrapper contains <code>item</code>
         *
         * @param item The item
         * @return If this wrapper contains <code>item</code>
         */
        public boolean contains(Object item) {
            return this.items.contains(item);
        }
    }

    private final HashMap<V, MapValuesWrapper> values;

    public MultiMap() {
        values = new HashMap<>();
    }

    /**
     * Maps a key to a value. If the key exists, the value
     * is added on to the values associated with the key
     *
     * @param key The key
     * @param value The value
     */
    public void put(V key, Object value) {
        if(this.containsKey(key)) {
            values.get(key).addItem(value);
        } else {
            this.values.put(key, new MapValuesWrapper().addItem(value));
        }
    }

    /**
     * Maps a key to an <code>ArrayList</code> of values
     *
     * @param key The key
     * @param values An <code>ArrayList</code> of values
     */
    public void put(V key, ArrayList<Object> values) {
        for(Object object : values) {
            this.put(key, object);
        }
    }

    /**
     * Removes a key and it's values from this map
     *
     * @param key The key to remove
     */
    public void remove(V key) {
        values.remove(key);
    }

    /**
     * Removes a value from any key it is associated with
     *
     * @param value The value
     */
    public void removeValue(Object value) {
        for(V key : this.keySet()) {
            this.get(key).remove(value);
        }
    }

    /**
     * Determines if <code>key</code> is in this map's
     * key set
     *
     * @param key The key to search for
     * @return If <code>key</code> is in the key set
     */
    public boolean containsKey(V key) {
        return values.containsKey(key);
    }

    /**
     * The keys in this <code>MultiMap</code>
     *
     * @return The keys in this <code>MultiMap</code>
     */
    public Set<V> keySet() {
        return values.keySet();
    }

    /**
     * All values associated to a key
     *
     * @param key The key
     * @return An <code>ArrayList</code> of all values associated with
     * <code>key</code>
     */
    public ArrayList<Object> get(V key) {
        return new ArrayList<>(this.values.get(key).getItems());
    }

    /**
     * Retrieves items of a specific class mapped to a value
     *
     * @param key The key
     * @param cls The class of the items to retrieve
     * @return A list of items of type <code>T</code>
     * @param <T> The type of the items to retrieve
     */
    public final <T> ArrayList<T> getValuesByClass(V key, Class<T> cls) {
        ArrayList<T> items = new ArrayList<>();
        ArrayList<Object> values = this.get(key);

        for(final Object object : values) {
            if(cls.isAssignableFrom(object.getClass())) {
                items.add((T) object);
            }
        }

        return items;
    }
}
