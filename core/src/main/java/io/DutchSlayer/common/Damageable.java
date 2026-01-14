package io.DutchSlayer.common;

/**
 * Interface for objects that can take damage and have health.
 */
public interface Damageable {
    
    /**
     * Get the current health.
     * @return Current health value
     */
    int getHealth();
    
    /**
     * Get the maximum health.
     * @return Maximum health value
     */
    int getMaxHealth();
    
    /**
     * Take damage.
     * @param amount Amount of damage to take
     */
    void takeDamage(int amount);
    
    /**
     * Heal the entity.
     * @param amount Amount to heal
     */
    default void heal(int amount) {
        // Default implementation does nothing
    }
    
    /**
     * Check if the entity is dead (health <= 0).
     * @return true if dead
     */
    default boolean isDead() {
        return getHealth() <= 0;
    }
    
    /**
     * Get health as a percentage (0.0 to 1.0).
     * @return Health percentage
     */
    default float getHealthPercent() {
        if (getMaxHealth() <= 0) return 0f;
        return (float) getHealth() / getMaxHealth();
    }
}
