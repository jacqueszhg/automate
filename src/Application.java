

import automaton.Automate;
import automaton.State;

import java.io.*;
import java.util.*;

public class Application {
    /**
     * Display the menu of the application
     */
    private static void menu(){
        System.out.println("-------------------- Menu -------------------");
        System.out.println("1. Smiley (to recognize one of the smileys)");
        System.out.println("2. HH:MM (to recognize a well-former hour:minute)");
        System.out.println("3. JJ/MM/AAAA (to recognize a well-formed date)");
        System.out.println("4. Email address (to recognize a well-formed email address)");
        System.out.println("5. Polynomial (to recognize a well-formed polynomial)");
        System.out.println("6. French licence plate (to recognize a well-formed licence plate)");
        System.out.println("7. HH:MM:SS (to recognize a well-former hour:minute:seconde)");
        System.out.println("8. Load your own automaton");
        System.out.println("9. Quit");
        System.out.println("Yout choise (1-9)?");
        System.out.println("Then I will ask you the string to analyze, Thank you");
        System.out.println("-------------------- Menu -------------------");
    }

    /**
     * Pauses the application
     * @param sc an object Scanner
     */
    private static void pause(Scanner sc){
        System.out.println("Press \"any key then Enter\" to pass");
        sc.next();
    }

