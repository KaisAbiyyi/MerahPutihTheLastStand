package io.DutchSlayer.config;

/**
 * Centralized asset path configuration.
 * All asset paths should be referenced from here for easy management.
 * 
 * Folder Structure (New - Organized):
 * assets/
 * ├── audio/
 * │   ├── music/           - Background music tracks
 * │   └── sfx/             - Sound effects
 * │       ├── ui/          - UI sounds (clicks, etc.)
 * │       ├── player/      - Player sounds
 * │       ├── enemy/       - Enemy sounds
 * │       ├── tower/       - Tower defense sounds
 * │       └── trap/        - Trap sounds
 * ├── backgrounds/
 * │   ├── attack/          - Attack mode backgrounds
 * │   └── defend/          - Defense mode backgrounds
 * ├── sprites/
 * │   ├── player/          - Player animations
 * │   ├── enemy/
 * │   │   ├── attack/      - Attack mode enemies
 * │   │   └── defend/      - Defense mode enemies
 * │   │       ├── basic/
 * │   │       ├── bomber/
 * │   │       ├── shield/
 * │   │       └── shooter/
 * │   ├── boss/            - Boss sprites
 * │   ├── tower/
 * │   │   ├── aoe/
 * │   │   ├── defensive/
 * │   │   └── speed/
 * │   ├── projectile/
 * │   └── trap/
 * ├── ui/
 * │   ├── buttons/
 * │   ├── icons/
 * │   ├── screens/
 * │   ├── cursor/
 * │   └── skin/
 * ├── story/               - Story scene images
 * └── buildings/           - Building sprites
 */
public final class AssetPaths {
    
    private AssetPaths() {} // Prevent instantiation
    
    // ==================== BASE DIRECTORIES ====================
    public static final class Dirs {
        public static final String AUDIO = "audio/";
        public static final String MUSIC = AUDIO + "music/";
        public static final String SFX = AUDIO + "sfx/";
        public static final String SFX_UI = SFX + "ui/";
        public static final String SFX_PLAYER = SFX + "player/";
        public static final String SFX_ENEMY = SFX + "enemy/";
        public static final String SFX_TOWER = SFX + "tower/";
        public static final String SFX_TRAP = SFX + "trap/";
        
        public static final String BACKGROUNDS = "backgrounds/";
        public static final String BG_ATTACK = BACKGROUNDS + "attack/";
        public static final String BG_DEFEND = BACKGROUNDS + "defend/";
        
        public static final String SPRITES = "sprites/";
        public static final String PLAYER = SPRITES + "player/";
        public static final String ENEMY = SPRITES + "enemy/";
        public static final String ENEMY_ATTACK = ENEMY + "attack/";
        public static final String ENEMY_DEFEND = ENEMY + "defend/";
        public static final String BOSS = SPRITES + "boss/";
        public static final String TOWER = SPRITES + "tower/";
        public static final String PROJECTILE = SPRITES + "projectile/";
        public static final String TRAP = SPRITES + "trap/";
        
        public static final String UI = "ui/";
        public static final String UI_BUTTONS = UI + "buttons/";
        public static final String UI_ICONS = UI + "icons/";
        public static final String UI_SCREENS = UI + "screens/";
        public static final String UI_CURSOR = UI + "cursor/";
        public static final String UI_SKIN = UI + "skin/";
        
        public static final String STORY = "story/";
        public static final String BUILDINGS = "buildings/";
        
        private Dirs() {}
    }
    
    // ==================== MUSIC ====================
    public static final class Music {
        public static final String MAIN_MENU = Dirs.MUSIC + "main_menu.mp3";
        public static final String GAMEPLAY = Dirs.MUSIC + "gameplay.mp3";
        public static final String BOSS = Dirs.MUSIC + "boss.mp3";
        public static final String BOSS_FIGHT = Dirs.MUSIC + "boss_fight.mp3";
        public static final String VICTORY = Dirs.MUSIC + "victory.mp3";
        public static final String DEFEAT = Dirs.MUSIC + "defeat.mp3";
        public static final String WIN = Dirs.MUSIC + "win.mp3";
        public static final String LOSE = Dirs.MUSIC + "lose.mp3";
        public static final String BACKGROUND = Dirs.MUSIC + "background.mp3";
        
