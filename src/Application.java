import java.io.*;
import java.util.*;

public class Application {
    /**
     * Affiche le menu de l'application
     */
    private static void menu(){
        System.out.println("----------------Menu de mon TP----------------");
        System.out.println("1. Smiley (pour reconnaitre un des smileys)");
        System.out.println("2. HH:MM (pour reconnaitre une heure:minute bien formée)");
        System.out.println("3. JJ/MM/AAAA (pour reconnaitre une date bien formée)");
        System.out.println("4. Adresse électronique (pour reconnaitre une adresse électronique bien formée)");
        System.out.println("5. Polynômes (pour reconnaitre un polynôme bien formé)");
        System.out.println("6. Plaque d'immatriculation française (pour reconnaitre une plaque d'immatriculation bien formée)");
        System.out.println("7. HH:MM:SS (pour reconnaitre une heure:minute:seconde bien formée)");
        System.out.println("8. Charger votre propre automate");
        System.out.println("9. Arrêt");
        System.out.println("Votre choix (1-9)?");
        System.out.println("Je vous demanderez ensuite la chaîne à analyser, Merci");
        System.out.println("-------------------Fin menu-------------------");
    }

    /**
     * Permet de mettre en pause l'application
     * @param sc un objet scanner
     */
    private static void pause(Scanner sc){
        System.out.println("Appuyer sur \"n'importe quel touche puis Entrer\" pour passer");
        sc.next();
    }

