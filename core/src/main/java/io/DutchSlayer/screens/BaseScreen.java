package io.DutchSlayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.DutchSlayer.Main;
import io.DutchSlayer.config.GameConfig;

/**
 * Abstract base class for all game screens.
 * Provides common functionality and reduces code duplication across screens.
 */
public abstract class BaseScreen implements Screen {
    
    protected final Main game;
    protected final Viewport viewport;
    protected final SpriteBatch batch;
    protected Stage stage;
    protected Texture background;
    
    // Default clear color (dark blue-gray)
    protected float clearColorR = 0.1f;
    protected float clearColorG = 0.1f;
    protected float clearColorB = 0.15f;
    
    /**
     * Create a new BaseScreen with default viewport.
     * @param game The main game instance
     */
    protected BaseScreen(Main game) {
        this(game, new FitViewport(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
    }
    
    /**
     * Create a new BaseScreen with custom viewport.
     * @param game The main game instance
     * @param viewport The viewport to use
     */
    protected BaseScreen(Main game, Viewport viewport) {
        this.game = game;
        this.batch = game.batch;
        this.viewport = viewport;
    }
    
    /**
     * Set the background clear color.
     * @param r Red component (0-1)
     * @param g Green component (0-1)
     * @param b Blue component (0-1)
     */
    protected void setClearColor(float r, float g, float b) {
        this.clearColorR = r;
        this.clearColorG = g;
        this.clearColorB = b;
    }
    
    /**
     * Clear the screen with the configured clear color.
     */
    protected void clearScreen() {
        Gdx.gl.glClearColor(clearColorR, clearColorG, clearColorB, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    
    /**
     * Render the background texture if it exists.
     */
    protected void renderBackground() {
        if (background != null) {
            batch.setProjectionMatrix(viewport.getCamera().combined);
            batch.begin();
            batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.end();
        }
    }
    
    /**
     * Update and render the stage if it exists.
     * @param delta Time since last frame
     */
    protected void renderStage(float delta) {
        if (stage != null) {
            stage.act(delta);
            stage.draw();
        }
    }
    
    /**
     * Common render pattern: clear screen, draw background, update stage.
     * Override this method for custom rendering behavior.
     * @param delta Time since last frame
     */
    @Override
    public void render(float delta) {
        clearScreen();
        renderBackground();
        renderStage(delta);
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        if (stage != null) {
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void show() {
        if (stage != null) {
            Gdx.input.setInputProcessor(stage);
        }
    }
    
    @Override
    public void hide() {
        // Default implementation - subclasses can override
    }
    
    @Override
    public void pause() {
        // Default implementation - subclasses can override
    }
    
    @Override
    public void resume() {
        // Default implementation - subclasses can override
    }
    
    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
        if (background != null) {
            background.dispose();
        }
    }
    
    /**
     * Get the main game instance.
     * @return The Main game instance
     */
    public Main getGame() {
        return game;
    }
    
    /**
     * Get the viewport.
     * @return The viewport
     */
    public Viewport getViewport() {
        return viewport;
    }
}