        private Music() {}
    }
    
    // ==================== SOUND EFFECTS ====================
    public static final class Sfx {
        // UI
        public static final String BUTTON_CLICK = Dirs.SFX_UI + "button_click.mp3";
        public static final String SELECT_NAVBAR = Dirs.SFX_UI + "select_navbar.mp3";
        public static final String SELECT_NAVBAR_1 = Dirs.SFX_UI + "select_navbar_1.mp3";
        public static final String SELECT_NAVBAR_2 = Dirs.SFX_UI + "select_navbar_2.mp3";
        
        // Player
        public static final String PISTOL = Dirs.SFX_PLAYER + "pistol.mp3";
        public static final String DASH = Dirs.SFX_PLAYER + "dash.mp3";
        public static final String JUMP = Dirs.SFX_PLAYER + "jump.mp3";
        public static final String DIES = Dirs.SFX_PLAYER + "dies.mp3";
        public static final String HIT = Dirs.SFX_PLAYER + "hit.mp3";
        public static final String GRENADE = Dirs.SFX_PLAYER + "grenade.mp3";
        public static final String GRENADE_THROW = Dirs.SFX_PLAYER + "grenade_throw.mp3";
        public static final String GRENADE_GROUND = Dirs.SFX_PLAYER + "grenade_ground.mp3";
        
        // Enemy
        public static final String ENEMY_DEATH = Dirs.SFX_ENEMY + "death.mp3";
        public static final String ENEMY_DEATH_ATTACK = Dirs.SFX_ENEMY + "death_attack.mp3";
        public static final String ENEMY_SHOOT = Dirs.SFX_ENEMY + "shoot.mp3";
        public static final String BOSS_SHOOT = Dirs.SFX_ENEMY + "boss_shoot.mp3";
        public static final String TANK_BULLET = Dirs.SFX_ENEMY + "tank_bullet.mp3";
        public static final String TANK_CHARGING = Dirs.SFX_ENEMY + "tank_charging.mp3";
        public static final String TANK_DESTROYED = Dirs.SFX_ENEMY + "tank_destroyed.mp3";
        public static final String TANK_GRENADE = Dirs.SFX_ENEMY + "tank_grenade.mp3";
        public static final String TANK_PREPARE_CHARGE = Dirs.SFX_ENEMY + "tank_prepare_charge.mp3";
        
        // Tower
        public static final String TOWER_SHOOT = Dirs.SFX_TOWER + "shoot.mp3";
        public static final String TOWER_AOE = Dirs.SFX_TOWER + "aoe.mp3";
        public static final String TOWER_BREAK = Dirs.SFX_TOWER + "break.mp3";
        public static final String TOWER_BROKE = Dirs.SFX_TOWER + "broke.mp3";
        public static final String BUILDING = Dirs.SFX_TOWER + "building.mp3";
        
        // Trap
        public static final String TRAP_PLACE = Dirs.SFX_TRAP + "place.mp3";
        public static final String TRAP_BOMB = Dirs.SFX_TRAP + "bomb.mp3";
        public static final String TRAP_SLOW = Dirs.SFX_TRAP + "slow.mp3";
        public static final String TRAP_SPIKE = Dirs.SFX_TRAP + "spike.mp3";
        public static final String WIND = Dirs.SFX_TRAP + "wind.mp3";
        public static final String WIND_PASSING = Dirs.SFX_TRAP + "wind_passing.mp3";
        
        private Sfx() {}
    }
    
    // ==================== BACKGROUNDS ====================
    public static final class Backgrounds {
        // Shared backgrounds (used by both Attack and Defend modes)
        public static final String BG = Dirs.BACKGROUNDS + "background.png";
        public static final String MOUNTAIN = Dirs.BACKGROUNDS + "mountain.png";
        public static final String TREE = Dirs.BACKGROUNDS + "tree.png";
        public static final String SKY = Dirs.BACKGROUNDS + "sky.png";
        public static final String TERRAIN = Dirs.BACKGROUNDS + "terrain.png";
        public static final String TERRAIN_2 = Dirs.BACKGROUNDS + "terrain_2.png";
        public static final String TERRAIN_3 = Dirs.BACKGROUNDS + "terrain_3.png";
        
