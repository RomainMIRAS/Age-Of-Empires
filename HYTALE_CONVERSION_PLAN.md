# 🎮 Age of Empires - Plan de Conversion Hytale

## 📋 Vue d'ensemble

Ce document détaille le plan de conversion du plugin Minecraft "Age of Empires" en mod Hytale moderne, propre et bien structuré.

---

## 🏛️ Architecture Proposée

### Structure du Projet

```
hytale-age-of-empires/
├── src/main/
│   ├── java/
│   │   └── com/ageofempires/
│   │       ├── AgeOfEmpiresPlugin.java          # Point d'entrée principal
│   │       │
│   │       ├── core/                             # 🔧 Core Systems
│   │       │   ├── game/
│   │       │   │   ├── GameManager.java          # Orchestrateur du jeu
│   │       │   │   ├── GameState.java            # État du jeu (enum)
│   │       │   │   ├── GameSession.java          # Session de jeu active
│   │       │   │   └── GameConfig.java           # Configuration de jeu
│   │       │   │
│   │       │   ├── team/
│   │       │   │   ├── TeamManager.java          # Gestion des équipes
│   │       │   │   ├── Team.java                 # Classe Team (enum)
│   │       │   │   ├── TeamData.java             # Données d'équipe (ressources, etc.)
│   │       │   │   └── TeamPlayer.java           # Joueur dans une équipe
│   │       │   │
│   │       │   └── arena/
│   │       │       ├── ArenaManager.java         # Gestion des arènes
│   │       │       ├── Arena.java                # Classe Arena
│   │       │       └── ArenaConfig.java          # Config d'arène
│   │       │
│   │       ├── gameplay/                         # 🎯 Gameplay Features
│   │       │   ├── resources/
│   │       │   │   ├── ResourceManager.java      # Gestion ressources
│   │       │   │   ├── ResourceType.java         # Types (WOOD, STONE, GOLD)
│   │       │   │   └── ResourceNode.java         # Point de collecte
│   │       │   │
│   │       │   ├── buildings/
│   │       │   │   ├── BuildingManager.java      # Gestion bâtiments
│   │       │   │   ├── Building.java             # Classe Building
│   │       │   │   ├── BuildingType.java         # Types de bâtiments
│   │       │   │   ├── BuildingPlot.java         # Parcelle de construction
│   │       │   │   └── PlotSize.java             # Tailles (SMALL, MEDIUM, LARGE)
│   │       │   │
│   │       │   ├── ages/
│   │       │   │   ├── AgeSystem.java            # Système d'âges
│   │       │   │   ├── Age.java                  # Âge (enum 1-4)
│   │       │   │   └── AgeRequirement.java       # Coûts/conditions
│   │       │   │
│   │       │   ├── kits/
│   │       │   │   ├── KitManager.java           # Gestion des kits
│   │       │   │   ├── Kit.java                  # Classe Kit
│   │       │   │   └── KitType.java              # Types de kits
│   │       │   │
│   │       │   └── voting/
│   │       │       ├── VoteManager.java          # Système de vote
│   │       │       ├── Vote.java                 # Vote instance
│   │       │       └── VoteType.java             # Types de votes
│   │       │
│   │       ├── commands/                         # 🎮 Commands
│   │       │   ├── AoeCommand.java               # Commande principale /aoe
│   │       │   ├── setup/
│   │       │   │   ├── SetupCommand.java         # /aoe setup
│   │       │   │   ├── SetLobbyCommand.java      # Sous-commande
│   │       │   │   ├── AddMapCommand.java        
│   │       │   │   ├── SetSpawnCommand.java      
│   │       │   │   └── SetForumCommand.java      
│   │       │   │
│   │       │   ├── game/
│   │       │   │   ├── LeaveCommand.java         # /leave
│   │       │   │   ├── KitCommand.java           # /kit
│   │       │   │   ├── StuckCommand.java         # /stuck
│   │       │   │   └── HelpCommand.java          # /help
│   │       │   │
│   │       │   └── admin/
│   │       │       ├── StartCommand.java         # /forcestart
│   │       │       └── DebugCommand.java         # Debug tools
│   │       │
│   │       ├── events/                           # 🎪 Event Handlers
│   │       │   ├── player/
│   │       │   │   ├── PlayerConnectionHandler.java
│   │       │   │   ├── PlayerMovementHandler.java
│   │       │   │   ├── PlayerCombatHandler.java
│   │       │   │   └── PlayerRespawnHandler.java
│   │       │   │
│   │       │   ├── world/
│   │       │   │   ├── BlockBreakHandler.java
│   │       │   │   ├── BlockPlaceHandler.java
│   │       │   │   └── ResourceSpawnHandler.java
│   │       │   │
│   │       │   └── game/
│   │       │       ├── GameStartHandler.java
│   │       │       ├── GameEndHandler.java
│   │       │       └── TeamDeathHandler.java
│   │       │
│   │       ├── ui/                               # 🎨 User Interface
│   │       │   ├── scoreboard/
│   │       │   │   ├── GameScoreboard.java       # Scoreboard personnalisé
│   │       │   │   └── ScoreboardRenderer.java
│   │       │   │
│   │       │   ├── hud/
│   │       │   │   ├── ResourceHud.java          # Affichage ressources
│   │       │   │   └── TeamHud.java              # Info équipe
│   │       │   │
│   │       │   └── menus/
│   │       │       ├── ShopMenu.java             # Menu d'achat
│   │       │       ├── TeamSelectorMenu.java     # Sélection d'équipe
│   │       │       └── VoteMenu.java             # Interface de vote
│   │       │
│   │       ├── data/                             # 💾 Data Management
│   │       │   ├── player/
│   │       │   │   ├── PlayerDataManager.java    # Gestion données joueurs
│   │       │   │   ├── PlayerStats.java          # Stats (kills, deaths, etc.)
│   │       │   │   └── PlayerSession.java        # Session en cours
│   │       │   │
│   │       │   ├── persistence/
│   │       │   │   ├── DataStore.java            # Interface de stockage
│   │       │   │   ├── JsonDataStore.java        # Stockage JSON
│   │       │   │   └── DatabaseStore.java        # Stockage SQL (optionnel)
│   │       │   │
│   │       │   └── config/
│   │       │       ├── ConfigManager.java        # Gestion configs
│   │       │       ├── MessagesConfig.java       # Messages
│   │       │       └── SettingsConfig.java       # Settings
│   │       │
│   │       └── utils/                            # 🛠️ Utilities
│   │           ├── Location.java                 # Wrapper Transform
│   │           ├── MessageUtil.java              # Formatage messages
│   │           ├── TimeUtil.java                 # Gestion temps
│   │           ├── PermissionUtil.java           # Permissions
│   │           └── ColorUtil.java                # Couleurs
│   │
│   └── resources/
│       ├── manifest.json                         # Manifest Hytale
│       ├── config/
│       │   ├── settings.json                     # Config principale
│       │   ├── messages.json                     # Messages localisés
│       │   └── buildings.json                    # Définitions bâtiments
│       │
│       └── assets/                               # Assets du jeu
│           └── structures/                       # Structures de bâtiments
│
├── build.gradle                                  # Configuration Gradle
└── README.md                                     # Documentation

```

