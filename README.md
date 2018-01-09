# Pandemic Game

Jeu Pandemic: Ce jeu de plateau se déroule sur une carte représentant les grandes capitales mondiales. Quatre maladies vont se développer dans ces villes. L'objectif des joueurs est de collaborer pour lutter contre la propagation des maladies et si possible les vaincre. <br/>
Les joueurs pourront effectuer des déplacements, et faire en sorte de diminuer les épidémies afin d'éradiquer les maladies.<br/>

## Installation

1. Lancer le jeu directement dans l'IDE Eclipse avec la classe principale *PandemicGame.java*

2. Déplacer le dossier *Images* et le fichier Graph.txt dans le dossier *src*. Puis à partir de ce dossier, lancer en ligne de commande:

```
$ javac *.java
$ java PandemicGame
```

## Usage

Lancez le jeu. Celui-ci vous demandera le nombre de joueurs, avec un minimum de 2 et un maximum actuel de 9, dû au nombre limité de pions de couleurs différentes. Vous devrez ensuite indiquer chacun votre tour le personnage que vous souhaitez incarner en entrant un chiffre situé devant le personnage. 9 maladies de 3 niveaux différents apparaissent ensuite sur le plateau. A vous de jouer !

Vous disposez de quatre actions maximum par tour. Une liste d'actions vous est proposée selon votre personnage. A vous de choisir, en entrant un chiffre situé devant une action ou de passer votre tour si vous estimez ne plus avoir d'actions à effectuer avant la fin de votre tour. Vous pouvez, selon votre "rôle" dans le jeu, choisir parmi une combinaison des actions suivantes: vous déplacer vers une ville voisine qui sera listée, déplacer un pion sur une ville voisine listée ou sur une ville où se situe au moins un autre pion, ou vous pourrez baisser le niveau de la maladie de 1 dans la ville où vous vous situez. </br>
Si des maladies sont toujours présentes (et que vous n'avez donc pas gagné), une d'entre elles va évoluer ou apparaître sur une ville (vous en serez notifié). S'en suit le tour d'un autre joueur.</br>
Le jeu se termine lorsque toutes les villes sont atteintes par une maladie de niveau 3 ou lorsque toutes les maladies sont éradiquées.

## License

Janvier 2017

## Authors
<b>CRETIN Gabriel</b> - M1 Bioinformatique , Paris Diderot (VII)<br/>
<b>LEMATRE Sophie</b> - M1 Bioinformatique , Paris Diderot (VII)
