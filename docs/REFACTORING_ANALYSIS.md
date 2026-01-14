# 🎮 Analisis Codebase: MerahPutihTheLastStand

> **Tanggal Analisis:** 14 Januari 2026  
> **Versi:** 2.0 (Updated after refactoring)
> **Author:** GitHub Copilot

---

## ✅ Refactoring Completed

Berikut adalah daftar refactoring yang telah diselesaikan:

### New Files Created

| File | Purpose |
|------|---------|
| `config/GameConfig.java` | Centralized constants (150+ constants) |
| `common/Updatable.java` | Interface for frame updates |
| `common/Renderable.java` | Interface for rendering |
| `common/Collidable.java` | Interface for collision detection |
| `common/GameEntity.java` | Combined entity interface |
| `common/Damageable.java` | Interface for health/damage |
| `common/Poolable.java` | Interface for object pooling |
| `screens/BaseScreen.java` | Abstract base screen class |
| `defend/game/GameStateNew.java` | Encapsulated game state |
| `defend/game/systems/EnemySystem.java` | Enemy-related logic |
| `defend/game/systems/ProjectileSystem.java` | Projectile logic |
| `defend/game/systems/TrapSystem.java` | Trap logic |
| `defend/game/systems/WaveSystem.java` | Wave spawning logic |
| `defend/game/systems/CooldownSystem.java` | Cooldown management |
| `defend/ui/TextureManager.java` | Singleton texture loader |

### Modified Files

| File | Changes |
|------|---------|
| `defend/game/GameConstants.java` | Uses GameConfig, new constants added |
| `defend/utils/AudioManager.java` | Fixed naming conventions |
| `attack/enemy/BasicEnemy.java` | Deprecated empty methods |

---

## 📋 Daftar Isi

