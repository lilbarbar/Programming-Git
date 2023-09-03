import java.security.*;
import java.util.*;
import java.io.*;

public class Tester {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        Blob a = new Blob();
        System.out.println();
        System.out.println(a.getSha1("a"));
        System.out.println();
    }
}
