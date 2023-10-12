import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class TreeTester2 {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        String input = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/";

        Tree tree = new Tree();
        tree.add("def.txt", input);
        System.out.println(tree.allBlobs());
        tree.add("lmao.txt", input);
        tree.remove("896c3840f9e63d1d245943215077a29aeaf9e41c");


        tree.add("FolderA", input);
        tree.add("FolderB", input);

        

        System.out.println(tree.allContents());

    }

}
