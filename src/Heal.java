public class Heal implements Action {
    @Override
    /* Diminution du niveau de la maladie de la ville actuelle */
    public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
        Disease d = initGb.getHashDiseasePerCity().get(p.getCity());

        if (d == null) {
            System.out.println("Il n'y a pas de maladie dans cette ville ! C'est au joueur suivant, désolé");

            return false;
        } else {
        	// La maladie diminue d'un niveau
        	d.heal(gb);
        	// La maladie va disparaître de la ville
            if (d.getState() == 0) {
                initGb.getHashDiseasePerCity().put(p.getCity(), null);
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Soin: Diminue de 1 le niveau d'une maladie";
    }
}
