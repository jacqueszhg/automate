package automaton;

import java.util.HashMap;
import java.util.Objects;

/**
 * EN : Class that defines a state of an automaton
 * FR : Classe qui défini un état d'un automate
 * @author jacques-zheng
 */
public class State {
    private String name;
    private HashMap<Character,State> listOut;

    /**
     * EN : Constructor of the class
     * FR : Constructeur de la classe
     * @param name EN : the name of the state || FR : le nom de l'état
     */
    public State(String name) {
        this.name = name;
        this.listOut = new HashMap<>();
    }

    /**
     * EN : Add an output for our state, without duplicates
     * FR : Ajoute une sortit possible pour notre état, sans doublons
     * @param key EN : a value of our alphabet || FR : une valeur de l'alphabet
     * @param value EN : a state of our automate || FR : un etat de l'automate
     */
    public void addOut(Character key, State value){
        this.listOut.put(key,value);
    }

    /**
     * EN : Check if the current object and the object in parameter are equals
     * FR : Vérifie si l'objet courant et l'objet en paramètre son identique
     * @param o EN : the object with which compared the current object || FR : l'objet avec lequel comparé l'objet courant
     * @return EN : true if equals, else false || FR : true si identique, sinon fals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name);
    }

    /**
     * EN : Return the hashcode of the object
     * FR : Retourne le hascode de l'objet
     * @return EN : the hashcode of the object || FR : le hashcode de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * EN : Gives the next state, of the current state after a transition
     * FR : Donne l'état suivant, de l'état courant après une transition
     * @param cle EN : the character used in the transition || FR : le caractère utilisé dans la transition
     * @return EN : the next state if the transition exists, otherwise return null || FR : l'état suivant si la transition existe, sinon retourne null
     */
    public State nextSate(Character cle){
        if(listOut.containsKey(cle)){
            return listOut.get(cle);
        }
        return null;
    }

    /**
     * EN : Returns the dictionary, of all the output of the state
     * FR : Retourne le dictionnaire de sortir possible pour l'état courant
     * @return EN : the dictionary of output of the state || FR :  l'ensemble des sortit possible
     */
    public HashMap<Character,State> getListOut(){
        return this.listOut;
    }

    /**
     * EN : Give the name of the state
     * FR :  Retourne le nom de l'état
     * @return EN : the name of the state || FR : le nom de l'état
     */
    public String getName() {
        return name;
    }
}
