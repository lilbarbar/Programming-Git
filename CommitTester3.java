public class CommitTester3 {

    public static void main(String[] args) throws Exception {

        Commit c1 = new Commit("Bari", "Hehe");

      

        Commit c2 = c1.makeNextCommit("Bari", "Hehe2");

        Commit c3 = c2.makeNextCommit("Bari", "Hehe3");
        


        c3.tree.add("FolderA", "/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/");


        c3.writeFile();

        c3.checkout("c3fc1720e9a5e6c46c4eee8799ec58132b35b189");

    }

}