    /**
     * Application of the automaton
     * @param args possible arguments for the application
     */
    public static void main(String[] args) {
        //Create the eobject Scanner
        Scanner sc = new Scanner(System.in);

        //Initialization of choice variables
        int choice = 0;
        int stop = 9;

        //Loop: The application continues to run until the user press quit
        while (choice != stop) {
            menu();
            boolean isNumber = false;

            //Loop: as long as the number entered does not correspond to one of the possibilities, we ask again
            while(!isNumber) {
                try {
                    System.out.println("Your choice : ");
                    isNumber = true;
                    choice = Integer.parseInt(sc.next());
                    if(choice > stop){
                        System.err.println("The choices are between 1 and "+stop);
                        isNumber = false;
                    }
                } catch (Exception e) {
                    isNumber = false;
                    System.err.println("Not a number");
                }
            }

            //Switch case: Depending on the number entered, a automaton is run or the application is stopped
            switch (choice) {
                case 1:
                    System.out.println("Your choice : Smiley");
                    load_smiley();
                    pause(sc);
                    break;
                case 2:
                    System.out.println("Your choice : HH:MM");
                    load_HHMM();
                    pause(sc);
                    break;
                case 3:
                    System.out.println("Your choice : JJ/MM/AAAA");
                    load_automate_par_fichier("automates/JJMMAAAA.txt");
                    pause(sc);
                    break;
                case 4:
                    System.out.println("Your choice : Email address");
                    load_automate_par_fichier("automates/EMAIL.txt");
                    pause(sc);
                    break;
                case 5:
                    System.out.println("Your choice : Polynomial");
                    load_automate_par_fichier("automates/POLYNOME.txt");
                    pause(sc);
                    break;
                case 6:
                    System.out.println("Your choice : French licence plate");
                    load_automate_par_fichier("automates/PLAQUE.txt");
                    pause(sc);
                    break;
                case 7:
                    System.out.println("Your choice : HH:MM:SS");
                    load_automate_par_fichier("automates/HHMMSS.txt");
                    pause(sc);
                    break;
                case 8:
                    System.out.println("Give the name of your file in the automates folder: ");
                    String name = sc.next();
                    load_automate_par_fichier("automates/"+name);
                    pause(sc);
                    break;
                case 9:
                    System.out.println("Quit");
                    sc.close();
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * The automaton to recognize a Smiley
     */
    private static void load_smiley(){
        Scanner sc = new Scanner(System.in);
        Automate smiley = new Automate("Smiley");
        smiley.setAlphabet(new ArrayList<>(Arrays.asList(':',';','-','=',')','(')));
        for(int i =0; i <5; i++){
            State state = new State("E"+i);
            smiley.addState(state);
        }

        for(int i = 0; i <5; i++){
            State state =smiley.getState("E"+i);
            switch (i){
                case 0:
                    smiley.setInitialState(smiley.getState("E0"));
                    smiley.addTransition(state,':', smiley.getState("E1"));
                    smiley.addTransition(state,';', smiley.getState("E2"));
                    break;
                case 1:
                    smiley.addTransition(state,'-', smiley.getState("E3"));
                    smiley.addTransition(state,'=', smiley.getState("E3"));
                    break;
                case 2:
                    smiley.addTransition(state,'-', smiley.getState("E3"));
                    break;
                case 3:
                    smiley.addTransition(state,'(', smiley.getState("E4"));
                    smiley.addTransition(state,')', smiley.getState("E4"));
                    break;
                case 4:
                    smiley.setEndState(new ArrayList<>(Arrays.asList(smiley.getState("E4"))));
                    break;
            }
        }

        displayAutomaton(smiley);

        System.out.println("Your string :");
        String sentence = sc.next();
        if(smiley.verifAutomaton(sentence)){
            System.out.println("OK");
        }else{
            System.err.println("KO");
        }
    }

    /**
     * The automaton to recognize an hour in the form HH:MM
     */
    private static void load_HHMM(){
        Scanner sc = new Scanner(System.in);
        Automate hhmm = new Automate("HHMM");
        hhmm.setAlphabet(new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9',':')));
        for(int i =0; i <7; i++){
            State state = new State("E"+i);
            hhmm.addState(state);
        }

        for(int i = 0; i <7; i++){
            State state =hhmm.getState("E"+i);
            switch (i){
                case 0:
                    hhmm.setInitialState(hhmm.getState("E0"));
                    hhmm.addTransition(state,'0', hhmm.getState("E1"));
                    hhmm.addTransition(state,'1', hhmm.getState("E1"));
                    hhmm.addTransition(state,'2', hhmm.getState("E2"));
                    break;
                case 1:
                    hhmm.addTransition(state,'0', hhmm.getState("E3"));
                    hhmm.addTransition(state,'1', hhmm.getState("E3"));
                    hhmm.addTransition(state,'2', hhmm.getState("E3"));
                    hhmm.addTransition(state,'3', hhmm.getState("E3"));
                    hhmm.addTransition(state,'4', hhmm.getState("E3"));
                    hhmm.addTransition(state,'5', hhmm.getState("E3"));
                    hhmm.addTransition(state,'6', hhmm.getState("E3"));
                    hhmm.addTransition(state,'7', hhmm.getState("E3"));
                    hhmm.addTransition(state,'8', hhmm.getState("E3"));
                    hhmm.addTransition(state,'9', hhmm.getState("E3"));

                    break;
                case 2:
                    hhmm.addTransition(state,'0', hhmm.getState("E3"));
                    hhmm.addTransition(state,'1', hhmm.getState("E3"));
                    hhmm.addTransition(state,'2', hhmm.getState("E3"));
                    hhmm.addTransition(state,'3', hhmm.getState("E3"));
                    break;
                case 3:
                    hhmm.addTransition(state,':', hhmm.getState("E4"));
                    break;
                case 4:
                    hhmm.addTransition(state,'0', hhmm.getState("E5"));
                    hhmm.addTransition(state,'1', hhmm.getState("E5"));
                    hhmm.addTransition(state,'2', hhmm.getState("E5"));
                    hhmm.addTransition(state,'3', hhmm.getState("E5"));
                    hhmm.addTransition(state,'4', hhmm.getState("E5"));
                    hhmm.addTransition(state,'5', hhmm.getState("E5"));
                    break;
                case 5:
                    hhmm.addTransition(state,'0', hhmm.getState("E6"));
                    hhmm.addTransition(state,'1', hhmm.getState("E6"));
                    hhmm.addTransition(state,'2', hhmm.getState("E6"));
                    hhmm.addTransition(state,'3', hhmm.getState("E6"));
                    hhmm.addTransition(state,'4', hhmm.getState("E6"));
                    hhmm.addTransition(state,'5', hhmm.getState("E6"));
                    hhmm.addTransition(state,'6', hhmm.getState("E6"));
                    hhmm.addTransition(state,'7', hhmm.getState("E6"));
                    hhmm.addTransition(state,'8', hhmm.getState("E6"));
                    hhmm.addTransition(state,'9', hhmm.getState("E6"));
                    break;
                case 6:
                    hhmm.setEndState(new ArrayList<>(Arrays.asList(hhmm.getState("E6"))));
                    break;
            }
        }

        displayAutomaton(hhmm);

        System.out.println("Your string :");
        String sentence = sc.next();
        if(hhmm.verifAutomaton(sentence)){
            System.out.println("OK");
        }else{
            System.err.println("KO");
        }
    }

    /**
     * Loads any automaton with a file
     * @param fileName the file containing the automaton
     */
    private static void load_automate_par_fichier(String fileName) {
        //Initialization to read the file
        Scanner scan = null;
        Automate automaton = null;
        try {
            scan = new Scanner(new File(fileName));
            //Loop: Reads the file to the end
            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                //Split the line
                String[] wordLine = line.split(" ");

                //Initialization of the automaton with its nom
                if (wordLine[0].equals("NAME")) {
                    automaton = new Automate(wordLine[1]);

                    //Initialization of the alphabet
                } else if (wordLine[0].equals("ALPHABET")) {
                    ArrayList<Character> alphabet = new ArrayList<>();
                    for (int i = 1; i < wordLine.length; i++) {
                        Character c = null;
                        if (wordLine[i].equals("space")) {
                            c = ' ';
                        } else {
                            c = wordLine[i].charAt(0);
                        }
                        alphabet.add(c);
                    }
                    automaton.setAlphabet(alphabet);

                    //Initialization of the initial state
                } else if (wordLine[0].equals("INITIAL")) {
                    State etatInitiale = automaton.getState(wordLine[1]);
                    automaton.setInitialState(etatInitiale);

                    //Initialization of the end-states
                } else if (wordLine[0].equals("FINAL")) {
                    ArrayList<State> finaux = new ArrayList<>();
                    for (int i = 1; i < wordLine.length; i++) {
                        State etat = automaton.getState(wordLine[i]);
                        finaux.add(etat);
                    }
                    automaton.setEndState(finaux);

                    //Initialization of states and transitions
                } else {
                    if (!automaton.containStateName(wordLine[0])) {
                        automaton.addState(new State(wordLine[0]));
                    }
                    if (!automaton.containStateName(wordLine[2])) {
                        automaton.addState(new State(wordLine[2]));
                    }
                    State state1 = automaton.getState(wordLine[0]);
                    State state2 = automaton.getState(wordLine[2]);
                    if (wordLine[1].equals("space")) {
                        automaton.addTransition(state1, ' ', state2);
                    } else {
                        automaton.addTransition(state1, wordLine[1].charAt(0), state2);
                    }
                }
            }

            Scanner sc = new Scanner(System.in);

            displayAutomaton(automaton);

            System.out.println("Your string :");
            String sentence = sc.nextLine();
            if (automaton.verifAutomaton(sentence)) {
                System.out.println("OK");
            } else {
                System.err.println("KO");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }

    /**
     * Method to ask whether we display the automaton or not
     * @param automaton the automaton that we want display
     */
    public static void displayAutomaton(Automate automaton){
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want display the automaton ?");
        System.out.println("1. yes");
        System.out.println("2. no");

        boolean isNumber=false;
        int choice = 0;
        while(!isNumber) {
            try {
                isNumber = true;
                choice = Integer.parseInt(sc.next());
            } catch (Exception e) {
                isNumber = false;
                System.err.println("Not a number");
            }
        }

        if(choice == 1){
            System.out.println(automaton.toString());
        }
    }
}
