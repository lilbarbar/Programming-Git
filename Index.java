import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Index {

    File tree;

    String startPath = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/";

    HashMap<String, String> blobs = new HashMap();

    File index = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Index");

    public Index() throws IOException {

        index.createNewFile();

        PrintWriter pw = new PrintWriter(index);

        String words = "";
        pw.print(words);

        pw.close();

    }

    public void init() throws IOException {

        File objects = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects");
        if (!objects.exists()) {
            objects.mkdirs();
        }

    }

    public void add(String name) throws NoSuchAlgorithmException, IOException {

        Blob blob = new Blob(name);
        blob.makeFile();

        blobs.put(blob.getFileName(), blob.getSha1(blob.fileContents()));
        printBlobs();
    }

    public void remove(String fileName) {
        blobs.remove(fileName);
        printBlobs();

    }

    public void printBlobs() {
        try {
            PrintWriter pw = new PrintWriter(
                    "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Index");

            String s = "";

            for (HashMap.Entry<String, String> entry : blobs.entrySet()) {

                File file = new File(startPath + entry.getKey());
                if (!file.isDirectory()) {
                    s += "Blob : " + entry.getValue() + " : " + entry.getKey() + "\n";

                } else {
                    s += "Tree : " + entry.getValue() + " : " + entry.getKey() + "\n";

                }

            }

            pw.print(s);
            pw.close();

        } catch (Exception e) {

        }

    }

    public static String indexContents() throws Exception {
        StringBuilder record = new StringBuilder("");
        BufferedReader br = new BufferedReader(
                new FileReader("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/Tree-Objects/Index"));

        while (br.ready()) {
            record.append((char) br.read());
        }

        br.close();
        String s = record.toString();
        return s;
    }
}
