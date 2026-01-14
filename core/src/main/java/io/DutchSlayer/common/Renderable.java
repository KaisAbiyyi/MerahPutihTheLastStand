package io.DutchSlayer.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Interface for objects that can be rendered.
 */
public interface Renderable {
    /**
     * Render the object using a SpriteBatch.
     * @param batch The SpriteBatch to render with
     */
    void render(SpriteBatch batch);
    
    /**
     * Render debug shapes or additional visual elements.
     * @param shapes The ShapeRenderer to render with
     */
    default void renderDebug(ShapeRenderer shapes) {
        // Default implementation does nothing
    }
}
