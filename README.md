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

<h2 align="center"> BlockAPI </h2>

```
public static ArrayList<Block> ReturnAllBlocksInRadius(Location location, int radius, int gap, Material... materials);
```

<h2 align="center">
<img src="Images/PandaBossBarAPI.png" alt="PandaBossBarAPI" width="600">
</h2>

```
public static PandaBossBar ReturnPandaBossBarByName(String barId);
public static PandaEntityBossBar ReturnPandaEntityBossBarByName(String barId);
```

<h2 align="center">
<img src="Images/PandaBossBar.png" alt="PandaBossBar" width="600">
</h2>

```
public static void Create(Player player, boolean translateColorCodes, boolean translateHexCodes){
        var entityBossBar = PandaBossBar
                .builder()
                .barID(UUID.randomUUID().toString())
                .barData(new BarData("TITLE", BarColor.BLUE, BarStyle.SEGMENTED_6, 0), 20)
                .barData(new BarData("TITLE2", BarColor.BLUE, BarStyle.SEGMENTED_6, 0), 20)
                .toAdd(player)
                .build(translateColorCodes, translateHexCodes, BarFlag.CREATE_FOG);
}
```

<h2 align="center">
<img src="Images/PandaEntityBossBar.png" alt="PandaEntityBossBar" width="600">
</h2>

```
public static void Create(LivingEntity livingEntity, Player player){
        var entityBossBar = PandaEntityBossBar
                .builder()
                .barID(UUID.randomUUID().toString())
                .pandaEntityBossValue(PandaEntityBossValue.HEALTH)
                .barData(new BarData("TITLE", BarColor.BLUE, BarStyle.SEGMENTED_6, 0))
                .toAdd(player)
                .build(livingEntity, BarFlag.CREATE_FOG);
}
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

<h2 align="center">
<img src="Images/PersistentDataCallbacks.png" alt=PersistentDataCallbacks" width="600">
</h2>

```
@PulseAutoRegister
public class TestPersistentDataCallbacks implements PersistentDataCallbacks{
    @Override
    public List<String> BlockHasTags() {
        return Arrays.asList(new String[]{"a", "b", "c"});
    }

    @Override
    public boolean BlockBreakEvent(BlockBreakEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags) {
        return true;
    }

