import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

public class TreeTest {
    @Test
    void testAddDirectory() throws NoSuchAlgorithmException, IOException {

        Tree tree = new Tree();
        File d = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/FolderA/");
        if (d.exists()) {
            tree.addDirectory("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/FolderA/");
        }

    }
}