        private Backgrounds() {}
    }
    
    // ==================== UI ====================
    public static final class Ui {
        // Skin
        public static final String SKIN = Dirs.UI_SKIN + "uiskin.json";
        
        // Screens
        public static final String MAIN_MENU = Dirs.UI_SCREENS + "main_menu.png";
        public static final String WIN = Dirs.UI_SCREENS + "win.png";
        public static final String LOSE = Dirs.UI_SCREENS + "lose.png";
        public static final String PAUSE = Dirs.UI_SCREENS + "pause.png";
        public static final String MODE_SELECTION = Dirs.UI_SCREENS + "mode_selection.png";
        
        // Buttons
        public static final String BTN_PAUSE = Dirs.UI_BUTTONS + "pause.png";
        public static final String BTN_REMOVE = Dirs.UI_BUTTONS + "remove.png";
        public static final String BTN_REMOVE_CLICKED = Dirs.UI_BUTTONS + "remove_clicked.png";
        public static final String BTN_MENU = Dirs.UI_BUTTONS + "menu.png";
        public static final String BTN_NEXT = Dirs.UI_BUTTONS + "next.png";
        public static final String BTN_RETRY = Dirs.UI_BUTTONS + "retry.png";
        public static final String BTN_PAUSE_MENU = Dirs.UI_BUTTONS + "pause_menu.png";
        public static final String BTN_RESUME = Dirs.UI_BUTTONS + "resume.png";
        public static final String BTN_SETTINGS = Dirs.UI_BUTTONS + "settings.png";
        
        // Icons
        public static final String ICON_GOLD = Dirs.UI_ICONS + "gold.png";
        public static final String ICON_TOWER_AOE = Dirs.UI_ICONS + "tower_aoe.png";
        public static final String ICON_TOWER_DEFENSIVE = Dirs.UI_ICONS + "tower_defensive.png";
        public static final String ICON_TOWER_SPEED = Dirs.UI_ICONS + "tower_speed.png";
        public static final String ICON_TRAP_ATTACK = Dirs.UI_ICONS + "trap_attack.png";
        public static final String ICON_TRAP_BOMB = Dirs.UI_ICONS + "trap_bomb.png";
        public static final String ICON_TRAP_SLOW = Dirs.UI_ICONS + "trap_slow.png";
        
        // Cursor
        public static final String CURSOR_HAMMER = Dirs.UI_CURSOR + "hammer.png";
        public static final String CURSOR_REMOVE = Dirs.UI_CURSOR + "remove.png";
        
        private Ui() {}
    }
    
    // ==================== SPRITES - PLAYER ====================
    public static final class Player {
        public static final String IDLE = Dirs.PLAYER + "idle.png";
        public static final String DEAD = Dirs.PLAYER + "dead.png";
        public static final String DUCK = Dirs.PLAYER + "duck.png";
        public static final String DUCK_AR = Dirs.PLAYER + "duck_ar.png";
        public static final String DASH = Dirs.PLAYER + "dash.png";
        public static final String JUMP = Dirs.PLAYER + "jump.png";
        public static final String BULLET = Dirs.PLAYER + "bullet.png";
        public static final String GRENADE = Dirs.PLAYER + "grenade.png";
        public static final String EXPLOSION = Dirs.PLAYER + "explosion.png";
        public static final String RUN_FRAME = Dirs.PLAYER + "run_%d.png";
        public static final String RUN_AR_FRAME = Dirs.PLAYER + "run_ar_%d.png";
        
        private Player() {}
    }
    
    // ==================== SPRITES - ENEMY (Attack Mode) ====================
    public static final class EnemyAttack {
        public static final String DEAD = Dirs.ENEMY_ATTACK + "dead.png";
        public static final String RUN_FRAME = Dirs.ENEMY_ATTACK + "run_%d.png";
        public static final String RUN_AR_FRAME = Dirs.ENEMY_ATTACK + "run_ar_%d.png";
        
