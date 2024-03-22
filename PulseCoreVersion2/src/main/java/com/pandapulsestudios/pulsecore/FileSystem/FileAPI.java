package com.pandapulsestudios.pulsecore.FileSystem;

import com.pandapulsestudios.pulsecore.Events.CustomEvents.FileCreatedEvent;
import com.pandapulsestudios.pulsecore.Events.CustomEvents.FileDeletedEvent;

import java.io.File;
import java.io.IOException;

public class FileAPI {
    public static void DeleteFile(File sourceFile){
        if(!sourceFile.exists()) return;
        var fileDeletedEvent = new FileDeletedEvent(sourceFile);
        if(fileDeletedEvent.isCancelled()) return;
        sourceFile.delete();
    }

    public static void CreateFile(String directory, String sourceFile) throws IOException {
        DirAPI.CreateDirectory(new File(directory));
        var newFile = new File(directory + "/" + sourceFile);
        var fileCreatedEvent = new FileCreatedEvent(newFile);
        if(fileCreatedEvent.isCancelled()) return;
        newFile.createNewFile();
    }
}
