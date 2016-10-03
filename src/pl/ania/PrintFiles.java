package pl.ania;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;


public class PrintFiles extends SimpleFileVisitor<Path> {

    public static void main(String[] args) {
        System.err.println("Start err");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Proszę podać ścieżkę");
        String path = scanner.nextLine();
        path = "d:\\test";

        printDir(Paths.get(path), "");

    }

    public static void printDir(Path path, String indent) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path file : stream) {

                System.out.println(indent + file.getFileName() + "-" + Files.isDirectory(file));
                if (Files.isDirectory(file)) {
                    printDir(file, "  " + indent);
//                    try (DirectoryStream<Path> stream2 = Files.newDirectoryStream(file)) {
//                        for (Path file2 : stream2) {
//
//                            System.out.println("    " + file2.getFileName() + "-" + Files.isDirectory(file2));
//                        }
//                    }
                }
            }
        } catch (IOException | DirectoryIteratorException x) {

            System.out.println(x);
        }

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes bfa) {
        if (bfa.isRegularFile()) {
            System.out.format("Plik: %s \n", file);
        } else {
            System.out.format("Katalog: %s ", file);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.format("wchodze do Katalog: %s \n", dir);

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.format("wychodze z Katalog: %s \n", dir);
        return FileVisitResult.CONTINUE;
    }
}
