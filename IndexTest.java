import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

public class IndexTest {

    private Index index;

    @Before
    public void setUp() {
        index = new Index();
    }

    @Test
    public void testInit() throws Exception {
        index.init();
        File objectsDirectory = new File(
                "C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\Programming-Git-Bari\\objects");
        assertTrue(objectsDirectory.exists());

        File indexFile = new File("C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\Programming-Git-Bari\\index.txt");
        assertTrue(indexFile.exists());
    }

    @Test
    public void testAdd() throws Exception {
        index.add("testFile");
        assertTrue(index.blobs.containsKey("testFile"));
        assertNotNull(index.blobs.get("testFile"));
    }

    @Test
    public void testRemove() {
        index.blobs.put("testFile", "testSha1");
        index.remove("testFile");
        assertNull(index.blobs.get("testFile"));
    }

    @Test
    public void testPrintBlobs() {
        index.blobs.put("testFile1", "testSha1");
        index.blobs.put("testFile2", "testSha2");
        index.printBlobs();

    }
}
