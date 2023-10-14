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
    public ArrayList<String> trees;
    public ArrayList<String> blobs;
    private int numberOfCommits;
    private File pointedAtFolder;
    private String startPath = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/";

    File tree;

    public Tree() throws IOException {

        File objects = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/");
        if (!objects.exists()) {
            objects.mkdir();
        }

        tree = new File(startPath + "Objects/Tree");
        if (!tree.exists()) {
            tree.createNewFile();
        }

        File file2 = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/");
        if (!file2.exists()) {
            file2.mkdir();
        }

        File index = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Index");
        if (!index.exists()) {
            index.createNewFile();
        }

        File treeIndex = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Tree");
        if (!treeIndex.exists()) {
            treeIndex.createNewFile();
        }

        this.numberOfCommits = 0;
        this.blobs = new ArrayList<String>();
        this.trees = new ArrayList<String>();
    }

    public Tree(String name) throws IOException {
        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/" + name);
        if (!file.exists()) {
            file.createNewFile();

        }

        File objects = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/");
        if (!objects.exists()) {
            objects.mkdir();
        }

        File index = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Index");
        if (!index.exists()) {
            index.createNewFile();
        }

        File treeIndex = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Tree");
        if (!treeIndex.exists()) {
            treeIndex.createNewFile();
        }

        this.numberOfCommits = 0;
        this.blobs = new ArrayList<String>();
        this.trees = new ArrayList<String>();

    }

    public void pointToFile(String folderName) {
        File folder = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/CommitTester.java");
        if (folder.isDirectory()) {

        }
    }

    // if type does not have "tree : " or "blob : " it must not be a valid string
    // input
    // if type contains "tree : " add it to the array list of trees and vice versa
    public boolean add(String fileName, String inputString) throws IOException, NoSuchAlgorithmException {

        String type = fileName;
        File fileToAdd = new File(inputString + fileName);

        if (!fileToAdd.exists()) {
            String isTree = fileName.substring(0, 6);
            if (!isTree.equalsIgnoreCase("tree :")) {
                throw new FileNotFoundException("Invalid file to add");
            }

        }

        if (fileToAdd.exists() && fileToAdd.isFile()) {
            String fileContents = Helper.fileContents(fileToAdd);
            String hashOfFile = Blob.getSha1(fileContents);

            String newEntryForTree = "Blob : " + hashOfFile + " : " + fileName;
            blobs.add(newEntryForTree);

            Blob b = new Blob(fileName);
            b.makeFile();


        } else if (fileToAdd.isDirectory()) {
            String folderContents = Helper.fileContents(fileToAdd);
            String newEntryForTree = "Tree : " + Helper.getSHA1(folderContents) + " : " + fileName;


            System.out.println("Added " + fileName);


            addDirectory(inputString + fileName + "/");

        } else {
            String newEntryForTree = fileName;
            trees.add(newEntryForTree);
        }

        // entries.add(fileToAdd)

        // the method accepts a filename, OR a tree string

        // tree : HASH : folderName

        writeTree();

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
    public boolean remove(String name) throws IOException {
        // removes a blob with its name



        for (int i = blobs.size()-1; i >= 0; i--)
        {
            if (blobs.get(i).indexOf (name) != -1)
            {
                blobs.remove(i);
            }
        }
        for (int i = trees.size()-1; i >= 0; i--)
        {
            if (trees.get(i).indexOf (name) != -1)
            {
                trees.remove(i);
            }
        }

        // for (String s : blobs) {
        //     if (s.contains(name)) {
        //         blobs.remove(s);
        //     }
        // }

        // for (String s : trees) {
        //     if (s.contains(name)) {
        //         trees.remove(s);
        //     }
        // }

        writeTree();
        return true;
    }


    public void generateBlob () throws NoSuchAlgorithmException, IOException
    {
        Blob blob = new Blob ("Tree");
        blob.makeFile();
        
    }

    public void save() throws Exception {
        if (numberOfCommits != calculateNumberOfCommits()) {
            numberOfCommits = calculateNumberOfCommits();
            String SHA1 = generateSHA1();
            Path objectFilePath = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects", SHA1);
            String originalString = returnStringOfCommits();
            byte[] originalSByte = originalString.getBytes();
            Files.write(objectFilePath, originalSByte);
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
                new FileReader("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Tree"));

        while (br.ready()) {
            record.append((char) br.read());
        }

        br.close();
        String s = record.toString() + trees.toString() + blobs.toString();
        return s;

    }

    public String allBlobs() {
        String out = "";
        for (String s : blobs) {
            out += s;
            out += "\n";
        }

        return out;

    }

    public String allTrees() {
        String out = "";
        for (String s : trees) {
            out += s;
            out += "\n";
        }

        return out;

    }

    public void writeTree() throws IOException {
        String toWrite = allTrees() + allBlobs();
        Helper.writeToFile(toWrite, "Tree", "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/");
    }

    public String addDirectory(String directoryPath) throws NoSuchAlgorithmException, IOException {
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {


            ArrayList <String> blobsInternal = new ArrayList<>();
            ArrayList <String> treesInternal = new ArrayList<>();



            int len = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/".length();

            String folderName = directoryPath.substring(len, directoryPath.length() - 1);

            System.out.println(folderName);

            String contents[] = directory.list(); // gotten from internet -->
            // https://www.tutorialspoint.com/how-to-get-list-of-all-files-folders-from-a-folder-in-java#:~:text=The%20List()%20method,of%20the%20files%20and%20directories.

            String output = "";

            for (String s : contents) {
                File temp = new File(directoryPath + s);

                // char sLast = s.charAt(s.length() - 1);
                // Path p = Paths.get(s);

                if (!temp.isDirectory()) {
                    
                    Blob b1 = new Blob(directoryPath + s);
                    b1.makeFile();
                    output += "Blob : " + b1.getSha1(b1.fileContents()) + " : " + s + " \n";
                    blobsInternal.add("Blob : " + b1.getSha1(b1.fileContents()) + " : " + s);


                    System.out.println("Blob Sha : " + b1.getSha1(b1.fileContents()));



                    

                } else {
                    Tree childTree = new Tree();
                    Blob b2 = new Blob(directoryPath + s);
                    String childSHA = childTree.addDirectory(directoryPath + s + "/");


                    System.out.println("Subtree Sha: " + childSHA);


                    output += "Tree : " + childSHA + " : " + s + "\n";
                    blobsInternal.add("Tree : " + childSHA + " : " + s);


                    


                }

            }

            String outputSHA = Blob.getSha1(output);

            StringBuilder sb = new StringBuilder("");
            for (String s : treesInternal) {
                sb.append(s);
            }
            for (String s : blobsInternal) {
                sb.append(s);
            }
        String input = sb.toString();
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sbuffer = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sbuffer.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        outputSHA = sbuffer.toString();

        outputSHA = Blob.getSha1(output);






            PrintWriter pw = new PrintWriter(
                    "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/" + outputSHA);
            pw.print(output);
            pw.close();

            trees.add("Tree : " + outputSHA + " : " + folderName);


            System.out.println("Done " + Blob.getSha1("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/" + outputSHA));


            String oldOutput = Blob.getSha1("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/" + outputSHA);
            return outputSHA;

        }
        return null;
    }
}
