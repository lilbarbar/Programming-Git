import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.security.*;

public class Commit {

    private String author;
    private String summary;
    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    


    private String prevSHA;
    public String currentSHA;
    private String currentTreeSHA;
    private String nextSHA;
    public Tree tree;
    private int indexOfCurrent;

    static String inputString = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/";

    ArrayList<String> hashes = new ArrayList<>();

    private int totalCommits = 0;
    private int commitIndex = 0;

    public void swapTreeAndIndex() throws Exception {
        String words = Index.indexContents();
        System.out.println(words);

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Tree");

        if (!file.exists()) {
            file.createNewFile();
        }

        PrintWriter pw = new PrintWriter(file);

        pw.print(words);
        pw.close();

        // PrintWriter indexClearer = new PrintWriter(
        // "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Index");
        // indexClearer.print("");
        // indexClearer.close();
    }

    public Commit(String author, String summary) throws Exception {

        makeTree();

        // swapTreeAndIndex();

        this.author = author;
        this.summary = summary;
        // tree.add("test.txt");
        // tree.generateBlob();

        indexOfCurrent = 0;

    }

    public Commit(String author, String summary, String prevSHA) throws Exception {

        this(author, summary);
        this.prevSHA = prevSHA;
        hashes.add(prevSHA);
        indexOfCurrent = 1;

    }

    public static String getDate() {
        Date date = new Date();
        return date.toString().substring(0, 11) + "2023";
    }

    public String hashesToString() {
        return hashes.toString();
    }

    public static String generateSHA(String input) throws NoSuchAlgorithmException {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile() throws NoSuchAlgorithmException, IOException {
        PrintWriter pw = new PrintWriter(
                "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/" + currentSHA);

        pw.print(getTreeSHA() + "\n");
        if (prevSHA != null) {
            pw.print("Previous: " + prevSHA + "\n");

        }
        if (nextSHA != null) {
            pw.print("Next: " + nextSHA + "\n");

        }
        pw.print(author + "\n" + getDate() + "\n" + summary);

        pw.close();

        File head = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Head");
        PrintWriter pw2 = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/Head");
        pw2.print(currentCommitSHA());
        pw2.close();

        // PrintWriter pw3 = new PrintWriter("/Users/lilbarbar/Desktop/Honors
        // Topics/Programming-Git/Objects/Tree");

        // if (prevSHA != null) {
        // pw3.print("Tree: " + prevSHA + " (prev)");
        // } else {
        // pw3.print("Previous Tree: none");

        // }
        // pw3.close();

        // System.out.println(prevSHA + " " + currentSHA + " " + nextSHA);

    }

    public void commitFile() throws NoSuchAlgorithmException, IOException {
        currentSHA = generateSHA(tree.allContents() + summary + getDate() + author);
        hashes.add(currentSHA);
        indexOfCurrent = hashes.indexOf(currentSHA);

        if (indexOfCurrent == hashes.size() - 1) {
            nextSHA = null;
        } else {
            nextSHA = hashes.get(indexOfCurrent + 1);
        }

        if (indexOfCurrent == 0) {
            prevSHA = null;
        } else {
            prevSHA = hashes.get(indexOfCurrent - 1);
        }

    }

    public void seeNext() throws FileNotFoundException {
        if (indexOfCurrent >= hashes.size() - 1) {
            nextSHA = null;
        } else {
            indexOfCurrent++;
        }

        currentSHA = hashes.get(indexOfCurrent);

        if (indexOfCurrent == hashes.size() - 1) {
            nextSHA = null;
        } else {
            nextSHA = hashes.get(indexOfCurrent + 1);
        }

        if (indexOfCurrent == 0) {
            prevSHA = null;
        } else {
            prevSHA = hashes.get(indexOfCurrent - 1);
        }

    }

    public void seePrev() throws FileNotFoundException {
        if (indexOfCurrent == 0) {
            // move back if we can...

        } else {
            indexOfCurrent--;
        }

        currentSHA = hashes.get(indexOfCurrent);

        if (indexOfCurrent == hashes.size() - 1) {
            nextSHA = null;
        } else {
            nextSHA = hashes.get(indexOfCurrent + 1);
        }

        if (indexOfCurrent == 0) {
            prevSHA = null;
        } else {
            prevSHA = hashes.get(indexOfCurrent - 1);
        }

    }

    public Commit makeNextCommit(String author, String summary)
            throws NoSuchAlgorithmException, IOException, Exception {

        Commit newCom = new Commit(author, summary, currentCommitSHA());

        nextSHA = newCom.currentCommitSHA();

        writeFile();

        return newCom;
    }

    public String makeTree() throws NoSuchAlgorithmException, IOException {
        tree = new Tree();
        currentSHA = tree.generateSHA1();


        addTree();

        return tree.generateSHA1();
    }

    public String getTreeSHA() throws NoSuchAlgorithmException {
        return tree.generateSHA1();
    }


    public void addTree () throws NoSuchAlgorithmException, IOException
    {
        Tree t2 = new Tree (getTreeSHA());
        File f = new File (inputString + "Objects/" + getTreeSHA());
        PrintWriter pw = new PrintWriter(f);


        File treeMain = new File (inputString + "Objects/Tree");
        String s = Helper.fileContents(treeMain);

        pw.print(s);

    }

    public String currentCommitSHA() throws NoSuchAlgorithmException, IOException {
        currentSHA = generateSHA(currentTreeSHA + "\n" + prevSHA + nextSHA + author + getDate() + summary);
        return currentSHA;
    }

    public String shaToTree(String sha) throws Exception {
        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Objects/" + sha);

        String treeContents = getContents(file);

        int indexOfNewLine = treeContents.indexOf("\n");

        return generateSHA(treeContents.substring(0, 40));

    }

    public static String getContents(File f) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        StringBuilder record = new StringBuilder("");
        BufferedReader br = new BufferedReader(
                new FileReader(f));

        while (br.ready()) {
            record.append((char) br.read());
        }

        br.close();
        String s = record.toString();
        return s;
    }




