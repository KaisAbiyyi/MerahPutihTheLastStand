package io.DutchSlayer.defend.game;

import io.DutchSlayer.config.GameConfig;

/**
 * Contains all game constants and configuration values for Tower Defense mode.
 * Note: Common constants are now in io.DutchSlayer.config.GameConfig
 */
public final class GameConstants {
    
    private GameConstants() {
        // Prevent instantiation
    }
    
    // ==================== UI LAYOUT ====================
    public static final float NAVBAR_HEIGHT = 80f;
    public static final float UI_HEIGHT = 600f;
    
    // ==================== GROUND ====================
    public static final float GROUND_Y = GameConfig.GROUND_Y;
    
    // ==================== COSTS ====================
    // Trap costs
    public static final int TRAP_ATTACK_COST = 15;
    public static final int TRAP_SLOW_COST = 20;
    public static final int TRAP_EXPLOSION_COST = 25;
    
    // Tower costs
    public static final int TOWER_AOE_COST = 50;
    public static final int TOWER_SPEED_COST = 30;
    public static final int TOWER_DEFENSIF_COST = 40;
    
    /** @deprecated Use TOWER_AOE_COST instead */
    @Deprecated
    public static final int TOWER1_COST = TOWER_AOE_COST;
    /** @deprecated Use TOWER_SPEED_COST instead */
    @Deprecated
    public static final int TOWER2_COST = TOWER_SPEED_COST;
    /** @deprecated Use TOWER_DEFENSIF_COST instead */
    @Deprecated
    public static final int TOWER3_COST = TOWER_DEFENSIF_COST;
    
    // ==================== INCOME ====================
    public static final float INCOME_INTERVAL = 2f;
    public static final int INCOME_AMOUNT = GameConfig.GOLD_INCOME_AMOUNT;
    
    // ==================== WAVES ====================
    public static final int MAX_WAVE = GameConfig.MAX_WAVE;
    public static final int FINAL_STAGE = GameConfig.FINAL_STAGE;
    public static final float WAVE_TRANSITION_DELAY = GameConfig.WAVE_TRANSITION_DELAY;
    
    // ==================== UPGRADES ====================
    public static final int BASE_ATTACK_UPGRADE_COST = 20;
    public static final int BASE_DEFENSE_UPGRADE_COST = 15;
    public static final int BASE_SPEED_UPGRADE_COST = 25;
    public static final float UPGRADE_COST_MULTIPLIER = 1.5f;
    
    // ==================== SPAWN CHANCES ====================
    public static final float BASIC_SPAWN_CHANCE = 0.4f;
    public static final float SHOOTER_SPAWN_CHANCE = 0.2f;
    public static final float BOMBER_SPAWN_CHANCE = 0.15f;
    public static final float SHIELD_SPAWN_CHANCE = 0.15f;
    public static final float BOSS_SPAWN_WAVE = 3; // Boss spawns on wave 3
    
    // ==================== NAV ITEMS ====================
    public static final String[] NAV_TOWERS = {"Tower1", "Tower2", "Tower3"};
    public static final String[] NAV_TRAPS = {"TrapAtk", "TrapSlow", "TrapBomb"};
    
    // ==================== COOLDOWNS ====================
    public static final float[] TOWER_MAX_COOLDOWNS = {3f, 2f, 4f};
    public static final float[] TRAP_MAX_COOLDOWNS = {2f, 3f, 5f};
    
    // ==================== TRAP INDICES ====================
    public static final int TRAP_ATTACK_INDEX = 0;
    public static final int TRAP_SLOW_INDEX = 1;
    public static final int TRAP_EXPLOSION_INDEX = 2;
    
    // ==================== WAVE BONUS ====================
    public static final int BASE_WAVE_BONUS = 50;
    public static final int WAVE_BONUS_INCREMENT = 10;
    public static final int ENEMIES_INCREMENT_PER_WAVE = 5;
}