    public static void main(String[] args) {
        //Crée l'objet Scanner
        Scanner sc = new Scanner(System.in);

        //Initialisation des variables de choix
        int choix = 0;
        int arret = 9;

        //Boucle : L'application continue de s'éxécuter tant qu'on indique pas clairement de quitter
        while (choix != arret) {
            menu();
            boolean estNombre = false;

            //Boucle : tant que le numéro entrée ne correspond pas à l'un des possibilité, on redemande
            while(!estNombre) {
                try {
                    System.out.println("Votre choix : ");
                    estNombre = true;
                    choix = Integer.parseInt(sc.next());
                    if(choix > arret){
                        System.err.println("Le choix est entre 1 et "+arret);
                        estNombre = false;
                    }
                } catch (Exception e) {
                    estNombre = false;
                    System.err.println("Pas un nombre");
                }
            }

            //Switch case : Selon le numéro entrée, on éxécute un automate accepteur précis ou on arret l'application
            switch (choix) {
                case 1:
                    System.out.println("Votre choix : Smiley");
                    load_smiley();
                    pause(sc);
                    break;
                case 2:
                    System.out.println("Votre choix : HH:MM");
                    load_HHMM();
                    pause(sc);
                    break;
                case 3:
                    System.out.println("Votre choix : JJ/MM/AAAA");
                    load_automate_par_fichier("automates/JJMMAAAA.txt");
                    pause(sc);
                    break;
                case 4:
                    System.out.println("Votre choix : Adresse électronique");
                    load_automate_par_fichier("automates/EMAIL.txt");
                    pause(sc);
                    break;
                case 5:
                    System.out.println("Votre choix : Polynômes");
                    load_automate_par_fichier("automates/POLYNOME.txt");
                    pause(sc);
                    break;
                case 6:
                    System.out.println("Votre choix : Plaque d'immatriculation");
                    load_automate_par_fichier("automates/PLAQUE.txt");
                    pause(sc);
                    break;
                case 7:
                    System.out.println("Votre choix : HH:MM:SS");
                    load_automate_par_fichier("automates/HHMMSS.txt");
                    pause(sc);
                    break;
                case 8:
                    System.out.println("Donner le nom de votre fichier placer dans le dossier automates : ");
                    String nom = sc.next();
                    load_automate_par_fichier("automates/"+nom);
                    pause(sc);
                    break;
                case 9:
                    System.out.println("Arrêt");
                    sc.close();
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * L'automate pour reconnaitre un Smiley
     */
    private static void load_smiley(){
        Scanner sc = new Scanner(System.in);
        Automate smiley = new Automate("Smiley");
        smiley.setAlphabet(new ArrayList<>(Arrays.asList(':',';','-','=',')','(')));
        for(int i =0; i <5; i++){
            State etat = new State("E"+i);
            smiley.addState(etat);
        }

        for(int i = 0; i <5; i++){
            State etat =smiley.getState("E"+i);
            switch (i){
                case 0:
                    smiley.setEtatInitiale(smiley.getState("E0"));
                    smiley.addTransition(etat,':', smiley.getState("E1"));
                    smiley.addTransition(etat,';', smiley.getState("E2"));
                    break;
                case 1:
                    smiley.addTransition(etat,'-', smiley.getState("E3"));
                    smiley.addTransition(etat,'=', smiley.getState("E3"));
                    break;
                case 2:
                    smiley.addTransition(etat,'-', smiley.getState("E3"));
                    break;
                case 3:
                    smiley.addTransition(etat,'(', smiley.getState("E4"));
                    smiley.addTransition(etat,')', smiley.getState("E4"));
                    break;
                case 4:
                    smiley.setEtatFinal(new ArrayList<>(Arrays.asList(smiley.getState("E4"))));
                    break;
            }
        }

        voirAutomate(smiley);

        System.out.println("Votre chaine :");
        String chaine = sc.next();
        if(smiley.verifAutomate(chaine)){
            System.out.println("OK");
        }else{
            System.out.println("KO");
        }
    }

    /**
     * L'automate pour reconnaitre une heure dans la forme HH:MM
     */
    private static void load_HHMM(){
        Scanner sc = new Scanner(System.in);
        Automate hhmm = new Automate("HHMM");
        hhmm.setAlphabet(new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9',':')));
        for(int i =0; i <7; i++){
            State etat = new State("E"+i);
            hhmm.addState(etat);
        }

        for(int i = 0; i <7; i++){
            State etat =hhmm.getState("E"+i);
            switch (i){
                case 0:
                    hhmm.setEtatInitiale(hhmm.getState("E0"));
                    hhmm.addTransition(etat,'0', hhmm.getState("E1"));
                    hhmm.addTransition(etat,'1', hhmm.getState("E1"));
                    hhmm.addTransition(etat,'2', hhmm.getState("E2"));
                    break;
                case 1:
                    hhmm.addTransition(etat,'0', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'1', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'2', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'3', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'4', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'5', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'6', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'7', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'8', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'9', hhmm.getState("E3"));

                    break;
                case 2:
                    hhmm.addTransition(etat,'0', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'1', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'2', hhmm.getState("E3"));
                    hhmm.addTransition(etat,'3', hhmm.getState("E3"));
                    break;
                case 3:
                    hhmm.addTransition(etat,':', hhmm.getState("E4"));
                    break;
                case 4:
                    hhmm.addTransition(etat,'0', hhmm.getState("E5"));
                    hhmm.addTransition(etat,'1', hhmm.getState("E5"));
                    hhmm.addTransition(etat,'2', hhmm.getState("E5"));
                    hhmm.addTransition(etat,'3', hhmm.getState("E5"));
                    hhmm.addTransition(etat,'4', hhmm.getState("E5"));
                    hhmm.addTransition(etat,'5', hhmm.getState("E5"));
                    break;
                case 5:
                    hhmm.addTransition(etat,'0', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'1', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'2', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'3', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'4', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'5', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'6', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'7', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'8', hhmm.getState("E6"));
                    hhmm.addTransition(etat,'9', hhmm.getState("E6"));
                    break;
                case 6:
                    hhmm.setEtatFinal(new ArrayList<>(Arrays.asList(hhmm.getState("E6"))));
                    break;
            }
        }

        voirAutomate(hhmm);

        System.out.println("Votre chaine :");
        String chaine = sc.next();
        if(hhmm.verifAutomate(chaine)){
            System.out.println("OK");
        }else{
            System.out.println("KO");
        }
    }

    /**
     * Charge un automate quelconque avec un fichier
     * @param cheminFichier le fichier contenant l'automate
     */
    private static void load_automate_par_fichier(String cheminFichier) {
        //Initialisation pour lire le fichier
        String nomFichier = cheminFichier;
        Scanner scan = null;
        Automate automate = null;
        try {
            scan = new Scanner(new File(nomFichier));
            //Boucle : Lit le fichier de A à Z
            while (scan.hasNextLine()) {
                String line = scan.nextLine();

                //Split la ligne obtenue
                String[] motLigne = line.split(" ");

                //Initialisation de l'automate avec son nom
                if (motLigne[0].equals("NOM")) {
                    automate = new Automate(motLigne[1]);

                    //Initialisation de l'alphabet
                } else if (motLigne[0].equals("ALPHABET")) {
                    ArrayList<Character> alphabet = new ArrayList<>();
                    for (int i = 1; i < motLigne.length; i++) {
                        Character c = null;
                        if (motLigne[i].equals("espace")) {
                            c = ' ';
                        } else {
                            c = motLigne[i].charAt(0);
                        }
                        alphabet.add(c);
                    }
                    automate.setAlphabet(alphabet);

                    //Initialisation de l'état initiale
                } else if (motLigne[0].equals("INITIAL")) {
                    State etatInitiale = automate.getState(motLigne[1]);
                    automate.setEtatInitiale(etatInitiale);

                    //Initialisaiton des états finaux
                } else if (motLigne[0].equals("FINAL")) {
                    ArrayList<State> finaux = new ArrayList<>();
                    for (int i = 1; i < motLigne.length; i++) {
                        State etat = automate.getState(motLigne[i]);
                        finaux.add(etat);
                    }
                    automate.setEtatFinal(finaux);

                    //Initialisation des états et des transitions
                } else {
                    if (!automate.containStateNom(motLigne[0])) {
                        automate.addState(new State(motLigne[0]));
                    }
                    if (!automate.containStateNom(motLigne[2])) {
                        automate.addState(new State(motLigne[2]));
                    }
                    State etat1 = automate.getState(motLigne[0]);
                    State etat2 = automate.getState(motLigne[2]);
                    if (motLigne[1].equals("espace")) {
                        automate.addTransition(etat1, ' ', etat2);
                    } else {
                        automate.addTransition(etat1, motLigne[1].charAt(0), etat2);
                    }
                }
            }

            Scanner sc = new Scanner(System.in);

            voirAutomate(automate);

            System.out.println("Votre chaine :");
            String chaine = sc.next();
            if (automate.verifAutomate(chaine)) {
                System.out.println("OK");
            } else {
                System.out.println("KO");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable");
        }
    }

    /**
     * Méthode qui permet de demander si oui ou non on affiche l'automate
     * @param automate l'automate que l'on veut afficher
     */
    public static void voirAutomate(Automate automate){
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous voir votre automate ?");
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean estNombre=false;
        int choix = 0;
        while(!estNombre) {
            try {
                estNombre = true;
                choix = Integer.parseInt(sc.next());
            } catch (Exception e) {
                estNombre = false;
                System.err.println("Pas un nombre");
            }
        }

        if(choix == 1){
            System.out.println(automate.toString());
        }
    }
}
