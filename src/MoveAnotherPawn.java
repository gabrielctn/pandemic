import java.util.InputMismatchException;

public class MoveAnotherPawn extends Move {
    @Override
    /* Déplace un pion choisi sur une ville choisie peuplée */
    public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
        boolean success = false;
        int indexP = 0;

        // Savoir quel index de joueur est à déplacer
        while (!success) {
            try {
                String whereArePlayers = "";

                for (int i = 0; i < initGb.getPlayers().length; i++) {
                    whereArePlayers += "Joueur " + (i + 1) + " est à " + initGb.getPlayers()[i].getCity() + "\n";
                }

                System.out.println(
                    "Quel joueur désirez-vous déplacer ? (numéro) sachant qu'ils sont aux emplacements suivants:\n"
                    + whereArePlayers);
                indexP = PandemicGame.s.nextInt();

                while ((indexP < 1) || (indexP > (initGb.getPlayers().length-1))) {
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

        // Joueur à déplacer
        Player pMoving = initGb.getPlayers()[indexP - 1];
        String city = "";

        success = false;

        // Vers quelle ville
        do {
            System.out.println("Ville d'arrivée : ");

            if (PandemicGame.s.hasNext()) {
                city = PandemicGame.s.nextLine();
            }

            for (Player pl : initGb.getPlayers()) {
                // Si la ville choisie est occupée par un autre pion
                // le déplacement peut se faire
                if (pl.getCity().equals(city)) {
                    gb.move(pMoving.getImage(), pMoving.getCity(), city);
                    pMoving.setCity(city);

                    return true;
                }
            }

            System.out.println("Aucun joueur n'est situé sur la ville");
        } while (!success);

        return true;
    }

    @Override
    public String toString() {
        return "Déplacement: Déplacer un pion dans une ville occupée par un autre pion";
    }
}

