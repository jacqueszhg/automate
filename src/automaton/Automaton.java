package automaton;

import java.util.*;

/**
 * Class that defines an automate
 * @author jacques-zheng
 */
public class Automaton {
    private String name;
    private HashSet<State> S;
    private HashSet<Character> A;
    private State So;
    private HashSet<State> Sf;

    /**
     * Constructor of the class
     * @param name the name of the automate
     */
    public Automaton(String name) {
        this.name = name;
        this.S = new HashSet<>();
        this.Sf = new HashSet<>();
    }

    /**
     * Checks with the name, if a state is present in the automate
     * @param name the name of the state
     * @return true if the automate contain the state, else false
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
     * Initialization of the automaton alphabet
     * @param alphabet the list of the alphabet used by the automaton
     */
    public void setAlphabet(ArrayList<Character> alphabet){
        this.A = new HashSet<>(alphabet);
    }

    /**
     * Initialization of the initial state
     * @param initial the initial state
     * @return true if add, false otherwise
     */
    public boolean setInitialState(State initial){
        if(initial == null | !this.S.contains(initial))
            return false;
        this.So = initial;
        return true;
    }


    /**
     * Initializations of final states, without duplicates
     * @param list the list that contains our end-states
     * @return true if add , false otherwise
     */
    public boolean setEndState(List<State> list){
        return this.Sf.addAll(list);
    }

    /**
     * Adds a state to the automaton, without duplicates
     * @param state the state to add
     * @return true if add , false otherwise
     */
    public boolean addState(State state){
        return this.S.add(state);
    }

    /**
     * Add a transition to the automaton
     * @param state the state on which the transition is added
     * @param key a value of our alphabet
     * @param value a state in the whole state
     * @return true if add , false otherwise
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
     * Allows to obtain a precise state, by its name
     * @param name the name of the desired state
     * @return the requested state if found, otherwise null if not found
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
     * Automaton acceptor: Checks a string according to the automaton format
     * @param sentence the string to check
     * @return true if the string is in the correct format, otherwise false
     */
    public boolean verifAutomaton(String sentence){
        State currentState = this.So;
        int i=0;
        Character currentChar = sentence.charAt(0);
        boolean finish = false;
        while (!finish && currentState != null){
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


        if(currentState == null){
            System.err.println("Fall into the well");
            return false;
        }else if(i < sentence.length()) {
            System.err.println("Too much character");
            return false;
        }
        if(this.Sf.contains(currentState)){
            System.out.println("Correct format");
            return true;
        }
        return false;
    }

    /**
     * Display of the automaton
     * @return display of the automaton
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
        return "automate.Automate "+ name +" {\n" +
                "S=[" + infoS +
                ", \nA=" + A +
                ", \nSo=" + So.getName() +
                ", \nSf=[" + infoSf +
                ", \ndelta =[\n"+infoListOutPut+
                '}';
    }


}