    public static void createFile()
    {

    }


    public static void createFolder()
    {

    }




    public  static void checkout(String SHA) throws Exception {

        File commitFile = new File (inputString + "Objects/" + SHA);

        


        String treeFileName =    Helper.fileContents(commitFile).substring (0,40);   





        

        File treeFile = new File (inputString + "Objects/" + treeFileName);

        File mainTree = new File (inputString + "Objects/Tree");


        
        
        //String contents = Helper.fileContents(treeFile);

        String input = inputString;



         




        //copied oline from Java Digital OCean

		try {
			BufferedReader reader = new BufferedReader(new FileReader(treeFile));
			String line = reader.readLine();
            File objects = new File (inputString + "Objects/");


            String contents[] = objects.list();

			while (line != null) {
				System.out.println(line);

                input = inputString;

                if (line.contains ("Tree :"))
                {
                    String noType = line.substring(6);
                    int indexOfColon = noType.indexOf(":");
                    String hash = noType.substring(0,indexOfColon);
                    String name = noType.substring(indexOfColon + 1);


                    File file = new File (input + name);
                    if (!file.exists())
                    {
                        file.mkdir();
                    }


                    input += name + "/";


                    for (String s : contents)
                    {
                        if (s.contains(hash) || hash.contains(s))
                        {
                            File f2 = new File (input + s)

                        }
                    }




                }
                else
                {
                    String noType = line.substring(6);
                    int indexOfColon = noType.indexOf(":");
                    String hash = noType.substring(0,indexOfColon);
                    String name = noType.substring(indexOfColon + 1);



                

                    for (String s : contents)
                    {
                        if (s.contains(hash) || hash.contains(s))
                        {
                            PrintWriter pw = new PrintWriter(input + name);
                            String stuff = Helper.fileContents( new File (input + "Objects/" + hash) );
                            pw.print(stuff);
                            pw.close();

                        }
                    }

                    
                    
                    


                }
				// read next line
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


    }

}
