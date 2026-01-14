package io.DutchSlayer.defend.game;

import com.badlogic.gdx.utils.Array;
import io.DutchSlayer.config.GameConfig;
import io.DutchSlayer.defend.entities.enemies.Enemy;
import io.DutchSlayer.defend.entities.projectiles.BombAsset;
import io.DutchSlayer.defend.entities.projectiles.EnemyProjectile;
import io.DutchSlayer.defend.entities.projectiles.Projectile;
import io.DutchSlayer.defend.entities.towers.Tower;
import io.DutchSlayer.defend.entities.traps.Trap;
import io.DutchSlayer.defend.screens.TowerDefenseScreen;

/**
 * Encapsulated game state for Tower Defense mode.
 * All fields are private with proper getters and controlled setters.
 * 
 * Note: This is a refactored version of the original GameState.
 * Once migration is complete, rename this class to GameState.
 */
public class GameStateNew {
    
    // ==================== ENTITY COLLECTIONS ====================
    private final Array<EnemyProjectile> enemyProjectiles = new Array<>();
    private final Array<BombAsset> bombs = new Array<>();
    private final Array<Tower> towers = new Array<>();
    private final Array<Enemy> enemies = new Array<>();
    private final Array<Projectile> projectiles = new Array<>();
    private final Array<Trap> trapZones = new Array<>();
    private final Array<float[]> trapVerts = new Array<>();
    private final Array<TowerDefenseScreen.Zone> zones = new Array<>();
    private final Array<TowerDefenseScreen.Zone> deployedTowerZones = new Array<>();
    
    // ==================== GAME PROGRESS ====================
    private int gold;
    private int currentWave = 1;
    private int spawnCount = 0;
    private int enemiesThisWave;
    private final int currentStage;
    
    // ==================== GAME STATE FLAGS ====================
    private boolean bossSpawned = false;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private boolean paused = false;
    private boolean waveTransition = false;
    private boolean waveCompleteBonusGiven = false;
    
    // ==================== TIMERS ====================
    private float spawnTimer = 0f;
    private float goldTimer = 0f;
    private float waveTransitionTimer = 0f;
    
    // ==================== UI STATE ====================
    private TowerDefenseScreen.NavItem selectedType;
    private Tower selectedTowerUI;
    private boolean removeButtonHovered = false;
    private boolean pauseButtonHovered = false;
    private float mouseX = 0f;
    private float mouseY = 0f;
    
    // ==================== BOSS STATE ====================
    private boolean bossIntroduction = false;
    private float bossIntroTimer = 0f;
    private Enemy currentBoss = null;
    private boolean bossMusicActive = false;
    
    // ==================== COOLDOWNS ====================
    private final float[] towerCooldowns = new float[3];
    private final float[] trapCooldowns = new float[3];
    private final boolean[] towerCooldownActive = new boolean[3];
    private final boolean[] trapCooldownActive = new boolean[3];
    
    // ==================== BUTTON STATES ====================
    private boolean resumeButtonPressed = false;
    private boolean settingButtonPressed = false;
    private boolean menuButtonPressed = false;
    private boolean nextButtonPressed = false;
    private boolean retryButtonPressed = false;
    private boolean continueButtonPressed = false;
    private boolean restartButtonPressed = false;
    private boolean quitButtonPressed = false;
    private float buttonPressTimer = 0f;
    
    // ==================== CONSTRUCTOR ====================
    
    public GameStateNew(int stage) {
        this.currentStage = stage;
        initializeStageSettings(stage);
    }
    
