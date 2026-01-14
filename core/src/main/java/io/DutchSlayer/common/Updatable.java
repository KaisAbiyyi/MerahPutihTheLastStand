package io.DutchSlayer.common;

/**
 * Interface for objects that need to be updated every frame.
 */
public interface Updatable {
    /**
     * Update the object's state.
     * @param delta Time elapsed since last frame in seconds
     */
    void update(float delta);
}
