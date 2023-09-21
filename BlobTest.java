import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.*;

public class BlobTest {

    private Blob blob;

    @BeforeEach
    public void setUp() {
        // Create a Blob instance for testing
        blob = new Blob("testFile.txt");
    }

    // Test the 'getFileName' method
    @Test
    public void testGetFileName() {
        // Verify that the 'getFileName' method returns the correct file name
        assertEquals("testFile.txt", blob.getFileName(), "File name should match");
    }

    // Test the 'fileContents' method
    @Test
    public void testFileContents() throws IOException {
        // Create a temporary test file with known content
        String testContent = "This is a test file content.";
        String fileName = "testFile.txt";
        PrintWriter writer = new PrintWriter(fileName);
        writer.print(testContent);
        writer.close();

        // Set the Blob instance to use the temporary test file
        blob = new Blob(fileName);

        // Verify that 'fileContents' returns the expected content
        String content = blob.fileContents();
        assertEquals(testContent, content, "File content should match");

        // Clean up: delete the temporary test file
        File fileToDelete = new File(fileName);
        assertTrue(fileToDelete.delete(), "Test file should be deleted");
    }

    // Test the 'makeFile' method
    @Test
    public void testMakeFile() throws NoSuchAlgorithmException, IOException {
        // Create a temporary test file with known content
        String testContent = "This is a test file content.";
        String fileName = "testFile.txt";
        PrintWriter writer = new PrintWriter(fileName);
        writer.print(testContent);
        writer.close();

        // Set the Blob instance to use the temporary test file
        blob = new Blob(fileName);

        // Call the 'makeFile' method to create a blob file
        blob.makeFile();

        // Verify that the blob file was created and contains the expected content
        String blobFileName = blob.getSha1(testContent);
        File blobFile = new File(blobFileName);
        assertTrue(blobFile.exists(), "Blob file should exist");
        assertEquals(testContent, blob.fileContents(), "Blob file content should match");

        // Clean up: delete the temporary test file and the created blob file
        File fileToDelete = new File(fileName);
        assertTrue(fileToDelete.delete(), "Test file should be deleted");
        assertTrue(blobFile.delete(), "Blob file should be deleted");
    }

    // Test the 'getSha1' method
    @Test
    public void testGetSha1() throws NoSuchAlgorithmException {
        // Compute SHA-1 hash for a known input
        String input = "Test Input";
        String expectedSha1 = "4e1243bd22c66e76c2ba9eddc1f91394e57f9f83";

        // Verify that 'getSha1' returns the expected hash
        String sha1 = blob.getSha1(input);
        assertEquals(expectedSha1, sha1, "SHA-1 hash should match");
    }
}
