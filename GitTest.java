import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Path path = Paths.get("Objects");
        Files.delete(path);
        Path indexPath = Paths.get("index.txt");
        Files.delete(indexPath);
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
