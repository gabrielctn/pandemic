import java.util.concurrent.ThreadLocalRandom;

public class SpreadCity implements Action {
    @Override
    /* Choisi une ville spécifique où augmenter la maladie */
    public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
    	int color = -1;
    	String city = "";
    	do {
	    	// On tire au hasard une ville
	        int randomCityIndex = ThreadLocalRandom.current().nextInt(0, g.getNbCities());
	        city = g.getCityName(randomCityIndex);
	        // On récupère la couleur géographique de la ville
	        color = g.getCityCoord().get(city).get('C');
    	} while(initGb.isCured(color));
    	
    	// Si la ville n'a pas encore de maladie, on la crée, sinon on la fait évoluer
        if (initGb.getHashDiseasePerCity().get(city) == null) {
            Disease d = new Disease(color, 1, city);

            gb.placeOnCity(city, d.getImage());
            initGb.getHashDiseasePerCity().put(city, d);
        } else {
            initGb.getHashDiseasePerCity().get(city).spread(gb, (Player)p, g, initGb);
        }
    	
        System.out.println("\n***** ATTENTION ! La maladie s'est propagée à " + city + " *****");

        return true;
    }
}
