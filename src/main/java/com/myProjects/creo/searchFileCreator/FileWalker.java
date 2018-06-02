package com.myProjects.creo.searchFileCreator;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.TreeSet;

class FileWalker extends SimpleFileVisitor<Path> {

    //TreeSet for keeping directories that have been visited by FileWalker
    private TreeSet<Path> folders = new TreeSet<>();


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        //Adding directory into the set
        folders.add(dir);

        return super.preVisitDirectory(dir, attrs);
    }

//    @Override
//    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//        //nothing to do
//        return super.visitFile(file, attrs);
//    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

        //Saying that something is wrong
        System.out.println("*** THERE IS A PROBLEM WITH A FILE VISITING!!! ***");

        return super.visitFileFailed(file, exc);
    }

    //getter
    TreeSet<Path> getFolders() {
        return folders;
    }
}