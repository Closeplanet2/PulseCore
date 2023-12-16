<h2 align="center">
<br>
<img src="Images/Logo.png" alt="Panda Spigot Core" width="600">
<br>
<br>
A Bukkit/Spigot API to allow for the ease and accesibility of spigot creation!
<br>
</h2>

**Downloads:**
- [All Downloads](https://github.com/Closeplanet2/PandaSpigotCore/releases)

**Tested Versions:**
- 1.20.1-R0.1-SNAPSHOT

**Other Plguins:**
- [PandaSpigotCore](https://github.com/Closeplanet2/PandaSpigotCore)
- [PandaVariableCore](https://github.com/Closeplanet2/PandaVariableCore)
- [PandaConfigCore](https://github.com/Closeplanet2/PandaConfigCore)
- [PandaCommandCore](https://github.com/Closeplanet2/PandaCommandCore)
- [PandaBossBarCore](https://github.com/Closeplanet2/PandaBossBarCore)
- [PandaSideBarCore](https://github.com/Closeplanet2/PandaSideBarCore)
- [PandaExternalCore](https://github.com/Closeplanet2/PandaExternalCore)
- [PandaMatchmakingCore](https://github.com/Closeplanet2/PandaMatchmakingCore)
- [PandaLeaderboardCore](https://github.com/Closeplanet2/PandaLeaderboardCore)

# Maven
```
<repository>
    <id>pulsecore</id>
    <url>https://maven.pkg.github.com/closeplanet2/PulseCore</url>
</repository>
```
```
<dependency>
  <groupId>com.pandapulsestudios</groupId>
  <artifactId>pulsecore</artifactId>
  <version>1.0.2-a</version>
</dependency>
```

# Console API: Functions For Bukkit Console
```
public enum MessageType {
    CONSOLE_MESSAGE,
    CONSOLE_ERROR,
    BROADCAST_MESSAGE
}
```
```
ConsoleAPI.BREAK();
ConsoleAPI.BREAK(10);
ConsoleAPI.LINE();
ConsoleAPI.LINE(10);
ConsoleAPI.DOUBLE_LINE();
ConsoleAPI.DOUBLE_LINE(10);
ConsoleAPI.NEW_LINE();
ConsoleAPI.NEW_LINE(10);
ConsoleAPI.SEND("Hello");
ConsoleAPI.SEND(MessageType.CONSOLE_MESSAGE, "Hello");
ConsoleAPI.SEND("Hello", "Hello", ....);
ConsoleAPI.SEND(MessageType.CONSOLE_MESSAGE, "Hello", "Hello", ....);
```

# Chat API: Functions For Player Chat (Use message checks)
```
ChatAPI.ClearPlayerChat(Player player);
ChatAPI.SEND("message", Player player);
```

# Panda Event: Auto Register Events
```
@PandaEvent
public class PlayerChat implements Listener {
    ...
}
```

```
@Override
public void onEnable() {
    try { JavaClassAPI.Register([Your Java Plugin], [Package Path Of Plugin]); }
    catch (Exception e) { e.printStackTrace(); }
}
```

# File API
```
FileAPI.Exist(String filePath, String fileName, String extension);
FileAPI.Exist(String filePath, String fileName);
FileAPI.Create(String filePath, String fileName, String extension);
FileAPI.Create(String filePath, String fileName);
```

# Directory API
```
DirAPI.DeleteAllFiles(File source);
DirAPI.CopyAllFiles(File dirA, File dirB, ArrayList<String> ignore);
DirAPI.Create(String directoryPath);
DirAPI.Create(File directory);
DirAPI.ReturnAllDirectoryPaths(File directory, boolean loop);
```

# ItemStackAPI
```
ItemStackAPI.CountItem(Player player, ItemStack itemStack);
ItemStackAPI.CountItem(Inventory inventory, ItemStack itemStack);
ItemStackAPI.CountItem(Player player, Material itemStack);
ItemStackAPI.CountItem(Inventory inventory, Material itemStack);
ItemStackAPI.IsItemTheSame(ItemStack a, ItemStack b);
```

# Loops: Auto Register Loops On The Server
```
@PandaLoop
public class MainLoop implements LoopValues {
    @Override
    public String ReturnID() { return "MainLoop"; }

    @Override
    public int RegisterLoop() {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(PandaSpigotCore.INSTANCE, new Runnable() {
            @Override
            public void run() {
                
            }
        }, 0L, 10L);
    }
}
```

```
@Override
public void onEnable() {
    try { JavaClassAPI.Register([Your Java Plugin], [Package Path Of Plugin]); }
    catch (Exception e) { e.printStackTrace(); }
}
```

# NBT API
```
NBTAPI.GetNBT(ItemStack itemStack, String key);
NBTAPI.GetNBT(Entity entity, String key);
NBTAPI.AddNBT(ItemStack itemStack, String key, String value);
NBTAPI.AddNBT(Entity entity, String key, String value);
NBTAPI.HasNBT(ItemStack itemStack, String key);
NBTAPI.HasNBT(Entity entity, String key);
NBTAPI.RemoveNBT(ItemStack itemStack, String key);
NBTAPI.RemoveNBT(Entity entity, String key);
```

# Movement API
```
MovementAPI.LOCK_PLAYER_LOCATION(Player player);
MovementAPI.LOCK_PLAYER_LOCATION(Player player, Location location);
MovementAPI.UN_FREEZE_PLAYER_LOCATION(Player player);
MovementAPI.MOVE_PLAYER_TO_LOCK(Player player);
MovementAPI.RETURN_LOCKED_LOCATION(Player player);
```

# Teleports API
```
TeleportsAPI.TELEPORT_PLAYER(layer player, LivingEntity liveTarget, Location softTarget, int timeToWait, boolean displayTime, boolean cancelOnMove);
TeleportsAPI.IS_PLAYER_TELEPORTING(Player player);
TeleportsAPI.CANCEL_PLAYER_TELEPORT(Player player);
```

# Head API
```
HeadAPI.ReturnPlayerHead(OfflinePlayer player);
HeadAPI.ReturnPlayerHead(String uuid);
HeadAPI.ReturnPlayerHead(String name, int amount, String url); //https://textures.minecraft.net/texture/
```

# Player API
```
public enum TOGGLE_ACTIONS {
    PlayerSendMessageEvent,
    PlayerGetMessageEvent,
    PlayerBedEnterEvent,
    PlayerBedLeaveEvent,
    PlayerCommandSendEvent,
    PlayerExpChangeEvent,
    PlayerLevelChangeEvent,
    PlayerHarvestBlockEvent,
    PlayerInteractEvent,
    PlayerItemConsumeEvent,
    PlayerMoveEvent,
    PlayerTeleportEvent,
    BlockBreakEvent,
    BlockPlaceEvent,
    BlockDropEvent,
    PlayerGetTitles,
    PlayerGetSounds,
    PlayerGetBossBars,
    InventoryOpenEvent,
    PlayerDamageEvent,
    PlayerClickInventory
}
```

```
PlayerAPI.TEST_PERMISSIONS(Player player, String[] perms)
List<UUID> PlayerAPI.CONVERT(List<Player> players);
PlayerAPI.TOGGLE_STAT(Player player, TOGGLE_ACTIONS toggle_stats, boolean state);
PlayerAPI.GET_TOGGLE_STAT(Player player, TOGGLE_ACTIONS toggle_stats);
```

# Vanish API
```
VanishAPI.HIDE_PLAYER_FROM_PLAYER(Player a, List<Player> players);
VanishAPI.HIDE_PLAYER_FROM_PLAYER(Player a, Player b);
VanishAPI.SHOW_PLAYER_TO_PLAYER(Player a, List<Player> players);
VanishAPI.SHOW_PLAYER_TO_PLAYER(Player a, Player b);
VanishAPI.REMOVE_ALL_VANISHES(Player player);
VanishAPI.IS_HIDDEN_FROM_PLAYER(Player a, Player b);
```

# PlayerData API

# ServerData API

# World API
