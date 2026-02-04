# Plants vs Zombies Clone - UML Design Document

## 1. Purpose and Scope
This document provides a comprehensive UML-oriented design overview of the Plants vs Zombies clone. It focuses on how the core gameplay, UI flow, persistence, and networking are structured in the current implementation.

Scope includes:
- Core gameplay entities (Plants, Zombies, Bullets, Sun).
- Map/grid and entity lifecycle management.
- UI controllers and application flow.
- Save/load serialization.
- Client/server synchronization helpers.

## 2. High-Level Architecture
The project is organized around four primary subsystems:

1. **Gameplay Domain**
   - Plants, zombies, bullets, sun tokens, and their behaviors.
   - Game map grid for placement and combat interactions.

2. **UI & Interaction**
   - JavaFX controllers for menus, card selection, map interaction, and pause flow.
   - Game loop timing and user input handling.

3. **Persistence**
   - Serialization for saving and loading game state.

4. **Networking (Optional)**
   - Client/server helpers for synchronized randomization and win/lose signaling.

## 3. Class Diagram (Core Gameplay)
```mermaid
classDiagram
    class GameMap {
        -ROWS: int
        -COLS: int
        -grid: Plant[][]
        -graved: boolean[][]
        -foged: boolean[][]
        +getInstance() GameMap
        +isCellEmpty(row,col) boolean
        +addPlant(plant,row,col)
        +removePlant(row,col)
        +checkWar()
    }

    class Plant {
        <<abstract>>
        -cost: int
        -hp: int
        -row: int
        -col: int
        -isDead: boolean
        +takeDamage()
        +die()
        +updateImageSituation(pane)
    }

    class Zombie {
        <<abstract>>
        -hp: int
        -speed: double
        -row: int
        -isDead: boolean
        -isEating: boolean
        +update(deltaTime)
        +takeDamage()
        +die()
        +startBiting(Plant)
        +playWalkingAnimation(pane)
    }

    class Bullet {
        <<abstract>>
        -speed: double
        -row: int
        -isAlive: boolean
        +move(deltaTime)
        +die()
        +onHit(Zombie)
    }

    class Sun {
        -x: double
        -y: double
        -clicked: boolean
        +addSun()
        +removeSun()
    }

    class ZombieFactory {
        +createRegularZombie(row,x)
        +createConeHeadZombie(row,x)
        +createZombie(zombie,row)
    }

    GameMap "1" --> "many" Plant
    GameMap "1" --> "many" Zombie
    ZombieFactory "1" --> "many" Zombie
    Plant <|-- PeaShooter
    Plant <|-- SunFlower
    Plant <|-- WallNut
    Plant <|-- SnowPea
    Zombie <|-- Regular
    Zombie <|-- ConeHead
    Zombie <|-- ScreenDoorZombie
    Zombie <|-- IMPZombie
    Bullet <|-- NormalBullet
    Bullet <|-- SnowBullet
    Bullet <|-- ShroomBullet
```

### Explanation
- **GameMap** is a singleton responsible for grid placement and combat checks between plants and zombies.
- **Plant** is an abstract base class containing shared stats (cost, HP) and lifecycle methods.
- **Zombie** is an abstract base class for movement, eating, and state effects (slowed, frozen, hypno, etc.).
- **Bullet** models projectile movement and hit behaviors.
- **Sun** represents collectible currency increments.
- **ZombieFactory** encapsulates zombie creation and registration.

## 4. UI & Control Flow Diagram
```mermaid
classDiagram
    class GameMain {
        +start(primaryStage)
        +setFadeTransition(root)
    }

    class MapController {
        +initialize()
        +mouseEvents()
        +checkChosenCard(Plant)
        +pause()
    }

    class ChooseCardController
    class MainMenuController
    class PauseGameController

    GameMain --> MapController
    GameMain --> MainMenuController
    MapController --> ChooseCardController
    MapController --> PauseGameController
```

### Explanation
- **GameMain** boots the JavaFX application and loads the main menu scene.
- **MapController** orchestrates gameplay, card selection, event handling, and the main game loop.
- Other controllers handle menu navigation, card selection UI, and pause/win/lose states.

