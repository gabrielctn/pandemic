import java.util.concurrent.ThreadLocalRandom;

public class SpreadDisease implements Action {
    @Override
    /* Choisi une maladie spécifique à faire évoluer */
    public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
        
    	// On tire au hasard une maladie et une ville (si un vaccin n'existe pas pour elle)
    	int randomDisease = -1;
    	do {
	        randomDisease = ThreadLocalRandom.current().nextInt(1, 5);
    	} while(initGb.isCured(randomDisease));
    	
        // Tant que la ville ne correspond pas géographiquement à la maladie (même couleur que la maladie)
    	String city = "";
        do {
            int randomCityIndex = ThreadLocalRandom.current().nextInt(0, g.getNbCities());

            city = g.getCityName(randomCityIndex);
        } while (!(g.getCityCoord().get(city).get('C').equals(randomDisease)));

        // Si la ville n'a pas de maladie, on la crée, sinon on la fait évoluer
        if (initGb.getHashDiseasePerCity().get(city) == null) {
            Disease d = new Disease(randomDisease, 1, city);

            gb.placeOnCity(city, d.getImage());
            initGb.getHashDiseasePerCity().put(city, d);
        } else {
            initGb.getHashDiseasePerCity().get(city).spread(gb, (Player)p, g, initGb);
        }
        System.out.println("\n***** ATTENTION ! La maladie s'est propagée à " + city + " *****\n");

        return true;
    }
}
