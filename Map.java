import java.util.ArrayList;

/**
 * A HashMap Implementation using an Array as the underlying Data Structure and buckets chaining through ArrayLists
 * @implSpec This implementation's performance can be further improved by refraining from the bucket and chaining method, and rather, using quadratic probing. Additionally, an alternate implementation of the re-size method can be far more performance friendly (especially if a parameterized array is passed into put) in order to save on the need to perform an O(n) operation of storing into the arraylist of (key, value) as well as retrieving all those which is another O(n) for scanning through and retrieving every single key.
 * @author Faycal Kilali
 * @version 1.0
 * @param <K> the key used by the compression function to access the appropriate index
 * @param <V> the value associated with the key
 */
public class Map<K, V> implements
        MapADT<K, V> {
    private static final int INITIAL_CAPACITY = 131; // Initial size of the array. This should be a prime number in order to prevent degenerative infinite loops when using some forms of probing.

    private int capacity = INITIAL_CAPACITY;
    private ArrayList<Entry<K, V>>[] array; // Inefficient way of handling collisions, but is convenient. A better way would be quadratic probing, among others.

    private int size = 0;

    public Map(){
        array = (ArrayList<Entry<K, V>>[]) new ArrayList[INITIAL_CAPACITY];
    }

    /**
     * Inner Helper Class to represent key-value pairs
     * @param <K> the associated key
     * @param <V> the associated value
     */
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Function that adds the key, value pair to this map.
     * <p>
     * Recall that you need to store the key, value pair and not
     * just the value! A helper class may be necessary.
     *
     * @param key the parameterized key
     * @param value the parameterized value
     */
    @Override
    public void put(K key, V value) {

        // Hash then compress
        int hash = processForHashTable(key);

        // Retrieve bucket at computed hash index
        ArrayList<Entry<K, V>> bucket = array[hash];

        // If the bucket is null, we create a new list and add a key-value pair.
        if (bucket == null) {
            bucket = new ArrayList<>();
            array[hash] = bucket;
        }

        // Iterate through the existing entries in the bucket
        for (Entry<K, V> entry: bucket) {
            // Check for occurrences of key, if there is one, update the value associated with the key.
            if (entry.key.equals(key)) {
                entry.value = value;
                return; // Exit the method, as the update is done
            }
        }

        // If no matching key is found, add a new entry to the existing list
        bucket.add(new Entry<>(key, value));
        size++; // Our hash table is to be incremented at this point.

        // When using ArrayLists as the underlying data structure, there's less reason to resize (but still, there is reason),
        // as we allow collisions.
        // If using a different form of handling collisions (e.g; probing), then we will run into maximum capacity far more often.
        // if load factor is > 0.5, resize and re-hash for efficiency purposes.

        // Check the load factor and resize if needed
        if ((double) size() / getCapacity() > 0.5) {
            resize();
        }


    }

    /**
     * Applies a Hashing Function then a Compression Function on the parameterized key.
     * @param key the parameterized key
     * @return the output of the hashing function and compression function, typically also called the hash.
     */
    private int processForHashTable(K key){
        int hash = hashingFunction(key);
        return compressionFunction(hash);
    }

    /**
     * Applies a hashing function to the provided key
     * @param key the parameterized key
     * @return the hash
     */
    private int hashingFunction(K key){
        return key.hashCode();
    }

    /**
     *   Compression Function to size of array
     * @param hash the hash to apply compression function to
     * @return hash after compression function was applied
     */
    private int compressionFunction(int hash){
        return hash % array.length;
    }

    /**
     * Doubles underlying Array Size and rehashes appropriately.
     */
    private void resize() {
        // We want a prime number bigger than the current number as our capacity.
        setCapacity(naivePrimeSearch());

        // We rehash the elements into the new array...
        ArrayList<Entry<K, V>>[] largerArray;
        largerArray = (ArrayList<Entry<K, V>>[]) new ArrayList[getCapacity()];

        ArrayList<K> keys = getKeys();

        // Store (key, value) pairs -- note there is probably a better approach to this, such as put taking parameterized values so we don't have to store all the keys.
        ArrayList<Entry<K, V>> pairs = new ArrayList<>();
        for (K key: keys){
            V value = get(key);
            Entry<K, V> entry = new Entry(key, value);
            pairs.add(entry);
            //put(key, value);
        }

        // We don't need the original array anymore, truncate it
        array = largerArray;
        size = 0; // reset size

        // Now we add to the new array
        for (Entry<K, V> entry: pairs){
            put(entry.key, entry.value);
        }

    }

    /**
     * A naive prime searching algorithm
     * @implSpec Inefficient prime-searching, through a naive comparison.
     * @return the located prime
     */
    private int naivePrimeSearch(){
        int potentialPrime = getCapacity() * 2 + 1;
        int factor = 2;
        while (true){

            while (potentialPrime % factor != 0){
                factor++;
            }

            if (factor == potentialPrime){
                // We found a prime
                return potentialPrime;
            }

            else{
                potentialPrime++;
                factor = 2;
            }
        }
    }

    /**
     * Function that retrieves the value associated to the
     * key provided to this function.
     *
     * @param key the parameterized key
     */
    @Override
    public V get(K key) {
        int hash = processForHashTable(key);

        ArrayList<Entry<K, V>> bucket = array[hash];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    // Key found, return the associated value
                    return entry.value;
                }
            }
        }

        // Key not found
        return null;
    }

    /**
     * Function that removes the key, value pair stored
     * in this map that matches to the key provided
     * to this function.
     *
     * @param key the key to attempt removing the entry of from the hash table
     */
    @Override
    public void remove(K key) {

        int index = processForHashTable(key);
        ArrayList<Entry<K,V>> bucket;

        if (array[index] != null){
            bucket = array[index];
        }
        else{
            return;
        }

        for (Entry<K,V> entry: bucket){
            if (key.equals(entry.key) == true){
                bucket.remove(entry);
                size--;
                return; // only one removal needed
            }
        }
    }

    /**
     * Function that retrieves a list of all of
     * the keys currently being used in this Map
     * to link to values.
     * @return an ArrayList of keys
     */
    @Override
    public ArrayList<K> getKeys() {

        ArrayList<K> keys = new ArrayList<>();
        // We'll iterate through each bucket in our array
        for (ArrayList<Entry<K,V>> bucket: array){
            if (bucket != null){

                // Each entry in each bucket must be added as a (key, value pair)
                for (Entry<K, V> entry: bucket){
                    keys.add(entry.key);
                }
            }
        }

        return keys;

    }

    /**
     * Checks if the function is empty
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0){
            return true;
        }
        return false;
    }

    /**
     * Function that checks how many key, value pairs
     * are stored in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Accessor Method for Capacity
     * @return returns the capacity of the array
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * Mutator method to set capacity
     * @param inCapacity the capacity to set the array to
     */
    public void setCapacity(int inCapacity){
        capacity = inCapacity;
    }

}
