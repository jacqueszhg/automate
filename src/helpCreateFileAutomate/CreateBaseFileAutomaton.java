package helpCreateFileAutomate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateBaseFileAutomaton {
    public static void main(String[] args) throws IOException {
        PrintWriter write;
        int n = 5;
        write = new PrintWriter(new BufferedWriter(new FileWriter("src/helpCreateFileAutomate/baseFile.txt")));
        write.println("NAME #write here the name of your automaton || Exemple : Automaton1#");
        write.println("ALPHABET #write here the alphabet use by your automaton || Exemple : A B C D a z - è _ @#");
        write.println("#write here the transitions of your automaton#");
        write.println("#### Exemple ####");
        write.println("E0 B E1");
        write.println("E0 A E1");
        write.println("E1 3 E2");
        write.println("#### End Exemple ####");
        write.println("INITIAL #write here the initial state automaton#");
        write.println("FINAL #write here the end-states of your automaton#");
        write.close();
    }
}
