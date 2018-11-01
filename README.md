### PLUGIN DISCONTINUED
##### FEEL FREE TO FORK IT BUT GIVE ME CREDITS xD

![header](https://i.imgur.com/iOFqYmU.png)

<p>This is a pvp minigame inspired by the famous Age Of Empire game from 2000.<br/>
The objective is to build a city by collecting resources and then attack the other empires.<br/>
The game consists of 4 teams but the game can start with 2 players or how much did you set in the config.<br/>
Stats available. This is not multi-arena.</p>


#### How to set up
<p>For setting up a new arena set Setup-Mode to true and then restart the server.<br/>
Join it and set the waiting lobby with /s setLobby and after add a map using /s addMap <name>.<br/>
Now set the spawn point for each team using /s setSpawn <Blue/Red/Green/Yellow>, the forum (king) spawn for each team with the command /s setForum <Blue/Red/Green/Yellow>,<br/>
and then you have to set the plots where the players will add buildings. <br/>
For example, you can add a small plot using /s addSmallPlot <team> <1/2> where team can be "Blue" and 1,2 are the pos.<br/>
You have to set it like a worlguard selection but please don't forget the set both the pos. <br/>
After you added some small, medium, and large plots you must go on the middle of the map and set some plocations where will be spawned some sea lanterns known as "rare items" in the game wich will give resources and xp faster,<br/>
this blocks will be spawned when the pvp countdown will finish. Done so, you can type /s saveMap and then /s finishSetup.<br/>
I reccomand this plugin for adding join signs in the main lobby.</p>

### Permissions
<p>aoe.vipkick - Allow vips to join a full arena<br/>
aoe.setup - Required for setting up a server<br/>
aoe.leave - Allow players to use /leave<br/>
aoe.start - Allow players to use /forcestart</p>

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

### Screensots
![](https://i.imgur.com/uROTGuI.jpg)
1[](https://i.imgur.com/0vMnpA2.jpg)
![](https://i.imgur.com/BdK6n7H.png)
![](https://i.imgur.com/2pqrYSH.png)