import java.io.*;

import java.util.*;

public class AdjacencyList extends Graphe {
    private HashMap<Integer, List<Integer>> adjList;

    /* A partir d'une matrice d'adjacence */
    public AdjacencyList(AdjacencyMatrix Matrix_Ref) {
        boolean [][] adjMatrix = Matrix_Ref.getAdjMatrix();

        adjList = new HashMap<Integer, List<Integer>>();

        // On lit la matrice d'adjacence
        for (int i = 0; i < Matrix_Ref.getNbCities(); i++) {
            List<Integer> tmpList = new ArrayList<Integer>();

            for (int j = 0; j < Matrix_Ref.getNbCities(); j++) {
                // Si on trouve true, il y a une liaison entre les deux villes
                // On ajoute l'index j de la ville à la liste de la ville i
                if (adjMatrix[i][j] == true) {
                    tmpList.add(j);
                }
            }

            adjList.put(i, tmpList);
        }

        // Transfert des dictionnaires de coordonnées, d'id et
        // le nombre de ville qui restent inchangés
        this.setCityCoord(Matrix_Ref.getCityCoord());
        this.setNbCities(Matrix_Ref.getNbCities());
        this.setMapCityToNum(Matrix_Ref.getMapCitytoNum());
    }

    // CONSTRUCTEUR
    /* A partir d'un fichier */
    public AdjacencyList(String fileName) throws IOException {
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
    @Override
    /* Remplir la Liste d'adjacence */
    public void fillAdjComp(Scanner s, Map<String, Integer> mapCityToNum) {
        adjList = new HashMap<Integer, List<Integer>>();

        while (s.hasNextLine()) {
            String [] line = s.nextLine().split("\\##");
            String cityToLink = line[0].trim();
            String cityLinkedTo = line[1].trim();

            // Si la ville existe déjà on ajoute les villes qui lui sont liées
            // Sinon on créé une liste avec la première ville liée
            if (adjList.containsKey(mapCityToNum.get(cityToLink))) {
                adjList.get(mapCityToNum.get(cityToLink)).add(mapCityToNum.get(cityLinkedTo));
            } else {
                List<Integer> tmpList = new ArrayList<Integer>();

                tmpList.add(mapCityToNum.get(cityLinkedTo));
                adjList.put(mapCityToNum.get(cityToLink), tmpList);
            }

            // Symétriquement, on effectue la même opération pour la ville liée
            if (adjList.containsKey(mapCityToNum.get(cityLinkedTo))) {
                adjList.get(mapCityToNum.get(cityLinkedTo)).add(mapCityToNum.get(cityToLink));
            } else {
                List<Integer> tmpList = new ArrayList<Integer>();

                tmpList.add(mapCityToNum.get(cityToLink));
                adjList.put(mapCityToNum.get(cityLinkedTo), tmpList);
            }
        }
    }

    // GETTER - SETTER
    public HashMap<Integer, List<Integer>> getAdjList() {
        return adjList;
    }

    /* Trouver les voisins d'une ville */
    public List<String> getNeighbours(String city) {
        Map<String, Integer> m = getMapCitytoNum();
        int indexCity = m.get(city);
        List<String> neighbours = new ArrayList<String>();

        // Parcours de la liste de la ville donnée
        // on récupère le nom des villes voisines
        for (int j : adjList.get(indexCity)) {
            neighbours.add(getCityName(j));
        }

        return neighbours;
    }
}


