package io.DutchSlayer.defend.game.systems;

import io.DutchSlayer.config.GameConfig;
import io.DutchSlayer.defend.game.GameConstants;

/**
 * System responsible for handling wave spawning logic.
 * Extracted from GameLogic to follow Single Responsibility Principle.
 */
public class WaveSystem {
    
    /**
     * Calculate wave bonus gold.
     * @param waveNumber Current wave number
     * @return Bonus gold for completing the wave
     */
    public static int calculateWaveBonus(int waveNumber) {
        return GameConstants.BASE_WAVE_BONUS + (waveNumber * GameConstants.WAVE_BONUS_INCREMENT);
    }
    
    /**
     * Check if the current wave is the boss wave.
     * @param waveNumber Current wave number
     * @return true if boss should spawn this wave
     */
    public static boolean isBossWave(int waveNumber) {
        return waveNumber == GameConstants.BOSS_SPAWN_WAVE;
    }
    
    /**
     * Check if all waves are completed.
     * @param currentWave Current wave number
     * @return true if all waves are completed
     */
    public static boolean isAllWavesCompleted(int currentWave) {
        return currentWave >= GameConstants.MAX_WAVE;
    }
    
    /**
     * Check if this is the final stage.
     * @param stage Current stage number
     * @return true if this is the final stage
     */
    public static boolean isFinalStage(int stage) {
        return stage == GameConstants.FINAL_STAGE;
    }
    
    /**
     * Calculate enemies for the next wave.
     * @param currentEnemies Current enemies per wave
     * @return New enemy count for next wave
     */
    public static int calculateNextWaveEnemies(int currentEnemies) {
        return currentEnemies + GameConstants.ENEMIES_INCREMENT_PER_WAVE;
    }
    
    /**
     * Check if wave transition should complete.
     * @param transitionTimer Current transition timer
     * @return true if transition delay has passed
     */
    public static boolean isWaveTransitionComplete(float transitionTimer) {
        return transitionTimer >= GameConstants.WAVE_TRANSITION_DELAY;
    }
}
