package com.pandapulsestudios.pulsecore.FileSystem;

import java.io.File;
import java.io.IOException;

public class FileAPI {
    public static boolean Exist(String filePath, String fileName, String extension){ return Exist(filePath, fileName + extension); }
    public static boolean Exist(String filePath, String fileName){
        var dir = new File(filePath);
        var listing = dir.listFiles();
        if(listing != null){
            for(var child: listing){
                if(child.getName().equalsIgnoreCase(fileName)) return true;
            }
        }
        return false;
    }

    public static File Create(String filePath, String fileName, String extension){ return Create(filePath, fileName + extension); }
    public static File Create(String filePath, String fileName){
        var state = DirAPI.Create(filePath);
        var file = new File(filePath + "/" + fileName);
        try {
            var created = file.createNewFile();
            return file;
        } catch (IOException e) { return null; }
    }
}
