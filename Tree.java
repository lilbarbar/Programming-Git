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

}
