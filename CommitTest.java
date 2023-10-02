import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommitTest {

    @BeforeAll
    static void setUpBeforeAll() throws IOException {
        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/");
        if (file.exists()) {

            String pString = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/";
            Path p = Paths.get(pString);
            // Files.delete(p);

            String contents[] = file.list();
            for (String s : contents) {
                Path temp = Paths.get(pString + s);
                Files.delete(temp);
            }
            file.delete();

        }
    }

    @AfterAll
    static void tearDownAfterClass() throws IOException {

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/");
        if (file.exists()) {

            String pString = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/";
            Path p = Paths.get(pString);
            // Files.delete(p);

            String contents[] = file.list();
            for (String s : contents) {
                Path temp = Paths.get(pString + s);
                Files.delete(temp);
            }
            Files.delete(p);

        }

    }

    @Test
    void testCommitFile() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");
        c.commitFile();

        assertEquals(c.hashes.size() == 1, true);

    }

    @Test
    void testGenerateSHA() throws NoSuchAlgorithmException {

        String normal = "bobby";
        String hash = Commit.generateSHA(normal);

        assertEquals(hash, "4501c3b0336cf2d19ed69a8d0ec436ee3f88b31b");

    }

    @Test
    void testGetDate() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");
        String date1 = c.getDate();

        Date date = new Date();
        String date2 = date.toString().substring(0, 11) + "2023";

        assertEquals(date1, date2);

    }

    @Test
    void testHashesToString() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");
        c.commitFile();
        c.writeFile();

        PrintWriter pw = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Bens-Amazing-Git/Tree-Objects/Tree");

        pw.write("UWU SUSSY BAKA");
        pw.close();
        c.commitFile();
        c.writeFile();

        String list = c.hashesToString();

        assertEquals(c.hashesToString() != null, true);
        assertEquals(list.indexOf(",") == -1, false);

    }

    @Test
    void testMakeTree() throws Exception {

        Tree tree = new Tree();
        File a = new File("/Users/lilbarbar/Desktop/Honors Topics/Bens-Amazing-Git/Tree-Objects/Tree");
        File b = new File("/Users/lilbarbar/Desktop/Honors Topics/Bens-Amazing-Git/Tree-Objects/");

        assertEquals(a.exists(), true);
        assertEquals(b.exists(), true);

    }

    @Test
    void testSeeNext() throws Exception {

        System.out.println(Commit.getDate());
        Commit com = new Commit("Bo", "Cool!");

        PrintWriter pw = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Bens-Amazing-Git/Tree-Objects/Tree");
        pw.write("lol");
        pw.close();
        com.commitFile();
        com.writeFile();

        System.out.println(com.hashesToString());

        PrintWriter pw2 = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Bens-Amazing-Git/Tree-Objects/Tree");
        pw2.write("lol2");
        pw2.close();
        com.commitFile();
        com.writeFile();
        System.out.println(com.hashesToString());

        PrintWriter pw3 = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Bens-Amazing-Git/Tree-Objects/Tree");
        pw3.write("lol3");
        pw3.close();
        com.commitFile();
        com.writeFile();
        System.out.println(com.hashesToString());

        com.seePrev();
        com.writeFile();

        com.seeNext();
        com.writeFile();
        String name = com.hashes.get(2);

        // recycle earlier code

        assertEquals(new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/" + name).exists(),
                true);

    }

    @Test
    void testSeePrev() throws Exception {

        System.out.println(Commit.getDate());
        Commit com = new Commit("Bo", "Cool!");

        PrintWriter pw = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Tree");
        pw.write("lol");
        pw.close();
        com.commitFile();
        com.writeFile();

        System.out.println(com.hashesToString());

        PrintWriter pw2 = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Tree");
        pw2.write("lol2");
        pw2.close();
        com.commitFile();
        com.writeFile();
        System.out.println(com.hashesToString());

        PrintWriter pw3 = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Tree");
        pw3.write("lol3");
        pw3.close();
        com.commitFile();
        com.writeFile();
        System.out.println(com.hashesToString());

        com.seePrev();
        com.writeFile();
        String name = com.hashes.get(1);

        // recycle earlier code

        assertEquals(new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/" + name).exists(),
                true);

    }

    @Test
    void testWriteFile() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");

        File directory = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/");

        String contents[] = directory.list();
        int oldLen = contents.length;

        c.commitFile();
        c.writeFile();

        String contents2[] = directory.list();
        int newLen = contents2.length;
        assertEquals(newLen == oldLen, false);

    }
}