# Automate
Auteur : Jacques-Zheng <br>
Email : jacques.zheng@etu.univ-nantes.fr
 ____________________________________________________________________________
### FRANCAIS

Pour compiler le code :
```
javac -d out/production/Automate src/*/*.java
```
Pour éxécuter le code
```
java -cp out/production/Automate application.Application
```


L'application fournit permet de vérifier si une expression est acceptée par un automate déterministe.
Dans l'application vous trouverez des automates par défault :
- Smiley (smiley possible => :-); :-(; :=); :=( ;-) ;-( )
- HH:MM (Heure : minute)
- JJ/MM/AAAA (une date entre l'an 0001 et 9999)
- Adresse Email
- Polynome de degré 2
- Plaque d'immatriculation française 

Vous avez aussi la possibilité d'importer vous même un automate de votre création. Il devra cependant suivre une syntaxe bien précise.<br>
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
FINAL E2 E1 (peut avoir plusieur états finaln toujour avec un espace entre)
```

Vous aurez aussi la possibilité de pouvoir téléchager un fichier .dot de l'automate.
Ce choix vous sera demandé, après que vous aurez choisi un automate.