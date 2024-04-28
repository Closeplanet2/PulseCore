package com.pandapulsestudios.pulsecore.FileAPI;

import java.io.File;
import java.io.IOException;

public class FileAPI {
    public static void DeleteFile(File sourceFile){
        if(!sourceFile.exists()) return;
        sourceFile.delete();
    }

    public static void CreateFile(String directory, String sourceFile) throws IOException {
        DirAPI.CreateDirectory(new File(directory));
        var newFile = new File(directory + "/" + sourceFile);
        newFile.createNewFile();
    }
}
