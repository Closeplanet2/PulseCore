package com.pandapulsestudios.pulsecore.FilesSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirAPI {
    public static void DeleteAllFiles(File source){
        if(source.exists()){
            for (File file : source.listFiles()) {
                if (file.isDirectory()) DeleteAllFiles(file);
                else file.delete();
            }
        }
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

    public static boolean Create(String directoryPath){ return Create(new File(directoryPath)); }
    public static boolean Create(File directory){ return directory.exists() || directory.mkdirs(); }
    public static List<File> ReturnAllDirectoryPaths(File directory, boolean loop){
        var data = new ArrayList<File>();
        if(directory.isDirectory() && !directory.exists()) return data;
        for(var child : directory.listFiles()){
            if(child.isDirectory() && loop) data.addAll(ReturnAllDirectoryPaths(child, loop));
            else data.add(child);
        }
        return data;
    }
}
