package com.myProjects.creo.searchFileCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;

class FilesUtil {

    public static Path getPath(String str) {

        return Paths.get(str);
    }


    @org.jetbrains.annotations.Nullable
    static TreeSet<Path> getFoldersTreeSet(Path dir) {

        TreeSet<Path> dirs;
        FileWalker walker = new FileWalker();

        try {
            Files.walkFileTree(dir, walker);
            dirs = walker.getFolders();
            dirs.pollFirst();                   //remove initial path
            return dirs;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("*** There is a problem with a folders list receiving ***");

            return null;
        }

    }


    static void writeToSearchFile(TreeSet<Path> folders, Path root) {

        //Path to a new search.pro file in the root directory
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
                    writer.write("search_path \"" + path.toAbsolutePath().toString() + "\"\r\n");
                    writer.flush();
                }

                writer.close();

                System.out.println("*** SEARCH.PRO FILE HAS BEEN CREATED! ***");

            } catch (IOException e) {
                e.printStackTrace();

            }

        } else {
            //great explanation
            System.out.println("*** SEARCH.PRO FILE HASN'T BEEN CREATED IN SOME REASONS ***");
        }


    }


    static Path getLocationPath() throws URISyntaxException {

        //getting path to a executed .jar file
        File file = new File(SearchFileCreator.class.getProtectionDomain().getCodeSource()
                .getLocation().toURI().getPath());

        //getting path to executed .jar file's directory
        Path path = Paths.get(file.getAbsolutePath()).getParent();

        //correct path if jar has been executed from "\\Server..." location
        if (path.toString().startsWith("\\\\")) {
//            System.out.println("*** The file is on a server! ***\n");

            String str = path.toString();
            str = str.substring(0, path.toString().indexOf(path.getFileName().toString())
                    + path.getFileName().toString().length());

//            System.out.println("Corrected directory path is: " + str);

            path = Paths.get(str);
        }

        return path;

    }

    //TODO: public static void updateConfigFile()
    //Do I need it? I think no)


}