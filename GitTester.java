import java.security.*;
import java.util.*;
import java.io.*;

public class GitTester {



    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Blob a = new Blob("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/balls.txt");
        System.out.println();
        System.out.println(a.getSha1(""));
        System.out.println();
        a.makeFile();

        Index index = new Index();

        index.init();
        index.add("balls2.txt");

    }
}