---

## 🔄 Mapping des Concepts

### 1. Gestion des États

**Avant (Minecraft - Problématique):**
```java
// Variables statiques globales éparpillées
public static Status STATUS = Status.LOBBY;
public static boolean pvp = false, assault = false;
public static String choosenMap = "";
```

**Après (Hytale - Moderne):**
```java
public class GameSession {
    private GameState state;
    private Arena arena;
    private final Map<Team, TeamData> teams;
    private final List<PlayerSession> players;
    private GameConfig config;
    
    // Gestion d'état encapsulée et thread-safe
}
```

### 2. Gestion des Joueurs

**Avant:**
```java
public static ArrayList<Player> bluePlayers = new ArrayList<>();
public static ArrayList<Player> greenPlayers = new ArrayList<>();
// ... variables statiques pour chaque équipe
```

**Après:**
```java
public class TeamManager {
    private final Map<Team, List<PlayerRef>> teamPlayers;
    
    public void addPlayer(Team team, PlayerRef player) {
        // Logique encapsulée
    }
}
```

### 3. Gestion des Ressources

**Avant:**
```java
public static double blue_wood = 100, green_wood = 100, ...;
public static double blue_stone = 100, green_stone = 100, ...;
```

**Après:**
```java
public class TeamData {
    private final Map<ResourceType, Double> resources;
    
    public void addResource(ResourceType type, double amount) {
        resources.merge(type, amount, Double::sum);
    }
    
    public double getResource(ResourceType type) {
        return resources.getOrDefault(type, 0.0);
    }
}
```

### 4. Event Handlers

**Avant:**
```java
@EventHandler
public void onPlayerJoin(PlayerJoinEvent event) {
    Player p = event.getPlayer();
    // Logique dans listener
}
```

**Après:**
```java
public class PlayerConnectionHandler {
    private final GameManager gameManager;
    
    public void register(EventRegistry events) {
        events.register(PlayerConnectEvent.class, this::onPlayerConnect);
    }
    
    private void onPlayerConnect(PlayerConnectEvent event) {
        PlayerRef player = event.getPlayerRef();
        gameManager.handlePlayerJoin(player);
    }
}
```

### 5. Commandes

**Avant:**
```java
public class Setup implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Parsing manuel des arguments
    }
}
```

**Après:**
```java
public class SetupCommand extends CommandBase {
    public SetupCommand() {
        super("setup", "aoe.commands.setup.desc");
        requirePermission("aoe.admin.setup");
    }
    
    @Override
    protected void executeSync(@Nonnull CommandContext context) {
        // Gestion moderne avec contexte
    }
}
```

---

## 🎯 Priorités de Développement

### Phase 1 : Infrastructure Core ✅
- [x] Analyser le code existant
- [x] Mapper les concepts Minecraft → Hytale
- [x] Récupérer la documentation Hytale
- [ ] Créer la structure du projet
- [ ] Implémenter les classes de base (Plugin, Managers)
- [ ] Système de configuration (JSON)

