import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public interface Helper {

    String introPath = "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/";

    public static String fileContents(File file) throws IOException {

        if (file.isFile()) {
            StringBuilder record = new StringBuilder("");
            BufferedReader br = new BufferedReader(new FileReader(file));

            while (br.ready()) {
                record.append((char) br.read());
            }

            br.close();
            String s = record.toString();
            return s;

        } else {
            String folderContents = "";
            String contents[] = file.list();
            for (String s : contents) {
                File f = new File("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/" + s);
                if (f.exists()) {
                    folderContents += Helper.fileContents(f);
                }
            }

            return folderContents;
        }

    }

    public static void writeToFile(String content, String fileName, String introPath) throws IOException {
        File file = new File(introPath + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        PrintWriter pw = new PrintWriter(file);
        pw.print(content);
        pw.close();

    }

    public static String getSHA1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static void audit() throws FileNotFoundException { // credit for sentry for teaching me how to do a for each
                                                              // loop
                                                              // with a Hashmap
        try {
            PrintWriter pw = new PrintWriter("AccountAudit.txt");

            String s = "";
            for (HashMap.Entry<String, Double> entry : accounts.entrySet()) {
                s += entry.getKey() + " " + entry.getValue() + "\n";
            }

            pw.print(s);
            pw.close();

        } catch (Exception e) {

        }

    }

    

}
