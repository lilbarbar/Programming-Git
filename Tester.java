import java.security.*;
import java.util.*;
import java.io.*;

public class Tester {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Blob a = new Blob("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/balls.txt");
        System.out.println();
        System.out.println(a.getSha1(""));
        System.out.println();
        a.makeFile();
    }
}
