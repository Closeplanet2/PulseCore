package com.pandapulsestudios.pulsecore._External.WorldEdit;

import com.pandapulsestudios.pulsecore.Java.PluginAPI;
import com.pandapulsestudios.pulsecore.Java.SoftDependPlugins;
import com.pandapulsestudios.pulsecore.PulseCore;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WorldEditAPI {

    private static com.sk89q.worldedit.world.World ConvertWorld(World world){
        if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldEdit)) return null;
        return BukkitAdapter.adapt(world);
    }

    public static void LoadAndPasteSchematic(String schematicName, Location location){
        if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldEdit)) return;
        var clipboard = LoadSchematic(schematicName);
        PasteSchematic(clipboard, location);
    }

    public static Clipboard LoadSchematic(String schematicName) {
        if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldEdit)) return null;
        var schematicPath = String.format("plugins/WorldEdit/schematics/%s", schematicName);
        var format = ClipboardFormats.findByFile(new File(schematicPath));
        try(var reader = format.getReader(new FileInputStream(new File(schematicPath)))){
            return reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void PasteSchematic(Clipboard clipboard, Location location){
        if(!PluginAPI.IsPluginInstalled(PulseCore.Instance, SoftDependPlugins.WorldEdit)) return;
        var worldEditWorld = ConvertWorld(location.getWorld());
        try(var editSession = WorldEdit.getInstance().newEditSession(worldEditWorld)){
            var operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                    .build();
            Operations.complete(operation);
        } catch (WorldEditException e) {
            throw new RuntimeException(e);
        }
    }
}
