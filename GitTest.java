import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//TESTS BLOBS 

public class GitTest {
    public static Index index;
    static String[] expectedContents = { "some content in file 1", "some content in file 2", "some content in file 3" };
    static String[] expectedSha = { "2e27b4d29c63a1242ee02973f5862cf26cf9679f",
            "d98d670ea7ca145dee0266961b8bf8ee5b12925a", "0a9d1240f29014f6677816388f4763e7fdc41445" };

    static String pathToObjectsFolder = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/";
    static String pathToIndexFolder = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Index";

    String inputString = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/";

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // try {
        // // Display the current working directory
        // System.out.println("Current working directory: " +
        // System.getProperty("user.dir"));
        // // initialize index
        // index = new Index();
        // index.init();
        // // create three test files in the workspace with content
        // PrintWriter pw1 = new PrintWriter(
        // "C:\\Users\\danie\\OneDrive\\Desktop\\Topics
        // Repos\\Programming-Git-Bari\\testFile1.txt");
        // PrintWriter pw2 = new PrintWriter(
        // "C:\\Users\\danie\\OneDrive\\Desktop\\Topics
        // Repos\\Programming-Git-Bari\\testFile2.txt");
        // PrintWriter pw3 = new PrintWriter(
        // "C:\\Users\\danie\\OneDrive\\Desktop\\Topics
        // Repos\\Programming-Git-Bari\\testFile3.txt");
        // pw1.print("some content in file 1");
        // pw2.print("some content in file 2");
        // pw3.print("some content in file 3");
        // pw1.close();
        // pw2.close();
        // pw3.close();
        // } catch (Exception e) {
        // e.printStackTrace();
        // fail("Exception occurred: " + e.getMessage());
        // }
    }

    // delete all the added files in setupBeforeClass ()
    @AfterAll
    static void tearDownAfterClass() throws Exception {
        // Path tree1 = Paths.get(
        // "C:\\Users\\danie\\OneDrive\\Desktop\\Topics
        // Repos\\Programming-Git-Bari\\objects\\10f228098914b028963a208273e41be47b4f417d");
        // Path tree2 = Paths.get(
        // "C:\\Users\\danie\\OneDrive\\Desktop\\Topics
        // Repos\\Programming-Git-Bari\\objects\\6016cd7c79df2958d3bc74b3dee21c7fe994e592");

        // Path textPath1 = Paths
        // .get("C:\\Users\\danie\\OneDrive\\Desktop\\Topics
        // Repos\\Programming-Git-Bari\\testFile1.txt");
        // Path textPath2 = Paths
        // .get("C:\\Users\\danie\\OneDrive\\Desktop\\Topics
        // Repos\\Programming-Git-Bari\\testFile2.txt");
        // Path textPath3 = Paths
        // .get("C:\\Users\\danie\\OneDrive\\Desktop\\Topics
        // Repos\\Programming-Git-Bari\\testFile3.txt");
        // Files.delete(textPath1);
        // Files.delete(textPath2);
        // Files.delete(textPath3);
        // Path objectsPath = Paths.get(pathToObjectsFolder);
        // // path to each file in the objects folder
        // Path p1 = Paths.get(pathToObjectsFolder + "\\" + expectedSha[0]);
        // Path p2 = Paths.get(pathToObjectsFolder + "\\" + expectedSha[1]);
        // Path p3 = Paths.get(pathToObjectsFolder + "\\" + expectedSha[2]);
        // Files.delete(p1);
        // Files.delete(p2);
        // Files.delete(p3);
        // if (Files.exists(textPath1)) {
        // Files.delete(textPath1);
        // Files.delete(textPath2);
        // Files.delete(textPath3);
        // // path to each file in the objects folder
        // Path p1 = Paths.get(pathToObjectsFolder + "\\" + expectedSha[0]);
        // Path p2 = Paths.get(pathToObjectsFolder + "\\" + expectedSha[1]);
        // Path p3 = Paths.get(pathToObjectsFolder + "\\" + expectedSha[2]);
        // Files.delete(p1);
        // Files.delete(p2);
        // Files.delete(p3);
        // }
        // if (Files.exists(tree1)) {
        // Files.delete(tree1);
        // Files.delete(tree2);
        // }
        // Files.delete(objectsPath);
        // Path indexPath = Paths.get(pathToIndexFolder);

    }

    @Test
    @DisplayName("[1] Test if initialize and objects are created correctly")
    void testInitialize() throws Exception {
        // check if the file index exists and the path to the objects folder exists

        Blob b = new Blob("chiefkeef.txt");
        Index i = new Index();
        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Index");
        Path path = Paths.get(pathToObjectsFolder);
        assertTrue(file.exists());
        assertTrue(Files.exists(path));
    }

    @Test
    @DisplayName("[2] Test if creating a blob works. 3 for sha and location, 3 for file contents")
    void testCreateBlob() throws Exception {

        Blob b1 = new Blob("yo.txt");
        Blob b2 = new Blob("testout.txt");
        Blob b3 = new Blob("jump.txt");

        assertEquals(b1.getSha1(b1.fileContents()), "637d1f5c6e6d1be22ed907eb3d223d858ca396d8");
        assertEquals(b2.getSha1(b2.fileContents()), "e0c9035898dd52fc65c41454cec9c4d2611bfb37");
        assertEquals(b3.getSha1(b3.fileContents()), "a22e0d880ad1a84437fdf1e7f1b573b945f42bd5");

        Index i = new Index();
        i.add("yo.txt");
        i.add("testout.txt");
        i.add("jump.txt");

        String s = Index.indexContents();
        String blob1Hash = Blob.getSha1(b1.fileContents());
        String blob2Hash = Blob.getSha1(b2.fileContents());
        String blob3Hash = Blob.getSha1(b3.fileContents());

        assertEquals(s.indexOf(blob1Hash) != -1, true);
        assertEquals(s.indexOf(blob2Hash) != -1, true);
        assertEquals(s.indexOf(blob3Hash) != -1, true);

    }

    @Test
    @DisplayName("[3] Test if adding a blob updates the index file.")
    void testIndexBlob() throws Exception {

        PrintWriter pw = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Index");
        pw.print("");

        String s = Index.indexContents();
        Index i = new Index();
        i.add("yo.txt");
        String s2 = Index.indexContents();

        assertEquals(s.equals(s2), false);
    }

    @Test
    @DisplayName("[4] Test if blobs are properly removed after calling remove method.")
    void testRemoveBlob() throws Exception {

        Index i = new Index();
        i.add("yo.txt");
        i.add("jump.txt");
        i.remove("jump.txt");
        String s = Index.indexContents();

        assertEquals(s.indexOf("jump.txt") == -1, true);
        assertEquals(s.indexOf("yo.txt") == -1, false);

    }

    @Test
    @DisplayName("[5] Test if objects in trees are added properly.")
    void testAddTree() throws Exception {
        Tree tree = new Tree();

        tree.add("yo.txt", inputString);
        tree.add("def.txt", inputString);

        File treeFile = new File(inputString + "Objects/Tree");
        String s = Helper.fileContents(treeFile);
        assertTrue(s.contains("yo.txt"));
        assertTrue(s.contains("def.txt"));
        assertTrue(s.contains("Blob"));

    }

    @Test
    @DisplayName("[6] Test if objects in trees are removed properly.")
    void testRemoveTree() throws Exception {
        Tree tree = new Tree();
        File t = new File(inputString + "Objects/Tree");

        tree.add("abc.txt", inputString);
        tree.add("chiefkeef.txt", inputString);

        tree.add("jump.txt", inputString);

        String string1 = Helper.fileContents(t);

        assertTrue(string1.contains("jump.txt"));
        assertTrue(string1.contains("abc.txt"));
        assertTrue(string1.contains("chiefkeef.txt"));

        tree.remove("jump.txt");
        tree.remove("bf66c9fecf6e0f873004b0ebeed29b7ad0761759");

        String string2 = Helper.fileContents(t);

        assertTrue(!string2.contains("jump.txt"));
        assertTrue(!string2.contains("abc.txt"));
        assertTrue(string2.contains("chiefkeef.txt"));

    }

    // techiedelight.com
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    // add blobs
    public static void addBlobs(Index name) throws Exception {
        name.add("testFile1.txt");
        name.add("testFile2.txt");
        name.add("testFile3.txt");
    }
}
