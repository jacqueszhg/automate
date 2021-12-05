package application;

import automaton.Automaton;
import automaton.State;

import java.io.*;
import java.util.*;

/**
 * EN : The application
 * || FR : L'application
 * @author : jacques-zheng
 */
public class Application {
    /**
     * EN : Display the menu of the application in english
     * || FR :: Affichage du menu de l'application en anglais
     */
    private static void menuEN(){
        System.out.println("-------------------- Menu -------------------");
        System.out.println("1. Smiley (to recognize one of the smileys)");
        System.out.println("2. HH:MM (to recognize a well-former hour:minute)");
        System.out.println("3. JJ/MM/AAAA (to recognize a well-formed date)");
        System.out.println("4. Email address (to recognize a well-formed email address)");
        System.out.println("5. Polynomial (to recognize a well-formed polynomial)");
        System.out.println("6. French licence plate (to recognize a well-formed licence plate)");
        System.out.println("7. HH:MM:SS (to recognize a well-former hour:minute:seconde)");
        System.out.println("8. Load your own automaton");
        System.out.println("9. Download base file for create an automaton");
        System.out.println("10. Quit");
        System.out.println("-------------------- Menu -------------------");
    }

    /**
     * EN : Display the menu of the application in french
     * || FR :: Affichage du menu de l'application en français
     */
    private static void menuFR(){
        System.out.println("-------------------- Menu -------------------");
        System.out.println("1. Smiley (pour reconnaitre un des smileys)");
        System.out.println("2. HH:MM (pour reconnaitre une heure:minute bien formee)");
        System.out.println("3. JJ/MM/AAAA (pour reconnaitre une date bien formee)");
        System.out.println("4. Adresse electronique (pour reconnaitre une adresse electronique bien formee)");
        System.out.println("5. Polynômes (pour reconnaitre un polynôme bien forme)");
        System.out.println("6. Plaque d'immatriculation française (pour reconnaitre une plaque d'immatriculation bien formee)");
        System.out.println("7. HH:MM:SS (pour reconnaitre une heure:minute:seconde bien formee)");
        System.out.println("8. Charger votre propre automate");
        System.out.println("9. Telecharger le fichier de base pour la creation d'un automate");
        System.out.println("10. Arrêt");
        System.out.println("-------------------- Menu -------------------");
    }

    /**
     * EN : Pauses the application
     * || FR : Met en pause l'application
     * @param sc EN : an object Scanner || FR : un objet Scanner
     * @param language EN : the language for the display || FR : la langue pour l'affichage
     */
    private static void pause(Scanner sc, int language){
        if (language == 1){
            System.out.println("Press \"any key then Enter\" to continue");
        }else{
            System.out.println("Appuyer \"sur n'importe touche puis Entrer\" pour continuer");
        }
        sc.next();
    }

    /**
     * EN : Sleep the application
     * || FR : Stop l'application pendant un certain temps
     * @param timeMilli EN : the time of sleep || FR : le temps du stop
     */
    private static void sleep(int timeMilli){
        try {
            Thread.sleep(timeMilli);
        } catch (InterruptedException e) {
            System.err.println("No sleep");
        }
    }

    /**
     * EN : The automaton to recognize a Smiley
     * || FR : L'automate qui reconnait un Smiley
     * @param language EN : the language for the display || FR : la langue pour l'affichage
     */
    private static void load_smiley(int language){
        Scanner sc = new Scanner(System.in);
        Automaton smiley = new Automaton("Smiley");
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

        displayAutomaton(smiley,language);
        if(language ==1){
            System.out.println("Your string :");
        }else{
            System.out.println("Votre chaine :");
        }
        String sentence = sc.next();
        if(smiley.verifAutomaton(sentence,language)){
            System.out.println("OK");
        }else{
            System.err.println("KO");
        }
        pause(sc,language);
        downloadDotFile(smiley,language);
    }

    /**
     * EN : The automaton to recognize an hour in the form HH:MM
     * || FR : L'automate qui reconnait une heure dans la forme HH:MM
     * @param language EN : the language for the display || FR : la langue pour l'affichage
     */
    private static void load_HHMM(int language){
        Scanner sc = new Scanner(System.in);
        Automaton hhmm = new Automaton("HHMM");
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

        displayAutomaton(hhmm,language);

        if(language ==1){
            System.out.println("Your string :");
        }else{
            System.out.println("Votre chaine :");
        }

        String sentence = sc.next();
        if(hhmm.verifAutomaton(sentence,language)){
            System.out.println("OK");
        }else{
            System.err.println("KO");
        }
        pause(sc,language);
        downloadDotFile(hhmm,language);
    }

