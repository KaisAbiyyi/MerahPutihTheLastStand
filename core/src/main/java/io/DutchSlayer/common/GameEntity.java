package io.DutchSlayer.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Base interface for all game entities that can be updated, rendered, and participate in collisions.
 * Combines Updatable, Renderable, and Collidable interfaces.
 */
public interface GameEntity extends Updatable, Renderable, Collidable {
    
    /**
     * Get the X position of the entity.
     * @return X coordinate
     */
    float getX();
    
    /**
     * Get the Y position of the entity.
     * @return Y coordinate
     */
    float getY();
    
    /**
     * Get the width of the entity.
     * @return Width
     */
    float getWidth();
    
    /**
     * Get the height of the entity.
     * @return Height
     */
    float getHeight();
    
    /**
     * Check if the entity is still alive/active.
     * @return true if alive
     */
    boolean isAlive();
    
    /**
     * Mark the entity for destruction/removal.
     */
    void destroy();
    
    /**
     * Dispose of any resources held by this entity.
     */
    default void dispose() {
        // Default implementation does nothing
    }
}