        private EnemyAttack() {}
    }
    
    // ==================== SPRITES - ENEMY (Defend Mode) ====================
    public static final class EnemyDefend {
        public static final String DUTCH_BASIC = Dirs.ENEMY_DEFEND + "dutch_basic.png";
        public static final String DUTCH_BOMBER = Dirs.ENEMY_DEFEND + "dutch_bomber.png";
        public static final String DUTCH_BOSS = Dirs.ENEMY_DEFEND + "dutch_boss.png";
        public static final String DUTCH_SHIELD = Dirs.ENEMY_DEFEND + "dutch_shield.png";
        public static final String DUTCH_SHOOTER = Dirs.ENEMY_DEFEND + "dutch_shooter.png";
        public static final String ENEMY = Dirs.ENEMY_DEFEND + "enemy.png";
        
        // Animation frames
        public static final String BASIC_FRAME = Dirs.ENEMY_DEFEND + "basic/frame_%d.png";
        public static final String BOMBER_FRAME = Dirs.ENEMY_DEFEND + "bomber/frame_%d.png";
        public static final String SHIELD_FRAME = Dirs.ENEMY_DEFEND + "shield/frame_%d.png";
        public static final String SHOOTER_FRAME = Dirs.ENEMY_DEFEND + "shooter/frame_%d.png";
        
        private EnemyDefend() {}
    }
    
    // ==================== SPRITES - BOSS ====================
    public static final class Boss {
        public static final String BULLET = Dirs.BOSS + "bullet.png";
        public static final String TANK_CHARGING = Dirs.BOSS + "tank_charging.png";
        public static final String TANK_DESTROYED = Dirs.BOSS + "tank_destroyed.png";
        public static final String TANK_GRENADE = Dirs.BOSS + "tank_grenade.png";
        public static final String TANK_TURRET = Dirs.BOSS + "tank_turret.png";
        public static final String WALL = Dirs.BOSS + "wall.png";
        
        private Boss() {}
    }
    
    // ==================== SPRITES - TOWER ====================
    public static final class Tower {
        public static final String MAIN = Dirs.TOWER + "main_tower.png";
        public static final String BASE = Dirs.TOWER + "tower.png";
        public static final String BASE_2 = Dirs.TOWER + "tower_2.png";
        
        // AOE Tower
        public static final String AOE_ICON = Dirs.TOWER + "aoe/icon.png";
        public static final String AOE_ICON_ALT = Dirs.TOWER + "aoe/icon_alt.png";
        public static final String AOE_FRAME = Dirs.TOWER + "aoe/frame_%d.png";
        
        // Defensive Tower
        public static final String DEFENSIVE_ICON = Dirs.TOWER + "defensive/icon.png";
        public static final String DEFENSIVE_FRAME = Dirs.TOWER + "defensive/frame_%d.png";
        
        // Speed Tower
        public static final String SPEED_ICON = Dirs.TOWER + "speed/icon.png";
        public static final String SPEED_ICON_ALT = Dirs.TOWER + "speed/icon_alt.png";
        public static final String SPEED_FRAME = Dirs.TOWER + "speed/frame_%d.png";
        
        private Tower() {}
    }
    
    // ==================== SPRITES - PROJECTILE ====================
    public static final class Projectile {
        public static final String BASIC = Dirs.PROJECTILE + "basic.png";
        public static final String BASIC_1 = Dirs.PROJECTILE + "basic_1.png";
        public static final String BASIC_2 = Dirs.PROJECTILE + "basic_2.png";
        public static final String AOE = Dirs.PROJECTILE + "aoe.png";
        public static final String AOE_1 = Dirs.PROJECTILE + "aoe_1.png";
        public static final String DEFENSIVE = Dirs.PROJECTILE + "defensive.png";
        public static final String SPEED = Dirs.PROJECTILE + "speed.png";
        public static final String BOSS = Dirs.PROJECTILE + "boss.png";
        public static final String BOMB = Dirs.PROJECTILE + "bomb.png";
        
