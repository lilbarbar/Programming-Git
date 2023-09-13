import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Tree {
    private ArrayList<String> trees;
    private ArrayList<String> blobs;
    private int numberOfCommits;

    public Tree() {
        this.numberOfCommits = 0;
    }

    // if type does not have "tree : " or "blob : " it must not be a valid string
    // input
    // if type contains "tree : " add it to the array list of trees and vice versa
    public boolean add(String type) {
        if (type.contains("tree : ") || type.contains("blob : ")) {
            if (type.contains("tree : ")) {
                trees.add(type);
                return true;
            } else if (type.contains("blob : ")) {
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
        if (type.contains(".txt")) {
            String temp = "";
            for (String s : blobs) {
                temp = s.substring(50);
                if (temp.equals(type))
                    blobs.remove(s);
                return true;
            }
        } else {
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
            for (String s : blobs) {
                temp = s.substring(7, 50);
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

    public void save() throws NoSuchAlgorithmException {
        if (numberOfCommits < calculateNumberOfCommits()) {
            String SHA1 = generateSHA1();

        }
    }

    // return the number of string objects in both the blobs and trees array list
    public int calculateNumberOfCommits() {
        return trees.size() + blobs.size();
    }

    public String generateSHA1() throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder("");
        for (String s : trees) {
            sb.append(s);
        }
        for (String s : trees) {
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
}
