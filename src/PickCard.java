import java.util.concurrent.ThreadLocalRandom;

public class PickCard implements Action {

	@Override
	/* Le joueur pioche une carte de couleur */
	public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
		
		// Le joueur a déjà 7 cartes (limite max)
		if(((Player)p).hasNCards() == 7) {
			System.out.println("Vous avez déjà le maximum de cartes (7) ... Entre nous, vous avez perdu une action assez bêtement, hum...");
			return true;
		}
		
		// La carte est intégrée dans la main du joueur, au 1er emplacement vide
		int card = ThreadLocalRandom.current().nextInt(1, 5);
		for(int i=0; i<((Player)p).getCards().length; i++) {
			if(((Player)p).getCards()[i] == 0) {
				((Player)p).getCards()[i] = card;
				break;
			}
		}
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Piocher: Vous piochez une carte dans la pile de cartes couleur";
	}
}
