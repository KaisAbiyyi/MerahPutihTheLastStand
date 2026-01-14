package io.DutchSlayer.defend.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import io.DutchSlayer.config.AssetPaths;
import io.DutchSlayer.defend.entities.towers.TowerType;
import io.DutchSlayer.defend.entities.enemies.EnemyType;

/**
 * Improved resource loader with singleton pattern and proper encapsulation.
 * Uses ObjectMap for efficient texture lookup and automatic disposal.
 * 
 * Usage:
 *   TextureManager.getInstance().load();
 *   Texture tex = TextureManager.getInstance().getTexture(TextureId.ENEMY_BASIC);
 *   TextureManager.getInstance().dispose();
 */
public class TextureManager implements Disposable {
    
    private static TextureManager instance;
    
    // Texture storage using ObjectMap for efficient lookup
    private final ObjectMap<String, Texture> textures = new ObjectMap<>();
    private final ObjectMap<String, Texture[]> animationFrames = new ObjectMap<>();
    
    private boolean loaded = false;
    
    // Texture IDs as constants for type safety
    public static final class TextureId {
        // Environment
        public static final String GRASS = "grass";
        public static final String TERRAIN = "terrain";
        public static final String SKY = "sky";
        
        // Enemies
        public static final String ENEMY_BASIC = "enemy_basic";
        public static final String ENEMY_SHOOTER = "enemy_shooter";
        public static final String ENEMY_BOMBER = "enemy_bomber";
        public static final String ENEMY_SHIELD = "enemy_shield";
        public static final String ENEMY_BOSS = "enemy_boss";
        
        // Towers
        public static final String TOWER_MAIN = "tower_main";
        public static final String TOWER_AOE = "tower_aoe";
        public static final String TOWER_SPEED = "tower_speed";
        public static final String TOWER_DEFENSIF = "tower_defensif";
        
        // Projectiles
        public static final String PROJ_BASIC = "proj_basic";
        public static final String PROJ_AOE = "proj_aoe";
        public static final String PROJ_FAST = "proj_fast";
        public static final String PROJ_SLOW = "proj_slow";
        public static final String PROJ_ENEMY = "proj_enemy";
        public static final String PROJ_BOSS = "proj_boss";
        
        // Traps
        public static final String TRAP_ATTACK = "trap_attack";
        public static final String TRAP_SLOW = "trap_slow";
        public static final String TRAP_BOMB = "trap_bomb";
        
        // UI Elements
        public static final String UI_TOWER_AOE = "ui_tower_aoe";
        public static final String UI_TOWER_SPEED = "ui_tower_speed";
        public static final String UI_TOWER_DEFENSIF = "ui_tower_defensif";
        public static final String UI_TRAP_ATTACK = "ui_trap_attack";
        public static final String UI_TRAP_SLOW = "ui_trap_slow";
        public static final String UI_TRAP_BOMB = "ui_trap_bomb";
        public static final String UI_GOLD_ICON = "ui_gold_icon";
        
        // Win/Lose UI
        public static final String UI_WIN = "ui_win";
        public static final String UI_LOSE = "ui_lose";
        public static final String BTN_NEXT = "btn_next";
        public static final String BTN_MENU = "btn_menu";
        public static final String BTN_RETRY = "btn_retry";
        public static final String BTN_MODE = "btn_mode";
        
        // Pause UI
        public static final String UI_PAUSE = "ui_pause";
        public static final String BTN_MENU_PAUSE = "btn_menu_pause";
        public static final String BTN_RESUME = "btn_resume";
        public static final String BTN_SETTING = "btn_setting";
        public static final String BTN_PAUSE = "btn_pause";
        public static final String BTN_REMOVE = "btn_remove";
        
        // Effects
        public static final String EXPLOSION = "explosion";
        public static final String BOMB_ASSET = "bomb_asset";
        
        // Animation frame keys
        public static final String ANIM_ENEMY_BASIC = "anim_enemy_basic";
        public static final String ANIM_ENEMY_SHIELD = "anim_enemy_shield";
        public static final String ANIM_ENEMY_SHOOTER = "anim_enemy_shooter";
        public static final String ANIM_ENEMY_BOMBER = "anim_enemy_bomber";
        public static final String ANIM_TOWER_AOE = "anim_tower_aoe";
        public static final String ANIM_TOWER_SPEED = "anim_tower_speed";
        public static final String ANIM_TOWER_DEFENSIF = "anim_tower_defensif";
        
        private TextureId() {} // Prevent instantiation
    }
    
    private TextureManager() {}
    
    public static TextureManager getInstance() {
        if (instance == null) {
            instance = new TextureManager();
        }
        return instance;
    }
    
    /**
     * Load all textures. Should be called once during game initialization.
     */
    public void load() {
        if (loaded) {
            Gdx.app.log("TextureManager", "Textures already loaded");
            return;
        }
        
        loadEnvironmentTextures();
        loadEnemyTextures();
        loadTowerTextures();
        loadProjectileTextures();
        loadTrapTextures();
        loadUITextures();
        loadEffectTextures();
        loadAnimationFrames();
        
        loaded = true;
        Gdx.app.log("TextureManager", "All textures loaded successfully");
    }
    
