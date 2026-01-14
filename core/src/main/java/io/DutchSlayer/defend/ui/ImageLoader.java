package io.DutchSlayer.defend.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import io.DutchSlayer.config.AssetPaths;
import io.DutchSlayer.defend.entities.towers.TowerType;

/**
 * Legacy image loader for tower defense mode.
 * @deprecated Use {@link TextureManager} instead for better resource management.
 */
@Deprecated
public class ImageLoader {
    public static Texture grassTex;
    public static Texture terratex;
    public static Texture skytex;

    public static Texture dutchtex;
    public static Texture enemyTex;

    public static Texture towerTex;
    public static Texture maintowertex;
    public static Texture tower1Tex, tower2Tex, tower3Tex;

    public static Texture[] towerAOEFrames = new Texture[2];
    public static Texture[] towerSpeedFrames = new Texture[2];
    public static Texture[] towerDefensifFrames = new Texture[3];

    public static Texture projTex, projtowtex;
    public static Texture aoeProjTex, fastProjTex, slowProjTex;
    public static Texture bossProjectileTex;

    public static Texture trapTex;
    public static Texture trapAttackTex;
    public static Texture trapSlowTex;
    public static Texture trapBombTex;

    public static Texture enemyBasicTex;
    public static Texture enemyShooterTex;
    public static Texture enemyBomberTex;
    public static Texture enemyShieldTex;
    public static Texture enemyBossTex;
    public static Texture[] enemyBasicFrames = new Texture[4];
    public static Texture[] enemyShieldFrames = new Texture[4];
    public static Texture[] enemyShooterFrames = new Texture[4];
    public static Texture[] enemyBomberFrames = new Texture[4];

    public static Texture bombAssetTex;
    public static Texture enemyProjectileTex;
    public static Texture removeBtnTex;
    public static Texture hammerCursorTex;
    public static Texture PauseBtntex;
    public static Texture explosionTex;

    public static Texture UITowerAOE;
    public static Texture UITowerSpeed;
    public static Texture UITowerDefensif;
    public static Texture UITrapAttack;
    public static Texture UITrapSlow;
    public static Texture UITrapBomb;

    public static Texture goldIconTex;

    public static Texture WinUI;
    public static Texture LoseUI;
    public static Texture BtnNext;
    public static Texture BtnMenu;
    public static Texture BtnRetry;
    public static Texture BtnMode;

    public static Texture PauseUI;
    public static Texture MenuBtn;
    public static Texture ResumeBtn;
    public static Texture SettingBtn;

    public static void load() {
        // Environment
        terratex = loadOrNull(AssetPaths.Backgrounds.TERRAIN_3);
        skytex = loadOrNull(AssetPaths.Backgrounds.BG);

        // Enemies
        enemyTex = loadOrNull(AssetPaths.EnemyDefend.ENEMY);
        dutchtex = loadOrNull(AssetPaths.EnemyDefend.DUTCH_BASIC);
        enemyBasicTex = loadOrNull(AssetPaths.EnemyDefend.DUTCH_BASIC);
        enemyShooterTex = loadOrNull(AssetPaths.EnemyDefend.DUTCH_SHOOTER);
        enemyBomberTex = loadOrNull(AssetPaths.EnemyDefend.DUTCH_BOMBER);
        enemyShieldTex = loadOrNull(AssetPaths.EnemyDefend.DUTCH_SHIELD);
        enemyBossTex = loadOrNull(AssetPaths.EnemyDefend.DUTCH_BOSS);
        
        // Projectiles
        bombAssetTex = loadOrNull(AssetPaths.Projectile.BOMB);
        enemyProjectileTex = loadOrNull(AssetPaths.Projectile.BASIC_2);
        bossProjectileTex = loadOrNull(AssetPaths.Projectile.BOSS);

        // Enemy animation frames
        for (int i = 0; i < 4; i++) {
            enemyBasicFrames[i] = loadOrNull(AssetPaths.getFramePath(AssetPaths.EnemyDefend.BASIC_FRAME, i + 1));
            enemyShieldFrames[i] = loadOrNull(AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHIELD_FRAME, i + 1));
            enemyShooterFrames[i] = loadOrNull(AssetPaths.getFramePath(AssetPaths.EnemyDefend.SHOOTER_FRAME, i + 1));
            enemyBomberFrames[i] = loadOrNull(AssetPaths.getFramePath(AssetPaths.EnemyDefend.BOMBER_FRAME, i + 1));
        }

