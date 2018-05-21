package com.myProjects.creo.searchFileCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;

public class FilesUtil {

//    public static TreeSet<Path> folders;


    public static Path getPath(String str) {

        return Paths.get(str);
    }


    @org.jetbrains.annotations.Nullable
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

            return null;
        }

    }


    //TODO: public static void writeToSearchFile(TreeSet<Path> folders, Path root)
    public static void writeToSearchFile(TreeSet<Path> folders, Path root) {

        Path fileToWrite = Paths.get(root.toAbsolutePath() + File.separator + "search.pro");

        if (!Files.exists(fileToWrite)) {
            try {
                Files.createFile(fileToWrite);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (Files.exists(fileToWrite)) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite.toAbsolutePath().toString()));

                for (Path path : folders) {
                    writer.write("search_path " + path.toAbsolutePath().toString() + "\n");
                    writer.flush();
                }

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();

            }

        }


    }

    //TODO: public static Path getLocationPath()
    public static Path getLocationPath() throws URISyntaxException {

        File file = new File(SearchFileCreator.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

        return Paths.get(file.getAbsolutePath()).getParent();

    }

    //TODO: public static void updateConfigFile()


}