    private void initializeStageSettings(int stage) {
        switch (stage) {
            case 1:
                gold = GameConfig.STAGE_1_INITIAL_GOLD;
                enemiesThisWave = GameConfig.STAGE_1_INITIAL_ENEMIES;
                break;
            case 2:
                gold = GameConfig.STAGE_2_INITIAL_GOLD;
                enemiesThisWave = GameConfig.STAGE_2_INITIAL_ENEMIES;
                break;
            case 3:
                gold = GameConfig.STAGE_3_INITIAL_GOLD;
                enemiesThisWave = GameConfig.STAGE_3_INITIAL_ENEMIES;
                break;
            case 4:
                gold = GameConfig.STAGE_4_INITIAL_GOLD;
                enemiesThisWave = GameConfig.STAGE_4_INITIAL_ENEMIES;
                break;
            default:
                gold = GameConfig.STAGE_DEFAULT_INITIAL_GOLD;
                enemiesThisWave = GameConfig.STAGE_DEFAULT_INITIAL_ENEMIES;
                break;
        }
    }
    
    // ==================== ENTITY COLLECTION GETTERS ====================
    
    public Array<EnemyProjectile> getEnemyProjectiles() { return enemyProjectiles; }
    public Array<BombAsset> getBombs() { return bombs; }
    public Array<Tower> getTowers() { return towers; }
    public Array<Enemy> getEnemies() { return enemies; }
    public Array<Projectile> getProjectiles() { return projectiles; }
    public Array<Trap> getTrapZones() { return trapZones; }
    public Array<float[]> getTrapVerts() { return trapVerts; }
    public Array<TowerDefenseScreen.Zone> getZones() { return zones; }
    public Array<TowerDefenseScreen.Zone> getDeployedTowerZones() { return deployedTowerZones; }
    
    // ==================== GOLD MANAGEMENT ====================
    
    public int getGold() { return gold; }
    
    public void addGold(int amount) {
        gold = Math.max(0, gold + amount);
    }
    