    private void loadEnvironmentTextures() {
        register(TextureId.TERRAIN, AssetPaths.Backgrounds.TERRAIN_3);
        register(TextureId.SKY, AssetPaths.Backgrounds.BG);
    }
    
    private void loadEnemyTextures() {
        register(TextureId.ENEMY_BASIC, AssetPaths.EnemyDefend.DUTCH_BASIC);
        register(TextureId.ENEMY_SHOOTER, AssetPaths.EnemyDefend.DUTCH_SHOOTER);
        register(TextureId.ENEMY_BOMBER, AssetPaths.EnemyDefend.DUTCH_BOMBER);
        register(TextureId.ENEMY_SHIELD, AssetPaths.EnemyDefend.DUTCH_SHIELD);
        register(TextureId.ENEMY_BOSS, AssetPaths.EnemyDefend.DUTCH_BOSS);
    }
    
    private void loadTowerTextures() {
        register(TextureId.TOWER_MAIN, AssetPaths.Tower.MAIN);
        register(TextureId.TOWER_AOE, AssetPaths.Tower.AOE_ICON);
        register(TextureId.TOWER_SPEED, AssetPaths.Tower.SPEED_ICON);
        register(TextureId.TOWER_DEFENSIF, AssetPaths.Tower.DEFENSIVE_ICON);
    }
    
    private void loadProjectileTextures() {
        register(TextureId.PROJ_BASIC, AssetPaths.Projectile.BASIC_1);
        register(TextureId.PROJ_AOE, AssetPaths.Projectile.AOE);
        register(TextureId.PROJ_FAST, AssetPaths.Projectile.SPEED);
        register(TextureId.PROJ_SLOW, AssetPaths.Projectile.DEFENSIVE);
        register(TextureId.PROJ_ENEMY, AssetPaths.Projectile.BASIC_2);
        register(TextureId.PROJ_BOSS, AssetPaths.Projectile.BOSS);
    }
    
    private void loadTrapTextures() {
        register(TextureId.TRAP_ATTACK, AssetPaths.Trap.ATTACK);
        register(TextureId.TRAP_SLOW, AssetPaths.Trap.SLOW);
        register(TextureId.TRAP_BOMB, AssetPaths.Trap.BOMB);
    }
    
    private void loadUITextures() {
        // Tower/Trap selection UI
        register(TextureId.UI_TOWER_AOE, AssetPaths.Ui.ICON_TOWER_AOE);
        register(TextureId.UI_TOWER_SPEED, AssetPaths.Ui.ICON_TOWER_SPEED);
        register(TextureId.UI_TOWER_DEFENSIF, AssetPaths.Ui.ICON_TOWER_DEFENSIVE);
        register(TextureId.UI_TRAP_ATTACK, AssetPaths.Ui.ICON_TRAP_ATTACK);
        register(TextureId.UI_TRAP_SLOW, AssetPaths.Ui.ICON_TRAP_SLOW);
        register(TextureId.UI_TRAP_BOMB, AssetPaths.Ui.ICON_TRAP_BOMB);
        register(TextureId.UI_GOLD_ICON, AssetPaths.Ui.ICON_GOLD);
        
        // Win/Lose UI
        register(TextureId.UI_WIN, AssetPaths.Ui.WIN);
        register(TextureId.UI_LOSE, AssetPaths.Ui.LOSE);
        register(TextureId.BTN_NEXT, AssetPaths.Ui.BTN_NEXT);
        register(TextureId.BTN_MENU, AssetPaths.Ui.BTN_MENU);
        register(TextureId.BTN_RETRY, AssetPaths.Ui.BTN_RETRY);
        register(TextureId.BTN_MODE, AssetPaths.Ui.MODE_SELECTION);
        
        // Pause UI
        register(TextureId.UI_PAUSE, AssetPaths.Ui.PAUSE);
        register(TextureId.BTN_MENU_PAUSE, AssetPaths.Ui.BTN_PAUSE_MENU);
        register(TextureId.BTN_RESUME, AssetPaths.Ui.BTN_RESUME);
        register(TextureId.BTN_SETTING, AssetPaths.Ui.BTN_SETTINGS);
        
        // Buttons
        register(TextureId.BTN_PAUSE, AssetPaths.Ui.BTN_PAUSE);
        register(TextureId.BTN_REMOVE, AssetPaths.Ui.BTN_REMOVE);
    }
    
    private void loadEffectTextures() {
        register(TextureId.BOMB_ASSET, AssetPaths.Projectile.BOMB);
    }
    
