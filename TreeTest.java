import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(new File(
                "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/a12d739775fcb663f01f52e026deadc67a83e0d7")
                .exists(), true);

    }
}
