import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.PrintWriter;
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
        PrintWriter pw = new PrintWriter("junit_example_file_data.txt");
        pw.print("test file contents");
        Path objectsPath = Paths.get("Objects");
        Files.delete(objectsPath);
        Path indexPath = Paths.get("index.txt");
        Files.delete(indexPath);
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

    }

    @Test
    public void testDivide() {
        int x = 1;
        int y = 2;
        demo obj = new demo();
        double result = obj.divide(x, y);
        // assertions -> compare expected value with result
        assertEquals(0.5, result);
    }
}