        private Projectile() {}
    }
    
    // ==================== SPRITES - TRAP ====================
    public static final class Trap {
        public static final String BASE = Dirs.TRAP + "base.png";
        public static final String ATTACK = Dirs.TRAP + "attack.png";
        public static final String BOMB = Dirs.TRAP + "bomb.png";
        public static final String SLOW = Dirs.TRAP + "slow.png";
        
        private Trap() {}
    }
    
    // ==================== STORY SCENES ====================
    public static final class Story {
        public static final String SCENE_PATTERN = Dirs.STORY + "scene%d.png";
        
        public static String getScene(int number) {
            return String.format(SCENE_PATTERN, number);
        }
        
        private Story() {}
    }
    
    // ==================== OBJECTS ====================
    public static final class Objects {
        public static final String TREE = Dirs.SPRITES + "tree.png";
        public static final String WHITE = "white.png";
        
        // Buildings
        public static final String ADMIN_OFFICE = Dirs.BUILDINGS + "admin_office.png";
        public static final String BARRACKS = Dirs.BUILDINGS + "barracks.png";
        public static final String FORT = Dirs.BUILDINGS + "fort.png";
        public static final String PLANTATION = Dirs.BUILDINGS + "plantation.png";
        public static final String WAREHOUSE = Dirs.BUILDINGS + "warehouse.png";
        
        private Objects() {}
    }
    
    // ==================== HELPER METHODS ====================
    
    /**
     * Get animation frame path with index.
     * @param pattern Path pattern with %d placeholder
     * @param frameIndex Frame index (1-based)
     * @return Formatted path
     */
    public static String getFramePath(String pattern, int frameIndex) {
        return String.format(pattern, frameIndex);
    }
    
    /**
     * Get player run animation frame.
     * @param frameIndex Frame index (1-5)
     * @param hasAssaultRifle Whether player has AR equipped
     * @return Path to animation frame
     */
    public static String getPlayerRunFrame(int frameIndex, boolean hasAssaultRifle) {
        return String.format(
            hasAssaultRifle ? Player.RUN_AR_FRAME : Player.RUN_FRAME, 
            frameIndex
        );
    }
    
    /**
     * Get enemy run animation frame (attack mode).
     * @param frameIndex Frame index (1-5 for normal, 1-4 for AR)
     * @param hasAssaultRifle Whether enemy has AR equipped
     * @return Path to animation frame
     */
    public static String getEnemyRunFrame(int frameIndex, boolean hasAssaultRifle) {
        return String.format(
            hasAssaultRifle ? EnemyAttack.RUN_AR_FRAME : EnemyAttack.RUN_FRAME, 
            frameIndex
        );
    }
    
    /**
     * Get defend mode enemy animation frame.
     * @param enemyType Enemy type: "basic", "bomber", "shield", "shooter"
     * @param frameIndex Frame index (1-4)
     * @return Path to animation frame
     */
    public static String getDefendEnemyFrame(String enemyType, int frameIndex) {
        String pattern = switch (enemyType.toLowerCase()) {
            case "basic" -> EnemyDefend.BASIC_FRAME;
            case "bomber" -> EnemyDefend.BOMBER_FRAME;
            case "shield" -> EnemyDefend.SHIELD_FRAME;
            case "shooter" -> EnemyDefend.SHOOTER_FRAME;
            default -> EnemyDefend.BASIC_FRAME;
        };
        return String.format(pattern, frameIndex);
    }
    
    /**
     * Get tower animation frame.
     * @param towerType Tower type: "aoe", "defensive", "speed"
     * @param frameIndex Frame index
     * @return Path to animation frame
     */
    public static String getTowerFrame(String towerType, int frameIndex) {
        String pattern = switch (towerType.toLowerCase()) {
            case "aoe" -> Tower.AOE_FRAME;
            case "defensive" -> Tower.DEFENSIVE_FRAME;
            case "speed" -> Tower.SPEED_FRAME;
            default -> Tower.AOE_FRAME;
        };
        return String.format(pattern, frameIndex);
    }
}
