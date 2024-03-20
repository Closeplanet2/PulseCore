<h2 align="center">
<br>
<img src="Images/Logo.png" alt="Panda Spigot Core" width="600">
<br>
<br>
A Bukkit/Spigot API to allow for the ease and accesibility of spigot creation!
<br>
</h2>

<h2 align="center">
<img src="Images/BlockAPI.png" alt="Panda Spigot Core" width="600">
</h2>

```
public static ArrayList<Block> ReturnAllBlocksInRadius(Location location, int radius, int gap, Material... materials);
```

<h2 align="center">
<img src="Images/ChatAPI.png" alt="ChatAPI" width="600">
</h2>

```
public static String FormatMessage(String message, boolean translateColorCodes, boolean translateHexCodes);

ChatAPI.chatBuilder()
  .messagePrefix(messagePrefix)
  .messageType(MessageType.Player)
  .playerToo(player)
  .translateHexCodes(true)
  .translateColorCodes(true)
  .SendMessage("hello");
ChatAPI.chatBuilder()
  .messagePrefix(messagePrefix)
  .messageType(MessageType.Broadcast)
  .translateHexCodes(true)
  .translateColorCodes(true)
  .SendMessage("hello");
```

<h2 align="center">
<img src="Images/PersistentDataAPI.png" alt=PersistentDataAPI" width="600">
</h2>

```
public static PersistentDataContainer ReturnPersistentDataContainer(Block block);
public static LinkedHashMap<PersistentDataTypeEnum, LinkedHashMap<String, Object>> GetALl(Block block);
public static LinkedHashMap<String, Object> GetALl(Block block, PersistentDataType persistentDataType);
public static Object Get(Block block, PersistentDataType persistentDataType, NamespacedKey namespacedKey);
public static boolean Has(Block block, NamespacedKey namespacedKey);
public static void Add(Block block, NamespacedKey namespacedKey, Object dataObject);
public static void Add(Block block, PersistentDataType persistentDataType, NamespacedKey namespacedKey, Object dataObject);
public static void Remove(Block block, PersistentDataType persistentDataType, NamespacedKey namespacedKey);
```

<h2 align="center">
<img src="Images/TempBlockDataAPI.png" alt="TempBlockDataAPI" width="600">
</h2>

```
public static LinkedHashMap<String, Object> GetALl(Block block);
public static Object Get(Block block, String nameSpacedKey);
public static boolean Has(Block block, String nameSpacedKey);
public static void Add(Block block, String nameSpacedKey, Object object);
public static void Remove(Block block, String nameSpacedKey);
```
