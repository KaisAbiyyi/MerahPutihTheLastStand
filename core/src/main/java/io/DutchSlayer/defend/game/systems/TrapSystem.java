package io.DutchSlayer.defend.game.systems;

import com.badlogic.gdx.utils.Array;
import io.DutchSlayer.config.GameConfig;
import io.DutchSlayer.defend.entities.enemies.Enemy;
import io.DutchSlayer.defend.entities.traps.Trap;
import io.DutchSlayer.defend.entities.traps.TrapType;
import io.DutchSlayer.defend.utils.AudioManager;

/**
 * System responsible for handling trap-related game logic.
 * Extracted from GameLogic to follow Single Responsibility Principle.
 */
public class TrapSystem {
    
    /**
     * Update all traps.
     * @param traps Array of traps to update
     * @param delta Time since last frame
     */
    public static void updateTraps(Array<Trap> traps, float delta) {
        for (Trap trap : traps) {
            trap.update(delta);
        }
    }
    
    /**
     * Check trap collisions with enemies.
     * @param traps Array of traps
     * @param enemies Array of enemies
     */
    public static void checkTrapCollisions(Array<Trap> traps, Array<Enemy> enemies) {
        for (int i = enemies.size - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);
            
            for (Trap trap : traps) {
                if (!trap.occupied || trap.isUsed()) continue;
                
                if (enemy.getBounds().overlaps(trap.bounds)) {
                    handleTrapEffect(trap, enemy, enemies);
                    trap.occupied = false;
                    break;
                }
            }
        }
    }
    
    private static void handleTrapEffect(Trap trap, Enemy targetEnemy, Array<Enemy> allEnemies) {
        switch (trap.getType()) {
            case ATTACK -> {
                AudioManager.playTrapAttackHit();
                targetEnemy.takeDamage(1);
                targetEnemy.slow(2f);
            }
            case SLOW -> {
                AudioManager.playTrapSlowHit();
                targetEnemy.slowHeavy(
                    GameConfig.TRAP_HEAVY_SLOW_DURATION, 
                    GameConfig.TRAP_HEAVY_SLOW_STRENGTH
                );
            }
            case EXPLOSION -> {
                AudioManager.playTrapExplosionHit();
                handleExplosionTrap(trap, allEnemies);
            }
        }
    }
    
    private static void handleExplosionTrap(Trap trap, Array<Enemy> enemies) {
        float trapX = trap.getCenterX();
        float trapY = trap.getCenterY();
        float explosionRadius = GameConfig.TRAP_EXPLOSION_RADIUS;
        int explosionDamage = GameConfig.TRAP_EXPLOSION_DAMAGE;
        
        for (int i = enemies.size - 1; i >= 0; i--) {
            Enemy target = enemies.get(i);
            if (target.isDestroyed()) continue;
            
            float targetX = target.getX();
            float targetY = target.getBounds().y + target.getBounds().height / 2;
            
            float distance = calculateDistance(trapX, trapY, targetX, targetY);
            
            if (distance <= explosionRadius) {
                target.takeDamage(explosionDamage);
            }
        }
    }
    
    private static float calculateDistance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
