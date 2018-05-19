package com.myProjects.creo.searchFileCreator;

import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FilesUtil {

//    public static TreeSet<Path> folders;


    public static Path getPath(String str) {

        return Paths.get(str);
    }


    @Nullable
    public static TreeSet<Path> getFoldersTreeSet(Path dir) {

        TreeSet<Path> dirs = null;
        FileWalker walker = new FileWalker();

        try {
            Files.walkFileTree(dir, walker);
            dirs = walker.getFolders();
            dirs.pollFirst();
            return dirs;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("*** There is a problem with a folders list receiving ***");

        }

        return dirs;
    }

    //TODO: public static writeToSearchFile(TreeSet<Path> folders, Path root)

}
