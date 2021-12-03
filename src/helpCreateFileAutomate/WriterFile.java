package helpCreateFileAutomate;

import java.io.*;

/**
 * Class that helps in the creation of the automate file transitions
 */
public class WriterFile {
    /**
     * EN : Help in the creation of transitions of the automaton
     * || FR : Aide dans la création des transitions de l'automate
     */
    public static void main(){
        PrintWriter write = null;
        int n = 5;
        try {
            write = new PrintWriter(new BufferedWriter(new FileWriter("src/helpCreateFileAutomate/text.txt")));
            /* Here the characters transition of your automaton, put a space between each characters*/
            //String s = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
            String s = "0 1 2 3 4 5 6 7 8 9";
            //String s = "0 1 2 3 4 5";
            String[] tab = s.split(" ");

            for(int i = 0; i<tab.length; i++){
                write.println("stateStart"+" "+ tab[i] +" "+"stateEnd"); //
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            write.close();
        }
    }
}


