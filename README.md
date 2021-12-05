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
Download the project and import it to your IDE, then run it on your IDE.

## The application
### Default automaton
The application uses deterministic automata to verify an expression. <br>
In the application you will find automatons by default.<br>
![Alt text](asset/menuAutomaton.jpg?raw=true "")

### Import of personal automaton
Possibility to import an automaton of your creation. It should follow a specific syntax. <br>
![Alt text](asset/menuLoadAutomaton.jpg?raw=true "")

During the execution of the application you will find a choice allowing you to create a basic file for the creation of an automaton, which will be created in the folder ``fileAutomaton`` with the name ``baseFile```<br>
![Alt text](asset/menuDownloadBaseFile.jpg?raw=true "")
```
Example of an import file:

NAME AutomatonName (without space)
ALPHABET A Z E C D Z A a c d f r t z - è _ @ space # ... 0 1 (with a space between each characters, can't use posix here expect the space who can be used)
E0 B alpha 0 E1 (transition of the automaton)
E0 A punct num E1 (transition of the automaton)
E1 3 space E2 (transition of the automaton)
E2 3 A Z E R T E3 (transition of the automaton)
E3 4 E4 (transition of the automaton)
INITIAL E0 (just one because is a determinist automaton)
FINAL E1 E2 (can have more than one state, separate with a space)
```
###### Words that can be used in the transitions, to simplify writing
|    POSIX   |     ASCII    |   Description |
| ---------- |------------- | ------------- |
| alphaUpper    |        A-Z        |     Capital letters |
| alphaLower    |        a-z	        |      Lower case letters|
| alpha         |        A-Za-z	        |      Alphabetic characters |
| punct         |][!"#$%&'()*+,./:;<=>?@\^_`{}~-&#x2502;|   Punctuation characters |
| num           |        0-9        |      Decimal digits |
| space         |          \t\r\n\v\f      |      Space characters |
| [X..Y]         |          X-Y      |      All characters between X and Y |

###### Detection error
If the file of the automaton contain an error the application will detect and write an error message on your terminal.<br>
<i><u>Error of syntaxe :</u></i><br>
![Alt text](asset/automatonErrorSyntaxe.png?raw=true "")<br>
<i><u>Error in transition :</u></i><br>
![Alt text](asset/automatonErrorCharacter.png?raw=true "")<br>

### File .dot
You will also have the option of uploading a .dot file from the automaton, which will be saved in the ```dotFile```folder. <br>
This choice will be requested after you have chosen an automaton. <br>
![Alt text](asset/graphviz.svg?raw=true "")
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
Dans l'application vous trouverez des automates par défaut.<br>
![Alt text](asset/menuAutomatonFR.jpg?raw=true "")

### Import d'automate personnel
Possibilité d'importer un automate de votre création. Il devra suivre une syntaxe précise.<br>
![Alt text](asset/menuLoadAutomatonFR.jpg?raw=true "")

Lors de l'éxécution de l'application vous trouverez un choix vous permettant de créer un fichier de base pour la création d'un automate, qui va être créer dans le dossier ```fileAutomaton``` avec le nom ```baseFile```<br>
![Alt text](asset/menuDownloadBaseFileFR.jpg?raw=true "")
```
Exemple d'un fichier importable:

NAME AutomatonName (sans espace)
ALPHABET A Z E C D Z A a c d f r t z - è _ @ space # ... 0 1 (avec un espace entre chaque caractères, les posix ne sont pas utilisable ici sauf le "space" est accpeté)
E0 B alpha 0 E1 (transition de l'automate)
E0 A punct num E1 (transition de l'automate)
E1 3 space E2 (transition de l'automate)
E2 3 A Z E R T E3 (transition de l'automate)
E3 4 E4 (transition de l'automate)
INITIAL E0 (juste un seul, car automate deterministe)
FINAL E1 E2 (peut avoir plusieurs etats final, toujours avec un espace entre chaque etat)
```
###### Mots qui peuvent être utilisés dans les transitions, pour simplifier l'écriture
|    POSIX   |     ASCII    |   Description |
| ---------- | ------------ | ------------- |
| alphaUpper    |        A-Z        |      Lettre capitales |
| alphaLower    |        a-z	        |      Lettres en minuscule|
| alpha         |        A-Za-z	        |      Caractères alphabétiques |
| punct         |][!"#$%&'()*+,./:;<=>?@\^_`{}~-&#x2502;|   Caractères de ponctuation |
| num           |        0-9        |      Chiffres décimaux  |
| space         |          \t\r\n\v\f      |      Caractères d'espacement |
| [X..Y]         |          X-Y      |      Tout les caractères entre X et Y |
###### Detection d'erreur
Si le fichier de l'automate présente une erreur l'application le signalera<br>
<i><u>Erreur de syntaxe :</u></i><br>
![Alt text](asset/automatonErrorSyntaxeFR.png?raw=true "")<br>
<i><u>Erreur dans des transitions :</u></i><br>
![Alt text](asset/automatonErrorCharacterFR.png?raw=true "")<br>

### Fichier .dot
Vous aurez aussi la possibilité de pouvoir téléchager un fichier .dot de l'automate, qui sera sauvegardé dans le dossier ```dotFile```.<br>
Ce choix vous sera demandé, après que vous aurez choisi un automate.<br>
![Alt text](asset/graphviz.svg?raw=true "")