1. [Overview Project](#-overview-project)
2. [Code Smells & Masalah](#-code-smells--masalah-yang-ditemukan)
3. [SOLID Principles Violations](#-solid-principles-violations)
4. [Performance Improvements](#-performance-improvements)
5. [Game Mechanics Suggestions](#-game-mechanics-suggestions)
6. [Recommended Architecture](#-recommended-architecture-refactoring)
7. [Quick Wins](#-quick-wins)
8. [Implementation Roadmap](#-implementation-roadmap)

---

## 📊 Overview Project

Game ini adalah **action-defense hybrid** yang terinspirasi dari:
- **Metal Slug** (Attack Mode) - Side-scrolling action shooter
- **Plant vs Zombies** (Defense Mode) - Tower defense strategy

### Tech Stack
- **Framework:** libGDX
- **Build Tool:** Gradle
- **Language:** Java 17+
- **Platform:** Desktop (LWJGL3)

### Project Structure
```
MerahPutihTheLastStand/
├── core/                          # Game logic
│   └── src/main/java/io/DutchSlayer/
│       ├── attack/                # Attack mode (Metal Slug style)
│       │   ├── boss/              # Boss entities & FSM
│       │   ├── enemy/             # Enemy entities & factory
│       │   ├── objects/           # Game objects (buildings, trees)
│       │   ├── player/            # Player & weapons
│       │   └── screens/           # Game screens & rendering
│       ├── defend/                # Defense mode (PvZ style)
│       │   ├── entities/          # Towers, enemies, projectiles
│       │   ├── game/              # Game logic & state
│       │   ├── screens/           # Tower defense screens
│       │   ├── ui/                # UI components
│       │   └── utils/             # Audio & utilities
│       ├── screens/               # Shared screens (menu, settings)
│       └── utils/                 # Constants & utilities
├── lwjgl3/                        # Desktop launcher
└── assets/                        # Game assets
```

### File Statistics

| Category | Files | Total Lines (est.) |
|----------|-------|-------------------|
| Attack Mode | 25 | ~4,500 |
| Defense Mode | 20 | ~3,800 |
| Shared | 10 | ~1,500 |
| **Total** | **55+** | **~10,000** |

---

## 🔴 Code Smells & Masalah yang Ditemukan

### 1. God Classes (Kelas Terlalu Besar)

Beberapa class memiliki terlalu banyak tanggung jawab:

| Class | Lines | Tanggung Jawab |
|-------|-------|----------------|
| `GameScreen.java` | 701 | Render, logic, VN, music, spawning, pause |
| `TowerDefenseScreen.java` | 651 | Rendering, game state, UI, input |
| `GameLogic.java` | 561 | Semua game logic dalam satu class |
| `Enemy.java` (defend) | 460 | AI, rendering, stats, animation |
| `BasicEnemy.java` (attack) | 454 | Combat, visuals, FSM, sound |
| `Tower.java` | 388 | Shooting, upgrades, animation, rendering |

**Dampak:**
- Sulit di-maintain dan di-test
- High coupling, low cohesion
- Perubahan kecil bisa menyebabkan bug di tempat lain

---

### 2. Primitive Obsession & Magic Numbers

Magic numbers tersebar di seluruh codebase tanpa penjelasan:

```java
// ❌ GameScreen.java
private final float wallRiseSpeed = 350f;
private final float wallTargetY = Constant.TERRAIN_HEIGHT;
private float victoryDelayTimer = 2.0f;

// ❌ Enemy.java
public static final float BASIC_SCALE = 0.2f;
public static final float SHOOTER_SCALE = 0.18f;
private static final float SHOOTER_INTERVAL = 1.5f;
private static final float KNOCKBACK_DURATION = 0.5f;

// ❌ Tower.java
private static final int MAX_TOTAL_UPGRADES = 10;
private static final float ANIMATION_DURATION = 0.2f;
private static final float TARGET_CHECK_INTERVAL = 0.1f;

// ❌ GameState.java
public static final float BUTTON_PRESS_DURATION = 0.15f;
```

**Solusi:** Extract ke configuration class atau enum

---

### 3. Public Fields (Encapsulation Buruk)

`GameState.java` mengekspos SEMUA field sebagai public:

```java
// ❌ SEMUA field public!
public class GameState {
    public final Array<EnemyProjectile> enemyProjectiles = new Array<>();
    public final Array<BombAsset> bombs = new Array<>();
    public final Array<Tower> towers = new Array<>();
    public final Array<Enemy> enemies = new Array<>();
    
    public int gold = 80;
    public int currentWave = 1;
    public int spawnCount = 0;
    public boolean bossSpawned = false;
    public boolean isGameOver = false;
    public boolean isGameWon = false;
    public boolean isPaused = false;
    
    public float spawnTimer = 0f;
    public float goldTimer = 0f;
    
    public boolean isResumeButtonPressed = false;
    public boolean isSettingButtonPressed = false;
    public boolean isMenuButtonPressed = false;
    // ... dan banyak lagi
}
```

**Dampak:**
- Tidak ada kontrol akses
- State bisa diubah dari mana saja
- Sulit debug dan track changes

---

### 4. Duplicate Code

#### A. Rendering Pattern Berulang
```java
// Pattern ini berulang di SEMUA Screen classes
@Override
public void render(float delta) {
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    game.batch.setProjectionMatrix(viewport.getCamera().combined);
    game.batch.begin();
    game.batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
    game.batch.end();

    stage.act(delta);
    stage.draw();
}
```

**Ditemukan di:**
- `MainMenuScreen.java`
- `ModeSelectionScreen.java`
- `SettingScreen.java`
- `AboutScreen.java`
- `StageSelectionScreen.java`

#### B. Empty Screen Methods
```java
// Berulang di hampir semua Screen
@Override public void pause()  {}
@Override public void resume() {}
@Override public void hide()   {}
```

#### C. Enemy Stats Configuration
```java
// Enemy.java (defend)
private EnemyStats getEnemyStats(EnemyType type) {
    return switch (type) {
        case BASIC -> new EnemyStats(texture, 3, 100f, 0.2f, 0f, 0f);
        case SHOOTER -> new EnemyStats(texture, 2, 80f, 0.18f, 1.5f, 0f);
        // ...
    };
}

// BasicEnemy.java (attack) - berbeda implementasi untuk hal serupa
private void configureWeaponByType() {
    switch (attackType) {
        case STRAIGHT_SHOOT, BURST_FIRE -> fireCooldown = 0.5f;
        case ARC_GRENADE -> fireCooldown = 2.5f;
    }
}
```

---

### 5. Long Methods

#### A. `GameScreen.render()` - 100+ lines
```java
@Override
public void render(float delta) {
    // Clear screen
    // Handle ESC key
    // Handle F11 fullscreen
    // Check game over
    // Check boss defeated
    // Handle VN display
    // Update game logic
    // Handle victory transition
    // Render pause menu
    // Render game world
    // ... semua dalam SATU method!
}
```

#### B. `GameLogic.update()` - 50+ lines
```java
public void update(float delta) {
    // Early return checks
    // Update cooldowns
    // Update traps
    // Update enemies
    // Update enemy projectiles
    // Update bombs
    // Update trap collisions
    // Update tower shooting
    // Update projectiles
    // Cleanup dead enemies
    // Update wave spawning
    // Update gold income
    // Update boss music
    // Validate victory music
    // ... semua dalam SATU method!
}
```

---

### 6. Feature Envy

Classes mengakses data dari class lain lebih dari data mereka sendiri:

```java
// ❌ Enemy.java terlalu bergantung pada ImageLoader
private EnemyStats getEnemyStats(EnemyType type) {
    return switch (type) {
        case BASIC -> new EnemyStats(
            ImageLoader.enemyBasicTex != null ? ImageLoader.enemyBasicTex : ImageLoader.dutchtex,
            3, 100f, BASIC_SCALE, 0f, 0f
        );
        // ImageLoader diakses berulang kali
    };
}

// ❌ GameRenderer.java mengakses terlalu banyak dari GameScreen
public void render(GameScreen screen, float delta) {
    OrthographicCamera camera = screen.getCamera();
    SpriteBatch spriteBatch = screen.getSpriteBatch();
    ShapeRenderer shapeRenderer = screen.getShapeRenderer();
    Texture backgroundTexture = screen.getBackgroundTexture();
    Texture bgTreeTexture = screen.getBgTreeTexture();
    Texture bgMountainTexture = screen.getBgMountainTexture();
    // ... 10+ getter calls
}
```

---

### 7. Inconsistent Naming

| Kategori | Contoh Inkonsistensi |
|----------|---------------------|
| Textures | `dutchtex` vs `enemyTex` vs `towerTex` |
| Methods | `PlayBtnSound()` vs `playTowerDeploy()` |
| Package | `DutchSlayer` vs `MerahPutihTheLastStand` |
| Variables | `tex` vs `texture` vs `towerTex` |
| Constants | `BASIC_SCALE` vs `scale` (instance var) |

---

### 8. Dead Code & Comments

```java
// ❌ Commented code yang dibiarkan
// Removed titleTexture.dispose(); as it's no longer a Texture
// titleTexture.dispose();

// ❌ Empty methods dengan TODO
public void setChasePrepared() {
}

public void setChaseDelayTimer() {
}
```

---

### 9. Static Fields untuk Non-Constants

`ImageLoader.java` menggunakan static fields untuk textures:

```java
// ❌ Static mutable state
public class ImageLoader {
    public static Texture grassTex;
    public static Texture terratex;
    public static Texture skytex;
    public static Texture dutchtex;
    // ... 50+ static texture fields
}
```

**Masalah:**
- Global mutable state
- Sulit di-test
- Memory leak potential
- Thread safety issues

---

## ⚠️ SOLID Principles Violations

### ❌ Single Responsibility Principle (SRP)

Setiap class seharusnya hanya punya SATU alasan untuk berubah.

| Class | Responsibilities (Seharusnya 1) |
|-------|--------------------------------|
| `GameScreen` | 1. Rendering, 2. Input, 3. Game Logic, 4. Music, 5. VN System, 6. Spawning, 7. Pause System |
| `GameState` | 1. Data Container, 2. Button State, 3. Stage Initialization, 4. Timer Management |
| `Enemy` | 1. AI/Behavior, 2. Rendering, 3. Stats, 4. Animation, 5. Combat, 6. Sound |
| `Tower` | 1. Shooting, 2. Upgrades, 3. Animation, 4. Rendering, 5. Cost Calculation |

**Refactoring Suggestion:**

```
GameScreen (sebelum)
└── GameScreen.java (701 lines, 7 responsibilities)

GameScreen (sesudah)
├── GameScreen.java (~100 lines, koordinasi)
├── GameRenderer.java (rendering)
├── GameInputHandler.java (input)
├── GameMusicController.java (music)
├── VNController.java (visual novel)
├── EnemySpawner.java (spawning)
└── PauseController.java (pause)
```

---

### ❌ Open/Closed Principle (OCP)

Software entities harus **open for extension** tapi **closed for modification**.

```java
// ❌ Harus modify class untuk menambah enemy type baru
private EnemyStats getEnemyStats(EnemyType type) {
    return switch (type) {
        case BASIC -> new EnemyStats(...);
        case SHOOTER -> new EnemyStats(...);
        case BOMBER -> new EnemyStats(...);
        case SHIELD -> new EnemyStats(...);
        case BOSS -> new EnemyStats(...);
        // Menambah FLYING enemy = modify class ini!
    };
}

// ✅ Seharusnya menggunakan strategy/factory pattern
public interface EnemyStatsProvider {
    EnemyStats getStats();
}

public class BasicEnemyStats implements EnemyStatsProvider {
    public EnemyStats getStats() {
        return new EnemyStats(...);
    }
}
// Menambah enemy baru = buat class baru, tidak modify existing
```

---

### ❌ Liskov Substitution Principle (LSP)

Objects of a superclass should be replaceable with objects of subclasses.

**Masalah:** Tidak ada interface/abstraction untuk entities

```java
// ❌ Tidak ada common interface
public class Enemy { ... }      // defend mode
public class BasicEnemy { ... } // attack mode
// Tidak bisa digunakan interchangeably!

// ✅ Seharusnya
public interface GameEntity {
    void update(float delta);
    void render(SpriteBatch batch);
    Rectangle getBounds();
    boolean isAlive();
}

public class DefenseEnemy implements GameEntity { ... }
public class AttackEnemy implements GameEntity { ... }
```

---

### ❌ Interface Segregation Principle (ISP)

Clients tidak boleh dipaksa depend on interfaces yang tidak mereka gunakan.

```java
// ❌ Screen interface memaksa implement semua methods
public class AboutScreen implements Screen {
    @Override public void show() { ... }
    @Override public void render(float delta) { ... }
    @Override public void resize(int w, int h) { ... }
    @Override public void pause() {}   // Empty!
    @Override public void resume() {}  // Empty!
    @Override public void hide() {}    // Empty!
    @Override public void dispose() { ... }
}
```

**Solusi:** Buat abstract base class

```java
public abstract class BaseScreen implements Screen {
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    
    // Subclass hanya override yang diperlukan
}
```

---

### ❌ Dependency Inversion Principle (DIP)

High-level modules tidak boleh depend on low-level modules. Keduanya harus depend on abstractions.

```java
// ❌ Direct dependencies pada concrete classes
public class GameLogic {
    private final GameState gameState;    // Concrete!
    private final UIManager uiManager;    // Concrete!
    
    public GameLogic(GameState gameState, UIManager uiManager) {
        this.gameState = gameState;
        this.uiManager = uiManager;
    }
}

// ✅ Seharusnya depend on interfaces
public interface IGameState {
    int getGold();
    void addGold(int amount);
    Array<Enemy> getEnemies();
    // ...
}

public interface IUIManager {
    void setupWinUI(int stage);
    void setupLoseUI();
}

public class GameLogic {
    private final IGameState gameState;
    private final IUIManager uiManager;
    
    public GameLogic(IGameState gameState, IUIManager uiManager) {
        this.gameState = gameState;
        this.uiManager = uiManager;
    }
}
```

---

## 🚀 Performance Improvements

### 1. Object Pooling

**Masalah:** Membuat object baru setiap frame menyebabkan garbage collection pressure.

```java
// ❌ Current: New objects setiap collision check
private void checkBossCollision(TankBoss boss) {
    Rectangle playerRect = getBounds();
    Rectangle bossRect = new Rectangle(  // NEW object!
        boss.getPosition().x, 
        boss.getPosition().y, 
        boss.getWidth(), 
        boss.getHeight()
    );
    if (playerRect.overlaps(bossRect)) { ... }
}

// ❌ Current: New Vector2 untuk kalkulasi
private float distance = (float) Math.sqrt(
    Math.pow(trapX - targetX, 2) + Math.pow(trapY - targetY, 2)  // Could use Vector2.dst()
);
```

**Solusi:** Implement object pooling

```java
// ✅ Use libGDX Pool
public class BulletPool extends Pool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}

// Usage
private final Pool<Bullet> bulletPool = new BulletPool();

public void fire() {
    Bullet bullet = bulletPool.obtain();
    bullet.init(x, y, direction);
    activeBullets.add(bullet);
}

public void removeBullet(Bullet bullet) {
    activeBullets.removeValue(bullet, true);
    bulletPool.free(bullet);  // Return to pool
}
```

**Objects yang perlu di-pool:**
- `Bullet` / `Projectile`
- `Rectangle` (untuk collision)
- `Vector2` (untuk kalkulasi)
- `Enemy` (untuk wave spawning)

---

### 2. Texture Atlas

**Masalah:** Loading individual textures = banyak draw calls

```java
// ❌ Current: 50+ individual texture loads
public static Texture enemyBasicFrames[0] = loadOrNull("Defend/Enemy/enemyBasic/EnemyB1.png");
public static Texture enemyBasicFrames[1] = loadOrNull("Defend/Enemy/enemyBasic/EnemyB2.png");
public static Texture enemyBasicFrames[2] = loadOrNull("Defend/Enemy/enemyBasic/EnemyB3.png");
public static Texture enemyBasicFrames[3] = loadOrNull("Defend/Enemy/enemyBasic/EnemyB4.png");
// ... repeat untuk setiap entity
```

**Solusi:** Gunakan TextureAtlas

```java
// ✅ Pack semua textures ke atlas
// assets/atlas/game.atlas

// Load sekali
TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/game.atlas"));

// Get regions
Animation<TextureRegion> enemyBasicAnim = new Animation<>(
    0.2f, 
    atlas.findRegions("enemy_basic"), 
    Animation.PlayMode.LOOP
);
```

**Benefits:**
- Reduce draw calls (batch rendering)
- Faster loading
- Less memory fragmentation

---

### 3. Spatial Partitioning

**Masalah:** O(n²) collision detection

```java
// ❌ Current: Check SETIAP projectile vs SETIAP enemy
for (int i = gameState.projectiles.size - 1; i >= 0; i--) {
    Projectile p = gameState.projectiles.get(i);
    for (int j = gameState.enemies.size - 1; j >= 0; j--) {
        Enemy e = gameState.enemies.get(j);
        if (!e.isDestroyed() && p.getBounds().overlaps(e.getBounds())) {
            // Handle collision
        }
    }
}
// 100 projectiles × 50 enemies = 5000 checks per frame!
```

**Solusi:** Grid-based atau QuadTree

```java
// ✅ Grid-based spatial partitioning
public class SpatialGrid {
    private final int cellSize = 64;
    private final Map<Integer, Array<Entity>> cells = new HashMap<>();
    
    public int getCellKey(float x, float y) {
        int cellX = (int)(x / cellSize);
        int cellY = (int)(y / cellSize);
        return cellX + cellY * 1000;
    }
    
    public void insert(Entity entity) {
        int key = getCellKey(entity.getX(), entity.getY());
        cells.computeIfAbsent(key, k -> new Array<>()).add(entity);
    }
    
    public Array<Entity> getNearby(float x, float y) {
        Array<Entity> nearby = new Array<>();
        // Check current cell and 8 neighbors
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int key = getCellKey(x + dx * cellSize, y + dy * cellSize);
                Array<Entity> cell = cells.get(key);
                if (cell != null) nearby.addAll(cell);
            }
        }
        return nearby;
    }
}
```

---

### 4. Batch Rendering Optimization

**Masalah:** Multiple begin/end calls

```java
// ❌ Current: renderGameWorld() di TowerDefenseScreen
game.batch.begin();
// draw main tower
game.batch.end();

shapes.begin(ShapeRenderer.ShapeType.Line);
// draw zones
shapes.end();

game.batch.begin();
// draw traps
game.batch.end();

// ... bergantian terus!
```

**Solusi:** Group semua batch draws bersama

```java
// ✅ Optimized rendering order
public void render() {
    // 1. All SpriteBatch draws together
    batch.begin();
    renderBackground(batch);
    renderEntities(batch);
    renderUI(batch);
    batch.end();
    
    // 2. All ShapeRenderer draws together
    shapes.begin(ShapeRenderer.ShapeType.Line);
    renderDebugShapes(shapes);
    shapes.end();
    
    shapes.begin(ShapeRenderer.ShapeType.Filled);
    renderHealthBars(shapes);
    shapes.end();
}
```

---

### 5. Lazy Loading & Asset Management

**Masalah:** Semua assets loaded di awal

```java
// ❌ Current: Load SEMUA textures di ImageLoader.load()
public static void load() {
    // Load 50+ textures sekaligus
    grassTex = loadOrNull("...");
    terratex = loadOrNull("...");
    // ... semua di-load walau belum tentu dipakai
}
```

**Solusi:** AssetManager dengan lazy loading

```java
// ✅ Use libGDX AssetManager
public class Assets {
    public final AssetManager manager = new AssetManager();
    
    public void loadDefenseAssets() {
        manager.load("atlas/defense.atlas", TextureAtlas.class);
        manager.load("sounds/defense.ogg", Sound.class);
    }
    
    public void loadAttackAssets() {
        manager.load("atlas/attack.atlas", TextureAtlas.class);
        manager.load("sounds/attack.ogg", Sound.class);
    }
    
    public boolean update() {
        return manager.update();  // Returns true when done
    }
    
    public float getProgress() {
        return manager.getProgress();
    }
}
```

---

### 6. Delta Time Caching

**Masalah:** `Gdx.graphics.getDeltaTime()` dipanggil berulang kali

```java
// ✅ Cache delta time sekali per frame
public void render(float delta) {
    // delta sudah di-pass, tidak perlu call getDeltaTime() lagi
    update(delta);
    draw(delta);
}
```

---

## 🎮 Game Mechanics Suggestions

### Defense Mode (Plant vs Zombies Style)

#### High Priority

| Feature | Description | Implementation Complexity |
|---------|-------------|--------------------------|
| **Sun/Resource Generation** | Tower khusus yang generate gold secara pasif (seperti Sunflower) | Medium |
| **Tower Synergy** | Bonus damage/effect ketika tower tertentu ditempatkan berdekatan | Medium |
| **Achievement System** | Unlock tower baru berdasarkan progress/achievements | Medium |
| **Wave Preview** | Tampilkan enemy types yang akan datang di wave berikutnya | Low |

#### Medium Priority

| Feature | Description | Implementation Complexity |
|---------|-------------|--------------------------|
| **Lane System** | Grid-based placement (5 lanes seperti PvZ) | High |
| **Day/Night Cycle** | Enemy types berbeda di malam hari, tower behavior berubah | Medium |
| **Special Abilities** | Cooldown-based skills (freeze all, nuke, heal towers) | Medium |
| **Enemy Variants** | Same enemy type dengan modifier (armored, fast, healing) | Low |
| **Tower Evolution** | Tower bisa evolve ke bentuk berbeda (bukan cuma upgrade stats) | High |

#### Low Priority

| Feature | Description | Implementation Complexity |
|---------|-------------|--------------------------|
| **Endless Mode** | Survival mode dengan leaderboard dan scaling difficulty | Medium |
| **Challenge Modes** | Limited towers, no gold, time limit, etc. | Low |
| **Tower Skins** | Cosmetic variations untuk towers | Low |

---

### Attack Mode (Metal Slug Style)

#### High Priority

| Feature | Description | Implementation Complexity |
|---------|-------------|--------------------------|
| **Vehicle System** | Bisa naik tank/jeep yang punya weapon sendiri | High |
| **Melee Combat** | Knife attack dengan combo system | Medium |
| **More Boss Patterns** | Multiple phases dengan different attack patterns | Medium |
| **Checkpoint System** | Save progress dalam level | Low |

#### Medium Priority

| Feature | Description | Implementation Complexity |
|---------|-------------|--------------------------|
| **POW Rescue** | NPC prisoners yang memberikan powerup/weapons | Medium |
| **Score Multiplier** | Combo kills untuk bonus points | Low |
| **Branching Paths** | Multiple routes dalam level | High |
| **Environmental Hazards** | Exploding barrels, falling objects, etc. | Medium |
| **Mini-bosses** | Mid-level boss encounters | Medium |

#### Low Priority

| Feature | Description | Implementation Complexity |
|---------|-------------|--------------------------|
| **Co-op Mode** | 2 player split screen/online | Very High |
| **Time Attack Mode** | Speedrun dengan leaderboard | Low |
| **Boss Rush Mode** | Fight all bosses consecutively | Low |

---

### General Improvements

| Feature | Description | Benefit |
|---------|-------------|---------|
| **Difficulty Settings** | Easy/Normal/Hard dengan enemy scaling | Accessibility |
| **Tutorial System** | Interactive guide untuk new players | Onboarding |
| **Save/Load System** | Progress persistence (stage unlocked, high scores) | Retention |
| **Upgrade Shop** | Permanent upgrades antar session (more health, starting gold) | Progression |
| **Daily Challenges** | Rotating objectives dengan rewards | Engagement |
| **Statistics Screen** | Total kills, accuracy, play time, etc. | Engagement |
| **Settings Persistence** | Save audio/video settings | QoL |

---

## 📐 Recommended Architecture Refactoring

### 1. Entity Component System (ECS) - Lite Version

Tidak perlu full ECS framework, tapi bisa adopt konsepnya:

```
core/src/main/java/io/DutchSlayer/
├── common/
│   ├── entities/
│   │   ├── Entity.java                 # Base class
│   │   ├── components/
│   │   │   ├── TransformComponent.java # Position, rotation, scale
│   │   │   ├── HealthComponent.java    # HP, damage, death
│   │   │   ├── RenderComponent.java    # Sprite, animation
│   │   │   ├── CollisionComponent.java # Bounds, collision mask
│   │   │   └── AIComponent.java        # Behavior state
│   │   └── systems/
│   │       ├── MovementSystem.java     # Update positions
│   │       ├── CollisionSystem.java    # Detect collisions
│   │       ├── RenderSystem.java       # Draw entities
│   │       └── AISystem.java           # Update behaviors
```

**Contoh Implementation:**

```java
// Entity.java
public class Entity {
    private final Map<Class<?>, Component> components = new HashMap<>();
    
    public <T extends Component> void addComponent(T component) {
        components.put(component.getClass(), component);
    }
    
    public <T extends Component> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }
    
    public <T extends Component> boolean hasComponent(Class<T> type) {
        return components.containsKey(type);
    }
}

// Component interface
public interface Component {}

// TransformComponent.java
public class TransformComponent implements Component {
    public float x, y;
    public float rotation;
    public float scaleX = 1f, scaleY = 1f;
}

// HealthComponent.java
public class HealthComponent implements Component {
    public int currentHealth;
    public int maxHealth;
    public boolean invincible;
    
    public void takeDamage(int amount) {
        if (!invincible) {
            currentHealth = Math.max(0, currentHealth - amount);
        }
    }
    
    public boolean isDead() {
        return currentHealth <= 0;
    }
}
```

---

### 2. Service Layer

Abstraksi untuk cross-cutting concerns:

```
common/
├── services/
│   ├── audio/
│   │   ├── AudioService.java           # Interface
│   │   └── GdxAudioService.java        # libGDX implementation
│   ├── assets/
│   │   ├── AssetService.java           # Interface
│   │   └── GdxAssetService.java        # libGDX implementation
│   ├── input/
│   │   ├── InputService.java           # Interface
│   │   └── GdxInputService.java        # libGDX implementation
│   └── persistence/
│       ├── SaveService.java            # Interface
│       └── PreferencesSaveService.java # libGDX Preferences impl
```

**Contoh Implementation:**

```java
// AudioService.java
public interface AudioService {
    void playSound(String soundId);
    void playSound(String soundId, float volume);
    void playMusic(String musicId);
    void stopMusic();
    void setMusicVolume(float volume);
    void setSfxVolume(float volume);
    void dispose();
}

// GdxAudioService.java
public class GdxAudioService implements AudioService {
    private final Map<String, Sound> sounds = new HashMap<>();
    private final Map<String, Music> musics = new HashMap<>();
    private Music currentMusic;
    private float musicVolume = 0.5f;
    private float sfxVolume = 0.8f;
    
    @Override
    public void playSound(String soundId) {
        Sound sound = sounds.get(soundId);
        if (sound != null) {
            sound.play(sfxVolume);
        }
    }
    
    // ... other implementations
}
```

---

### 3. State Machine untuk Game States

```
common/
├── fsm/
│   ├── State.java                      # Interface
│   ├── StateMachine.java               # State manager
│   └── states/
│       ├── PlayingState.java
│       ├── PausedState.java
│       ├── VictoryState.java
│       ├── DefeatState.java
│       └── CutsceneState.java
```

**Contoh Implementation:**

```java
// State.java
public interface State<T> {
    void enter(T owner);
    void update(T owner, float delta);
    void exit(T owner);
}

// StateMachine.java
public class StateMachine<T> {
    private T owner;
    private State<T> currentState;
    private State<T> previousState;
    
    public StateMachine(T owner) {
        this.owner = owner;
    }
    
    public void changeState(State<T> newState) {
        previousState = currentState;
        if (currentState != null) {
            currentState.exit(owner);
        }
        currentState = newState;
        currentState.enter(owner);
    }
    
    public void update(float delta) {
        if (currentState != null) {
            currentState.update(owner, delta);
        }
    }
    
    public void revertToPreviousState() {
        changeState(previousState);
    }
}

// PlayingState.java
public class PlayingState implements State<GameScreen> {
    @Override
    public void enter(GameScreen screen) {
        screen.resumeMusic();
        Gdx.input.setInputProcessor(screen.getInputHandler());
    }
    
    @Override
    public void update(GameScreen screen, float delta) {
        screen.updateGameLogic(delta);
        screen.renderGame(delta);
    }
    
    @Override
    public void exit(GameScreen screen) {
        // Cleanup if needed
    }
}
```

---

### 4. Factory Pattern untuk Entities

```
common/
├── factories/
│   ├── EnemyFactory.java               # Interface
│   ├── TowerFactory.java               # Interface
│   ├── ProjectileFactory.java          # Interface
│   └── impl/
│       ├── DefenseEnemyFactory.java
│       ├── AttackEnemyFactory.java
│       └── ...
```

**Contoh Implementation:**

```java
// EnemyFactory.java
public interface EnemyFactory {
    Enemy create(String type, float x, float y);
    Enemy create(String type, float x, float y, Map<String, Object> config);
}

// DefenseEnemyFactory.java
public class DefenseEnemyFactory implements EnemyFactory {
    private final AssetService assets;
    private final Pool<Enemy> enemyPool;
    
    public DefenseEnemyFactory(AssetService assets) {
        this.assets = assets;
        this.enemyPool = new EnemyPool();
    }
    
    @Override
    public Enemy create(String type, float x, float y) {
        Enemy enemy = enemyPool.obtain();
        enemy.init(getConfig(type), x, y);
        return enemy;
    }
    
    private EnemyConfig getConfig(String type) {
        return switch (type) {
            case "basic" -> EnemyConfigs.BASIC;
            case "shooter" -> EnemyConfigs.SHOOTER;
            case "bomber" -> EnemyConfigs.BOMBER;
            case "shield" -> EnemyConfigs.SHIELD;
            case "boss" -> EnemyConfigs.BOSS;
            default -> throw new IllegalArgumentException("Unknown enemy: " + type);
        };
    }
}
```

---

### 5. Event System

Untuk loose coupling antara systems:

```
common/
├── events/
│   ├── Event.java                      # Base event
│   ├── EventBus.java                   # Pub/sub manager
│   └── types/
│       ├── EnemyKilledEvent.java
│       ├── TowerPlacedEvent.java
│       ├── WaveCompletedEvent.java
│       ├── PlayerDamagedEvent.java
│       └── ...
```

**Contoh Implementation:**

```java
// EventBus.java
public class EventBus {
    private static final EventBus instance = new EventBus();
    private final Map<Class<?>, Array<EventListener<?>>> listeners = new HashMap<>();
    
    public static EventBus getInstance() {
        return instance;
    }
    
    public <T extends Event> void register(Class<T> eventType, EventListener<T> listener) {
        listeners.computeIfAbsent(eventType, k -> new Array<>()).add(listener);
    }
    
    public <T extends Event> void unregister(Class<T> eventType, EventListener<T> listener) {
        Array<EventListener<?>> list = listeners.get(eventType);
        if (list != null) {
            list.removeValue(listener, true);
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Event> void post(T event) {
        Array<EventListener<?>> list = listeners.get(event.getClass());
        if (list != null) {
            for (EventListener<?> listener : list) {
                ((EventListener<T>) listener).onEvent(event);
            }
        }
    }
}

// Event.java
public abstract class Event {
    public final long timestamp = System.currentTimeMillis();
}

// EnemyKilledEvent.java
public class EnemyKilledEvent extends Event {
    public final String enemyType;
    public final int goldReward;
    public final float x, y;
    
    public EnemyKilledEvent(String enemyType, int goldReward, float x, float y) {
        this.enemyType = enemyType;
        this.goldReward = goldReward;
        this.x = x;
        this.y = y;
    }
}

// Usage
EventBus.getInstance().register(EnemyKilledEvent.class, event -> {
    gameState.addGold(event.goldReward);
    spawnGoldPopup(event.x, event.y, event.goldReward);
    playSound("enemy_death");
});
```

---

### Proposed Package Structure (Final)

```
core/src/main/java/io/DutchSlayer/
├── common/
│   ├── entities/
│   │   ├── Entity.java
│   │   ├── components/
│   │   └── systems/
│   ├── services/
│   │   ├── audio/
│   │   ├── assets/
│   │   ├── input/
│   │   └── persistence/
│   ├── fsm/
│   │   ├── State.java
│   │   └── StateMachine.java
│   ├── factories/
│   ├── events/
│   ├── config/
│   │   ├── GameConfig.java
│   │   ├── EnemyConfigs.java
│   │   └── TowerConfigs.java
│   ├── screens/
│   │   ├── BaseScreen.java
│   │   ├── MainMenuScreen.java
│   │   └── ...
│   └── utils/
│       ├── Constants.java
│       ├── ObjectPool.java
│       └── SpatialGrid.java
├── attack/
│   ├── entities/
│   │   ├── Player.java
│   │   ├── AttackEnemy.java
│   │   └── Boss.java
│   ├── screens/
│   │   └── AttackGameScreen.java
│   └── systems/
│       ├── AttackCombatSystem.java
│       └── AttackCameraSystem.java
├── defend/
│   ├── entities/
│   │   ├── Tower.java
│   │   ├── DefenseEnemy.java
│   │   └── Trap.java
│   ├── screens/
│   │   └── DefenseGameScreen.java
│   └── systems/
│       ├── WaveSystem.java
│       └── TowerTargetingSystem.java
└── Main.java
```

---

## 🎯 Quick Wins

Perubahan yang bisa langsung diterapkan dengan effort minimal:

### 1. Extract Constants

```java
// ✅ Buat file GameConfig.java
public final class GameConfig {
    private GameConfig() {} // Prevent instantiation
    
    // Timing
    public static final float WALL_RISE_SPEED = 350f;
    public static final float VICTORY_DELAY = 2.0f;
    public static final float BUTTON_PRESS_DURATION = 0.15f;
    
    // Combat
    public static final float KNOCKBACK_DURATION = 0.5f;
    public static final float KNOCKBACK_SPEED = 200f;
    public static final float ATTACK_COOLDOWN = 1f;
    
    // Scaling
    public static final float ENEMY_BASIC_SCALE = 0.2f;
    public static final float ENEMY_SHOOTER_SCALE = 0.18f;
    public static final float ENEMY_BOMBER_SCALE = 0.2f;
    public static final float ENEMY_SHIELD_SCALE = 0.25f;
    public static final float ENEMY_BOSS_SCALE = 0.4f;
}
```

### 2. Encapsulate GameState

```java
// ✅ Buat field private dengan getters/setters
public class GameState {
    // Private fields
    private int gold = 80;
    private int currentWave = 1;
    private boolean gameOver = false;
    
    // Getters
    public int getGold() { return gold; }
    public int getCurrentWave() { return currentWave; }
    public boolean isGameOver() { return gameOver; }
    
    // Controlled setters/modifiers
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
    
    public void nextWave() {
        currentWave++;
        // Reset wave-specific state
    }
}
```

### 3. Create Base Screen Class

```java
// ✅ Abstract base untuk semua screens
public abstract class BaseScreen implements Screen {
    protected final Main game;
    protected final Viewport viewport;
    protected final SpriteBatch batch;
    
    protected BaseScreen(Main game) {
        this.game = game;
        this.batch = game.batch;
        this.viewport = createViewport();
    }
    
    protected Viewport createViewport() {
        return new FitViewport(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
    }
    
    protected void clearScreen(float r, float g, float b) {
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
```

### 4. Create Entity Interfaces

```java
// ✅ Common interfaces
public interface Updatable {
    void update(float delta);
}

public interface Renderable {
    void render(SpriteBatch batch);
    void render(ShapeRenderer shapes);
}

public interface Collidable {
    Rectangle getBounds();
    boolean overlaps(Collidable other);
}

public interface Disposable {
    void dispose();
}

// Combined interface for game entities
public interface GameEntity extends Updatable, Renderable, Collidable {
    float getX();
    float getY();
    boolean isAlive();
    void destroy();
}
```

### 5. Use Builder Pattern for Complex Objects

```java
// ✅ Builder untuk Tower
public class Tower {
    private Tower(Builder builder) {
        this.type = builder.type;
        this.x = builder.x;
        this.y = builder.y;
        this.health = builder.health;
        this.damage = builder.damage;
        // ...
    }
    
    public static class Builder {
        private TowerType type;
        private float x, y;
        private int health = 5;
        private int damage = 1;
        private float fireRate = 1f;
        private boolean canShoot = true;
        
        public Builder type(TowerType type) {
            this.type = type;
            return this;
        }
        
        public Builder position(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }
        
        public Builder health(int health) {
            this.health = health;
            return this;
        }
        
        public Tower build() {
            validate();
            return new Tower(this);
        }
        
        private void validate() {
            if (type == null) throw new IllegalStateException("Type required");
        }
    }
}

// Usage
Tower tower = new Tower.Builder()
    .type(TowerType.AOE)
    .position(100, 200)
    .health(10)
    .build();
```

---

## 📅 Implementation Roadmap

### Phase 1: Foundation (Week 1-2)
- [ ] Extract constants ke `GameConfig.java`
- [ ] Create `BaseScreen` abstract class
- [ ] Encapsulate `GameState` fields
- [ ] Create basic interfaces (`Updatable`, `Renderable`, `Collidable`)
- [ ] Fix naming inconsistencies
- [ ] Remove dead code dan commented code

### Phase 2: Core Refactoring (Week 3-4)
- [ ] Split `GameScreen` menjadi komponen lebih kecil
- [ ] Split `TowerDefenseScreen` menjadi komponen lebih kecil
- [ ] Implement `AudioService` interface
- [ ] Implement `AssetService` interface
- [ ] Create `EnemyFactory` dan `TowerFactory`

### Phase 3: Performance (Week 5-6)
- [ ] Implement Object Pooling untuk Bullets/Projectiles
- [ ] Create TextureAtlas (pack semua textures)
- [ ] Optimize rendering (reduce begin/end calls)
- [ ] Implement basic spatial partitioning untuk collision

### Phase 4: Architecture (Week 7-8)
- [ ] Implement Event Bus untuk loose coupling
- [ ] Create State Machine untuk game states
- [ ] Move to component-based entity system
- [ ] Implement proper Dependency Injection

### Phase 5: Features (Week 9+)
- [ ] Add new game mechanics (lihat suggestions)
- [ ] Implement save/load system
- [ ] Add achievements
- [ ] Polish dan bug fixing

---

## 📚 References

### libGDX Documentation
- [libGDX Wiki](https://libgdx.com/wiki/)
- [libGDX API Docs](https://libgdx.badlogicgames.com/ci/nightlies/docs/api/)

### Design Patterns
- [Game Programming Patterns](https://gameprogrammingpatterns.com/)
- [Refactoring Guru](https://refactoring.guru/)

### Performance
- [libGDX Performance Tips](https://libgdx.com/wiki/articles/profiling)
- [Object Pooling in libGDX](https://libgdx.com/wiki/articles/memory-management)

---

> **Note:** Document ini akan di-update seiring progress refactoring. Setiap perubahan major harus didokumentasikan di sini.
