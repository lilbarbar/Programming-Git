import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
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

public class GitTest {
    public static File testFile1;
    public static File testFile2;
    public static File testFile3;
    public static Index index;

    static String[] expectedContents = { "some content in file 1", "some content in file 2", "some content in file 3" };
    static String[] expectedSha = { "2e27b4d29c63a1242ee02973f5862cf26cf9679f",
            "d98d670ea7ca145dee0266961b8bf8ee5b12925a", "0a9d1240f29014f6677816388f4763e7fdc41445" };

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // initialize index
        index = new Index();
        index.init();

        // create three test files in the workspace with content
        testFile1 = new File("testFile1.txt");
        testFile2 = new File("testFile2.txt");
        testFile3 = new File("testFile3.txt");
        FileWriter fw1 = new FileWriter(testFile1);
        FileWriter fw2 = new FileWriter(testFile2);
        FileWriter fw3 = new FileWriter(testFile3);
        fw1.write("some content in file 1");
        fw2.write("some content in file 2");
        fw3.write("some content in file 3");
        fw1.close();
        fw2.close();
        fw3.close();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        Path textPath = Paths.get("junit_example_file_data.txt");
        Files.delete(textPath);
        Path objectsPath = Paths.get("Objects");
        Files.delete(objectsPath);
        Path indexPath = Paths.get("index.txt");
        Files.delete(indexPath);
    }

    @Test
    @DisplayName("[1] Test if initialize and objects are created correctly")
    void testInitialize() throws Exception {
        // check if the file index exists and the path to the objects folder exists
        File file = new File("index");
        Path path = Paths.get("objects");
        assertTrue(file.exists());
        assertTrue(Files.exists(path));
    }

    @Test
    @DisplayName("[2] Test if creating a blob works. 3 for sha and location, 3 for file contents")
    void testCreateBlob() throws Exception {
        try {
            // create the 3 blobs for testing
            addBlobs(index);
        } catch (Exception e) {
            System.out.println("An error ocurred: " + e.getMessage());
        }

        // check the sha1 hashes and the correct location (inside the objects folder)
        Path p1 = Paths.get("objects", expectedSha[0]);
        Path p2 = Paths.get("objects", expectedSha[1]);
        Path p3 = Paths.get("objects", expectedSha[2]);
        assertTrue(Files.exists(p1));
        assertTrue(Files.exists(p2));
        assertTrue(Files.exists(p3));

        // check if the file contents are the same
        String content1 = readFile(p1.toString(), StandardCharsets.UTF_8);
        String content2 = readFile(p1.toString(), StandardCharsets.UTF_8);
        String content3 = readFile(p1.toString(), StandardCharsets.UTF_8);
        assertEquals(expectedContents[0], content1);
        assertEquals(expectedContents[1], content2);
        assertEquals(expectedContents[2], content3);
    }

    @Test
    @DisplayName("[3] Test if adding a blob updates the index file.")
    void testIndexBlob() throws Exception {
        try {
            // create the 3 blobs for testing
            addBlobs(index);
        } catch (Exception e) {
            System.out.println("An error ocurred: " + e.getMessage());
        }
        Path indexPath = Paths.get("index.txt");
        String indexContents = readFile(indexPath.toString(), StandardCharsets.UTF_8);
        for (int i = 0; i < 2; i++) {
            assertTrue(indexContents.contains(expectedContents[i]));
        }
    }

    @Test
    @DisplayName("[4] Test if blobs are properly removed after calling remove method.")
    void testRemoveBlob() throws Exception {
        try {
            // create the 3 blobs for testing
            addBlobs(index);
            // remove the second and last file
            index.remove("testFile2.txt");
            index.remove("testFile3.txt");
        } catch (Exception e) {
            System.out.println("An error ocurred: " + e.getMessage());
        }
        Path path1 = Paths.get("objects", expectedSha[1]);
        Path path2 = Paths.get("objects", expectedSha[2]);
        Path pathToActualFile2 = Paths.get("testFile2.txt");
        Path pathToActualFile3 = Paths.get("testFile3.txt");
        Path indexPath = Paths.get("index.txt");

        // test if the file still exists in objects folder
        assertTrue(Files.exists(path1));
        assertTrue(Files.exists(path2));
        // test if the file still exists in the workspace
        assertTrue(Files.exists(pathToActualFile2));
        assertTrue(Files.exists(pathToActualFile3));
        String indexContents = readFile(indexPath.toString(), StandardCharsets.UTF_8);
        // test if the index file no longer contains the file
        assertTrue(!indexContents.contains(expectedSha[1]));
        assertTrue(!indexContents.contains(expectedSha[2]));
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
