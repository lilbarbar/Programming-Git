import java.io.File;

public class RandomTester {

    public static void main (String [] args)
    {



        File folderA = new File ("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/FolderA/");
        File folderB = new File ("/Users/lilbarbar/Desktop/Honors Topics/Programming-Git/FolderB/");

        if (folderA.exists())
        {
            folderA.delete();



        }

        if (folderB.exists())
        {
            folderB.delete();


        }


    }
    
}