### Phase 2 : Systèmes de Jeu 🎮
- [ ] GameManager et états de jeu
- [ ] TeamManager et gestion d'équipes
- [ ] ResourceManager et système de ressources
- [ ] ArenaManager et arènes

### Phase 3 : Gameplay Features 🎪
- [ ] BuildingManager et système de construction
- [ ] AgeSystem et progression
- [ ] KitManager et équipements
- [ ] VoteManager et votes d'équipe

### Phase 4 : Event Handlers 🎭
- [ ] Handlers de connexion/déconnexion
- [ ] Handlers de combat
- [ ] Handlers de construction
- [ ] Handlers de ressources

### Phase 5 : UI & Commands 🎨
- [ ] Commandes de setup
- [ ] Commandes de jeu
- [ ] Scoreboard personnalisé
- [ ] Menus d'interface

### Phase 6 : Data & Persistence 💾
- [ ] Système de stats
- [ ] Sauvegarde/chargement
- [ ] Support base de données (optionnel)

### Phase 7 : Polish & Testing 🎉
- [ ] Tests unitaires
- [ ] Tests d'intégration
- [ ] Documentation utilisateur
- [ ] Optimisations

---

## 📊 Améliorations par rapport à l'Original

### 🚀 Performance
- ✅ Suppression des variables statiques globales
- ✅ Gestion mémoire optimisée (pas de fuites)
- ✅ Utilisation du TaskRegistry pour les tâches asynchrones
- ✅ Cache intelligent pour les calculs répétitifs

### 🏗️ Architecture
- ✅ Séparation des responsabilités (SRP)
- ✅ Injection de dépendances
- ✅ Gestion d'état encapsulée
- ✅ Architecture modulaire

### 🛡️ Robustesse
- ✅ Gestion d'erreurs appropriée
- ✅ Thread-safety
- ✅ Validation des données
- ✅ Logs structurés

### 🎨 Maintenabilité
- ✅ Code documenté (Javadoc)
- ✅ Nommage clair et cohérent
- ✅ Pas de "magic numbers"
- ✅ Configuration externalisée

### 🔧 Fonctionnalités Modernes
- ✅ Hot-reload support (Hytale natif)
- ✅ Configuration JSON avec schéma
- ✅ Events système moderne
- ✅ Commands avec auto-completion
- ✅ Permissions granulaires

---

## 🔑 Concepts Clés Hytale

### 1. Entity Component System (ECS)
Hytale utilise un ECS moderne pour gérer les entités. Au lieu d'hériter, on compose :

```java
// Accès au composant Player
Ref<EntityStore> ref = playerRef.getReference();
if (ref != null) {
    Store<EntityStore> store = ref.getStore();
    Player player = store.getComponent(ref, Player.getComponentType());
}
```

### 2. Event System
Enregistrement fonctionnel au lieu d'annotations :

```java
events.register(PlayerConnectEvent.class, event -> {
    PlayerRef player = event.getPlayerRef();
    // Logique
});
```

### 3. Configuration via Codec
Système de sérialisation/désérialisation type-safe :

```java
public static final BuilderCodec<MyConfig> CODEC =
    BuilderCodec.builder(MyConfig.class, MyConfig::new)
        .append(new KeyedCodec<>("Enabled", Codec.BOOLEAN),
            (c, v) -> c.enabled = v, c -> c.enabled)
        .build();
```

### 4. Task Management
Gestion automatique des tâches asynchrones :

```java
CompletableFuture<Void> task = CompletableFuture.runAsync(() -> {
    // Tâche longue
});
getTaskRegistry().registerTask(task); // Auto-cancel on shutdown
```

---

## 📝 Notes Importantes

### ⚠️ Différences Critiques Minecraft ↔ Hytale

1. **Pas de NMS** : Hytale expose une API stable, pas besoin de version-specific code
2. **Server-First** : Tout s'exécute côté serveur, clients reçoivent les updates
3. **ECS** : Architecture composants au lieu d'héritage
4. **Assets** : Pas de `.schematic`, utiliser le système d'assets Hytale
5. **Java 21+** : Utiliser les features modernes (records, pattern matching, etc.)

### 🎯 Objectifs de Qualité

- ✅ **Clean Code** : Lisible, maintenable, bien structuré
- ✅ **Performance** : Optimisé pour multi-joueurs
- ✅ **Robustesse** : Gestion d'erreurs, validation
- ✅ **Documentation** : Code et utilisateur
- ✅ **Testable** : Architecture permettant les tests

---

## 🚀 Prochaines Étapes

1. ✅ Créer la structure de base du projet
2. Implémenter le plugin principal avec manifest
3. Créer les managers core (Game, Team, Arena)
4. Implémenter les systèmes de ressources et construction
5. Développer les event handlers
6. Créer les commandes
7. Tester et itérer

---

**Date de création**: 2026-01-16  
**Version du plan**: 1.0  
**Statut**: En développement
