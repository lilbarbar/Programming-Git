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
        File d = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/FolderA/");
        if (d.exists()) {
            tree.addDirectory("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/FolderA/");
        }


        
        assertTrue (tree.blobs.size() > 0 || tree.trees.size() > 0);

    }

    @Test
    void testAdd() {

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
    void testAllContents() {

    }

    @Test
    void testAllTrees() {

    }

    @Test
    void testCalculateNumberOfCommits() {

    }

    @Test
    void testGenerateSHA1() {

    }

    @Test
    void testPointToFile() {

    }

    @Test
    void testRemove() {

    }

    @Test
    void testReturnStringOfCommits() {

    }

    @Test
    void testSave() {

    }

    @Test
    void testWriteTree() {

    }

}
