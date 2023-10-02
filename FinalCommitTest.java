import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FinalCommitTest {

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

        // File file = new File("/Users/lilbarbar/Desktop/Honors
        // Topics/Programming-Git/Tree-Objects/");
        // if (file.exists()) {

        // String pString = "/Users/lilbarbar/Desktop/Honors
        // Topics/Programming-Git/Tree-Objects/";
        // Path p = Paths.get(pString);
        // // Files.delete(p);

        // String contents[] = file.list();
        // for (String s : contents) {
        // Path temp = Paths.get(pString + s);
        // Files.delete(temp);
        // }
        // Files.delete(p);

        // }

    }

    @Test
    void testCommitFile1() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");
        c.commitFile();
        c.writeFile();

        c.tree.add("yo.txt");
        c.tree.add("jump.txt");
        c.tree.add("testout.txt");
        c.tree.add("Hehe");
        c.commitFile();
        c.writeFile();

        c.seePrev();
        c.writeFile();

        File directory = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects");
        String start = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/";

        String contents[] = directory.list();

        int goodFiles = 0;
        for (String s : contents) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 || info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }

        assertEquals(goodFiles > 0, true);

    }

    @Test
    void testCommitFile2() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");
        c.commitFile();
        c.writeFile();

        c.tree.add("yo.txt");
        c.tree.add("jump.txt");

        c.commitFile();
        c.writeFile();

        c.seePrev();
        c.writeFile();

        File directory = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects");
        String start = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/";

        String contents[] = directory.list();

        int goodFiles = 0;
        for (String s : contents) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 || info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }

        assertEquals(goodFiles > 0, true);

        System.out.println("\f");

        Commit c2 = new Commit("Billy", "W Billy Commit!");
        c2.commitFile();
        c2.writeFile();

        c2.tree.add("tesout.txt");
        c2.tree.add("Hehe");
        c2.tree.add("FolderB");

        c2.commitFile();
        c2.writeFile();

        c2.seePrev();
        c2.writeFile();

        goodFiles = 0;
        String contents2[] = directory.list();
        for (String s : contents2) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 || info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }

        assertEquals(goodFiles > 0, true);

    }

    @Test
    void testCommitFile3() throws Exception {

        Commit c1 = new Commit("Billy", "W Billy Commit!");
        c1.commitFile();
        c1.writeFile();
        c1.tree.add("abc.txt");
        c1.tree.add("def.txt");
        c1.commitFile();
        c1.writeFile();

        File directory = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects");
        String start = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/";

        String contents[] = directory.list();

        int goodFiles = 0;
        for (String s : contents) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 || info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }

        assertEquals(goodFiles > 0, true);

        Commit c2 = new Commit("Billy", "W Billy Commit!");
        c2.commitFile();
        c2.writeFile();
        c2.tree.add("jump.txt");
        c2.tree.add("lmap.txt");
        c2.commitFile();
        c2.writeFile();

        String contents2[] = directory.list();

        goodFiles = 0;
        for (String s : contents2) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 || info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }

        assertEquals(goodFiles > 0, true);

        Commit c3 = new Commit("Billy", "W Billy Commit!");
        c3.commitFile();
        c3.writeFile();
        c3.tree.add("yo.txt");
        c3.tree.add("chiefkeef.txt");
        c3.tree.add("FolderB");
        c3.commitFile();
        c3.writeFile();

        String contents3[] = directory.list();

        goodFiles = 0;
        for (String s : contents3) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 || info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }

        assertEquals(goodFiles > 0, true);

        Commit c4 = new Commit("Billy", "W Billy Commit!");

        c4.commitFile();
        c4.writeFile();
        c4.tree.add("testout.txt");
        c4.tree.add("Hehe");
        c4.tree.add("FolderA");
        c4.commitFile();
        c4.writeFile();

        String contents4[] = directory.list();

        goodFiles = 0;
        for (String s : contents4) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 || info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }
        assertEquals(goodFiles > 0, true);

    }
}
