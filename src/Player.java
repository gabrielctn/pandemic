import java.util.concurrent.ThreadLocalRandom;

public class Player extends GameParticipant {
    private Action [] behavior;
    private int [] cards = new int[7];

    // CONSTRUCTEUR
    public Player(Action [] b) {
        super("Paris");
        behavior = b;
        cards[0] = ThreadLocalRandom.current().nextInt(1, 5);
        cards[1] = ThreadLocalRandom.current().nextInt(1, 5);
    }

 // METHODES
    @Override
    /* Retourne les actions possibles du joueur */
    public String toString() {
        String s = "";
        int i = 0;
        for (i = 1; i <= behavior.length; i++) {
        	if(i == 5) {
        		s += "\nActions spéciales:\n";
        	}
            s += i + " :" + behavior[i - 1] + "\n";
        }
        
        String c = "";
        for(i=0; i<7; i++) {
        	if(cards[i] != 0) {
        		c += (i+1) + "." + cardsColor(cards[i]) + "  ";
        	}
        }

        return "\n  → Ville actuelle: " + getCity() + "\n" + "  → Cartes en main : " +  c + 
        		"\n\nActions possibles:\n" + s;
    }
    
    
    public String cardsColor(int indexColor) {
    	switch(indexColor) {
	    	case 1 : return "Jaune";
	    	case 2 : return "Rouge";
	    	case 3 : return "Bleue";
	    	case 4 : return "Noire";
	    	default : return null;
    	}
    }
    
    
    public int hasNCards() {
    	int nbCards = 0;
    	for(int card : cards) {
    		if(card != 0) {
    			nbCards++;
    		}
    	}
    	return nbCards;
    }


    // GETTER - SETTER
    public Action [] getBehavior() {
        return behavior;
    }
    
    
    public int [] getCards() {
    	return cards;
    }
}