    private void loadAnimationFrames() {
        // Enemy animations - using AssetPaths.getFramePath for consistency
        registerFrames(TextureId.ANIM_ENEMY_BASIC, new String[]{
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.BASIC_FRAME, 1),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.BASIC_FRAME, 2),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.BASIC_FRAME, 3),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.BASIC_FRAME, 4)
        });
        
        registerFrames(TextureId.ANIM_ENEMY_SHIELD, new String[]{
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHIELD_FRAME, 1),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHIELD_FRAME, 2),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHIELD_FRAME, 3),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHIELD_FRAME, 4)
        });
        
        registerFrames(TextureId.ANIM_ENEMY_SHOOTER, new String[]{
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHOOTER_FRAME, 1),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHOOTER_FRAME, 2),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHOOTER_FRAME, 3),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHOOTER_FRAME, 4)
        });
        
        registerFrames(TextureId.ANIM_ENEMY_BOMBER, new String[]{
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.BOMBER_FRAME, 1),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.BOMBER_FRAME, 2),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.BOMBER_FRAME, 3),
            AssetPaths.getFramePath(AssetPaths.EnemyDefend.BOMBER_FRAME, 4)
        });
        
        // Tower animations
        registerFrames(TextureId.ANIM_TOWER_AOE, new String[]{
            AssetPaths.getFramePath(AssetPaths.Tower.AOE_FRAME, 1),
            AssetPaths.getFramePath(AssetPaths.Tower.AOE_FRAME, 2)
        });
        
        registerFrames(TextureId.ANIM_TOWER_SPEED, new String[]{
            AssetPaths.getFramePath(AssetPaths.Tower.SPEED_FRAME, 1),
            AssetPaths.getFramePath(AssetPaths.Tower.SPEED_FRAME, 2)
        });
        
        registerFrames(TextureId.ANIM_TOWER_DEFENSIF, new String[]{
            AssetPaths.getFramePath(AssetPaths.Tower.DEFENSIVE_FRAME, 1),
            AssetPaths.getFramePath(AssetPaths.Tower.DEFENSIVE_FRAME, 2),
            AssetPaths.getFramePath(AssetPaths.Tower.DEFENSIVE_FRAME, 3)
        });
    }
    
    private void register(String id, String path) {
        Texture tex = loadOrNull(path);
        if (tex != null) {
            textures.put(id, tex);
        }
    }
    
    private void registerFrames(String id, String[] paths) {
        Texture[] frames = new Texture[paths.length];
        for (int i = 0; i < paths.length; i++) {
            frames[i] = loadOrNull(paths[i]);
        }
        animationFrames.put(id, frames);
    }
    
    private Texture loadOrNull(String path) {
        try {
            return new Texture(Gdx.files.internal(path));
        } catch (Exception e) {
            Gdx.app.error("TextureManager", "Failed to load " + path, e);
            return null;
        }
    }
    
    /**
     * Get a texture by its ID.
     * @param id The texture ID from TextureId constants
     * @return The texture, or null if not found
     */
    public Texture getTexture(String id) {
        return textures.get(id);
    }
    
    /**
     * Get animation frames by ID.
     * @param id The animation ID from TextureId constants
     * @return Array of textures, or null if not found
     */
    public Texture[] getAnimationFrames(String id) {
        return animationFrames.get(id);
    }
    
    /**
     * Get tower animation frames by tower type.
     * @param type The tower type
     * @return Animation frames for that tower type
     */
    public Texture[] getTowerAnimationFrames(TowerType type) {
        return switch (type) {
            case AOE -> getAnimationFrames(TextureId.ANIM_TOWER_AOE);
            case FAST -> getAnimationFrames(TextureId.ANIM_TOWER_SPEED);
            case SLOW -> getAnimationFrames(TextureId.ANIM_TOWER_DEFENSIF);
            default -> null;
        };
    }
    
    /**
     * Get enemy animation frames by enemy type.
     * @param type The enemy type
     * @return Animation frames for that enemy type
     */
    public Texture[] getEnemyAnimationFrames(EnemyType type) {
        return switch (type) {
            case BASIC -> getAnimationFrames(TextureId.ANIM_ENEMY_BASIC);
            case SHOOTER -> getAnimationFrames(TextureId.ANIM_ENEMY_SHOOTER);
            case BOMBER -> getAnimationFrames(TextureId.ANIM_ENEMY_BOMBER);
            case SHIELD -> getAnimationFrames(TextureId.ANIM_ENEMY_SHIELD);
            default -> null;
        };
    }
    
    /**
     * Check if textures are loaded.
     * @return true if load() has been called
     */
    public boolean isLoaded() {
        return loaded;
    }
    
    @Override
    public void dispose() {
        for (Texture tex : textures.values()) {
            if (tex != null) {
                tex.dispose();
            }
        }
        textures.clear();
        
        for (Texture[] frames : animationFrames.values()) {
            if (frames != null) {
                for (Texture frame : frames) {
                    if (frame != null) {
                        frame.dispose();
                    }
                }
            }
        }
        animationFrames.clear();
        
        loaded = false;
        Gdx.app.log("TextureManager", "All textures disposed");
    }
    
    /**
     * Reset the singleton instance. Use for testing or full reload.
     */
    public static void reset() {
        if (instance != null) {
            instance.dispose();
            instance = null;
        }
    }
}