    /**
     * EN : Loads any automaton with a file situate in the folder fileAutomaton
     * || FR : Charge un automate à partir d'un fichier placer dans le dossier fileAutomaton
     * @param fileName EN : the file containing the automaton || FR : le fichier qui coneitn l'automate
     * @param language EN : the language for the display || FR : la langue pour l'affichage
     */
    private static  void load_automate_by_file(String fileName, int language){
        Scanner scan = null;
        Automaton automaton = null;
        boolean badSyntaxe = false;
        boolean ajouter = true;
        int errorLine = 0;

        try {
            scan = new Scanner(new File(fileName));

            //Loop: Reads the file to the end
            while (scan.hasNextLine() && !badSyntaxe && ajouter) {
                errorLine++;
                String line = scan.nextLine();

                //Split the line
                String[] wordLine = line.split(" ");

                //Initialization of the automaton with is name
                if (wordLine[0].equals("NAME")) {
                    automaton = new Automaton(wordLine[1]);

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
                } else if(wordLine.length  > 2){
                    //Check if the automaton contain or not the states
                    if (!automaton.containStateName(wordLine[0])) {
                        automaton.addState(new State(wordLine[0]));
                    }
                    if (!automaton.containStateName(wordLine[wordLine.length-1])) {
                        automaton.addState(new State(wordLine[wordLine.length-1]));
                    }

                    //Get the sates use in the transition
                    State state1 = automaton.getState(wordLine[0]);
                    State state2 = automaton.getState(wordLine[wordLine.length-1]);

                    //Add transition  : state1 to state2
                    for (int i = 1; i < wordLine.length-1; i++){
                        if(wordLine[i].equals("alphaUpper")){
                            for(Character c = 'A'; c <= 'Z'; c++){
                                ajouter = automaton.addTransition(state1,c,state2);
                            }
                        }else if(wordLine[i].equals("alphaLower")){
                            for(Character c = 'a'; c <= 'z'; c++){
                                ajouter = automaton.addTransition(state1,c,state2);
                            }
                        }else if(wordLine[i].equals("alpha")){
                            for(Character c = 'A'; c <= 'Z'; c++){
                                ajouter = automaton.addTransition(state1,c,state2);
                            }
                            for(Character c = 'a'; c <= 'z'; c++){
                                ajouter = automaton.addTransition(state1,c,state2);
                            }

                        }else if(wordLine[i].equals("num")){
                            for(Character c = '0'; c <= '9'; c++){
                                ajouter = automaton.addTransition(state1,c,state2);
                            }

                        }else if(wordLine[i].equals("punct")){
                            String punct = "][!\"#$%&'()*+,./:;<=>?@\\^_`{|}~-";
                            for (int c = 0; c < punct.length(); c++){
                                ajouter = automaton.addTransition(state1, punct.charAt(c), state2);
                            }

                        }else if(wordLine[i].equals("space")){
                            ajouter = automaton.addTransition(state1,' ',state2);

                        }else if(wordLine[i].length() == 6){
                            if(wordLine[i].charAt(0) == '[' && wordLine[i].charAt(5) == ']' && wordLine[i].charAt(2) == '.' && wordLine[i].charAt(3) == '.'){
                                for (char c = wordLine[i].charAt(1); c <= wordLine[i].charAt(4); c++){
                                    ajouter = automaton.addTransition(state1,c,state2);
                                }
                            }else {
                                badSyntaxe = true;
                            }
                        }else if(wordLine[i].length() == 1){
                            ajouter = automaton.addTransition(state1,wordLine[i].charAt(0),state2);
                        }else {
                            badSyntaxe = true;
                        }
                    }
                }else{
                    badSyntaxe = true;
                }
            }

            if(!badSyntaxe && ajouter){
                if(language == 1){
                    System.out.println("Your choice : "+automaton.getName());
                }else{
                    System.out.println("Votre choix : "+automaton.getName());
                }
                System.out.println("");

                Scanner sc = new Scanner(System.in);

                displayAutomaton(automaton,language);

                if(language == 1 ){
                    System.out.println("Your string :");
                }else{
                    System.out.println("Votre chaine :");
                }
                String sentence = sc.nextLine();
                if (automaton.verifAutomaton(sentence,language)) {
                    System.out.println("OK");
                } else {
                    System.err.println("KO");
                }
                pause(sc,language);
                downloadDotFile(automaton,language);
            }else {
                if(!ajouter){
                    if(language == 1){
                        System.err.println("Using characters not define in the alphabet at line "+errorLine);
                    }else{
                        System.err.println("Utilisation de caractère non présent dans l'alphabet à la ligne "+errorLine);
                    }
                }else{
                    if(language == 1){
                        System.err.println("Bad syntaxe in the file at line "+errorLine);
                    }else{
                        System.err.println("Mauvaise syntaxe dans le fichier à la ligne "+errorLine);
                    }
                }

                sleep(2000);
            }
        }catch (FileNotFoundException e){
            if(language==1){
                System.err.println("File not found");
            }else{
                System.err.println("Fichier introuvable");
            }
            sleep(2000);
        }
    }

