package automate;

import java.util.HashMap;
import java.util.Objects;

/**
 * Class that defines a automate.State of an automate.Automate
 * @author jacques-zheng
 */
public class State {
    private String name;
    private HashMap<Character,State> listOut;

    /**
     * Constructor of the class
     * @param name the name of the state
     */
    public State(String name) {
        this.name = name;
        this.listOut = new HashMap<>();
    }

    /**
     * Add an output for our state, without duplicates
     * @param key a value of our alphabet
     * @param value a state of our automate
     */
    public void addOut(Character key, State value){
        this.listOut.put(key,value);
    }

    /**
     * Check if the current object and the object in parameter are equals
     * @param o the object with which compared the current object
     * @return true if equals, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name);
    }

    /**
     * Return the hashcode of the object
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Gives the next state, of the current state after a transition
     * @param cle the character used in the transition
     * @return the next state if the transition exists, otherwise return null
     */
    public State nextSate(Character cle){
        if(listOut.containsKey(cle)){
            return listOut.get(cle);
        }
        return null;
    }

    /**
     * Returns the dictionary, of all the output of the state
     * @return the dictionary of output of the state
     */
    public HashMap getListOut(){
        return this.listOut;
    }

    /**
     * Give the name of the state
     * @return the name of the state
     */
    public String getName() {
        return name;
    }
}
