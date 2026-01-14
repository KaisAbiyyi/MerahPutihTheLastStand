package io.DutchSlayer.config;

/**
 * Centralized configuration constants for the game.
 * All magic numbers and configuration values should be defined here.
 */
public final class GameConfig {
    
    private GameConfig() {
        // Prevent instantiation
    }
    
    // ==================== SCREEN & DISPLAY ====================
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    
    // ==================== PLAYER SETTINGS ====================
    public static final float PLAYER_WIDTH = 56f;
    public static final float PLAYER_HEIGHT = 96f;
    public static final float PLAYER_SPEED = 300f;
    public static final float PLAYER_START_X = 100f;
    public static final int PLAYER_INITIAL_LIVES = 5;
    public static final int PLAYER_INITIAL_GRENADES = 5;
    public static final float PLAYER_INVINCIBILITY_DURATION = 2.0f;
    
    // ==================== MAP & TERRAIN ====================
    public static final float MAP_WIDTH = 5000f;
    public static final float WALL_WIDTH = 32f;
    public static final float TERRAIN_HEIGHT = 84f;
    
    // ==================== TREE SETTINGS ====================
    public static final float TREE_MIN_WIDTH = 50f;
    public static final float TREE_MAX_WIDTH = 100f;
    
    // ==================== WEAPON SETTINGS ====================
    public static final float BULLET_WIDTH = 20f;
    public static final float BULLET_HEIGHT = 10f;
    public static final float BULLET_SPEED = 1000f;
    public static final float GRENADE_RADIUS = 80f;
    public static final float GRENADE_TIMER = 1.5f;
    
    // ==================== TIMING ====================
    public static final float WALL_RISE_SPEED = 350f;
    public static final float VICTORY_DELAY = 2.0f;
    public static final float BUTTON_PRESS_DURATION = 0.15f;
    public static final float WAVE_TRANSITION_DELAY = 3.0f;
    
    // ==================== COMBAT ====================
    public static final float KNOCKBACK_DURATION = 0.5f;
    public static final float KNOCKBACK_SPEED = 200f;
    public static final float ATTACK_COOLDOWN = 1.0f;
    public static final float SHOOTER_INTERVAL = 1.5f;
    public static final float BOSS_SHOOT_INTERVAL = 8.0f;
    
    // ==================== ENEMY SCALING ====================
    public static final float ENEMY_BASIC_SCALE = 0.2f;
    public static final float ENEMY_SHOOTER_SCALE = 0.18f;
    public static final float ENEMY_BOMBER_SCALE = 0.2f;
    public static final float ENEMY_SHIELD_SCALE = 0.25f;
    public static final float ENEMY_BOSS_SCALE = 0.4f;
    
    // ==================== ENEMY STATS ====================
    public static final int ENEMY_BASIC_HEALTH = 3;
    public static final int ENEMY_SHOOTER_HEALTH = 2;
    public static final int ENEMY_BOMBER_HEALTH = 2;
    public static final int ENEMY_SHIELD_HEALTH = 8;
    public static final int ENEMY_BOSS_HEALTH = 100;
    
    public static final float ENEMY_BASIC_SPEED = 100f;
    public static final float ENEMY_SHOOTER_SPEED = 80f;
    public static final float ENEMY_BOMBER_SPEED = 120f;
    public static final float ENEMY_SHIELD_SPEED = 60f;
    public static final float ENEMY_BOSS_SPEED = 50f;
    
    // ==================== GOLD REWARDS ====================
    public static final int GOLD_REWARD_BASIC = 10;
    public static final int GOLD_REWARD_SHOOTER = 15;
    public static final int GOLD_REWARD_BOMBER = 12;
    public static final int GOLD_REWARD_SHIELD = 20;
    public static final int GOLD_REWARD_BOSS = 50;
    
    // ==================== TOWER SETTINGS ====================
    public static final int TOWER_MAX_UPGRADES = 10;
    public static final float TOWER_ANIMATION_DURATION = 0.2f;
    public static final float TOWER_IDLE_ANIMATION_SPEED = 0.6f;
    public static final float TOWER_TARGET_CHECK_INTERVAL = 0.1f;
    public static final float TOWER_MIN_FIRE_RATE = 0.05f;
    
    // Tower base fire rates
    public static final float TOWER_BASIC_FIRE_RATE = 5f;
    public static final float TOWER_AOE_FIRE_RATE = 4f;
    public static final float TOWER_FAST_FIRE_RATE = 0.5f;
    public static final float TOWER_SLOW_FIRE_RATE = 1.5f;
    
    // Tower base damage
    public static final int TOWER_BASIC_DAMAGE = 1;
    public static final int TOWER_AOE_DAMAGE = 2;
    public static final int TOWER_FAST_DAMAGE = 1;
    public static final int TOWER_SLOW_DAMAGE = 0;
    
    // ==================== TRAP SETTINGS ====================
    public static final float TRAP_EXPLOSION_RADIUS = 250f;
    public static final int TRAP_EXPLOSION_DAMAGE = 2;
    public static final float TRAP_SLOW_DURATION = 2f;
    public static final float TRAP_SLOW_STRENGTH = 0.5f;
    public static final float TRAP_HEAVY_SLOW_DURATION = 5f;
    public static final float TRAP_HEAVY_SLOW_STRENGTH = 0.1f;
    
    // ==================== ANIMATION ====================
    public static final float ANIMATION_SPEED_DEFAULT = 0.2f;
    public static final float ANIMATION_SPEED_BOMBER = 0.15f;
    public static final float ANIMATION_SPEED_SHIELD = 0.25f;
    
    // ==================== AUDIO VOLUMES ====================
    public static final float MUSIC_VOLUME_DEFAULT = 0.5f;
    public static final float SFX_VOLUME_DEFAULT = 0.8f;
    public static final float BACKGROUND_MUSIC_VOLUME = 0.2f;
    public static final float BOSS_MUSIC_VOLUME = 0.5f;
    
    // ==================== STAGE SETTINGS ====================
    public static final int MAX_WAVE = 10;
    public static final int FINAL_STAGE = 4;
    
    // Initial gold per stage
    public static final int STAGE_1_INITIAL_GOLD = 100;
    public static final int STAGE_2_INITIAL_GOLD = 80;
    public static final int STAGE_3_INITIAL_GOLD = 60;
    public static final int STAGE_4_INITIAL_GOLD = 50;
    public static final int STAGE_DEFAULT_INITIAL_GOLD = 80;
    
    // Initial enemies per stage
    public static final int STAGE_1_INITIAL_ENEMIES = 4;
    public static final int STAGE_2_INITIAL_ENEMIES = 5;
    public static final int STAGE_3_INITIAL_ENEMIES = 6;
    public static final int STAGE_4_INITIAL_ENEMIES = 8;
    public static final int STAGE_DEFAULT_INITIAL_ENEMIES = 5;
    
    // ==================== GAME WORLD ====================
    public static final float GROUND_Y = 150f;
    public static final float SPAWN_TIMER_INTERVAL = 2f;
    public static final float GOLD_INCOME_INTERVAL = 5f;
    public static final int GOLD_INCOME_AMOUNT = 5;
    
    // ==================== BURST FIRE ====================
    public static final float ENEMY_BURST_DELAY = 0.08f;
    public static final int BURST_SHOTS_COUNT = 3;
    
    // ==================== BOSS SETTINGS ====================
    public static final float BOSS_TARGET_X = 1100f;
    public static final float BOSS_INTRO_DURATION = 2f;
}
