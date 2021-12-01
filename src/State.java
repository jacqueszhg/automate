import java.util.HashMap;
import java.util.Objects;

public class State {
    private String nom;
    private HashMap<Character,State> ensembleSortit;

    /**
     * Constructeur de la classe
     * @param nom le nom de l'état
     */
    public State(String nom) {
        this.nom = nom;
        this.ensembleSortit = new HashMap<>();
    }

    /**
     * Ajoute une sortit possible pour notre état, sans doublons
     * @param cle une valeur de l'alphabet
     * @param valeur un etat de l'automate
     */
    public void addensembleSortit(Character cle, State valeur){
        this.ensembleSortit.put(cle,valeur);
    }

    /**
     * Vérifie si l'objet courant et l'objet en paramètre son identique
     * @param o l'objet avec lequel comparé l'objet courant
     * @return true si identique, sinon false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(nom, state.nom);
    }

    /**
     * Retourne le hascode de l'objet
     * @return le hashcode de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    /**
     * Donne l'état suivant, de l'état courant après une transition
     * @param cle le caractere utilisé dans la transition
     * @return l'état suivant si la transition existe, sinon retourne l'état "puits"
     */
    public State etatSuivant(Character cle){
        if(ensembleSortit.containsKey(cle)){
            return ensembleSortit.get(cle);
        }
        return new State("puit");
    }

    /**
     * Retourne nombre ensemble de sortir possible pour l'état courant
     * @return l'ensemble des sortit possible
     */
    public HashMap getensembleSortit(){
        return this.ensembleSortit;
    }

    /**
     * Vérifie si l'état courant est le puit
     * @return true si l'état courant est le puit, sinon false
     */
    public boolean isPuit(){
        if(this.nom == "puit"){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Retourne le nom de l'état
     * @return le nom de l'état
     */
    public String getnom() {
        return nom;
    }
}
