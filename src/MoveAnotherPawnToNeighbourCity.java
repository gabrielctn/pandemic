import java.util.InputMismatchException;

public class MoveAnotherPawnToNeighbourCity extends Move {
    @Override
    /* Déplacer un joueur choisi dans une ville voisine */
    public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
        boolean success = false;
        int indexP = 0;

        // Savoir quel index de joueur est à déplacer
        while (!success) {
            try {
                String whereArePlayers = "";

                // On parcours les joueurs (sans compter la maladie qui est le dier joueur, d'où length-1
                for (int i = 0; i < initGb.getPlayers().length - 1; i++) {
                    whereArePlayers += "Joueur " + (i + 1) + " est à " + initGb.getPlayers()[i].getCity() + "\n";
                }

                System.out.println(
                    "Quel joueur désirez-vous déplacer ? (numéro) sachant qu'ils sont aux emplacements suivants:\n"
                    + whereArePlayers);
                indexP = PandemicGame.s.nextInt();

                while ((indexP < 1) || (indexP > (initGb.getPlayers().length - 1))) {
                    System.out.println("Joueur incorrect, ressaisissez un numéro : ");
                    indexP = PandemicGame.s.nextInt();
                }

                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Vous devez saisir un numéro");
            } finally {
                PandemicGame.s.nextLine();
            }
        }

        // Appel à la fonction de la classe mère pour un déplacement
        // simple du pion vers la ville voisine
        super.effet(gb, initGb.getPlayers()[indexP - 1], g, initGb);

        return true;
    }

    @Override
    public String toString() {
        return "Déplacement: Déplacer un pion vers une de ses villes voisines";
    }
}

