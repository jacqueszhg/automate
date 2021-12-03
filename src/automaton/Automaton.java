package automaton;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * EN : Class that defines an automate
 * FR : Classe qui défini un automate
 * @author jacques-zheng
 */
public class Automaton {
    private String name;
    private HashSet<State> S;
    private HashSet<Character> A;
    private State So;
    private HashSet<State> Sf;

    /**
     * EN : Constructor of the class
     * FR : Constructeur de la classe
     * @param name EN : the name of the automate || FR : nom nom de l'automate
     */
    public Automaton(String name) {
        this.name = name;
        this.S = new HashSet<>();
        this.Sf = new HashSet<>();
    }

    /**
     * EN : Checks with the name, if a state is present in the automate
     * FR : Vérifie avec le nom, si un état est présent dans l'automate
     * @param name EN : the name of the state || FR : le nom de l'état rechercher
     * @return EN : true if the automate contain the state, else false || FR : true si l'automate contient l'état, false sinon
     */
    public boolean containStateName(String name){
        for(State etat : S){
            if(etat.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * EN : Initialization of the automaton alphabet
     * FR : Initialisation de l'alphabet de l'automate
     * @param alphabet EN : the list of the alphabet used by the automaton || FR : la liste de l'alphabet utilisé par l'automate
     */
    public void setAlphabet(ArrayList<Character> alphabet){
        this.A = new HashSet<>(alphabet);
    }

    /**
     * EN : Initialization of the initial state
     * FR : Initialisation de l'état initiale
     * @param initial EN : the initial state || FR : objet State correspondant à l'état initiale
     * @return EN : true if add, false otherwise || FR : true si ajouté, false sinon
     */
    public boolean setInitialState(State initial){
        if(initial == null | !this.S.contains(initial))
            return false;
        this.So = initial;
        return true;
    }


    /**
     * EN : Initializations of final states, without duplicates
     * FR : Initialisations des états finaux, sans doublons
     * @param list EN : the list that contains our end-states ||FR : la liste qui contient nos état finaux
     * @return EN : true if add , false otherwise ||FR : true si ajouté, false sinon
     */
    public boolean setEndState(List<State> list){
        return this.Sf.addAll(list);
    }

    /**
     * EN : Adds a state to the automaton, without duplicates
     * FR : Ajoute un état à l'automate, sans doublons
     * @param state EN : the state to add || FR : l'état à ajouter
     * @return EN : true if add , false otherwise || FR : true si ajouté, false sinon
     */
    public boolean addState(State state){
        return this.S.add(state);
    }

    /**
     * EN : Add a transition to the automaton
     * FR : Ajoute une transition à l'automate
     * @param state EN : the state on which the transition is added || FR : l'état sur lequel la transition est ajoutée
     * @param key EN : a value of our alphabet || FR : une valeur de l'alphabet
     * @param value EN : a state in the whole state || FR : un état dans l'ensemble d'état
     * @return EN : true if add , false otherwise ||FR : true si ajouter, false sinon
     */
    public boolean addTransition(State state, Character key, State value){
        if(state == null || key == null || value == null)
            return false;

        State currentState = null;
        if(this.S.contains(state)){
            for(State s : this.S){
                if(s.equals(state)){
                    currentState = s;
                }
            }
        }else{
            return false;
        }
        if(this.A.contains(key)) {
            currentState.addOut(key, value);
        }else{
            return false;
        }
        return true;
    }

    /**
     * EN : Allows to obtain a precise state, by its name
     * FR : Permet d'obtenir un etat précis, par son nom
     * @param name EN : the name of the desired state || FR : le nom de l'état voulu
     * @return the EN : requested state if found, otherwise null if not found || FR : l'état demandé si trouvé, sinon null
     */
    public State getState(String name){
        State currentState = new State(name);
        if(this.S.contains(currentState)){
            for(State s : this.S){
                if(s.equals(currentState)){
                    currentState = s;
                }
            }
        }else{
            return null;
        }
        return currentState;
    }

    /**
     * EN : Automaton acceptor: Checks a string according to the automaton format
     * FR : Accepteur de l'automate : Vérifie une chaine de caractère selon le format de l'automate
     * @param sentence EN : the string to check || FR : la chaine à vérifier
     * @param language EN : the language for the display || FR : la langue pour l'affichage
     * @return EN : true if the string is in the correct format, otherwise false || FR : true si la chaine est dans le bon format, sinon false
     */
    public boolean verifAutomaton(String sentence, int language){
        State currentState = this.So;
        int i=0;
        Character currentChar = sentence.charAt(0);
        boolean finish = false;

        while (!finish && !currentState.isWell()){
            i++;
            System.out.println(currentState.getName() +" | Character{carac='"+ currentChar+"}");
            currentState = currentState.nextSate(currentChar);

            if(this.Sf.contains(currentState)){
                if(i < sentence.length()) {
                    if(currentState.nextSate(sentence.charAt(i)) != null){
                        currentChar = sentence.charAt(i);
                    }else{
                        finish=true;
                    }
                }else{
                    finish=true;
                }
            }else{
                if(i < sentence.length()){
                    currentChar = sentence.charAt(i);
                }else {
                    finish = true;
                }
            }
        }


        if(currentState.isWell()){
            if(language == 1){
                System.err.println("Fall into the well");
            }else{
                System.err.println("Tomber dans le puit");
            }
            return false;
        }else if(i < sentence.length()) {
            if(language == 1){
                System.err.println("Too much characters");
            }else{
                System.err.println("Trop de caractères");
            }
            return false;
        }
        if(this.Sf.contains(currentState)){
            if(language == 1){
                System.out.println("Correct format");
            }else{
                System.out.println("Format correcte");
            }
            return true;
        }
        return false;
    }

    /**
     * EN : Display of the automaton
     * FR : Affichage de l'automate
     * @return EN : display of the automaton || FR : l'affichage de l'automate
     */
    @Override
    public String toString() {
        String infoS="";
        for(State s : S){
            infoS+= s.getName()+",";
        }
        infoS+="]";

        String infoSf = "";
        for(State s : Sf){
            infoSf+=s.getName()+",";
        }
        infoSf+="]";

        String infoListOutPut ="";
        for(State s : S){
            HashMap<Character, State> h = s.getListOut();
            for(Character key : h.keySet()){
                //infoensembleSortit += "{"+s.getnom()+","+key+","+h.get(key).getnom()+"}";
                infoListOutPut += "{'"+key+"'|"+s.getName()+"-->"+h.get(key).getName()+" }";
            }
            infoListOutPut +="\n";
        }
        return "Automate "+ name +" {\n" +
                "S=[" + infoS +
                ", \nA=" + A +
                ", \nSo=" + So.getName() +
                ", \nSf=[" + infoSf +
                ", \ndelta =[\n"+infoListOutPut+
                ']';
    }

    /**
     * EN : Create a .dot file of the automaton
     * FR : Créer un fichier .dot de l'automate
     */
    public void createDotFile(){
        PrintWriter write;
        try {
            write = new PrintWriter(new BufferedWriter(new FileWriter("dotFile/"+this.name+".dot")));
            write.println("digraph "+ this.name +" {");
            write.println("node [shape=plaintext];");
            write.println(this.So.getName() + " [shape=circle];");
            for(State s : S){
                HashMap<Character, State> h = s.getListOut();
                if(this.Sf.contains(s)){
                    write.println(s.getName() + " [shape=doublecircle];");
                }
                for(Character key : h.keySet()){
                    write.println(s.getName()+" -> "+h.get(key).getName()+"[label=\""+key+"\"];");
                }
            }
            write.println("}");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * EN : Give the name of the automaton
     * FR : Donne le nom de l'automate
     * @return EN : the name of the automaton || FR : le nom de l'automate
     */
    public String getName() {
        return name;
    }
}
