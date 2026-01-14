package io.DutchSlayer.defend.game.systems;

import com.badlogic.gdx.utils.Array;
import io.DutchSlayer.config.GameConfig;
import io.DutchSlayer.defend.entities.enemies.Enemy;
import io.DutchSlayer.defend.entities.enemies.EnemyType;

/**
 * System responsible for handling enemy-related game logic.
 * Extracted from GameLogic to follow Single Responsibility Principle.
 */
public class EnemySystem {
    
    /**
     * Get gold reward for defeating an enemy.
     * @param enemyType The type of enemy defeated
     * @return Gold reward amount
     */
    public static int getGoldReward(EnemyType enemyType) {
        return switch (enemyType) {
            case BASIC -> GameConfig.GOLD_REWARD_BASIC;
            case SHOOTER -> GameConfig.GOLD_REWARD_SHOOTER;
            case BOMBER -> GameConfig.GOLD_REWARD_BOMBER;
            case SHIELD -> GameConfig.GOLD_REWARD_SHIELD;
            case BOSS -> GameConfig.GOLD_REWARD_BOSS;
        };
    }
    
    /**
     * Check if any enemy is a boss type and is still alive.
     * @param enemies Array of enemies to check
     * @return true if a boss is present and alive
     */
    public static boolean hasBossAlive(Array<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.getType() == EnemyType.BOSS && !enemy.isDestroyed()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Count enemies of a specific type.
     * @param enemies Array of enemies
     * @param type Type to count
     * @return Number of enemies of that type
     */
    public static int countEnemiesOfType(Array<Enemy> enemies, EnemyType type) {
        int count = 0;
        for (Enemy enemy : enemies) {
            if (enemy.getType() == type && !enemy.isDestroyed()) {
                count++;
            }
        }
        return count;
    }
}
