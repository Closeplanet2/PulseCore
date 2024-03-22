<h2 align="center">
<br>
<img src="Images/Logo.png" alt="Panda Spigot Core" width="600">
<br>
<br>
A Bukkit/Spigot API to allow for the ease and accesibility of spigot creation!
<br>
</h2>

```
<repositories>
      <id>pulsecore</id>
      <url>https://maven.pkg.github.com/closeplanet2/PulseCore</url>
</repository>
```

```
<dependency>
      <groupId>com.pandapulsestudios</groupId>
      <artifactId>pulsecore</artifactId>
      <version>1.2.0-a</version>
</dependency>
```

<h2 align="center">
<img src="Images/BlockAPI.png" alt="BlockAPI" width="600">
</h2>

```
public static ArrayList<Block> ReturnAllBlocksInRadius(Location location, int radius, int gap, Material... materials);
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

<h2 align="center">
<img src="Images/ChatAPI.png" alt="ChatAPI" width="600">
</h2>

```
public static String FormatMessage(String message, boolean translateColorCodes, boolean translateHexCodes);
```

```
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
<img src="Images/ServerDataAPI.png" alt=ServerDataAPI" width="600">
</h2>

```
public static void Store(String key, Object data);
public static boolean Contains(String key);
public static void Remove(String key);
public static Object Get(String key, Object defaultValue);
public static LinkedHashMap<String, Object> ReturnAll();
```

<h2 align="center">
<img src="Images/UUIDDataAPI.png" alt=UUIDDataAPI" width="600">
</h2>

```
public static void Store(UUID uuid, String key, Object data);
public static boolean Contains(UUID uuid, String key);
public static void Remove(UUID uuid, String key);
public static Object Get(UUID uuid, String key, Object defaultValue);
public static LinkedHashMap<String, Object> GetAll(UUID uuid);
```

<h2 align="center">
<img src="Images/VariableAPI.png" alt=VariableAPI" width="600">
</h2>

```
public static boolean REGISTER_VAR_TEST(Class<?> test_class, PulseVariableTest variableLogic, boolean override_if_found);
public static PulseVariableTest RETURN_TEST_FROM_TYPE(Class<?> classType);
public static PersistentDataTypes ReturnTypeFromVariableTest(Class<?> classType);
public static List<String> RETURN_AS_ALL_TYPES(String text, boolean addVariableName, boolean isArrayType);
```

<h2 align="center">
<img src="Images/CustomVariable.png" alt=CustomVariable" width="600">
</h2>

```
@PulseAutoRegister
public class TestCustomVariable implements CustomVariable{
    public int x;
    public int y;
    public int z;
    
    public TestCustomVariable(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public HashMap<String, Object> SerializeData() {
        var data = new HashMap<String, Object>();
        data.put("x", x);
        data.put("y", y);
        data.put("z", z);
        return data;
    }

    @Override
    public Object DeSerializeData(HashMap<String, Object> configData) {
        x = (Integer) configData.getOrDefault("x", 0);
        y = (Integer) configData.getOrDefault("y", 0);
        z = (Integer) configData.getOrDefault("z", 0);
        return this;
    }

    @Override
    public Object DefaultValue() {
        return new TestCustomVariable(0, 0, 0);
    }
}
```


