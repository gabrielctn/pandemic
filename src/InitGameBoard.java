import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class InitGameBoard {
    private Player [] players; // Tableau des joueurs
    private Map<String, Disease> hashDiseasePerCity;   /* Dictionnaire indiquant pour chaque 
    												    ville la maladie qui s'y est développé */
    private boolean [] curedDiseases = new boolean[4]; /* Tableau dont les indices sont les maladies 
    												      et indiquant si une maladie est éradiquée ou non */

    // CONSTRUCTEUR
    public InitGameBoard(Graphe g, GameBoard gb, int nbPlayers) {
        // Place les villes sur le plateau de jeu
        Map<String, Map<Character, Integer>> d = g.getCityCoord();

        for (Map.Entry<String, Map<Character, Integer>> entry : d.entrySet()) {
            gb.setCase(entry.getKey(), entry.getValue().get('X'), entry.getValue().get('Y'));
        }
        
        // Les emplacements des fioles indiquant qu'un vaccin a été trouvé pour une maladie
        gb.setCase("CureJaune", 392, 722);
        gb.setCase("CureRouge", 444, 722);
        gb.setCase("CureBleue", 502, 722);
        gb.setCase("CureNoire", 554, 722);
        
        // Initialise le tableau des villes avec leurs maladies respectives
        initHashDiseasePerCity(d);
        
        // Construction des joueurs et leurs actions respectives
        // Puis place les joueurs sur Paris
        players = new Player[nbPlayers + 1];    // Le dernier joueur "phantome" est la maladie

        int i;
        for (i = 0; i < nbPlayers; i++) {
            players[i] = new Player(setPlayerActions(i + 1));
            players[i].setImage(new SimpleImage("Images/pin" + i + ".png", 50, 50));
            gb.placeOnCity("Paris", players[i].getImage());
        }
        

        // Ce joueur joue le rôle de la maladie à chaque tour
        players[i] = new Player(new Action[] { new SpreadDisease(), new SpreadCity() });

        // Place les maladies
        List<Integer> numArray = new ArrayList<Integer>();
        int randomNum = -1;

        for (i = 0; i < 3; i++) {             // 3 villes par niveau de maladie
            for (int j = 1; j <= 3; j++) {    // 3 niveaux de maladie
                do {
                    randomNum = ThreadLocalRandom.current().nextInt(0, g.getNbCities());
                } while (numArray.contains(randomNum));

                numArray.add(randomNum);

                String cityName = g.getCityName(randomNum);
                Disease di = new Disease(d.get(cityName).get('C'), j, cityName);

                gb.placeOnCity(cityName, di.getImage());
                hashDiseasePerCity.put(cityName, di);
            }
        }
    }


    // METHODES
    /* Rempli le dictionnaire qui répertorie les maladies pour toutes les villes */
    private void initHashDiseasePerCity(Map<String, Map<Character, Integer>> d) {
        hashDiseasePerCity = new HashMap<String, Disease>();

        for (Map.Entry<String, Map<Character, Integer>> entry : d.entrySet()) {
            hashDiseasePerCity.put(entry.getKey(), null);
        }
    }

    public Map<String, Disease> getHashDiseasePerCity() {
        return hashDiseasePerCity;
    }
    
    /* Retourne si un vaccin a été trouvé pour l'index de la couleur donnée */
    public boolean isCured(int diseaseColor) {
    	return curedDiseases[diseaseColor-1];
    }

    
    /* Attribue les actions suivant le type de personnage choisi */
    private Action [] setPlayerActions(int playerIndex) {
        boolean success = false;
        int playerType = -1;

        while (!success) {
            try {
                System.out.println("Joueur " + playerIndex + ", quel type de joueur êtes-vous:\n" + "1 : Infirmier\n"
                                   + "2 : French Doctor\n" + "3 : Répartiteur");
                playerType = PandemicGame.s.nextInt();

                // Choix en dehors des propositions
                while (!((playerType >= 1) && (playerType <= 3))) {
                    System.out.println("Type de joueur incorrect, réessayez (entre 1 et 3): ");
                    playerType = PandemicGame.s.nextInt();
                }

                success = true;
            } catch (InputMismatchException e) {
                PandemicGame.s.nextLine();
                System.out.println("Vous devez saisir un chiffre !");
                continue;
            }
        }

        // Attribution de la liste d'actions selon le personnage choisi
        Action [] actions = null;

        switch (playerType) {
            case 1 :
                actions = new Action[] { new Pass(),
                						 new Discard(),
                						 new PickCard(),
                						 new Change(),
                						 new Heal(),
                						 new Cure()};
                break;

            case 2 :
                actions = new Action[] { new Pass(),
										 new Discard(),
										 new PickCard(),
										 new Change(),
										 new Heal(), 
                						 new Cure(),
                						 new Move()};
                break;

            case 3 :
                actions = new Action[] { new Pass(),
										 new Discard(),
										 new PickCard(),
										 new Change(),
										 new Cure(),
                						 new Move(), 
                						 new MoveAnotherPawn(), 
                						 new MoveAnotherPawnToNeighbourCity()};
                break;
        }

        return actions;
    }

    
    /* Les maladies gagnent s'il y a au moins 10 villes de niveau 3 */
    public boolean diseaseWin() {
    	int nbDiseases = 0;
        for (Map.Entry<String, Disease> city : hashDiseasePerCity.entrySet()) {
            if(city.getValue() != null && city.getValue().getState() == 3) {
            	nbDiseases++;
            }
            if(nbDiseases == 10) {
            	return true;
            }
        }
        return nbDiseases == 10;
    }
    
    
    /* Cherche si aucune ville n'a de maladie */
    public boolean humansWin() {
        return hashDiseasePerCity.values().isEmpty();
    }
    

    /* Cherche si les joueurs ou la maladie a gagné */
    public boolean endGame() {
        return humansWin() || diseaseWin();
    }
    
    
    // GETTER - SETTER
    public Player [] getPlayers() {
        return players;
    }
    
    
    public boolean [] getCuredDiseases() {
    	return curedDiseases;
    }
}

