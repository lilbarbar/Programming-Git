import java.security.*;
import java.util.*;
import java.io.*;

public class GitTester {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Blob a = new Blob("balls.txt");
        System.out.println();
        System.out.println(a.getSha1(""));
        System.out.println();
        a.makeFile();

        Index index = new Index();

        index.init();
        index.add("balls2.txt");
        index.add("balls.txt");
        index.add("balls3.txt");

        index.printBlobs();
        index.remove("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/balls2.txt");

    }
}
