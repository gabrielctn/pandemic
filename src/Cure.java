
public class Cure implements Action {

	@Override
	/* Eradique une maladie si le joueur a au moins 5 cartes d'une même couleur */
	public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
		
		/* Si le joueur n'a pas au moins 5 cartes, il ne peut pas 
		trouver de vaccin et perd son tour */
		if(((Player)p).hasNCards() != 5) {
			System.out.println("Vous n'avez pas 5 cartes de la même couleur, vous perdez votre tour\n");
			return false;
		}
		
		// Calcule combien de cartes de chaque maladie possède le joueur
		int [] cardsOccurence = new int[4];
		for (int i=0; i<((Player)p).hasNCards(); i++) {
			if(((Player)p).getCards()[i] != 0) {
				cardsOccurence[((Player)p).getCards()[i]-1]++;
			}
		}
		// Si le joueur a 5 cartes de la même couleur de maladie alors il a trouvé son vaccin
		for (int i=0; i<cardsOccurence.length; i++) {
			if (cardsOccurence[i] >= 5) {
				initGb.getCuredDiseases()[i] = true;
				for(int j=0; j<((Player)p).getCards().length; j++) {
					if(((Player)p).getCards()[j] == i+1) {
						((Player)p).getCards()[j] = 0;
					}
				}
				String color = ((Player)p).cardsColor(i+1);
				System.out.println("Bravo ! Vous avez trouvé un vaccin contre la maladie "+color);
				// 
		        gb.placeOnCity("Cure"+color, new SimpleImage("Images/"+color+".png", 50, 70));

				return true;
			}
		}
		System.out.println("Vous n'avez pas 5 cartes de la même couleur, vous perdez votre tour\n");
		return false;
	}
	
	@Override
	public String toString() {
		return "Vaccin: Eureckâ ! Vous avez trouvé un vaccin pour la maladie !";
	}

	
}