    @Override
    public boolean AsyncPlayerChatEvent(AsyncPlayerChatEvent event, Block block, LinkedHashMap<PersistentDataTypes, LinkedHashMap<String, Object>> tags) {
        return true;
    }
}
```

<h2 align="center">
<img src="Images/PulseVariableTest.png" alt=PulseVariableTest" width="600">
</h2>

```
@PulseAutoRegister
public class UUIDTest implements PulseVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }

    @Override
    public boolean IsType(Object variable) {
        try {
            var uuid = UUID.fromString(variable.toString());
            return true;
        } catch (Exception ex) { return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var classTypes = new ArrayList<Class<?>>();
        classTypes.add(UUID.class);
        classTypes.add(UUID[].class);
        return classTypes;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return UUID.fromString(serializedData.toString());
    }

    @Override
    public Object SerializeBinaryData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeBinaryData(Object serializedData) {
        return UUID.fromString(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return UUID.randomUUID(); }
}
```

<h2 align="center">
<img src="Images/EnchantmentAPI.png" alt=EnchantmentAPI" width="600">
</h2>

```
public static LinkedHashMap<String, PulseEnchantment> ReturnAllStoredEnchantments();
public static ArrayList<PulseEnchantment> ReturnAllCustomEnchantmentsFromItem(ItemStack itemStack);
public static PulseEnchantment ReturnPulseEnchantment(String pulseEnchantmentName);
public static boolean AddPulseEnchantmentToItemStack(ItemStack itemStack, String pulseEnchantmentName);
```

<h2 align="center">
<img src="Images/PulseEnchantment.png" alt=PulseEnchantment" width="600">
</h2>

```
@PulseAutoRegister
public class TestPulseEnchantment implements PulseEnchantment{
    @Override
    public String enchantmentName() {
        return "Funny name";
    }

    @Override
    public boolean treasure() {
        return true;
    }
}
```

<h2 align="center">
<img src="Images/ArmorStandAPI.png" alt=ArmorStandAPI" width="600">
</h2>

```
 public static ArmorStand SpawnArmorStand(Location location, boolean isVisible, boolean customNameVisible, String customName, boolean canPickupItems, boolean useGravity);
```

<h2 align="center">
<img src="Images/CustomEvents.png" alt=CustomEvents" width="600">
</h2>

```
@PulseAutoRegister
public class TestEvents implements Listener {
    @EventHandler
    public void Test(PandaBosBarDeleteEvent event){ }
    @EventHandler
    public void Test(PandaBossBarCreateEvent event){ }
    @EventHandler
    public void Test(PandaBossBarPlayerAddEvent event){ }
    @EventHandler
    public void Test(PandaBossBarPlayeRemovedEvent event){ }
    @EventHandler
    public void Test(PandaEntityBossBarCreateEvent event){ }
    @EventHandler
    public void Test(PandaEntityBossBarDeleteEvent event){ }
    @EventHandler
    public void Test(PandaEntityBossBarPlayerAddEvent event){ }
    @EventHandler
    public void Test(PandaEntityBossBarPlayerRemoveEvent event){ }
    @EventHandler
    public void Test(PersistentDataAddedEvent event){ }
    @EventHandler
    public void Test(PersistentDataRemovedEvent event){ }
    @EventHandler
    public void Test(ServerDataAddedEvent event){ }
    @EventHandler
    public void Test(ServerDataRemovedEvent event){ }
    @EventHandler
    public void Test(TempBlockDataAddedEvent event){ }
    @EventHandler
    public void Test(TempBlockDataRemovedEvent event){ }
    @EventHandler
    public void Test(UUIDDataAddedEvent event){ }
    @EventHandler
    public void Test(UUIDDataRemovedEvent event){ }
    @EventHandler
    public void Test(DirCreatedEvent event){ }
    @EventHandler
    public void Test(DirDeletedEvent event){ }
    @EventHandler
    public void Test(FileCreatedEvent event){ }
    @EventHandler
    public void Test(FileDeletedEvent event){ }
    @EventHandler
    public void Test(HologramAddLineEvent event){ }
    @EventHandler
    public void Test(HologramCreatedEvent event){ }
    @EventHandler
    public void Test(HologramDeleteLineEvent event){ }
    @EventHandler
    public void Test(HologramEditLineEvent event){ }
    @EventHandler
    public void Test(TogglePlayerActionEvent event){ }
    @EventHandler
    public void Test(ScoreboardCreationEvent event){ }
    @EventHandler
    public void Test(ScoreboardPlayerAddEvent event){ }
    @EventHandler
    public void Test(ScoreboardPlayerRemovedEvent event){ }
    @EventHandler
    public void Test(PlayerPlaySoundEvent event){ }
    @EventHandler
    public void Test(WorldPlaySoundEvent event){ }
}
```

<h2 align="center">
<img src="Images/DirAPI.png" alt=DirAPI" width="600">
</h2>

```
public static void DeleteAllFiles(File directory, boolean cascadeFolders);
public static void DeleteDirectory(File directory);
public static void CreateDirectory(File directory);
public static List<File> ReturnAllFilesFromDirectory(File directory, boolean cascadeFolders);
public static void CopyAllFiles(File dirA, File dirB, ArrayList<String> ignore);
```

<h2 align="center">
<img src="Images/FileAPI.png" alt=FileAPI" width="600">
</h2>

```
public static void DeleteFile(File sourceFile);
public static void CreateFile(String directory, String sourceFile) throws IOException;
```

<h2 align="center">
<img src="Images/Hologram.png" alt=Hologram" width="600">
</h2>

```
var hologram = new Hologram.HologramBuilder()
                .CustomNameVisible(false)
                .IsVisible(false)
                .UseGravity(false)
                .GapBetweenLines(-0.5f)
                .Line("Line 1")
                .Line("Line 2")
                .Line("Line 3")
                .Create(location);

public void AddNewLine(String line);
public void EditLine(int index, String newLine);
public void DeleteHologram();
public void RemoveLine(int index);
```

<h2 align="center">
<img src="Images/InventoryAPI.png" alt=InventoryAPI" width="600">
</h2>

```
public static HashMap<ItemStack, ItemLocation> ReturnALlItemsWithLocation(LivingEntity livingEntity);
public static HashMap<ItemStack, ItemLocation> ReturnALlItemsWithLocation(PlayerInventory playerInventory);
```

<h2 align="center">
<img src="Images/ItemStackAPI.png" alt=ItemStackAPI" width="600">
</h2>

```
public static int CountItem(Player player, ItemStack itemStack);
public static int CountItem(Inventory inventory, ItemStack itemStack);
public static int CountItem(Player player, Material itemStack);
public static int CountItem(Inventory inventory, Material itemStack);
public static boolean IsItemTheSame(ItemStack a, ItemStack b);
public static PulseItemStack ReturnPulseItem(String itemName);
public static PulseItemStack ReturnPulseItem(ItemStack itemStack);
```

<h2 align="center">
<img src="Images/PulseItemStack.png" alt=PulseItemStack" width="600">
</h2>

```
@PulseAutoRegister
public class TestPulseItemStack implements PulseItemStack{
    @Override
    public String itemName() {
        return "Test";
    }

    @Override
    public Material itemType() {
        return Material.DIAMOND_SWORD;
    }

    @Override
    public int itemAmount() {
        return 2;
    }
}
```

<h2 align="center">
<img src="Images/ClassAPI.png" alt=ClassAPI" width="600">
</h2>

```
public static void RegisterClasses(JavaPlugin javaPlugin);
public static void RegisterClassesRaw(JavaPlugin javaPlugin) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
```

<h2 align="center">
<img src="Images/JavaAPI.png" alt=JavaAPI" width="600">
</h2>

```
public static ArrayList<Class<?>> ReturnAllAutoRegisterClasses(JavaPlugin javaPlugin);
public static ArrayList<Class<?>> ReturnAllAutoRegisterClassesRaw(JavaPlugin javaPlugin) throws URISyntaxException, IOException, ClassNotFoundException;
public static List<Class<?>> ReturnAllCLassesFromPlugin(JavaPlugin javaPlugin) throws URISyntaxException, IOException, ClassNotFoundException;
```

<h2 align="center">
<img src="Images/LocationAPI.png" alt=LocationAPI" width="600">
</h2>

```
public static Location LOCATION(Player player, double x, double y, double z);
public static Location LOCATION(UUID worldUUID, double x, double y, double z);
public static Location LOCATION(String worldName, double x, double y, double z);
public static Location LOCATION(World world, double x, double y, double z);
public static Location LOCATION(Player player, double x, double y, double z, float yaw, float pitch);
public static Location LOCATION(UUID worldUUID, double x, double y, double z, float yaw, float pitch);
public static Location LOCATION(String worldName, double x, double y, double z, float yaw, float pitch);
public static Location LOCATION(World world, double x, double y, double z, float yaw, float pitch);
public static void TeleportLocation(String locationName, Player... players);
public static void TeleportLocation(String locationName, Entity... entities);
public static List<PulseLocation> ReturnAllPulseLocations(Location location, boolean useDistanceForEvent);
public static Location ReturnClosestLocation(List<Location> locations, Location originLocation);
public static Location FindMidPointBetween2Locations(Location a, Location b);
public static boolean IsBlockBetweenLocations(Location location1, Location location2, Block block);
public Location[] ReturnAllLocationsBetweenTwoLocations(Location start, Location end, double spacing);
public static List<Location> GetAllLocationsInCubeArea(Location a, Location b);
```

<h2 align="center">
<img src="Images/PulseLocation.png" alt=PulseLocation" width="600">
</h2>

```
@PulseAutoRegister
public class TestPulseLocation implements PulseLocation{

    private final Location location;

    @Override
    public Location storedLocation() { return location; }

    @Override
    public int distanceForEvents() { return 10; }

    public TestPulseLocation(Location location){
        this.location = location;
    }

    @Override
    public boolean BlockBreakEvent(BlockBreakEvent event, Location location) {
        return true;
    }

    @Override
    public void EntityDeathEvent(EntityDeathEvent event, Location location) {
        return;
    }
}
```

<h2 align="center">
<img src="Images/LoopAPI.png" alt=LoopAPI" width="600">
</h2>

```
public static void CancelLoops(JavaPlugin javaPlugin);
public static void CancelLoops(int id);
public static void CancelLoops(String loopName);
public static void CancelLoops(PulseLoop pulseLoop);
```

<h2 align="center">
<img src="Images/PulseLoop.png" alt=PulseLoop" width="600">
</h2>

```
@PulseAutoRegister
public class TestPulseLoop implements PulseLoop{
    @Override
    public Long StartDelay() {
        return 0L;
    }

    @Override
    public Long LoopInterval() {
        return 20L;
    }

    @Override
    public void LoopFunction() {

    }
}
```

<h2 align="center">
<img src="Images/MovementAPI.png" alt=MovementAPI" width="600">
</h2>

```
public static Location ReturnLocationLock(Player player);
public static void LockPlayerLocation(Player player, boolean state, Location location);
public static void LockPlayerRotation(Player player, boolean state, Location location);
```

<h2 align="center">
<img src="Images/TeleportsAPI.png" alt=TeleportsAPI" width="600">
</h2>

```
public static void TeleportPlayer(Player player, LivingEntity liveTarget, Location softTarget, int timeToWait, boolean displayTime, boolean cancelOnMove);
public static boolean isPlayerTeleporting(Player player);
public static void CANCEL_PLAYER_TELEPORT(Player player);
```

<h2 align="center">
<img src="Images/NBTAPI.png" alt=NBTAPI" width="600">
</h2>

```
public static boolean DoesItemStackContainNBTTags(ItemStack itemStack, List<String> tags);
public static HashMap<String, Object> GetAll(ItemStack itemStack, PersistentDataType persistentDataType);
public static HashMap<String, Object> GetAll(ItemStack itemStack);
public static Object Get(JavaPlugin javaPlugin, ItemStack itemStack, String key, PersistentDataType persistentDataType);
public static void Add(JavaPlugin javaPlugin, ItemStack itemStack, PersistentDataType persistentDataType, String key, Object value);
public static boolean Has(JavaPlugin javaPlugin, ItemStack itemStack, PersistentDataType persistentDataType, String key);
public static void Remove(JavaPlugin javaPlugin, ItemStack itemStack, String key);
```

<h2 align="center">
<img src="Images/ParticleAPI.png" alt=ParticleAPI" width="600">
</h2>

```
public static void SpawnParticle(World world, Particle particle, Location point);
public static void SpawnParticle(World world, Particle particle, Vector point);
public static void SpawnParticle(World world, Particle particle, Location location, int i, int vm, int v1, int v2, int v3);
public static void SpawnSphere(Location location, Particle particle, int density, int duration, double sphereRadius);
```

<h2 align="center">
<img src="Images/HeadAPI.png" alt=HeadAPI" width="600">
</h2>

```
public static ItemStack ReturnPlayerHead(OfflinePlayer player);
public static ItemStack ReturnPlayerHead(String uuid);
public static ItemStack ReturnPlayerHead(String name, int amount, String url); <- https://textures.minecraft.net/texture/
```

<h2 align="center">
<img src="Images/PlayerAPI.png" alt=PlayerAPI" width="600">
</h2>

```
public static LinkedHashMap<PlayerAction, Boolean> ReturnPlayerActionData(Player player)
public static boolean CanPlayerAction(PlayerAction playerAction, Player player);
public static void TogglePlayerAction(PlayerAction playerAction, boolean actionState, Player player);
public static String[] GetPlayerTexture(Player player);
```

<h2 align="center">
<img src="Images/TitleAPI.png" alt=TitleAPI" width="600">
</h2>

```
public static void SendTitleToAllPlayers(String title, String subtitle, int fadeIn, int stay, int fadeOut);
public static void SendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);
```


<h2 align="center">
<img src="Images/VanishAPI.png" alt=VanishAPI" width="600">
</h2>

```
public static void HideTargetFromViewer(Player target, Player viewer);
public static void ShowTargetToViewer(Player target, Player viewer;
public static boolean CanViewerSeeTarget(Player target, Player viewer);
```

<h2 align="center">
<img src="Images/PulseRecipe.png" alt=PulseRecipe" width="600">
</h2>

```
@PulseAutoRegister
public class TestPulseRecipe implements PulseRecipe, PulseItemStack {

    @Override
    public String itemName() {
        return "Test Item";
    }

    @Override
    public Material itemType() {
        return Material.DIAMOND_SWORD;
    }

    @Override
    public int itemAmount() {
        return 0;
    }

    @Override
    public RecipeType recipeType() {
        return RecipeType.ShapedRecipe;
    }

    @Override
    public ItemStack recipeResult() {
        return PulseItemStackAPI.ReturnItemStack(this);
    }
}
```

<h2 align="center">
<img src="Images/PulseScoreboard.png" alt=PulseScoreboard" width="600">
</h2>

```
var firstSection = PulseScoreboardLines.builder()
                .scoreboardTitle("Section 1")
                .addLine(1, new PulseScoreboardData("Hello1"))
                .addLine(2, new PulseScoreboardData("Hello2"))
                .build();
        var secondSection = PulseScoreboardLines.builder()
                .scoreboardTitle("Section 2")
                .addLine(1, new PulseScoreboardData("Hello3"))
                .addLine(2, new PulseScoreboardData("Hello4"))
                .build();
        var pulseScoreboard = PulseScoreboard.builder()
                .scoreboardID(event.getPlayer().getDisplayName())
                .addPlayer(event.getPlayer())
                .addLineHolder(20, firstSection)
                .addLineHolder(20, secondSection)
                .create(false);
```

<h2 align="center">
<img src="Images/SoundAPI.png" alt=SoundAPI" width="600">
</h2>

```
public static void PlaySound(Sound minecraftSound, Player player, Location location, int volume, int pitch);
public static void PlaySound(Sound minecraftSound, Location location, int volume, int pitch);
```

<h2 align="center">
<img src="Images/WorldAPI.png" alt=WorldAPI" width="600">
</h2>

```
public static boolean IsWorldLoaded(String worldName);
public static boolean IsWorldLoaded(UUID worldUUID);
public static World LoadWorld(String worldName);
public static void UnloadWorld(String worldName);
public static void UnloadWorld(UUID worldUUID);
public static void UnloadWorld(World world);
public static void DeleteWorld(String worldName;
public static void DeleteWorld(UUID worldUUID);
public static void DeleteWorld(World world);
public static void DeleteWorld(File worldSource);
public static World CreateCopy(String baseWorldName, String newWorldName, boolean deleteOldWorld, ArrayList<String> ignore);
public static void WorldLockPlayerAction(String worldName, PlayerAction playerAction, boolean playerActionState);
public static void WorldLockPlayerAction(World world, PlayerAction playerAction, boolean playerActionState);
public static void TimeLock(World world, TimeLock timeLock);
public static void DifficultyLock(World world, Difficulty difficulty);
public static void GameModeLock(World world, GameMode gameMode);
public static void HeartLock(World world, int heartLevel);
public static void HungerLock(World world, int hungerLevel);
public static void SaturationLock(World world, int saturationLevel);
```

<h2 align="center">
<img src="Images/WorldEditAPI.png" alt=WorldEditAPI" width="600">
</h2>

```
public static void LoadAndPasteSchematic(String schematicName, Location location);
public static Clipboard LoadSchematic(String schematicName);
public static void PasteSchematic(Clipboard clipboard, Location location);
```

<h2 align="center">
<img src="Images/WorldGuardAPI.png" alt=WorldGuardAPI" width="600">
</h2>

```
public static ProtectedRegion ReturnProtectedRegion(org.bukkit.World world, String regionName)
public static ProtectedRegion CreateCuboidProtectedRegion(String regionName, org.bukkit.World world, List<BlockVector3> points, boolean addToManager)
public static ProtectedRegion CreatePolygonProtectedRegion(String regionName, org.bukkit.World world, int minY, int maxY, List<BlockVector2> points, boolean addToManager)
public static ProtectedRegion CreateglobalProtectedRegion(String regionName, org.bukkit.World world, boolean addToManager)
public static void AddProtectedRegion(org.bukkit.World world, ProtectedRegion protectedRegion)
public static void RemoveProtectedRegion(org.bukkit.World world, String regionName)
public static boolean IsLocationInRegion(org.bukkit.World world, String regionName, Location location)
public static boolean IsLocationInRegion(ProtectedRegion protectedRegion, Location location)
public static boolean DoesRegionOverlap(org.bukkit.World world, String regionNameA, String regionNameB)
public static boolean DoesRegionOverlap(ProtectedRegion protectedRegionA, ProtectedRegion protectedRegionB)
public static boolean DoAllRegionOverlap(ProtectedRegion protectedRegionA, List<ProtectedRegion> protectedRegions)
public static  List<ProtectedRegion> ReturnOverlapRegions(org.bukkit.World world, String regionNameA, String regionNameB)
public static  List<ProtectedRegion> ReturnOverlapRegions(ProtectedRegion protectedRegionA, ProtectedRegion protectedRegionB)
public static List<ProtectedRegion> ReturnAllOverlapRegions(ProtectedRegion protectedRegionA, List<ProtectedRegion> protectedRegions)
public static boolean IsPlayerWithinRegion(Player player, org.bukkit.World world, String regionName)
public static boolean IsPlayerWithinRegion(Player player, ProtectedRegion protectedRegion)
public static void RegisterCustomFlag(String flagName, boolean state)
```
