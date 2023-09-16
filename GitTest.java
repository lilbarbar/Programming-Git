import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GitTest {
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        PrintWriter pw1 = new PrintWriter("junit_example_file_data1.txt");
        pw1.print("test file contents one");
        PrintWriter pw2 = new PrintWriter("junit_example_file_data2.txt");
        pw1.print("test file contents two");
        PrintWriter pw3 = new PrintWriter("junit_example_file_data3.txt");
        pw1.print("test file contents three");
        PrintWriter pw4 = new PrintWriter("junit_example_file_data4.txt");
        pw1.print("test file contents four");
        PrintWriter pw5 = new PrintWriter("junit_example_file_data5.txt");
        pw1.print("test file contents five");
        Path objectsPath = Paths.get("Objects");
        Files.delete(objectsPath);
        Path indexPath = Paths.get("index.txt");
        Files.delete(indexPath);
        pw1.close();
        pw2.close();
        pw3.close();
        pw4.close();
        pw5.close();

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
    @DisplayName("[8] Test if initialize and objects are created correctly")
    void testInitialize() throws Exception {
        Index index = new Index();
        index.init();
        // check if the file exists
        File file = new File("index");
        Path path = Paths.get("objects");
        assertTrue(file.exists());
        assertTrue(Files.exists(path));
    }

    @Test
    @DisplayName("[15] Test if adding a blob works. 5 for sha, 5 for file contents, 5 for correct location")
    void testCreateBlob() throws Exception {
        // expected sha1 hash values
        String[] sha1 = { "5a1fd7b393a24123b1b7dda32a0272ed490a89d6", "dcb33305c60ffa6a5b38dd58172dbdf04c2c7b48",
                "c766df3281b44ba77ed851278fd9398ffac07c58", "0b5b083670146187f7dd447b04ca08079a54a1a8",
                "7e3e7a1554dc8dc7f3659fea93063e2763edd4bc" };
        // pre blob expected contents
        String[] expectedContents = { "test file contents 1", "test file contents 2", "test file contents 3",
                "test file contents 4", "test file contents 5" };
        try {
            Index index = new Index();
            // create the 5 blobs
            index.add("junit_example_file_data1.txt");
            index.add("junit_example_file_data2.txt");
            index.add("junit_example_file_data3.txt");
            index.add("junit_example_file_data4.txt");
            index.add("junit_example_file_data5.txt");
        } catch (Exception e) {
            System.out.println("An error ocurred: " + e.getMessage());
        }
        // check if the blob exists in the objects folder

        // check the sha1 hashes
        Path p1 = Paths.get("objects", sha1[0]);
        Path p2 = Paths.get("objects", sha1[0]);
        Path p3 = Paths.get("objects", sha1[0]);
        Path p4 = Paths.get("objects", sha1[0]);
        Path p5 = Paths.get("objects", sha1[0]);
        assertTrue(Files.exists(p1));
        assertTrue(Files.exists(p2));
        assertTrue(Files.exists(p3));
        assertTrue(Files.exists(p4));
        assertTrue(Files.exists(p5));

        // check if the file contents are the same

    }

    // techiedelight.com
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
