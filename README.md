# Automate
Auteur : Jacques-Zheng <br>
Email : jacques.zheng@etu.univ-nantes.fr
 _____________________________________________________________________________________
# Sommaire
1. [ReadMe : English](#FRANCAIS)
2. [ReadMe : Français]()
 _____________________________________________________________________________________
# ENGLISH
## Compile and execute code
### On terminal
To compile the code:
```
javac -d out/production/Automate src/*/*.java
```
To execute the code
```
java -cp out/production/Automate application.Application
```
### On an IDE
Download the project and import it to your IDE, then run it via your IDE.

## The application
### Default automaton
The application uses deterministic automata to verify an expression. <br>
In the application you will find automatons by default.<br>
![Alt text](asset/menuAutomaton.jpg?raw=true "")

### Import of personal automaton
Possibility to import an automaton of your creation. It should follow a specific syntax. <br>
![Alt text](asset/menuLoadAutomaton.jpg?raw=true "")

During the execution of the application you will find a choice allowing you to create a basic file for the creation of an automaton, which will be created in the folder ``fileAutomaton```' with the name ``baseFile```
![Alt text](asset/menuDownloadBaseFile.jpg?raw=true "")
```
Example of an import file:

NAME AutomateExample(name without space)
ALPHABET A B C ... Z (alphabet with a space between each carat)
E0 TO E1 (state transition)
E0 B E1 (state transition)
E1 0 E2 (state transition)
E1 4 E2 (state transition)
INITIAL E0 (just one, because deterministic automaton)
FINAL E2 E1 (may have multiple final states, always with a space between each state)
```
###### Help for creating automate file
In the ```helpCreateFileAuomate`' package, the ``WriterFile``class helps to create repetitive transitions between two states. <br>
The result will be found in ``text.txt``. <br>
Modify only:
-   the variable ``s````s```
-   "stateStart" and "stateEnd" in ``write.println("stateStart"+" "+ tab[i] +" "+"stateEnd");``

### File .dot
You will also have the option of uploading a . dot file from the PLC, which will be saved in the ```dotFile```folder. <br>
This choice will be requested after you have chosen an automaton. <br>
 _____________________________________________________________________________________
# FRANCAIS
## Compiler et exécuter le code
### Sur terminal
Pour compiler le code :
```
javac -d out/production/Automate src/*/*.java
```
Pour exécuter le code
```
java -cp out/production/Automate application.Application
```
### Sur un IDE
Télécharger le projet et importer le sur votre IDE, puis éxécuter le via votre IDE.

## L'application
### Automates par défault
L'application utilise des automates déterministes pour vérifier une expression.<br>
Dans l'application vous trouverez des automates par défaut.
![Alt text](asset/menuAutomatonFR.jpg?raw=true "")

### Import d'automate personnel
Possibilité d'importer un automate de votre création. Il devra suivre une syntaxe précise.<br>
![Alt text](asset/menuLoadAutomatonFR.jpg?raw=true "")

Lors de l'éxécution de l'application vous trouverez un choix vous permettant de créer un fichier de base pour la création d'un automate, qui va être créer dans le dossier ```fileAutomaton``` avec le nom ```baseFile```
![Alt text](asset/menuDownloadBaseFileFR.jpg?raw=true "")
```
Exemple d'un fichier importable:

NAME AutomateExemple(nom sans espace)
ALPHABET A B C ... Z (alphabet avec un espace entre chaque caratères)
E0 A E1 (transition de l'automate)
E0 B E1 (transition de l'automate)
E1 0 E2 (transition de l'automate)
E1 4 E2 (transition de l'automate)
INITIAL E0 (juste un seul, car automate déterministe)
FINAL E2 E1 (peut avoir plusieurs états final, toujours avec un espace entre chaque état)
```
###### Aide pour création fichier automate
Dans le package ``helpCreateFileAuomate``, la classe ``WriterFile`` aide pour la création des transitions répétitif entre deux états.<br>
Le résultat se trouvera dans ``text.txt``.<br>
Modifier seulement :
-   la variable ```s``` avec les caractères de transition
-   "stateStart" et "stateEnd" dans ``write.println("stateStart"+" "+ tab[i] +" "+"stateEnd");``

### Fichier .dot
Vous aurez aussi la possibilité de pouvoir téléchager un fichier .dot de l'automate, qui sera sauvegardé dans le dossier ```dotFile```.<br>
Ce choix vous sera demandé, après que vous aurez choisi un automate.<br>

