package com.myProjects.creo.searchFileCreator;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SearchFileCreator {


    public static void main(String[] args) {

        //get a root directory
        Path root = Paths.get("src/test/tempFolder");


        TreeSet<Path> folderTree = FilesUtil.getFoldersTreeSet(root);

        for (Path path : folderTree) {
            System.out.println(path.toAbsolutePath());
        }


        try {
//            Path file = Files.createTempFile(root, "search", ".pro");
            Path file = Paths.get(root.toAbsolutePath() + File.separator + "search.pro");

            if (!Files.exists(file)) Files.createFile(file);

//            FileOutputStream fos = new FileOutputStream(file.toAbsolutePath().toString());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.toAbsolutePath().toString()));

            for (Path path : folderTree) {
                writer.write(path.toAbsolutePath().toString() + "\n");
                writer.flush();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
