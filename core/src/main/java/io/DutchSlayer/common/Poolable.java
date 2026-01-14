package io.DutchSlayer.common;

/**
 * Interface for objects that can be pooled for reuse.
 * Helps reduce garbage collection by reusing objects instead of creating new ones.
 */
public interface Poolable {
    
    /**
     * Reset the object to its initial state for reuse.
     * Called when the object is obtained from a pool.
     */
    void reset();
    
    /**
     * Check if this object can be returned to the pool.
     * @return true if the object can be pooled
     */
    default boolean isPoolable() {
        return true;
    }
}