    /**
     * EN : Method to ask whether we display the automaton or not
     * || FR : Methode qui demande si on affiche l'automate ou non
     * @param automaton EN : the automaton that we want display || FR : l'automate que nous voulons afficher
     * @param language EN : the language for the display || FR : la langue pour l'affichage
     */
    public static void displayAutomaton(Automaton automaton, int language){
        Scanner sc = new Scanner(System.in);
        if(language == 1){
            System.out.println("Do you want display the automaton ?");
            System.out.println("1. yes");
            System.out.println("2. no");
        }else{
            System.out.println("Voulez-vous affichez l'automate ?");
            System.out.println("1. oui");
            System.out.println("2. non");

        }

        boolean isNumber=false;
        int choice = 0;
        while(!isNumber) {
            try {
                isNumber = true;
                choice = Integer.parseInt(sc.next());
            } catch (Exception e) {
                isNumber = false;
                if(language == 1){
                    System.err.println("Not a number");
                }else{
                    System.err.println("Ce n'est pas un nombre");
                }
            }
        }

        if(choice == 1){
            System.out.println(automaton.toString());
        }
    }

    /**
     * EN : Allow to download a .dot file of the automaton
     * || FR : Permet de telecharger un fichier .dot de l'automate
     * @param automaton l'automate à telecharget en .dot
     * @param language EN : the language for the display || FR : la langue pour l'affichage
     */
    public static void downloadDotFile(Automaton automaton,int language){
        Scanner sc = new Scanner(System.in);
        if(language == 1){
            System.out.println("Do you want download the .dot file of the automaton ?");
            System.out.println("1. yes");
            System.out.println("2. no");
        }else{
            System.out.println("Voulez-vous telecharger le fichier .dot de l'automate ?");
            System.out.println("1. oui");
            System.out.println("2. non");
        }

        boolean isNumber=false;
        int choice = 0;
        while(!isNumber) {
            try {
                isNumber = true;
                choice = Integer.parseInt(sc.next());
            } catch (Exception e) {
                isNumber = false;
                if(language == 1){
                    System.err.println("Not a number");
                }else{
                    System.err.println("Ce n'est pas un nombre");
                }
            }
        }

        if(choice == 1){
            automaton.createDotFile();
            if (language == 1){
                System.out.println("Downloaded");
            }else{
                System.out.println("Telecharge");
            }
        }
    }

    /**
     * EN : Allow to download a base file of automaton
     * || FR : Permet de telecharger un fichier de base pour la creation d'automate
     * @param language EN : the language for the display || FR : la langue pour l'affichage
     */
    public static void downloadBaseFileAutomaton(int language){
        PrintWriter write;
        int n = 5;
        try {
            write = new PrintWriter(new BufferedWriter(new FileWriter("fileAutomaton/baseFile.txt")));
            if (language == 1){
                write.println("NAME AutomatonName (without space)");
                write.println("ALPHABET A Z E C D Z A a c d f r t z - è _ @ space # ... 0 1 (with a space between each characters)");
                write.println("E0 B alpha 0 E1 (transition of the automaton)");
                write.println("E0 A punct num E1 (transition of the automaton)");
                write.println("E1 3 space E2 (transition of the automaton)");
                write.println("E2 3 A Z E R T E3 (transition of the automaton)");
                write.println("E3 4 E4 (transition of the automaton)");
                write.println("INITIAL E0 (just one because is a determinist automaton)");
                write.println("FINAL E1 E2 (can have more than one state, separate with a space)");
            }else{
                write.println("NAME AutomatonName (sans espace)");
                write.println("ALPHABET A Z E C D Z A a c d f r t z - è _ @ space # ... 0 1 (avec un espace entre chaque caractères)");
                write.println("E0 B alpha 0 E1 (transition de l'automate)");
                write.println("E0 A punct num E1 (transition de l'automate)");
                write.println("E1 3 space E2 (transition de l'automate)");
                write.println("E2 3 A Z E R T E3 (transition de l'automate)");
                write.println("E3 4 E4 (transition de l'automate)");
                write.println("INITIAL E0 (juste un seul, car automate deterministe)");
                write.println("FINAL E1 E2 (peut avoir plusieurs etats final, toujours avec un espace entre chaque etat)");
            }

            write.close();
            if (language==1){
                System.out.println("File create");
            }else{
                System.out.println("Fichier créer");
            }
        } catch (IOException e) {
            if(language==1){
                System.err.println("File not create");
            }else{
                System.err.println("Fichier non creer");
            }
        }
        sleep(2000);
    }

