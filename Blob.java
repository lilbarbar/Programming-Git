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
import java.util.zip.Deflater;

//with Help from Mr. Lopez, the internet, Daniel and Aidan
public class Blob {

    private String pathName;

    public Blob(String name) {

        this.pathName = name;

    }

    public String getFileName() {
        return pathName;
    }

    // based off Chris' code in fileTOString since his code allows me to retrieve
    // multiple lines
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

        String blobFileName = getSha1(fileContents());

        PrintWriter pw = new PrintWriter(
                "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/" + blobFileName); // found
        // // out
        // // online
        // // from Java Oracle
        // // and Danny
        // =======
        // PrintWriter pw = new PrintWriter("./Objects/" + blobFileName); // found out
        // online
        // from Java Oracle
        // Print writer makes a file while File file =
        // newe File gets an already made file.
        // and Danny

        // I
        // "C://user/bari/"
        // disc
        // makes a file under fileName

        // <<<<<<< master
        // =======
        String words = fileContents();

        byte[] info = StringCompressor.compressString(words); // files contain the zip compressed version of the
        // // original file data instead of the actual original
        // // string

        // >>>>>>> master
        // not sure if I did it right
        pw.print(words);

        pw.close(); // releases the info

        System.out.println(words);

    }

    public static String getSha1(String input) throws NoSuchAlgorithmException { // credit to
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