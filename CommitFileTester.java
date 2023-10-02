import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CommitFileTester {

    public static void main(String[] args) throws Exception {
        Commit c = new Commit("Billy", "W Billy Commit!");
        c.commitFile();
        c.writeFile();

        c.tree.add("yo.txt");
        c.tree.add("jump.txt");

        c.commitFile();
        c.writeFile();

        c.seePrev();
        c.writeFile();

        File directory = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects");
        String start = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/";

        String contents[] = directory.list();

        int goodFiles = 0;
        for (String s : contents) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 ||
                    info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }

        System.out.println("\f");

        Commit c2 = new Commit("Billy", "W Billy Commit!");
        c2.commitFile();
        c2.writeFile();

        c2.tree.add("tesout.txt");
        c2.tree.add("Hehe");
        c2.tree.add("FolderB");

        c2.commitFile();
        c2.writeFile();

        c2.seePrev();
        c2.writeFile();

        goodFiles = 0;
        String contents2[] = directory.list();
        for (String s : contents2) {
            File file = new File(start + s);
            String info = Helper.fileContents(file);
            if (info.indexOf("Next") != -1 || info.indexOf("Previous") != -1 || info.indexOf("Current") != -1) {
                goodFiles++;
            }

        }

    }
}