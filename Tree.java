import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Tree {
    private ArrayList<String> trees;
    private ArrayList<String> blobs;
    private int numberOfCommits;

    public Tree() throws FileNotFoundException {

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/");
        if (!file.exists()) {
            file.mkdir();
        }

        File treeIndex = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Tree");
        PrintWriter pw = new PrintWriter(treeIndex);

        this.numberOfCommits = 0;
        this.blobs = new ArrayList<String>();
        this.trees = new ArrayList<String>();
    }

    // if type does not have "tree : " or "blob : " it must not be a valid string
    // input
    // if type contains "tree : " add it to the array list of trees and vice versa
    public boolean add(String type) {
        if (type.contains("tree : ") || type.contains("blob : ")) {
            if (type.contains("tree : ") && !trees.contains(type)) {
                trees.add(type);
                return true;
            } else if (type.contains("blob : ") && !blobs.contains(type)) {
                blobs.add(type);
                return true;
            }
        }
        return false;
    }

    // if the string contains ".txt" then you must be tyring to remove via file name
    // loop through the blobs
    // loop through the trees
    // note that if type is a sha1 file, you must loop through both the trees and
    // the blob array list
    public boolean remove(String type) {
        // removes a blob with its name
        if (type.contains(".txt")) {
            String temp = "";
            for (String s : blobs) {
                temp = s.substring(50);
                if (temp.equals(type)) {
                    blobs.remove(s);
                    return true;
                } else {
                    temp = "";
                }
            }
        } else {
            // removing a tree with its hash
            String temp = "";
            for (String s : trees) {
                temp = s.substring(7);
                if (temp.equals(type)) {
                    trees.remove(s);
                    return true;
                } else {
                    temp = "";
                }
            }
            // trying to remove a blob with its hash
            for (String s : blobs) {
                temp = s.substring(7, 47);
                System.out.println(temp);
                if (temp.equals(type)) {
                    blobs.remove(s);
                    return true;
                } else {
                    temp = "";
                }
            }

        }
        return false;
    }

    public void save() throws Exception {
        if (numberOfCommits != calculateNumberOfCommits()) {
            numberOfCommits = calculateNumberOfCommits();
            String SHA1 = generateSHA1();
            Path objectFilePath = Paths
                    .get("C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\Programming-Git-Bari\\objects", SHA1);
            byte[] originalString = returnStringOfCommits().getBytes();
            Files.write(objectFilePath, originalString);
        } else {
            throw new Exception("Cannot save when you have not added a new tree or blob");
        }
    }

    // return the number of string objects in both the blobs and trees array list
    public int calculateNumberOfCommits() {
        return trees.size() + blobs.size();
    }

    // generate the sha1 hash
    public String generateSHA1() throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder("");
        for (String s : trees) {
            sb.append(s);
        }
        for (String s : blobs) {
            sb.append(s);
        }
        String input = sb.toString();
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sbuffer = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sbuffer.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sbuffer.toString();
    }

    // returns a giant string of all the commits withing blobs array and trees array
    public String returnStringOfCommits() {
        StringBuilder sb = new StringBuilder("");
        int numBlobs = blobs.size();
        int numTrees = trees.size();
        for (int i = 0; i < numBlobs; i++) {
            if (i != numBlobs - 1) {
                sb.append(blobs.get(i) + "\n");
            } else if (numTrees >= 1) {
                sb.append(blobs.get(i) + "\n");
            } else {
                sb.append(blobs.get(i));
            }
        }
        for (int i = 0; i < numTrees; i++) {
            if (i != numTrees - 1) {
                sb.append(trees.get(i) + "\n");
            } else {
                sb.append(trees.get(i));
            }
        }
        return sb.toString();
    }

    public String allContents() throws IOException { // got from prev shared code

        StringBuilder resultStringBuilder = new StringBuilder();
        StringBuilder record = new StringBuilder("");
        BufferedReader br = new BufferedReader(
                new FileReader("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Tree"));

        while (br.ready()) {
            record.append((char) br.read());
        }

        br.close();
        String s = record.toString();
        return s;

    }

    public String addDirectory(String directoryPath) throws NoSuchAlgorithmException, IOException {
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            String contents[] = directory.list(); // gotten from internet -->
            // https://www.tutorialspoint.com/how-to-get-list-of-all-files-folders-from-a-folder-in-java#:~:text=The%20List()%20method,of%20the%20files%20and%20directories.

            String output = "";

            for (String s : contents) {
                File temp = new File(s);
                char sLast = s.charAt(s.length() - 1);
                Path p = Paths.get(s);
                if (temp.isDirectory()) {
                    System.out.println("Blob...");
                    Blob b1 = new Blob(directoryPath + s);
                    output += "Blob : " + b1.getSha1(b1.fileContents()) + " : " + s + " \n";
                    System.out.println(s);

                } else {
                    System.out.println("Tree...");
                    Tree childTree = new Tree();
                    Blob b2 = new Blob(directoryPath + s);
                    String childSHA = childTree.addDirectory(directoryPath + s);

                    output += "Tree : " + childSHA + " : " + s + "\n";
                }

            }

            String outputSHA = Blob.getSha1(output);
            PrintWriter pw = new PrintWriter(
                    "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/" + outputSHA);
            pw.print(output);
            pw.close();

            return Blob.getSha1("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/" + outputSHA);

        }
        return null;
    }
}
