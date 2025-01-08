### Age Of Empire

<p align="center">
    <b>This is a PvP minigame inspired by the famous Age Of Empire game from 2000 originally made by <a href="https://github.com/andrei1058">andrei1058</a>.</b><br/>
    The objective is to build a city by collecting resources and then attack the other empires.<br/>
    The game consists of 4 teams but can start with 2 players or the number set in the config.<br/>
</p>

---

#### Rules 
Age of Empires is a team game, with up to four opposing sides. Each team has a base, originally consisting of a huge building - the Forum - and a number of plots. The aim is simple: to destroy the opponents' Forum.

At the spawn, players set about collecting wood and stone to begin building their Empire. These resources are shared by the whole team, but collecting them earns individual gold.

Once accumulated, resources can be used with the Forum NPC representing the player's heart, to buy a building or change age. After spending the necessary wood (Spruce Wood) and stone (Andesite), the team votes on whether or not to purchase for its strategic value. If the team approves the purchase, the building can be placed on a plot in the base.

> **Note:** The number of plots is limited, and once placed, a building cannot be removed. You'll need to think ahead and strategize to avoid wasting space, while still having enough to fight for. What's more, plot sizes are predefined, so the size of the building must match the size of the plot on which the player wants to install it.

*Installed buildings provide players with stores to arm or equip themselves through an NPC, who is the heart of the building. They can then use their gold to buy items from the NPC. Changing Age improves the content of buildings, but also unlocks new ones in the Forum.*

---

#### How to Set Up

For setting up a new arena, set `Setup-Mode` to `true` and then restart the server.

1. Join the server and set the waiting lobby with `/s setLobby`.
2. Add a map using `/s addMap <name>`.
3. Set the spawn point for each team using `/s setSpawn <Blue/Red/Green/Yellow>`.
4. Set the forum (king) spawn for each team with the command `/s setForum <Blue/Red/Green/Yellow>`.
5. Set the plots where players will add buildings. For example, add a small plot using `/s addSmallPlot <team> <1/2>` where `team` can be "Blue" and `1,2` are the positions. Make sure to set both positions like a WorldGuard selection.
6. After adding some small, medium, and large plots, go to the middle of the map and set locations where sea lanterns (known as "rare items") will spawn. These blocks will give resources and XP faster and will spawn when the PvP countdown finishes.
7. Save the map with `/s saveMap` and finish the setup with `/s finishSetup`.

I recommend using this plugin for adding join signs in the main lobby.

---

### Permissions

| Permission      | Description                           |
|-----------------|---------------------------------------|
| `aoe.vipkick`   | Allow VIPs to join a full arena       |
| `aoe.setup`     | Required for setting up a server      |
| `aoe.leave`     | Allow players to use `/leave`         |
| `aoe.start`     | Allow players to use `/forcestart`    |

---

### Configuration

```yaml
#Are you setting up the server?
Setup-Mode: false

#The aoe main lobby name
lobby-server: ageofempire

#How much players must be in a team?
max-in-team: 2

#How many players should be online to start the lobby countdown?
min-players: 2

#Various countdowns
countdowns:
lobby: 40 (in seconds)
pregame: 10 (in seconds)
pvp: 10 (in minutes)
assault: 10 (in minutes)

#This command is executed at the end of the game
restart-cmd: restart

#Various plots radius
plot-radius:
small: 9
medium: 12
large: 16

#Database credentials. Needed for stats. enable: true
Database:
host: localhost
port: 3306
database: AOE
table: stats_
username: root
password: p4ss2

#Don't touch this unless you don't know what you're doing
Arenas: []
```

### Screenshots
![](https://i.imgur.com/uROTGuI.jpg)
![](https://i.imgur.com/0vMnpA2.jpg)
![](https://i.imgur.com/BdK6n7H.png)
![](https://i.imgur.com/2pqrYSH.png)


### Map
**Download the map from here:**
https://www.dropbox.com/sh/0bd7astg3lb0uq0/AAAu9lr5jCJkMM3jysWjANiFa?dl=0

*NB : To avoid setting up the map, you can use the basic configuration in the basic-config folder.*

---

### Credits

This project is a fork from the original repository on GitLab. Special thanks to the original creator for their hard work and dedication.

Original Creator: [andrei1058](https://gitlab.com/andrei1058)