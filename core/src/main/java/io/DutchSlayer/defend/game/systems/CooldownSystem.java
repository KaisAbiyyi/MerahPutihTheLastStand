package io.DutchSlayer.defend.game.systems;

/**
 * System responsible for handling cooldown timers.
 * Extracted to promote code reuse and clarity.
 */
public class CooldownSystem {
    
    /**
     * Update cooldown timers.
     * @param cooldowns Array of cooldown values
     * @param active Array of active flags
     * @param delta Time since last frame
     */
    public static void updateCooldowns(float[] cooldowns, boolean[] active, float delta) {
        for (int i = 0; i < cooldowns.length; i++) {
            if (active[i]) {
                cooldowns[i] -= delta;
                if (cooldowns[i] <= 0f) {
                    active[i] = false;
                    cooldowns[i] = 0f;
                }
            }
        }
    }
    
    /**
     * Start a cooldown.
     * @param cooldowns Array of cooldown values
     * @param active Array of active flags
     * @param index Index of the cooldown to start
     * @param duration Duration of the cooldown
     */
    public static void startCooldown(float[] cooldowns, boolean[] active, int index, float duration) {
        if (index >= 0 && index < cooldowns.length) {
            cooldowns[index] = duration;
            active[index] = true;
        }
    }
    
    /**
     * Check if a cooldown is ready (not active).
     * @param active Array of active flags
     * @param index Index to check
     * @return true if cooldown is ready
     */
    public static boolean isReady(boolean[] active, int index) {
        if (index >= 0 && index < active.length) {
            return !active[index];
        }
        return false;
    }
    
    /**
     * Get remaining cooldown time.
     * @param cooldowns Array of cooldown values
     * @param index Index to check
     * @return Remaining time, or 0 if invalid index
     */
    public static float getRemainingTime(float[] cooldowns, int index) {
        if (index >= 0 && index < cooldowns.length) {
            return Math.max(0f, cooldowns[index]);
        }
        return 0f;
    }
    
    /**
     * Get cooldown progress (0.0 = just started, 1.0 = ready).
     * @param cooldowns Array of cooldown values
     * @param maxCooldowns Array of max cooldown values
     * @param index Index to check
     * @return Progress from 0.0 to 1.0
     */
    public static float getProgress(float[] cooldowns, float[] maxCooldowns, int index) {
        if (index >= 0 && index < cooldowns.length && index < maxCooldowns.length) {
            float max = maxCooldowns[index];
            if (max <= 0f) return 1f;
            float remaining = Math.max(0f, cooldowns[index]);
            return 1f - (remaining / max);
        }
        return 1f;
    }
}
