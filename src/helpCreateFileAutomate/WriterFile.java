package helpCreateFileAutomate;

import java.io.*;

/**
 * Class that helps in the creation of the automate file transitions
 */
public class WriterFile {
    public static void main(String[] args) throws IOException {
        PrintWriter write;
        int n = 5;
        write = new PrintWriter(new BufferedWriter(new FileWriter("src/CréationTransition/text.txt")));

        //String s = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
        String s = "0 1 2 3 4 5 6 7 8 9";
        //String s = "0 1 2 3 4 5";
        String[] tab = s.split(" ");

        for(int i = 0; i<tab.length; i++){
            write.println("S1"+" "+ tab[i] +" "+"S2");
        }
        write.close();
    }
}