## 5. Sequence Diagram: Placing a Plant
```mermaid
sequenceDiagram
    actor Player
    participant MapController
    participant GameMap
    participant Plant

    Player->>MapController: Click card
    MapController->>MapController: checkChosenCard()
    Player->>MapController: Click grid cell
    MapController->>GameMap: isCellEmpty(row,col)
    GameMap-->>MapController: true
    MapController->>GameMap: addPlant(plant,row,col)
    MapController->>Plant: setRow()/setCol()/setX()/setY()
```

## 6. Sequence Diagram: Zombie Attack Loop
```mermaid
sequenceDiagram
    participant MapController
    participant GameMap
    participant Zombie
    participant Plant

    MapController->>GameMap: checkWar()
    loop each Zombie
        GameMap->>Zombie: distance check
        Zombie->>Zombie: stopWalking()
        Zombie->>Zombie: startBiting(Plant)
        Zombie->>Plant: bite()/takeDamage()
        Plant-->>Zombie: HP reduces or die()
    end
```

## 7. Activity Diagram: Main Game Loop
```mermaid
flowchart TD
    A[Start Game Loop] --> B[Update Progress Bar]
    B --> C{Win/Lose?}
    C -- Yes --> D[Pause + End Game]
    C -- No --> E[Update Cursor/Fog]
    E --> F[Spawn Sun (day mode)]
    F --> G[checkWar()]
    G --> H[Handle Zombie Waves]
    H --> I[Sync Win/Lose State]
    I --> A
```

## 8. State Diagram: Zombie Lifecycle
```mermaid
stateDiagram-v2
    [*] --> Walking
    Walking --> Eating: encounters plant
    Eating --> Walking: plant destroyed
    Walking --> Slowed: hit by ice
    Slowed --> Walking: slow timer ends
    Walking --> Freezed: hit by freeze
    Freezed --> Walking: freeze timer ends
    Walking --> Dead: HP <= 0
    Eating --> Dead: HP <= 0
```

## 9. Persistence (Save/Load) Diagram
```mermaid
classDiagram
    class SaveGame {
        +saveGame()
        +loadGame()
        +identifyKindsOfPlant(Plant)
        +identifyKindsOfBullet(Bullet)
    }

    class SaveData {
        -mode: Mode
        -score: int
        -time: long
        -zombies: ArrayList~Zombie~
        -plants: ArrayList~Plant~
        -suns: ArrayList~Sun~
        -bullets: ArrayList~Bullet~
    }

    SaveGame --> SaveData
```

### Explanation
- **SaveData** is a serializable snapshot of the core game state.
- **SaveGame** constructs SaveData and reconstructs entity lists on load.

## 10. Networking (Client/Server) Diagram
```mermaid
sequenceDiagram
    participant Client
    participant Server
    participant GameMain

    Client->>Server: Connect socket
    Server->>GameMain: Launch server-side game
    Client->>GameMain: Launch client-side game
    Server-->>Client: rand: <value>
    Client-->>Server: win/lose signals
```

### Explanation
- Server and client exchange win/lose signals and seed-like random values for consistent gameplay events.

## 11. Design Notes & Key Patterns
- **Singleton**: GameMap is instantiated once via `getInstance()`.
- **Factory**: ZombieFactory centralizes zombie creation and registration.
- **Inheritance**: Abstract base classes Plant, Zombie, Bullet define shared behavior and are specialized by concrete entities.
- **Serialization**: SaveData captures runtime state for persistence.

## 12. Future Extension Points
- Add more plant/zombie subclasses with unique skills.
- Extend SaveData to include additional UI/animation state if needed.
- Introduce AI or strategy modules for advanced waves.
- Extract interfaces for testability in MapController.

## 13. Glossary
- **HP**: Hit points of plants/zombies.
- **Wave**: A timed zombie attack batch.
- **Fog**: Night mode visibility obstruction.
- **Hypno**: Zombie converted to attack other zombies.
