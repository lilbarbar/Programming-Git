import java.util.ArrayList;

public class Tree {
    private ArrayList<String> trees;
    private ArrayList<String> blobs;

    public Tree() {

    }

    // if type does not have "tree : " or "blob : " it must not be a valid string
    // input
    // if type contains "tree : " add it to the array list of trees and vice versa
    public void add(String type) {
        if (type.contains("tree : ") || type.contains("blob : ")) {
            if (type.contains("tree : ")) {
                trees.add(type);
            } else if (type.contains("blob : ")) {
                blobs.add(type);
            }
        }
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
}
