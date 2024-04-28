package com.pandapulsestudios.pulsecore.FileAPI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DirAPI {
    public static void DeleteAllFiles(File directory, boolean cascadeFolders){
        if(!directory.exists()) return;
        for(var file : directory.listFiles()){
            if(file.isDirectory() && cascadeFolders) DeleteAllFiles(file, true);
            else FileAPI.DeleteFile(file);
        }
    }

    public static void DeleteDirectory(File directory){
        if(!directory.exists()) return;
        directory.delete();
    }

    public static void CreateDirectory(File directory){
        if(directory.exists()) return;
        directory.mkdirs();
    }

    public static List<File> ReturnAllFilesFromDirectory(File directory, boolean cascadeFolders){
        var data = new ArrayList<File>();
        if(!directory.exists() || !directory.isDirectory()) return data;
        for(var sourceFile : directory.listFiles()){
            if(sourceFile.isDirectory() && cascadeFolders) data.addAll(ReturnAllFilesFromDirectory(sourceFile, true));
            else data.add(sourceFile);
        }
        return data;
    }

    public static void CopyAllFiles(File dirA, File dirB, ArrayList<String> ignore){
        try {
            if(!ignore.contains(dirA.getName())) {
                if(dirA.isDirectory()) {
                    if(!dirB.exists())
                        dirB.mkdirs();
                    String files[] = dirA.list();
                    for (String file : files) {
                        File srcFile = new File(dirA, file);
                        File destFile = new File(dirB, file);
                        CopyAllFiles(srcFile, destFile, ignore);
                    }
                } else {
                    InputStream in = new FileInputStream(dirA);
                    OutputStream out = new FileOutputStream(dirB);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) { }
    }
}