    public boolean spendGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }
        return false;
    }
    
    public boolean canAfford(int amount) {
        return gold >= amount;
    }
    
    // ==================== WAVE MANAGEMENT ====================
    
    public int getCurrentWave() { return currentWave; }
    public int getSpawnCount() { return spawnCount; }
    public int getEnemiesThisWave() { return enemiesThisWave; }
    public int getCurrentStage() { return currentStage; }
    
    public void incrementSpawnCount() { spawnCount++; }
    public void setEnemiesThisWave(int count) { enemiesThisWave = count; }
    
    public void nextWave() {
        currentWave++;
        spawnCount = 0;
        waveCompleteBonusGiven = false;
        waveTransition = false;
        bossSpawned = false;
    }
    
    public void incrementEnemiesThisWave(int amount) {
        enemiesThisWave += amount;
    }
    
    // ==================== GAME STATE FLAGS ====================
    
    public boolean isBossSpawned() { return bossSpawned; }
    public void setBossSpawned(boolean spawned) { bossSpawned = spawned; }
    
    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean over) { gameOver = over; }
    
    public boolean isGameWon() { return gameWon; }
    public void setGameWon(boolean won) { gameWon = won; }
    
    public boolean isPaused() { return paused; }
    public void setPaused(boolean paused) { this.paused = paused; }
    public void togglePause() { this.paused = !this.paused; }
    
    public boolean isWaveTransition() { return waveTransition; }
    public void setWaveTransition(boolean transition) { waveTransition = transition; }
    
    public boolean isWaveCompleteBonusGiven() { return waveCompleteBonusGiven; }
    public void setWaveCompleteBonusGiven(boolean given) { waveCompleteBonusGiven = given; }
    
    // ==================== TIMERS ====================
    
    public float getSpawnTimer() { return spawnTimer; }
    public void setSpawnTimer(float timer) { spawnTimer = timer; }
    public void addToSpawnTimer(float delta) { spawnTimer += delta; }
    public void resetSpawnTimer() { spawnTimer = 0f; }
    
    public float getGoldTimer() { return goldTimer; }
    public void setGoldTimer(float timer) { goldTimer = timer; }
    public void addToGoldTimer(float delta) { goldTimer += delta; }
    public void resetGoldTimer() { goldTimer = 0f; }
    
    public float getWaveTransitionTimer() { return waveTransitionTimer; }
    public void setWaveTransitionTimer(float timer) { waveTransitionTimer = timer; }
    public void addToWaveTransitionTimer(float delta) { waveTransitionTimer += delta; }
    
    // ==================== UI STATE ====================
    
    public TowerDefenseScreen.NavItem getSelectedType() { return selectedType; }
    public void setSelectedType(TowerDefenseScreen.NavItem type) { selectedType = type; }
    
    public Tower getSelectedTowerUI() { return selectedTowerUI; }
    public void setSelectedTowerUI(Tower tower) { selectedTowerUI = tower; }
    
    public boolean isRemoveButtonHovered() { return removeButtonHovered; }
    public void setRemoveButtonHovered(boolean hovered) { removeButtonHovered = hovered; }
    
    public boolean isPauseButtonHovered() { return pauseButtonHovered; }
    public void setPauseButtonHovered(boolean hovered) { pauseButtonHovered = hovered; }
    
    public float getMouseX() { return mouseX; }
    public float getMouseY() { return mouseY; }
    public void setMousePosition(float x, float y) { mouseX = x; mouseY = y; }
    
    // ==================== BOSS STATE ====================
    
    public boolean isBossIntroduction() { return bossIntroduction; }
    public void setBossIntroduction(boolean intro) { bossIntroduction = intro; }
    
    public float getBossIntroTimer() { return bossIntroTimer; }
    public void setBossIntroTimer(float timer) { bossIntroTimer = timer; }
    public void addToBossIntroTimer(float delta) { bossIntroTimer += delta; }
    
    public Enemy getCurrentBoss() { return currentBoss; }
    public void setCurrentBoss(Enemy boss) { currentBoss = boss; }
    
    public boolean isBossMusicActive() { return bossMusicActive; }
    public void setBossMusicActive(boolean active) { bossMusicActive = active; }
    
    public void clearBossReference() {
        currentBoss = null;
        bossIntroduction = false;
        bossIntroTimer = 0f;
        bossMusicActive = false;
    }
    
    // ==================== COOLDOWNS ====================
    
    public float getTowerCooldown(int index) {
        if (index >= 0 && index < towerCooldowns.length) {
            return towerCooldowns[index];
        }
        return 0f;
    }
    
    public void setTowerCooldown(int index, float cooldown) {
        if (index >= 0 && index < towerCooldowns.length) {
            towerCooldowns[index] = cooldown;
        }
    }
    
    public boolean isTowerCooldownActive(int index) {
        if (index >= 0 && index < towerCooldownActive.length) {
            return towerCooldownActive[index];
        }
        return false;
    }
    
    public void setTowerCooldownActive(int index, boolean active) {
        if (index >= 0 && index < towerCooldownActive.length) {
            towerCooldownActive[index] = active;
        }
    }
    
    public float getTrapCooldown(int index) {
        if (index >= 0 && index < trapCooldowns.length) {
            return trapCooldowns[index];
        }
        return 0f;
    }
    
    public void setTrapCooldown(int index, float cooldown) {
        if (index >= 0 && index < trapCooldowns.length) {
            trapCooldowns[index] = cooldown;
        }
    }
    
    public boolean isTrapCooldownActive(int index) {
        if (index >= 0 && index < trapCooldownActive.length) {
            return trapCooldownActive[index];
        }
        return false;
    }
    
    public void setTrapCooldownActive(int index, boolean active) {
        if (index >= 0 && index < trapCooldownActive.length) {
            trapCooldownActive[index] = active;
        }
    }
    
    // ==================== BUTTON STATES ====================
    
    public boolean isResumeButtonPressed() { return resumeButtonPressed; }
    public boolean isSettingButtonPressed() { return settingButtonPressed; }
    public boolean isMenuButtonPressed() { return menuButtonPressed; }
    public boolean isNextButtonPressed() { return nextButtonPressed; }
    public boolean isRetryButtonPressed() { return retryButtonPressed; }
    public boolean isContinueButtonPressed() { return continueButtonPressed; }
    public boolean isRestartButtonPressed() { return restartButtonPressed; }
    public boolean isQuitButtonPressed() { return quitButtonPressed; }
    
    public void resetButtonStates() {
        menuButtonPressed = false;
        nextButtonPressed = false;
        retryButtonPressed = false;
        continueButtonPressed = false;
        restartButtonPressed = false;
        quitButtonPressed = false;
        resumeButtonPressed = false;
        settingButtonPressed = false;
        buttonPressTimer = 0f;
    }
    
    public void updateButtonPressTimer(float delta) {
        if (buttonPressTimer > 0f) {
            buttonPressTimer -= delta;
            if (buttonPressTimer <= 0f) {
                resetButtonStates();
            }
        }
    }
    
    public void pressButton(ButtonType buttonType) {
        resetButtonStates();
        
        switch (buttonType) {
            case MENU -> menuButtonPressed = true;
            case NEXT -> nextButtonPressed = true;
            case RETRY -> retryButtonPressed = true;
            case CONTINUE, RESUME -> resumeButtonPressed = true;
            case RESTART -> restartButtonPressed = true;
            case QUIT -> quitButtonPressed = true;
            case SETTING -> settingButtonPressed = true;
        }
        buttonPressTimer = GameConfig.BUTTON_PRESS_DURATION;
    }
    
    /**
     * @deprecated Use pressButton(ButtonType) instead
     */
    @Deprecated
    public void pressButton(String buttonType) {
        ButtonType type = ButtonType.fromString(buttonType);
        if (type != null) {
            pressButton(type);
        }
    }
    
    // ==================== BUTTON TYPE ENUM ====================
    
    public enum ButtonType {
        MENU, NEXT, RETRY, CONTINUE, RESUME, RESTART, QUIT, SETTING;
        
        public static ButtonType fromString(String str) {
            if (str == null) return null;
            return switch (str.toLowerCase()) {
                case "menu" -> MENU;
                case "next" -> NEXT;
                case "retry" -> RETRY;
                case "continue" -> CONTINUE;
                case "resume" -> RESUME;
                case "restart" -> RESTART;
                case "quit" -> QUIT;
                case "setting" -> SETTING;
                default -> null;
            };
        }
    }
    
    // ==================== LEGACY COMPATIBILITY ====================
    // These public fields are kept temporarily for backward compatibility
    // TODO: Remove these after refactoring all dependent classes
    
    /** @deprecated Use getEnemyProjectiles() instead */
    @Deprecated public Array<EnemyProjectile> enemyProjectiles() { return enemyProjectiles; }
    /** @deprecated Use getBombs() instead */
    @Deprecated public Array<BombAsset> bombs() { return bombs; }
    /** @deprecated Use getTowers() instead */
    @Deprecated public Array<Tower> towers() { return towers; }
    /** @deprecated Use getEnemies() instead */
    @Deprecated public Array<Enemy> enemies() { return enemies; }
    /** @deprecated Use getProjectiles() instead */
    @Deprecated public Array<Projectile> projectiles() { return projectiles; }
    /** @deprecated Use getTrapZones() instead */
    @Deprecated public Array<Trap> trapZones() { return trapZones; }
    /** @deprecated Use getTrapVerts() instead */
    @Deprecated public Array<float[]> trapVerts() { return trapVerts; }
    /** @deprecated Use getZones() instead */
    @Deprecated public Array<TowerDefenseScreen.Zone> zones() { return zones; }
    /** @deprecated Use getDeployedTowerZones() instead */
    @Deprecated public Array<TowerDefenseScreen.Zone> deployedTowerZones() { return deployedTowerZones; }
}
