package com.myProjects.creo.searchFileCreator;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.TreeSet;

public class FileWalker extends SimpleFileVisitor<Path> {

    private TreeSet<Path> folders = new TreeSet<>();


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        folders.add(dir);

        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

        System.out.println("*** THERE IS A PROBLEM WITH A FILE VISITING!!! ***");

        return super.visitFileFailed(file, exc);
    }


    public TreeSet<Path> getFolders() {
        return folders;
    }
}
