
import java.util.*;
import java.io.*;


public class ConstructGraphe implements Graphe{

	private int nbCities;
	private boolean [][] adjMatrix;
	private Map<String, Map<Character, Integer>> dCityCoord;
	
	
	
	public boolean [][] getAdjMatrix() {
		return adjMatrix;
	}
	
	
	public int getNbCities() {
		return nbCities;
	}
	
	
	/* Genere un dictionnaire {ville : indice dans la matrice} */
	public Map<String, Integer> getNumNoeuds() {
		Map<String, Integer> mapCitytoNum = new HashMap<String, Integer>();
		
		int i = 0;
		for (Map.Entry<String, Map<Character, Integer>> entry : dCityCoord.entrySet()) {
			mapCitytoNum.put(entry.getKey(), i++);
		}
		return mapCitytoNum;
	}
	
	// Matrice d'adjacence: "true" où les villes sont liées
	public void fillMatrix(Scanner s, Map<String, Integer> mapCityToNum){
		
		adjMatrix = new boolean[nbCities][nbCities];
		
		while(s.hasNextLine()) {
			String[] line = s.nextLine().split("\\##");
			String cityToLink = line[0].trim();
			String cityLinkedTo = line[1].trim();
			adjMatrix[mapCityToNum.get(cityToLink)][mapCityToNum.get(cityLinkedTo)] = true;
			adjMatrix[mapCityToNum.get(cityLinkedTo)][mapCityToNum.get(cityToLink)] = true; // Matrice symétrique
		}
	}
	
	public void getCityCoord(Scanner s){
		
		dCityCoord = new HashMap<String, Map<Character, Integer>>();
		nbCities = Integer.parseInt(s.nextLine());
		s.nextLine();// Taille du plateau utile de le garder ou non????
		Map<String, Integer> dColor = new HashMap<String, Integer>(){ // Code couleur pour les villes / maladies
			{
				put("Bleue", 1);
				put("Jaune", 2);
				put("Noire", 3);
				put("Rouge", 4);
			}
		};
		
		for(int i=0; i<nbCities; i++) {
			String[] line = s.nextLine().split("#");
			
			String[] coord = line[2].split(",");
			String xCoord = coord[0].trim().substring(1, coord[0].trim().length());
			String yCoord = coord[1].trim().substring(0, coord[1].trim().length()-1);
			
			// Initialise le dictionnaire { 'C':couleur, 'X':coordonnéeX, 'Y':coordonnéeY }
			HashMap<Character, Integer> dCityCoordValues = new HashMap<Character, Integer>(){
				{
					put('C', dColor.get(line[1].trim()));
					put('X', Integer.parseInt(xCoord.trim())); 
					put('Y', Integer.parseInt(yCoord.trim()));
				}
			};
			// Initialise le dictionnaire des villes. Chaque ville (clé) a un dictionnaire (valeur) avec couleur et coordonnées correspondantes
			dCityCoord.put(line[0].trim() , dCityCoordValues);
		}
	}
	
	
	public ConstructGraphe(String fileName) throws IOException{
	
		Scanner s = null;
	    try {
			s = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8")));
			getCityCoord(s);
			Map<String, Integer> mapCityToNum = getNumNoeuds();
			fillMatrix(s, mapCityToNum);
	    } finally {
            if (s != null) {
            	s.close();
            }
        }
	}
	
	@Override
	public int getNbNoeud() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Integer> getNoeudsVoisin(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putEtiquette(String nomVille, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVoisins(int num1, int num2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumNoeud(String nomVille) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<String> getVillesVoisines(String ville) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
