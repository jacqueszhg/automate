# Automate
Auteur : Jacques-Zheng <br>
Email : jacques.zheng@etu.univ-nantes.fr
 _____________________________________________________________________________________
# Sommaire
1. [Read Me : English]()
2. [Read Me : Français]()
 _____________________________________________________________________________________
#ENGLISH
 _____________________________________________________________________________________
# FRANCAIS
## Compiler et éxécuter le code
### Sur terminal
Pour compiler le code :
```
javac -d out/production/Automate src/*/*.java
```
Pour éxécuter le code
```
java -cp out/production/Automate application.Application
```
###Sur un IDE
Télécharger le projet et importer le sur votre IDE, puis éxécuter le via votre IDE.

## L'application
### Automate par défault
L'application utilise des automates déterministes pour vérifier une expression.<br>
Dans l'application vous trouverez des automates par défault :
- Smiley (smiley possible => :-); :-(; :=); :=( ;-) ;-( )
- HH:MM (Format Heure : minute)
- JJ/MM/AAAA (une date entre l'an 0001 et 9999)
- Adresse Email
- Polynome de degré 2
- Plaque d'immatriculation française 

### Import d'automate personnel
Possibilité d'importer un automate de votre création. Il devra suivre une syntaxe précise.<br>
Lors de l'éxécution de l'application vous trouverez un choix vous permettant de créer un fichier de base pour la création d'un automate, qui va être créer dans le dossier ```fileAutomaton``` avec le nom ```baseFile```
```
Exemple d'un fichier importable:

NAME AutomateExemple(nom sans espace)
ALPHABET A B C ... Z (alphabet avec un espace entre chaque caratère)
E0 A E1 (transition de l'automate)
E0 B E1 (transition de l'automate)
E1 0 E2 (transition de l'automate)
E1 4 E2 (transition de l'automate)
INITIAL E0 (juste un seul, car automate déterministe)
FINAL E2 E1 (peut avoir plusieur états final, toujours avec un espace entre chaque état)
```
###### Aide pour création fichier automate
Dans le package ``helpCreateFileAuomate``, la classe ``WriterFile`` aide pour la création des transitions répétitif entre deux états.<br>
Modifier seulement :
-   la variable ```s``` avec les caractères de transition
-   "stateStart" et "stateEnd" dans ``write.println("stateStart"+" "+ tab[i] +" "+"stateEnd");``

### Fichier .dot
Vous aurez aussi la possibilité de pouvoir téléchager un fichier .dot de l'automate, qui sera sauvegardé dans le dossier ```dotFile```.<br>
Ce choix vous sera demandé, après que vous aurez choisi un automate.<br>

