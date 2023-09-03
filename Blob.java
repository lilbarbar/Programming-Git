import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

public class Blob {

    private Path p;
    private String pathName;
    private File file;

    public Blob(String name) {

        this.pathName = name;
        Path p = Paths.get(name);
        file = new File(name);
    }

    public String fileContents() throws IOException // based off Chris' code in fileTOString since his code allows me to
                                                    // retrieve multiple lines
    {
        StringBuilder record = new StringBuilder("");
        BufferedReader br = new BufferedReader(new FileReader(pathName));

        while (br.ready()) {
            record.append((char) br.read());
        }

        br.close();
        String s = record.toString();
        return s;
    }

    public void makeFile() throws NoSuchAlgorithmException, IOException {

        PrintWriter pw = new PrintWriter(getSha1(fileContents())); // found out online from Java Oracle and Danny and I
        // disc
        // makes a file under fileName

        String words = fileContents();

        pw.print(words);

        pw.close(); // releases the info

        // System.out.println(words);

    }

    public String getSha1(String input) throws NoSuchAlgorithmException { // credit to
                                                                          // http://www.sha1-online.com/sha1-java/
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}