        // Towers
        towerTex = loadOrNull(AssetPaths.Tower.BASE_2);
        maintowertex = loadOrNull(AssetPaths.Tower.MAIN);
        tower1Tex = loadOrNull(AssetPaths.Tower.AOE_ICON);
        tower2Tex = loadOrNull(AssetPaths.Tower.SPEED_ICON);
        tower3Tex = loadOrNull(AssetPaths.Tower.DEFENSIVE_ICON);
        
        // Tower animation frames
        towerAOEFrames[0] = loadOrNull(AssetPaths.getFramePath(AssetPaths.Tower.AOE_FRAME, 1));
        towerAOEFrames[1] = loadOrNull(AssetPaths.getFramePath(AssetPaths.Tower.AOE_FRAME, 2));
        towerSpeedFrames[0] = loadOrNull(AssetPaths.getFramePath(AssetPaths.Tower.SPEED_FRAME, 1));
        towerSpeedFrames[1] = loadOrNull(AssetPaths.getFramePath(AssetPaths.Tower.SPEED_FRAME, 2));
        towerDefensifFrames[0] = loadOrNull(AssetPaths.getFramePath(AssetPaths.Tower.DEFENSIVE_FRAME, 1));
        towerDefensifFrames[1] = loadOrNull(AssetPaths.getFramePath(AssetPaths.Tower.DEFENSIVE_FRAME, 2));
        towerDefensifFrames[2] = loadOrNull(AssetPaths.getFramePath(AssetPaths.Tower.DEFENSIVE_FRAME, 3));

        // Projectiles
        projTex = loadOrNull(AssetPaths.Projectile.BASIC_2);
        projtowtex = loadOrNull(AssetPaths.Projectile.BASIC_1);
        aoeProjTex = loadOrNull(AssetPaths.Projectile.AOE);
        fastProjTex = loadOrNull(AssetPaths.Projectile.SPEED);
        slowProjTex = loadOrNull(AssetPaths.Projectile.DEFENSIVE);

        // Traps
        trapTex = loadOrNull(AssetPaths.Trap.BASE);
        trapAttackTex = loadOrNull(AssetPaths.Trap.ATTACK);
        trapSlowTex = loadOrNull(AssetPaths.Trap.SLOW);
        trapBombTex = loadOrNull(AssetPaths.Trap.BOMB);

        // Buttons
        removeBtnTex = loadOrNull(AssetPaths.Ui.BTN_REMOVE);
        PauseBtntex = loadOrNull(AssetPaths.Ui.BTN_PAUSE);

        // UI - Tower/Trap selection
        UITowerAOE = loadOrNull(AssetPaths.Ui.ICON_TOWER_AOE);
        UITowerSpeed = loadOrNull(AssetPaths.Ui.ICON_TOWER_SPEED);
        UITowerDefensif = loadOrNull(AssetPaths.Ui.ICON_TOWER_DEFENSIVE);
        UITrapAttack = loadOrNull(AssetPaths.Ui.ICON_TRAP_ATTACK);
        UITrapSlow = loadOrNull(AssetPaths.Ui.ICON_TRAP_SLOW);
        UITrapBomb = loadOrNull(AssetPaths.Ui.ICON_TRAP_BOMB);
        goldIconTex = loadOrNull(AssetPaths.Ui.ICON_GOLD);

        // UI - Win/Lose
        WinUI = loadOrNull(AssetPaths.Ui.WIN);
        LoseUI = loadOrNull(AssetPaths.Ui.LOSE);
        BtnNext = loadOrNull(AssetPaths.Ui.BTN_NEXT);
        BtnMenu = loadOrNull(AssetPaths.Ui.BTN_MENU);
        BtnRetry = loadOrNull(AssetPaths.Ui.BTN_RETRY);
        BtnMode = loadOrNull(AssetPaths.Ui.MODE_SELECTION);

