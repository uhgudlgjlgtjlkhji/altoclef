# 🤖 AltoClef — Minecraft 26.1.2

> **The world's first bot to beat Minecraft fully autonomously — now updated for Minecraft 26.1.2 with Fabric.**

[![Minecraft](https://img.shields.io/badge/Minecraft-26.1.2-green?style=flat-square&logo=minecraft)](https://minecraft.net)
[![Fabric](https://img.shields.io/badge/Fabric-0.18.4-blue?style=flat-square)](https://fabricmc.net)
[![Build](https://img.shields.io/github/actions/workflow/status/drmcbride12/altoclef/build.yml?style=flat-square)](https://github.com/drmcbride12/altoclef/actions)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)

---

## 📖 What is AltoClef?

AltoClef is a **client-side Minecraft bot** that plays the game for you — autonomously, intelligently, and without stopping until the job is done. Originally created by [gaucho-matrero](https://github.com/gaucho-matrero), it made history on **May 24, 2021** as the first bot ever to defeat Minecraft completely on its own, from punching the first tree to killing the Ender Dragon.

Built as a **Fabric mod** and powered by [Baritone](https://github.com/cabaletta/baritone) pathfinding, AltoClef operates through a sophisticated **task tree system**. Give it a high-level goal like `@gamer` (beat the game) or `@get diamond_sword`, and it will decompose that goal into every sub-task needed — mining, crafting, exploring, fighting mobs, bartering with piglins — all without any human input.

This fork ports AltoClef to **Minecraft 26.1.2**, maintaining full compatibility with Fabric Loader and Fabric API.

---

## ✨ Key Features

### 🧠 Autonomous Task Execution
AltoClef breaks large goals into smaller tasks recursively. Ask it to get diamond armor and it will mine coal for torches, find iron for tools, descend to the correct Y-level, mine diamonds, smelt them, and craft the armor — all on its own.

### 🗺️ Baritone-Powered Pathfinding
Under the hood, AltoClef uses Baritone's world-class pathfinding engine to navigate terrain, avoid obstacles, swim, climb, bridge gaps, and traverse the Nether. You do **not** need to install Baritone separately — it is already bundled inside the AltoClef JAR.

### 📦 400+ Obtainable Items
AltoClef can gather over 400 different Minecraft items automatically. Run `@list` in chat to see every item the bot supports collecting.

### ⚔️ Combat & Self-Defence
The bot can equip gear, fight hostile mobs, and defend itself during resource gathering. It can be configured to prioritize self-preservation or task completion.

### 🌍 World Awareness
AltoClef maintains real-time awareness of its surroundings through a system of trackers:
- **Entity Tracker** — monitors mobs, players, and animals nearby
- **Block Tracker** — remembers block positions across explored chunks
- **Inventory Tracker** — knows exactly what's in its inventory at all times
- **Chunk Tracker** — tracks loaded/unloaded areas to plan exploration

### 💬 Chat Commands
Control the bot from in-game chat using `@` prefixed commands. Commands can also be sent via `/msg` from another player on a server.

### ⚙️ Configurable Settings
A simple config file in your `.minecraft/altoclef/` folder lets you tune the bot's behaviour without touching any code. Changes can be reloaded live with a command.

---

## 🚀 Quick Start

### Step 1 — Download the JAR

Go to the [**Releases**](https://github.com/drmcbride12/altoclef/releases) page and download the latest:

```
altoclef-26.1.2.jar
```

### Step 2 — Install Fabric

If you haven't already, install [Fabric Loader 0.18.4](https://fabricmc.net/use/installer/) for Minecraft 26.1.2.

### Step 3 — Install Fabric API

Download [Fabric API 0.149.0+26.1.2](https://modrinth.com/mod/fabric-api) and place it in your `.minecraft/mods` folder.

### Step 4 — Install AltoClef

Place the downloaded `altoclef-26.1.2.jar` into your `.minecraft/mods` folder.

> ⚠️ **Do NOT install a separate Baritone JAR.** Baritone is already bundled inside AltoClef. Adding it separately will cause crashes.

### Step 5 — Launch the Game

Start the Minecraft 26.1.2 Fabric profile from the launcher. Once in-game, AltoClef is active and ready to receive commands.

---

## 📋 Requirements

| Dependency | Version | Download |
|---|---|---|
| Minecraft | 26.1.2 | [minecraft.net](https://minecraft.net) |
| Fabric Loader | 0.18.4 | [fabricmc.net](https://fabricmc.net/use/installer/) |
| Fabric API | 0.149.0+26.1.2 | [Modrinth](https://modrinth.com/mod/fabric-api) |
| Java | 21+ | [adoptium.net](https://adoptium.net) |

---

## 🎮 Commands

All commands are typed in-game chat and prefixed with `@`. You can also send them via `/msg <botname> <command>` if playing on a server.

| Command | Description | Example |
|---|---|---|
| `@help` | Lists all available commands | `@help` |
| `@coords` | Prints the bot's current coordinates | `@coords` |
| `@gamer` | **Beats the game autonomously** (the big one) | `@gamer` |
| `@get [items...]` | Collects specified items, fetching all sub-resources needed | `@get diamond 5` |
| `@get [item1 qty, item2 qty]` | Collect multiple items at once | `@get [cobblestone 40, oak_log 10]` |
| `@equip {material}` | Equips a full armour set of the given material | `@equip diamond` |
| `@equip [items...]` | Equips specific items, obtaining them if needed | `@equip [diamond_chestplate, iron_leggings]` |
| `@follow {player}` | Follows a player continuously | `@follow Steve` |
| `@food {amount}` | Gathers food (1 unit = half a hunger bar) | `@food 20` |
| `@give {player} {item} {qty}` | Gives another player an item, collecting it first if needed | `@give Steve diamond 3` |
| `@stop` | Stops whatever the bot is currently doing | `@stop` |
| `@list` | Prints all items the bot is capable of obtaining | `@list` |

---

## 🏆 Notable Tasks AltoClef Can Do

- **Beat the game** — Full autonomous Minecraft speedrun from a fresh world to Ender Dragon death (`@gamer`)
- **Gear up** — Collect full diamond or netherite armour and weapons automatically
- **Bee Movie** — Print the entire Bee Movie script using signs placed in a line (yes, really)
- **Terminator Mode** — Flee from players while secretly gearing up in diamond, then return and fight
- **Piglin Bartering** — Collect 12 Ender Pearls by bartering gold with Piglins in the Nether
- **Mob Farming** — Automatically hunt mobs for drops like string, leather, bones, and gunpowder
- **Crop Farming** — Harvest and replant crops for renewable food sources

---

## 🔧 Configuration

After launching Minecraft with AltoClef at least once, a config folder will be created at:

```
.minecraft/altoclef/
```

You can edit the config files here to adjust the bot's behaviour, such as combat aggressiveness, pathfinding preferences, and item priorities. Config changes can be reloaded without restarting the game.

---

## ⚠️ Important Notes

- **Do NOT use with other mods.** AltoClef is not designed to be compatible with other client mods. If a crash is caused by a mod conflict, it is unlikely to be supported.
- **Delete old Baritone configs.** If you have previously used Baritone standalone, move or delete your old Baritone configuration files. They will interfere with AltoClef and cause bugs.
- **Client-side only.** AltoClef runs entirely on the client. It can be used on vanilla servers and does not require anything to be installed server-side.
- **Use responsibly.** Using bots on servers where they are not permitted may violate server rules. Always check the rules of any server you join.

---

## 🏗️ Build Status

Check the [**Actions**](https://github.com/drmcbride12/altoclef/actions) tab to see the latest build results and CI status.

---

## 🤝 Credits

- **Original Author:** [gaucho-matrero](https://github.com/gaucho-matrero) — creator of AltoClef
- **Baritone:** [cabaletta](https://github.com/cabaletta/baritone) — pathfinding engine powering AltoClef
- **The forked githhub:** [drmcbride12](https://github.com/drmcbride12) — updated for Minecraft 26.1.2 + Fabric
- **This repo** [You will never know](https://github.com/uhgudlgjlgtjlkhji/altoclef) — updated for Minecraft 26.1.2 + Fabric with bug fixes
---

## 📜 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
