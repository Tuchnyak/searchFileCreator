package com.myProjects.creo.searchFileCreator;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;

public class SearchFileCreator {


    public static void main(String[] args) {

        try {
            System.out.println("JAR has been executed in: " + FilesUtil.getLocationPath());

            Path path = FilesUtil.getLocationPath();

            TreeSet<Path> folderTree = FilesUtil.getFoldersTreeSet(path);

            FilesUtil.writeToSearchFile(folderTree, path);


        } catch (URISyntaxException e) {
            e.printStackTrace();

        }


    }


}
