import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

abstract public class Graphe {
    private int nbCities;
    private Map<String, Map<Character, Integer>> dCityCoord;
    private Map<String, Integer> mapCityToNum;

    // ABSTRACT METHODES
    abstract public void fillAdjComp(Scanner s, Map<String, Integer> mapCityToNum);

    // GETTER - SETTER
    public Map<String, Map<Character, Integer>> getCityCoord() {
        return dCityCoord;
    }

    public void setCityCoord(Map<String, Map<Character, Integer>> d) {
        this.dCityCoord = d;
    }

    /* Renvoie le nom d'une ville suivant son id */
    public String getCityName(int index) {
        for (String s : mapCityToNum.keySet()) {
            if (mapCityToNum.get(s) == index) {
                return s;
            }
        }

        return null;
    }

    public void setMapCityToNum(Map<String, Integer> m) {
        this.mapCityToNum = m;
    }

    public Map<String, Integer> getMapCitytoNum() {
        return mapCityToNum;
    }

    public int getNbCities() {
        return nbCities;
    }

    public void setNbCities(int n) {
        this.nbCities = n;
    }

    abstract public List<String> getNeighbours(String city);

    // METHODES
    /* Genere un dictionnaire {ville : indice dans la matrice} */
    public void getNumNoeuds() {
        mapCityToNum = new HashMap<String, Integer>();

        int i = 0;

        for (Map.Entry<String, Map<Character, Integer>> entry : dCityCoord.entrySet()) {
            mapCityToNum.put(entry.getKey(), i++);
        }
    }
    
    /* Initialise le dictionnaire des coordonnées des villes */
    public void InitCityCoord(Scanner s) {
        dCityCoord = new HashMap<String, Map<Character, Integer>>();
        nbCities = Integer.parseInt(s.nextLine());
        s.nextLine();    // Taille du plateau

        final Map<String, Integer> dColor = new HashMap<String, Integer>() {    // Code couleur pour les villes / maladies
            {
                put("Jaune", 1);
                put("Rouge", 2);
                put("Bleue", 3);
                put("Noire", 4);
            }
        };

        for (int i = 0; i < nbCities; i++) {
            final String [] line = s.nextLine().split("#");
            String [] coord = line[2].split(",");
            // le substring permet d'enlever l'accolade
            // le trim permet d'enlever les espaces inutiles
            final String xCoord = coord[0].trim().substring(1, coord[0].trim().length());
            final String yCoord = coord[1].trim().substring(0, coord[1].trim().length() - 1);
            // Initialise le dictionnaire { 'C':couleur, 'X':coordonnéeX, 'Y':coordonnéeY }
            HashMap<Character, Integer> dCityCoordValues = new HashMap<Character, Integer>() {
                {
                    put('C', dColor.get(line[1].trim()));
                    put('X', Integer.parseInt(xCoord.trim()));
                    put('Y', Integer.parseInt(yCoord.trim()));
                }
            };

            // Initialise le dictionnaire des villes.
            // Chaque ville (clé) a un dictionnaire
            // (valeur) avec couleur et coordonnées correspondantes
            dCityCoord.put(line[0].trim(), dCityCoordValues);
        }
    }
    
}

