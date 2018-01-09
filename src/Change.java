import java.util.InputMismatchException;

public class Change implements Action{

	@Override
	public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
		if(((Player)p).hasNCards() == 0) {
			System.out.println("Vous n'avez rien à échanger ... Entre nous, vous avez perdu une action assez bêtement, hum...");
			return true;
		}
		
		 boolean success = false;
	        int indexC = 0;
	        int indexP = 0;
	        // Savoir quelle carte est à échanger
	        while (!success) {
	            try {
	                System.out.println("Quelle carte voulez-vous échangez?");
	                indexC = PandemicGame.s.nextInt();

	                while ((indexC < 1) || (indexC > ((Player)p).getCards().length) || ((Player)p).getCards()[indexC-1] == 0) {
	                    System.out.println("Numéro de carte incorrect, ressaisissez un numéro : ");
	                    indexC = PandemicGame.s.nextInt();
	                }
	                
	                int nbPlayerWithCards = 0;
	                for (int i = 0; i < initGb.getPlayers().length; i++) {
	                	if(initGb.getPlayers()[i].hasNCards() != 0) {
	                		nbPlayerWithCards += 1;
	                	}	
	                }
	                // Il y a obligatoirement une personne, le joueur en question ayant des cartes
	                if(nbPlayerWithCards == 1) {
	                	System.out.println("Il n'y a aucun joueur avec qui échanger des cartes, dommage ....");
	                	return true;
	                }
	                
	   
	                System.out.println("Avec quel joueur ayant des cartes voulez-vous échanger ?");
	                indexP = PandemicGame.s.nextInt();

	                while ((indexP < 1) || (indexP > (initGb.getPlayers().length - 1)) ||
	                		indexP == (PandemicGame.currentPlayer+1) ||
	                		(initGb.getPlayers()[indexP-1].hasNCards() == 0)) {
	                    System.out.println("Numéro de Joueur incorrect, ressaisissez un numéro : ");
	                    indexP = PandemicGame.s.nextInt();
	                }
	                success = true;
	            } catch (InputMismatchException e) {
	                System.out.println("Vous devez saisir un numéro");
	            } finally {
	                PandemicGame.s.nextLine();
	            }
	        }
	        
	        // Par rapport à l'indexation du tableau
	        indexC--;
	        indexP--;
	        int i = 0;
	        for(i=0; i<initGb.getPlayers()[indexP].getCards().length; i++) {
	        	// On récupère la première case du tableau non vide
	        	if(initGb.getPlayers()[indexP].getCards()[i] != 0) break;
	        }
	        
	        // Echange de cartes avec par défaut la première carte du joueur choisi
	        int tmp = ((Player)p).getCards()[indexC];
	        ((Player)p).getCards()[indexC] = initGb.getPlayers()[indexP].getCards()[i];
	        initGb.getPlayers()[indexP].getCards()[i] = tmp;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Echange: Echanger une carte choisie avec un joueur choisi\n";
	}

}
