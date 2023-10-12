import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommitTest {


    String inputString = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/";

    @BeforeAll
    static void setUpBeforeAll() throws IOException {
        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/");
        if (file.exists()) {

            String pString = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/";
            Path p = Paths.get(pString);
            // Files.delete(p);

            String contents[] = file.list();
            for (String s : contents) {
                Path temp = Paths.get(pString + s);
                Files.delete(temp);
            }
            file.delete();

        }
    }

    @AfterAll
    static void tearDownAfterClass() throws IOException {

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/");
        if (file.exists()) {

            String pString = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/";
            Path p = Paths.get(pString);
            // Files.delete(p);

            String contents[] = file.list();
            for (String s : contents) {
                Path temp = Paths.get(pString + s);
                Files.delete(temp);
            }
            Files.delete(p);

        }

    }


    @Test
    void testConstructor () throws Exception {

        Commit c = new Commit ("Bari", "Initial Tree");


        assertTrue (c != null);
        assertTrue (c.getAuthor() != null);
        assertTrue (c.getSummary() != null);





        
    }


    @Test
    void testCommitFile() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");
        c.commitFile();

        assertEquals(c.hashes.size() == 1, true);

    }

    @Test
    void testGenerateSHA() throws NoSuchAlgorithmException {

        String normal = "bobby";
        String hash = Commit.generateSHA(normal);

        assertEquals(hash, "4501c3b0336cf2d19ed69a8d0ec436ee3f88b31b");

    }

    @Test
    void testGetDate() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");
        String date1 = c.getDate();

        Date date = new Date();
        String date2 = date.toString().substring(0, 11) + "2023";

        assertEquals(date1, date2);

    }

    @Test
    void testHashesToString() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");
        c.commitFile();
        c.writeFile();

        PrintWriter pw = new PrintWriter(inputString + "Objects/Tree");

        pw.write("UWU SUSSY BAKA");
        pw.close();
        c.commitFile();
        c.writeFile();

        String list = c.hashesToString();

        assertEquals(c.hashesToString() != null, true);
        assertEquals(list.indexOf(",") == -1, false);

    }

    @Test
    void testMakeTree() throws Exception {

        Tree tree = new Tree();
        File a = new File(inputString + "Objects/Tree");
        File b = new File(inputString + "Objects/");

        assertEquals(a.exists(), true);
        assertEquals(b.exists(), true);

    }

    @Test
    void testSeeNext() throws Exception {

        System.out.println(Commit.getDate());
        Commit com = new Commit("Bo", "Cool!");

        PrintWriter pw = new PrintWriter(inputString + "Objects/Tree");
        pw.write("lol");
        pw.close();
        com.commitFile();
        com.writeFile();

        System.out.println(com.hashesToString());

        PrintWriter pw2 = new PrintWriter(inputString + "Objects/Tree");
        pw2.write("lol2");
        pw2.close();
        com.commitFile();
        com.writeFile();
        System.out.println(com.hashesToString());

        PrintWriter pw3 = new PrintWriter(inputString + "Objects/Tree");
        pw3.write("lol3");
        pw3.close();
        com.commitFile();
        com.writeFile();
        System.out.println(com.hashesToString());

        com.seePrev();
        com.writeFile();

        com.seeNext();
        com.writeFile();
        String name = com.hashes.get(2);

        // recycle earlier code

        assertEquals(new File(inputString + "Objects/" + name).exists(),
                true);

    }

    @Test
    void testSeePrev() throws Exception {

        System.out.println(Commit.getDate());
        Commit com = new Commit("Bo", "Cool!");

        PrintWriter pw = new PrintWriter(inputString + "Objects/Tree");
        pw.write("lol");
        pw.close();
        com.commitFile();
        com.writeFile();

        System.out.println(com.hashesToString());

        PrintWriter pw2 = new PrintWriter(inputString + "Objects/Tree");
        pw2.write("lol2");
        pw2.close();
        com.commitFile();
        com.writeFile();
        System.out.println(com.hashesToString());

        PrintWriter pw3 = new PrintWriter(inputString + "Objects/Tree");
        pw3.write("lol3");
        pw3.close();
        com.commitFile();
        com.writeFile();
        System.out.println(com.hashesToString());

        com.seePrev();
        com.writeFile();
        String name = com.hashes.get(1);

        // recycle earlier code

        assertEquals(new File(inputString + "Objects/" + name).exists(),
                true);

    }

    @Test
    void testWriteFile() throws Exception {

        Commit c = new Commit("Billy", "W Billy Commit!");

        File directory = new File(inputString + "Objects/");

        String contents[] = directory.list();
        int oldLen = contents.length;

        c.commitFile();
        c.writeFile();

        String contents2[] = directory.list();
        int newLen = contents2.length;
        assertEquals(newLen == oldLen, false);

    }

    @Test
    void testAddTree() throws Exception {

        Commit c1 = new Commit ("Bo", "lol");
        c1.tree.add("abc.txt", inputString);

        c1.addTree();

        File objects = new File (inputString + "Objects/");

        String treeContents = Helper.fileContents(new File (inputString + "Objects/Tree"));

        int same = 0;

        String contents[] = objects.list();

        for (String s : contents)
        {
            File f = new File (inputString + "Objects/" + s);

            String stuff = Helper.fileContents(f);

            if (stuff.equals(treeContents))
            {
                same++;
            }
        }


        assertTrue(same > 0);



        
    }

    @Test
    void testCheckout() {
        
    }

   

    @Test
    void testCurrentCommitSHA() throws Exception {



        Commit c1 = new Commit ("Bo", "lol");

        String s = c1.currentCommitSHA();
        c1.writeFile();




        File file = new File (inputString + "Objects/" + s);
        assertTrue ( file.exists() );
        
    }

    

    @Test
    void testGetContents() throws Exception {


        Commit c1 = new Commit ("Bo", "lol");

        String s =  c1.getContents( new File (inputString + "abc.txt") );
 
        assertEquals("AHAHAHA", s);


        
        
    }

    

    @Test
    void testGetTreeSHA() throws Exception {


        Commit c1 = new Commit ("Bo", "lol");

       String s =  c1.getTreeSHA();

       assertTrue(s != null);





        
    }

   

    @Test
    void testMakeNextCommit() throws Exception {

        Commit c1 = new Commit ("Bo", "lol");

        Commit c2 = c1.makeNextCommit("Bo", "lol2");

        assertTrue (c2 != null);
        assertTrue (c2.getAuthor() != null);

        assertTrue (c2.getSummary() != null);


        
    }

   

    

    @Test
    void testShaToTree() throws Exception {


//         C        c.shaToTree("")
// ommit c = new Commit ("Bari", "Summary");
        
    }

    
    
}