        // UI - Pause
        PauseUI = loadOrNull(AssetPaths.Ui.PAUSE);
        MenuBtn = loadOrNull(AssetPaths.Ui.BTN_PAUSE_MENU);
        ResumeBtn = loadOrNull(AssetPaths.Ui.BTN_RESUME);
        SettingBtn = loadOrNull(AssetPaths.Ui.BTN_SETTINGS);
    }

    private static Texture loadOrNull(String path) {
        try {
            return new Texture(Gdx.files.internal(path));
        } catch (Exception e) {
            Gdx.app.error("ImageLoader", "Gagal load "+path+", akan pakai shape", e);
            return null;
        }
    }

    public static Texture[] getTowerAnimationFrames(TowerType type) {
        return switch (type) {
            case AOE -> towerAOEFrames;
            case FAST -> towerSpeedFrames;
            case SLOW -> towerDefensifFrames;
            default -> null;
        };
    }

    public static void dispose() {
        if (towerTex != null) towerTex.dispose();
        if (enemyTex  != null) enemyTex.dispose();
        if (projTex   != null) projTex.dispose();
        if (trapTex != null) trapTex.dispose();
        if (grassTex  != null) grassTex.dispose();
        if (terratex  != null) terratex.dispose();
        if (skytex  != null) skytex.dispose();
        if (dutchtex  != null) dutchtex.dispose();
        if (maintowertex != null) maintowertex.dispose();

        if (tower1Tex != null) tower1Tex.dispose();
        if (tower2Tex != null) tower2Tex.dispose();
        if (tower3Tex != null) tower3Tex.dispose();

        if (aoeProjTex != null) aoeProjTex.dispose();
        if (fastProjTex != null) fastProjTex.dispose();
        if (slowProjTex != null) slowProjTex.dispose();
        if (projtowtex != null)  projtowtex.dispose();

        if (trapAttackTex != null) trapAttackTex.dispose();
        if (trapSlowTex != null) trapSlowTex.dispose();
        if (trapBombTex != null) trapBombTex.dispose();
        if (explosionTex != null) explosionTex.dispose();

        if (hammerCursorTex != null) hammerCursorTex.dispose();
        if (removeBtnTex != null) removeBtnTex.dispose();
        if (PauseBtntex != null) PauseBtntex.dispose();

        if (UITowerAOE != null) UITowerAOE.dispose();
        if (UITowerSpeed != null) UITowerSpeed.dispose();
        if (UITowerDefensif != null) UITowerDefensif.dispose();
        if (UITrapAttack != null) UITrapAttack.dispose();
        if (UITrapSlow != null) UITrapSlow.dispose();
        if (UITrapBomb != null) UITrapBomb.dispose();

        if (WinUI != null) WinUI.dispose();
        if (LoseUI != null) LoseUI.dispose();
        if (BtnNext != null) BtnNext.dispose();
        if (BtnMenu != null) BtnMenu.dispose();
        if (BtnRetry != null) BtnRetry.dispose();

        if (PauseUI != null) PauseUI.dispose();
        if (MenuBtn != null) MenuBtn.dispose();
        if (ResumeBtn != null) ResumeBtn.dispose();
        if (SettingBtn != null) SettingBtn.dispose();
        if (BtnMode != null) BtnMode.dispose();

        for (Texture enemyBasicFrame : enemyBasicFrames) {
            if (enemyBasicFrame != null) {
                enemyBasicFrame.dispose();
            }
        }

        for (Texture enemyShieldFrame : enemyShieldFrames) {
            if (enemyShieldFrame != null) {
                enemyShieldFrame.dispose();
            }
        }

        for (Texture enemyShooterFrame : enemyShooterFrames) {
            if (enemyShooterFrame != null) {
                enemyShooterFrame.dispose();
            }
        }

        for (Texture enemyBomberFrame : enemyBomberFrames) {
            if (enemyBomberFrame != null) {
                enemyBomberFrame.dispose();
            }
        }

        for (Texture towerAOEFrame : towerAOEFrames) {
            if (towerAOEFrame != null) {
                towerAOEFrame.dispose();
            }
        }

        for (Texture towerSpeedFrame : towerSpeedFrames) {
            if (towerSpeedFrame != null) {
                towerSpeedFrame.dispose();
            }
        }

        for (Texture towerDefensifFrame : towerDefensifFrames) {
            if (towerDefensifFrame != null) {
                towerDefensifFrame.dispose();
            }
        }
    }
}
