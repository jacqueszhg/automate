import java.util.*;

public class Automate {
    private String nom;
    private HashSet<State> S;
    private HashSet<Character> A;
    private State So;
    private HashSet<State> Sf;

    /**
     * Constructeur de la classe
     * @param nom nom de l'automate
     */
    public Automate(String nom) {
        this.nom = nom;
        this.S = new HashSet<>();
        this.Sf = new HashSet<>();
    }

    public boolean containStateNom(String nom){
        for(State etat : S){
            if(etat.getnom().equals(nom)){
                return true;
            }
        }
        return false;
    }

    /**
     * Initialisation de l'alphabet de l'automate
     * @param alphabet la liste de l'alphabet utilisé par l'automate
     */
    public void setAlphabet(ArrayList<Character> alphabet){
        this.A = new HashSet<>(alphabet);
    }

    /**
     * Initialisation de l'état initiale
     * @param initiale objet State correspondant à l'état initiale
     * @return true si ajouter, false sinon
     */
    public boolean setEtatInitiale(State initiale){
        if(initiale == null | !this.S.contains(initiale))
            return false;
        this.So = initiale;
        return true;
    }


    /**
     * Initialisations des états finaux, sans doublons
     * @param list la list qui contient nos état finaux
     * @return true si ajouter , false sinon
     */
    public boolean setEtatFinal(List<State> list){
        return this.Sf.addAll(list);
    }

    /**
     * Ajoute un état à l'automate, sans doublons
     * @param etat l'état à ajouter
     * @return true si ajouter, false sinon
     */
    public boolean addState(State etat){
        return this.S.add(etat);
    }

    /**
     * Ajoute une transition à l'automate
     * @param etat l'état sur lequel la transition est ajoutée
     * @param cle une valeur de l'alphabet
     * @param valeur un état dans l'ensemble d'état
     * @return true si ajouter, false sinon
     */
    public boolean addTransition(State etat, Character cle, State valeur){
        if(etat == null || cle == null || valeur == null)
            return false;

        State etatCourant = null;
        if(this.S.contains(etat)){
            for(State s : this.S){
                if(s.equals(etat)){
                    etatCourant = s;
                }
            }
        }else{
            return false;
        }
        if(this.A.contains(cle)) {
            etatCourant.addensembleSortit(cle, valeur);
        }else{
            return false;
        }
        return true;
    }

    /**
     * Permet d'obtenir un etat précis, par son nom
     * @param nom nom de l'état voulu
     * @return l'état demandé si trouvé, sinon null si non trouvé
     */
    public State getState(String nom){
        State etatCourant = new State(nom);
        if(this.S.contains(etatCourant)){
            for(State s : this.S){
                if(s.equals(etatCourant)){
                    etatCourant = s;
                }
            }
        }else{
            return null;
        }
        return etatCourant;
    }

    /**
     * Accepteur de l'automate : Vérifie une chaine de caractère selon le format de l'automate
     * @param chaine la chaine à vérifier
     * @return true si la chaine est dans le bon format, sinon false
     */
    public boolean verifAutomate(String chaine){
        State etatCourant = this.So;
        int i=0;
        Character carCourant = chaine.charAt(0);
        boolean terminer = false;
        while (!terminer && !etatCourant.isPuit()){
            i++;
            System.out.println(etatCourant.getnom() +" | Caractère{carac='"+ carCourant+"}");
            etatCourant = etatCourant.etatSuivant(carCourant);

            if(this.Sf.contains(etatCourant)){
                if(i < chaine.length()) {
                    if(!etatCourant.etatSuivant(chaine.charAt(i)).equals(new State("puit"))){
                        carCourant = chaine.charAt(i);
                    }else{
                        terminer=true;
                    }
                }else{
                    terminer=true;
                }
            }else{
                if(i < chaine.length()){
                    carCourant = chaine.charAt(i);
                }else {
                    terminer = true;
                }
            }
        }


        if(etatCourant.equals(new State("puit"))){
            System.err.println("Tomber dans le puit");
            return false;
        }else if(i < chaine.length()) {
            System.err.println("Trop de caractère");
            return false;
        }
        if(this.Sf.contains(etatCourant)){
            System.out.println("Bon format");
            return true;
        }


        /*
        if(etatCourant.equals(new State("puit"))){
            System.err.println("Tomber dans le puit");
            return false;
        }else if(i < chaine.length()) {
            System.err.println("Trop de caractère");
            return false;
        }
        if(this.Sf.contains(etatCourant)){
            System.out.println("Bon format");
            return true;
        }
        */
        return false;
    }

    /**
     * Affichage de l'objet
     * @return l'affichage de l'objet
     */
    @Override
    public String toString() {
        String infoS="";
        for(State s : S){
            infoS+= s.getnom()+",";
        }
        infoS+="]";

        String infoSf = "";
        for(State s : Sf){
            infoSf+=s.getnom()+",";
        }
        infoSf+="]";

        String infoensembleSortit ="";
        for(State s : S){
            HashMap<Character,State> h = s.getensembleSortit();
            for(Character key : h.keySet()){
                infoensembleSortit += "{"+s.getnom()+","+key+","+h.get(key).getnom()+"}";
            }
            infoensembleSortit +="\n";
        }
        return "Automate{" +
                "nom='" + nom + '\'' +
                ", \nS=[" + infoS +
                ", \nA=" + A +
                ", \nSo=" + So.getnom() +
                ", \nSf=[" + infoSf +
                ", \ndelta =[\n"+infoensembleSortit+
                '}';
    }


}
