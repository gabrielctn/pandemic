import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PandemicGame {
    public static Scanner s;
    public static int currentPlayer;
    
    public static void main(String [] args) {
        // CREATION GRAPHE
        String fileName = "graph.txt";
        // Génère la matrice d'adjacence à partir du fichier
        AdjacencyMatrix adjMatrix = null;

        // AdjacencyList adjList = null;
        try {
            /*
             *  Pour générer la matrice d'adjacence
             * et la liste d'adjacence
             */
            adjMatrix = new AdjacencyMatrix(fileName);
            // adjList = new AdjacencyList(fileName);
            // adjList = new AdjacencyList(adjMatrix);
            // adjMatrix = new AdjacencyMatrix(adjList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameBoard gb = new ViewExample("Images/pandemicExemple.jpg", 1200, 800);

        // NOMBRE DE JOUEURS
        s = new Scanner(System.in);

        boolean success = false;
        int nbPlayers = -1;

        // Demande le nombre de joueurs
        while (!success) {
            try {
                System.out.println("Combien de joueurs ? (au moins 2, maximum 9) ");
                nbPlayers = s.nextInt();

                while ((nbPlayers < 2) || (nbPlayers > 9)) {
                    System.out.println("Nombre de joueur incorrect, réessayez : ");
                    nbPlayers = s.nextInt();
                }

                success = true;
            } catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("Vous devez saisir un nombre !");
                continue;
            }
        }

        // Initiation du jeu avec le bon nombre de joueurs
        InitGameBoard initGb = new InitGameBoard(adjMatrix, gb, nbPlayers);
        // BOUCLE DU JEU
        Player [] players = initGb.getPlayers();
        currentPlayer = 0;
        boolean theEnd = initGb.endGame();

        while (!theEnd) {
            System.out.println("\n\n*************** Au tour du joueur " + (currentPlayer + 1) + " ***************");

            for (int i = 0; i < 4; i++) {
                // Affiche les éléments de jeu du joueur actuel:
                // - Sa ville actuelle
                // - Ses actions possibles
                System.out.println(players[currentPlayer]);
                // Récupère l'action choisie
                success = false;

                int actionChoice = -1;

                while (!success) {
                    try {
                        System.out.println("Que voulez-vous faire ?");
                        actionChoice = s.nextInt();

                        while ((actionChoice < 1) || (actionChoice > players[currentPlayer].getBehavior().length)) {
                            System.out.println("Action incorrecte, réessayez : ");
                            actionChoice = s.nextInt();
                        }

                        success = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Vous devez saisir un chiffre !");
                    } finally {
                        s.nextLine();
                    }
                }

                // Fait l'action choisie
                if (!players[currentPlayer].getBehavior()[actionChoice - 1]
                                           .effet(gb, players[currentPlayer], adjMatrix, initGb)) {
                    // L'action choisie est de passer, donc on passe au joueur suivant
                    break;
                }
            }

            theEnd = initGb.endGame();

            if (!theEnd) {
                // Change le joueur
                currentPlayer = nextPlayer(currentPlayer, nbPlayers);

                /*
                 *  La maladie effectue au hasard une action parmis:
                 * - propager une maladie spécifique dans une ville
                 * - propager une maladie dans une ville spécifique
                 */
                int randChoice = ThreadLocalRandom.current().nextInt(0, 2);

                players[nbPlayers].getBehavior()[randChoice].effet(gb, players[nbPlayers], adjMatrix, initGb);
            }
        }

        if (initGb.humansWin()) {
            System.out.println("Bravo Humains, vous avez vaincu les maladies !");
        } else {
            System.out.println("La maladie a gagné, il n'y a plus aucun humain sur Terre, "
                               + "l'humanité est terrassée, vous êtes mauvais.");
        }

        s.close();
    }

    // METHODES
    public static int nextPlayer(int currentPlayer, int nbPlayers) {
        if (currentPlayer == nbPlayers - 1) {
            return 0;
        }

        return currentPlayer += 1;
    }
}
