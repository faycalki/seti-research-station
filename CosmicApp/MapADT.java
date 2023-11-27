package CosmicApp;

import java.util.ArrayList;

/**
 * 
 * Interface to define the interface that represents the
 * generic behavior of a CosmicApp.Map object.
 * 
 * Implementations of this interface serve as a structure
 * that uses objects of type K as key objects to access
 * the values stored in the map of type V in constant time.
 * 
 * Your implementation should use an array to store the <K, V>
 * pairs, and accordingly should handle managing this map's
 * load as well as re-hashing the contents if the size of
 * the backing array changes.
 * 
 * You can simply use the hashCode function built into whatever
 * object of type K is given, so you only have to handle the
 * compression function part of this.
 * 
 * You may handle collisions however feels most appropriate to you.
 * 
 */

public interface MapADT <K, V>
{
 
    /**
     * 
     * Function that adds the key, value pair to this map.
     * 
     * Recall that you need to store the key, value pair and not
     * just the value! A helper class may be necessary.
     * 
     */
    
    public abstract void put(K key, V value);
    
    /**
     * 
     * Function that retrieves the value associated to the
     * key provided to this function.
     * 
     */
    
    public abstract V get(K key);
    
    /**
     * 
     * Function that removes the key, value pair stored
     * in this map that matches to the key provided
     * to this function.
     * 
     */
    
    public abstract void remove(K key);
    
    /**
     * 
     * Function that retrieves a list of all of
     * the keys currently being used in this CosmicApp.Map
     * to link to values.
     * 
     */
    
    public abstract ArrayList<K> getKeys();
    
    /**
     * 
     * Function that checks if this map is empty.
     * 
     */
    
    public abstract boolean isEmpty();
    
    /**
     * 
     * Function that checks how many key, value pairs
     * are stored in this map.
     * 
     */
    
    public abstract int size();
    
}
