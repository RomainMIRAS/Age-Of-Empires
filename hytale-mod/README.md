# 🏛️ Age of Empires - Hytale Edition

> A complete rewrite of the classic Age of Empires Minecraft minigame for Hytale, featuring modern architecture, clean code, and enhanced gameplay.

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Hytale](https://img.shields.io/badge/Hytale-1.0.0+-green.svg)](https://hytale.com)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)

---

## 📖 About

Age of Empires is a team-based PvP minigame where players:
- 🪓 Collect resources (wood, stone, gold)
- 🏗️ Build structures on plots
- ⚔️ Battle enemy teams
- 🏰 Destroy the enemy Forum to win

Originally created by [andrei1058](https://github.com/andrei1058) for Minecraft, this is a modern, clean rewrite for Hytale with:
- ✅ **Clean Architecture** - Proper separation of concerns
- ✅ **Modern Java** - Java 21+ features
- ✅ **Performance** - Optimized for multiplayer
- ✅ **Maintainability** - Well-documented, testable code
- ✅ **Hytale-Native** - Built for Hytale's server-first architecture

---

## 🎮 Features

### Core Gameplay
- **4 Teams**: Blue, Red, Green, Yellow
- **Resource System**: Gather wood, stone, and gold
- **Building System**: Construct buildings on plots (Small, Medium, Large)
- **Age Progression**: Advance through 4 ages to unlock better items
- **Team Voting**: Vote as a team on building purchases
- **Kit System**: Choose from various equipment kits
- **Combat**: PvP with assault and defense mechanics

### Technical Features
- **Hot-Reload Support**: Update without server restart
- **Event-Driven**: Modern event handling system
- **Configurable**: JSON-based configuration
- **Permissions**: Granular permission system
- **Multi-Arena**: Support for multiple game arenas
- **Stats Tracking**: Player statistics and leaderboards

---

## 🚀 Installation

### Prerequisites
- Hytale Server 1.0.0 or higher
- Java 21 or higher

### Steps
1. Download the latest `AgeOfEmpires-Hytale-X.X.X.jar` from [Releases](../../releases)
2. Place the JAR file in your server's `mods/` directory
3. Start/restart your Hytale server
4. Configure the plugin in `mods/AgeOfEmpires/config/`

---

## ⚙️ Configuration

Configuration files are located in `mods/AgeOfEmpires/config/`:

### `settings.json`
```json
{
  "game": {
    "min_players": 2,
    "max_players": 16,
    "max_players_per_team": 4
  },
  "countdowns": {
    "lobby": 40,
    "pregame": 10
  }
}
```

### `messages.json`
Customize all game messages

### `buildings.json`
Define buildings, costs, and requirements

---

## 🎯 Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/aoe` | Main command | - |
| `/leave` | Leave the current game | `aoe.command.leave` |
| `/kit <name>` | Choose a kit | `aoe.command.kit` |
| `/stuck` | Teleport to spawn if stuck | `aoe.command.stuck` |
| `/help` | Toggle help/guide | - |
| `/forcestart` | Force start the game | `aoe.command.forcestart` |

### Setup Commands
| Command | Description | Permission |
|---------|-------------|------------|
| `/aoe setup` | Enter setup mode | `aoe.setup` |
| `/aoe addmap <name>` | Create a new arena | `aoe.setup` |
| `/aoe setspawn <team>` | Set team spawn | `aoe.setup` |
| `/aoe setforum <team>` | Set team forum location | `aoe.setup` |

---

## 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `aoe.admin` | Full admin access | OP |
| `aoe.setup` | Arena setup commands | OP |
| `aoe.play` | Play the game | Everyone |
| `aoe.vip` | VIP features (join full games) | False |
| `aoe.command.*` | All game commands | Everyone |

---

## 🏗️ Building from Source

```bash
# Clone the repository
git clone https://github.com/yourrepo/hytale-age-of-empires.git
cd hytale-age-of-empires/hytale-mod

# Place HytaleServer.jar in libs/
# (Download from Hytale official sources)

# Build with Gradle
./gradlew build

# The JAR will be in build/libs/
```

---

## 🛠️ Development

### Project Structure
```
hytale-mod/
├── src/main/java/com/ageofempires/
│   ├── core/           # Core game systems
│   ├── gameplay/       # Gameplay features
│   ├── commands/       # Command implementations
│   ├── events/         # Event handlers
│   ├── data/           # Data management
│   └── utils/          # Utilities
└── src/main/resources/
    ├── manifest.json   # Plugin manifest
    └── config/         # Configuration files
```

### Architecture Highlights
- **Managers**: GameManager, TeamManager, ArenaManager, etc.
- **Sessions**: GameSession, PlayerSession for state management
- **Events**: Clean event handlers with functional registration
- **Config**: JSON-based configuration with hot-reload support

---

## 📝 Roadmap

### Version 2.0 (Current)
- [x] Core game infrastructure
- [x] Team and resource management
- [x] Event system
- [ ] Building system
- [ ] Combat mechanics
- [ ] UI/HUD implementation

### Version 2.1
- [ ] Stats and leaderboards
- [ ] Database integration
- [ ] Advanced voting system
- [ ] Kit customization

### Version 2.2
- [ ] Custom events API
- [ ] Add-on support
- [ ] Web dashboard

---

## 🤝 Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Credits

- **Original Plugin**: [andrei1058](https://github.com/andrei1058)
- **Hytale Conversion**: RomainMiras
- **Hytale**: [Hypixel Studios](https://hytale.com)

---

## 📞 Support

- **Issues**: [GitHub Issues](../../issues)
- **Discord**: [Join our Discord](#)
- **Documentation**: [Wiki](../../wiki)

---

## 🌟 Show Your Support

If you like this project, please give it a ⭐ on GitHub!

---

**Made with ❤️ for the Hytale community**
