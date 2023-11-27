package CosmicApp;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MapADTImplTest {

    @org.junit.jupiter.api.Test
    void putAndGet() {
        Map<String, Integer> map = new Map<>();

        // Test put and get for a single entry
        map.put("one", 1);
        assertEquals(1, map.get("one"));

        // Test put and get for multiple entries
        map.put("two", 2);
        map.put("three", 3);
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        Map<String, Integer> map = new Map<>();

        // Test remove for an entry
        map.put("one", 1);
        assertEquals(1, map.get("one"));
        map.remove("one");
        assertNull(map.get("one"));

        // Test remove for non-existing entry
        map.remove("nonexistent");
        assertNull(map.get("nonexistent"));
    }

    @org.junit.jupiter.api.Test
    void getKeys() {
        Map<String, Integer> map = new Map<>();

        // Test getKeys for an empty map
        assertTrue(map.getKeys().isEmpty());

        // Test getKeys for a map with entries
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        assertEquals(Set.of("one", "two", "three"), new HashSet<>(map.getKeys()));
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        Map<String, Integer> map = new Map<>();

        // Test isEmpty for an empty map
        assertTrue(map.isEmpty());

        // Test isEmpty for a non-empty map
        map.put("one", 1);
        assertFalse(map.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void size() {
        Map<String, Integer> map = new Map<>();

        // Test size for an empty map
        assertEquals(0, map.size());

        // Test size for a map with entries
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        assertEquals(3, map.size());
    }

    @org.junit.jupiter.api.Test
    void resize() {
        Map<String, Integer> map = new Map<>();

        // Add entries to trigger resizing
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // Add more entries to trigger resizing
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);

        // Check if the capacity has went up to the next prime (of double size + 1 at minimum)
        assertEquals(17, map.getCapacity());
    }



}