package io.DutchSlayer.defend.game.systems;

import com.badlogic.gdx.utils.Array;
import io.DutchSlayer.config.GameConfig;
import io.DutchSlayer.defend.entities.enemies.Enemy;
import io.DutchSlayer.defend.entities.projectiles.AoeProjectile;
import io.DutchSlayer.defend.entities.projectiles.Projectile;

/**
 * System responsible for handling projectile-related game logic.
 * Extracted from GameLogic to follow Single Responsibility Principle.
 */
public class ProjectileSystem {
    
    private static final float SCREEN_WIDTH = GameConfig.SCREEN_WIDTH;
    
    /**
     * Update a single projectile and check for hits.
     * @param projectile The projectile to update
     * @param enemies Array of enemies to check collision against
     * @param delta Time since last frame
     * @return true if the projectile should be removed
     */
    public static boolean updateProjectile(Projectile projectile, Array<Enemy> enemies, float delta) {
        projectile.update(delta);
        
        // Handle AOE projectiles separately
        if (projectile instanceof AoeProjectile aoeProj) {
            return handleAoeProjectile(aoeProj, enemies);
        }
        
        // Regular projectile collision check
        return handleRegularProjectile(projectile, enemies);
    }
    
    private static boolean handleAoeProjectile(AoeProjectile aoeProj, Array<Enemy> enemies) {
        if (aoeProj.hasExploded()) {
            aoeProj.triggerAOEDamage(enemies);
            return true;
        }
        
        // Check if out of bounds
        float halfWidth = aoeProj.getBounds().width / 2;
        return aoeProj.getX() > SCREEN_WIDTH + halfWidth || aoeProj.getX() < -halfWidth;
    }
    
    private static boolean handleRegularProjectile(Projectile projectile, Array<Enemy> enemies) {
        // Check collision with enemies
        for (int j = enemies.size - 1; j >= 0; j--) {
            Enemy enemy = enemies.get(j);
            if (!enemy.isDestroyed() && projectile.getBounds().overlaps(enemy.getBounds())) {
                projectile.onHit(enemies);
                return true;
            }
        }
        
        // Check if out of bounds
        float halfWidth = projectile.getBounds().width / 2;
        return projectile.getX() > SCREEN_WIDTH + halfWidth;
    }
    
    /**
     * Process all projectiles in the array.
     * @param projectiles Array of projectiles to process
     * @param enemies Array of enemies for collision checking
     * @param delta Time since last frame
     */
    public static void updateAllProjectiles(Array<Projectile> projectiles, Array<Enemy> enemies, float delta) {
        for (int i = projectiles.size - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            if (updateProjectile(projectile, enemies, delta)) {
                projectiles.removeIndex(i);
            }
        }
    }
}
