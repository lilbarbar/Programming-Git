import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreeTest {

    String input = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/";

    // a good unit test, makes sure that my constructor works properly
    // generate a tree file

    @Test
    @DisplayName("Testing the tree consuctor to make a tree file")
    public void testTreeConsturctor() throws IOException {
        Tree tree = new Tree();
        File treeFile = new File("Tree");
        assertTrue(treeFile.exists());
    }

    @Test
    @DisplayName("Testing the tree consuctor to make a tree file")
    public void testTreeConsturctor2() throws IOException {
        Tree tree = new Tree("SuperTree");
        File treeFile = new File("SuperTree");
        assertTrue(treeFile.exists());
    }

    @Test
    @DisplayName("Testing the tree consuctor to make a tree file")
    public void testWriteToTree() throws IOException, NoSuchAlgorithmException {
        Tree tree = new Tree();
        File treeFile = new File("Tree");
        assertTrue(treeFile.exists());

        // Blob b = new Blob("jump.txt");

        tree.add("jump.txt", input);

        String treeFileContents = Helper.fileContents(treeFile);

        String fileContents = Helper.fileContents(new File("jump.txt"));
        String fileSHA = Blob.getSha1(fileContents);

        String newLine = "Blob : " + fileSHA + " : " + "jump.txt";


        assertTrue (treeFileContents.length() > 0);
    }

    @Test
    void testAddDirectory() throws NoSuchAlgorithmException, IOException {

        Tree tree = new Tree();
        File treeFile = new File(input + "Objects/Tree");

        File d = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/FolderA/");
        if (d.exists()) {
            tree.addDirectory("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/FolderA/");
        }


        tree.add("FolderA", input);

        String s = Helper.fileContents(treeFile);




        
        assertTrue (tree.blobs.size() > 0 || tree.trees.size() > 0);
        assertEquals (s.contains("FolderA"), true);

    }

    @Test
    void testAdd() throws IOException, NoSuchAlgorithmException {


        Tree tree = new Tree ();
        File treeFile = new File(input + "Objects/Tree");


        tree.add("def.txt", input);

        String s = Helper.fileContents(treeFile);


        assertEquals(s.indexOf("def.txt") >= 0, true);

 

        // Tree tree = new Tree();

        // tree.add("yo.txt", inputString);
        // tree.add("def.txt", inputString);

        // String s = Helper.fileContents(treeFile);
        // assertTrue(s.contains("yo.txt"));
        // assertTrue(s.contains("def.txt"));
        // assertTrue(s.contains("Blob"));


    }

    @Test
    void testAllBlobs() throws IOException, NoSuchAlgorithmException {

        Tree tree = new Tree();
        File treeF = new File("Tree");

        tree.add("testout.txt", input);

        String contents = Helper.fileContents(treeF);

        assertTrue(contents.length() > 0);

    }

    

    

  

    @Test
    void testRemove() throws IOException, NoSuchAlgorithmException {


        Tree tree = new Tree();
        File t = new File(input + "Objects/Tree");

        tree.add("abc.txt", input);
        tree.add("chiefkeef.txt", input);

        tree.add("jump.txt", input);

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



    

}