    /**
     * EN : Application of the automaton
     * || FR : L'application automate
     * @param args EN : possible arguments for the application || FR : arguments possible pour l'application
     */
    public static void main(String[] args) {
        //Create the eobject Scanner
        Scanner sc = new Scanner(System.in);

        //Initialization of choice variables
        int choice = 0;
        int stop = 10;
        boolean isNumber = false;
        int language = 0;

        //Choise language of the application
        System.out.println("Langue :");
        System.out.println("1. English");
        System.out.println("2. Français");
        while(!isNumber) {
            try {
                System.out.println("Your choice : || Votre choix : ");
                isNumber = true;
                language = Integer.parseInt(sc.next());
            } catch (Exception e) {
                isNumber = false;
                System.err.println("Not a number || Ce n'est pas un nombre");
            }
        }

        //Loop: The application continues to run until the user press quit
        while (choice != stop) {
            if(language == 1){
                menuEN();
            }else{
                menuFR();
            }
            isNumber = false;

            //Loop: as long as the number entered does not correspond to one of the possibilities, we ask again
            while(!isNumber) {
                try {
                    if(language == 1){
                        System.out.println("Your choice ? ");
                    }else{
                        System.out.println("Votre choix ? ");
                    }
                    isNumber = true;
                    choice = Integer.parseInt(sc.next());
                    if(choice > stop){
                        if(language == 1 ){
                            System.err.println("The choices are between 1 and "+stop);
                        }else{
                            System.err.println("Le choix est entre 1 et "+stop);
                        }
                        isNumber = false;
                    }
                } catch (Exception e) {
                    isNumber = false;
                    if(language == 1){
                        System.err.println("Not a number");
                    }else{
                        System.err.println("Ce n'est pas un nombre");
                    }
                }
            }

            //Switch case: Depending on the number entered, a automaton is run or the application is stopped
            switch (choice) {
                case 1:
                    if(language == 1){
                        System.out.println("Your choice : Smiley");
                    }else{
                        System.out.println("Votre choix : Smiley");
                    }
                    load_smiley(language);
                    break;
                case 2:
                    if(language == 1){
                        System.out.println("Your choice : HH:MM");
                    }else{
                        System.out.println("Votre choix : HH:MM");
                    }
                    load_HHMM(language);
                    break;
                case 3:
                    load_automate_by_file("fileAutomaton/default/JJMMAAAA.txt",language);
                    break;
                case 4:
                    load_automate_by_file("fileAutomaton/default/EMAIL.txt",language);
                    break;
                case 5:
                    load_automate_by_file("fileAutomaton/default/POLYNOME.txt",language);
                    break;
                case 6:
                    load_automate_by_file("fileAutomaton/default/PLAQUE.txt",language);
                    break;
                case 7:
                    load_automate_by_file("fileAutomaton/default/HHMMSS.txt",language);
                    break;
                case 8:
                    if(language==1){
                        System.out.println("Give the name of your file who is in the fileAutomaton folder. ");
                        System.out.println("If your file is not create, create it now with the good syntaxe(2).");
                    }else{
                        System.out.println("Donnez le nom de votre fichier qui est dans le dossier fileAutomaton");
                        System.out.println("Si votre fichier n'est pas créée, créée le maintenant avec la bonne syntaxe(2).");
                    }
                    String name2 = sc.next();
                    load_automate_by_file("fileAutomaton/"+name2,language);
                    break;
                case 9:
                    downloadBaseFileAutomaton(language);
                    break;
                case 10:
                    System.out.println("Quit");
                    sc.close();
                    System.exit(0);
                    break;
            }
        }
    }
}
