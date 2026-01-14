package io.DutchSlayer.common;

import com.badlogic.gdx.math.Rectangle;

/**
 * Interface for objects that can participate in collision detection.
 */
public interface Collidable {
    /**
     * Get the bounding rectangle for collision detection.
     * @return The bounds of this object
     */
    Rectangle getBounds();
    
    /**
     * Check if this object collides with another collidable object.
     * @param other The other collidable object
     * @return true if the objects overlap
     */
    default boolean collidesWith(Collidable other) {
        if (other == null) return false;
        Rectangle otherBounds = other.getBounds();
        if (otherBounds == null) return false;
        return getBounds().overlaps(otherBounds);
    }
    
    /**
     * Get the center X position of this collidable.
     * @return Center X coordinate
     */
    default float getCenterX() {
        Rectangle bounds = getBounds();
        return bounds.x + bounds.width / 2f;
    }
    
    /**
     * Get the center Y position of this collidable.
     * @return Center Y coordinate
     */
    default float getCenterY() {
        Rectangle bounds = getBounds();
        return bounds.y + bounds.height / 2f;
    }
}
