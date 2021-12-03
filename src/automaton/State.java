package automaton;

import java.util.HashMap;
import java.util.Objects;

/**
 * EN : Class that defines a state of an automaton
 * || FR : Classe qui defini un etat d'un automate
 * @author jacques-zheng
 */
public class State {
    private String name;
    private HashMap<Character,State> listOut;

    /**
     * EN : Constructor of the class
     * || FR : Constructeur de la classe
     * @param name EN : the name of the state || FR : le nom de l'etat
     */
    public State(String name) {
        this.name = name;
        this.listOut = new HashMap<>();
    }

    /**
     * EN : Add an output for our state, without duplicates
     * || FR : Ajoute une sortit possible pour notre etat, sans doublons
     * @param key EN : a value of our alphabet || FR : une valeur de l'alphabet
     * @param value EN : a state of our automate || FR : un etat de l'automate
     */
    public void addOut(Character key, State value){
        this.listOut.put(key,value);
    }

    /**
     * EN : Check if the current object and the object in parameter are equals
     * || FR : Verifie si l'objet courant et l'objet en parametre son identique
     * @param o EN : the object with which compared the current object || FR : l'objet avec lequel compare l'objet courant
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
     * || FR : Retourne le hascode de l'objet
     * @return EN : the hashcode of the object || FR : le hashcode de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * EN : Gives the next state, of the current state after a transition
     * || FR : Donne l'etat suivant, de l'etat courant apres une transition
     * @param cle EN : the character used in the transition || FR : le caractere utilise dans la transition
     * @return EN : the next state if the transition exists, otherwise return null || FR : l'etat suivant si la transition existe, sinon retourne null
     */
    public State nextSate(Character cle){
        if(listOut.containsKey(cle)){
            return listOut.get(cle);
        }
        return new State("well");
    }

    /**
     * EN : Returns the dictionary, of all the output of the state
     * || FR : Retourne le dictionnaire de sortir possible pour l'etat courant
     * @return EN : the dictionary of output of the state || FR :  l'ensemble des sortit possible
     */
    public HashMap<Character,State> getListOut(){
        return this.listOut;
    }

    /**
     * EN : Give the name of the state
     * || FR :  Retourne le nom de l'etat
     * @return EN : the name of the state || FR : le nom de l'etat
     */
    public String getName() {
        return name;
    }

    /**
     * EN : Determine if the state is a well
     * || FR : Determine if l'etat est puit
     * @return EN : true if it's a well, false otherwise || FR : true si puit, false sinon
     */
    public boolean isWell(){
        return  this.name.equals("well");
    }
}
