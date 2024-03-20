package com.pandapulsestudios.pulsecore.Class;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipInputStream;

public class JavaAPI {
    public static ArrayList<Class<?>> ReturnAllAutoRegisterClasses(JavaPlugin javaPlugin){
        try { return ReturnAllAutoRegisterClassesRaw(javaPlugin); }
        catch (URISyntaxException | IOException | ClassNotFoundException e) { throw new RuntimeException(e);}
    }

    public static ArrayList<Class<?>> ReturnAllAutoRegisterClassesRaw(JavaPlugin javaPlugin) throws URISyntaxException, IOException, ClassNotFoundException {
        var foundInformation = new ArrayList<Class<?>>();
        for(var classFound : ReturnAllCLassesFromPlugin(javaPlugin)){
            if(classFound.isAnnotationPresent(PulseAutoRegister.class)) foundInformation.add(classFound);
        }
        return foundInformation;
    }

    public static List<Class<?>> ReturnAllCLassesFromPlugin(JavaPlugin javaPlugin) throws URISyntaxException, IOException, ClassNotFoundException {
        var data = new ArrayList<Class<?>>();
        for(var className : ReturnAllClassNamesFromPlugin(javaPlugin)) data.add(Class.forName(className));
        return data;
    }

    private static List<String> ReturnAllClassNamesFromPlugin(JavaPlugin javaPlugin) throws URISyntaxException, IOException {
        var fileDir = new File(javaPlugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
        var zip = new ZipInputStream(new FileInputStream(fileDir));
        var classNames = new ArrayList<String>();
        var entry = zip.getNextEntry();
        while(entry != null){
            if(!entry.isDirectory() && entry.getName().endsWith(".class") && !entry.getName().contains("$")){
                var className = entry.getName().replace('/', '.').replace(".class", "");
                if(className.contains(javaPlugin.getClass().getPackageName())){
                    classNames.add(className);
                }
            }
            entry = zip.getNextEntry();
        }
        return classNames;
    }
}
