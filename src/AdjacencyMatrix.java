import java.io.*;

import java.util.*;

public class AdjacencyMatrix extends Graphe {
    private boolean [][] adjMatrix;

    /* A partir d'une liste d'adjacence */
    public AdjacencyMatrix(AdjacencyList List_Ref) {
        adjMatrix = new boolean[List_Ref.getNbCities()][List_Ref.getNbCities()];

        for (Map.Entry<Integer, List<Integer>> entry : List_Ref.getAdjList().entrySet()) {
            for (int j : entry.getValue()) {
                adjMatrix[entry.getKey()][j] = true;
            }
        }

        // Transfert des dictionnaires de coordonnées, d'id et
        // le nombre de ville qui restent inchangés
        this.setCityCoord(List_Ref.getCityCoord());
        this.setNbCities(List_Ref.getNbCities());
        this.setMapCityToNum(List_Ref.getMapCitytoNum());
    }

    // CONSTRUCTEUR
    /* A partir d'un fichier */
    public AdjacencyMatrix(String fileName) throws IOException {
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8")));
            InitCityCoord(s);                     // Remplir le dictionnaire des coordonnées des villes
            getNumNoeuds();                       // Attribuer à chaque ville un id
            fillAdjComp(s, getMapCitytoNum());    // Remplir la liste d'adjacence
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    // METHODES
    /* Matrice d'adjacence: "true" où les villes sont liées */
    @Override
    public void fillAdjComp(Scanner s, Map<String, Integer> mapCityToNum) {
        adjMatrix = new boolean[this.getNbCities()][this.getNbCities()];

        while (s.hasNextLine()) {
            String [] line = s.nextLine().split("\\##");
            String cityToLink = line[0].trim();
            String cityLinkedTo = line[1].trim();

            adjMatrix[mapCityToNum.get(cityToLink)][mapCityToNum.get(cityLinkedTo)] = true;
            adjMatrix[mapCityToNum.get(cityLinkedTo)][mapCityToNum.get(cityToLink)] = true;    // Matrice symétrique
        }
    }

    // GETTER - SETTER
    public boolean [][] getAdjMatrix() {
        return adjMatrix;
    }

    /* Trouver les voisins d'une ville */
    public List<String> getNeighbours(String city) {
        Map<String, Integer> m = getMapCitytoNum();
        int indexCity = m.get(city);
        List<String> neighbours = new ArrayList<String>();

        for (int j = 0; j < getNbCities(); j++) {
            if (adjMatrix[indexCity][j]) {
                neighbours.add(getCityName(j));
            }
        }

        return neighbours;
    }
}


