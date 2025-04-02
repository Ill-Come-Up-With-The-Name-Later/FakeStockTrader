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
        private ArrayList<Object> items;

        public MapValuesWrapper() {
            items = new ArrayList<>();
        }
    }

    public HashMap<V, MapValuesWrapper> values;

    public MultiMap() {
        values = new HashMap<>();
    }
}
