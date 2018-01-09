import java.util.InputMismatchException;

public class Discard implements Action {

	@Override
	public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
		
		if(((Player)p).hasNCards() == 0) {
			System.out.println("Vous n'avez rien à défausser ... Entre nous, vous avez perdu une action assez bêtement, hum...");
			return true;
		}
		
        int cardChoice = -1;
        boolean success = false;

        // Demande le numéro de la carte à défausser
        while (!success) {
            try {
                System.out.println("Quelle numéro de carte souhaitez-vous défausser ?");
                cardChoice = PandemicGame.s.nextInt();

                while ((cardChoice < 1) || cardChoice > 7 || ((Player)p).getCards()[cardChoice-1] == 0) {
                    System.out.println("Numéro de carte incorrect, réessayez : ");
                    cardChoice = PandemicGame.s.nextInt();
                }
                
                ((Player)p).getCards()[cardChoice-1] = 0;
                success = true;
            } catch (InputMismatchException e) {
            	PandemicGame.s.nextLine();
                System.out.println("Vous devez saisir un numéro !");
                continue;
            }
        }
        return success;
	}

	
	@Override
	public String toString() {
		return "Défausser: Vous choisissez une carte dans votre main à défausser";
	}
	
}
