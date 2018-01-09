import java.util.List;
import java.util.Map;

public class Explosion implements Action {
    @Override
    /* Augmentation du niveau des villes voisines de la ville touchée */
    public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
        // Récupère les voisins de la ville sélectionnée
        List<String> neighbours = g.getNeighbours(p.getCity());

        // Propage la maladie aux villes voisines en faisant attention à bien assigner la couleur
        for (Map.Entry<String, Disease> entry : initGb.getHashDiseasePerCity().entrySet()) {
            // Si la ville est voisine
        	int diseaseColor = g.getCityCoord().get(entry.getKey()).get('C');
            if (neighbours.contains(entry.getKey()) && !initGb.isCured(diseaseColor)) {
                // Si elle n'a pas de maladie création sinon augmentation du niveau
                if (entry.getValue() == null) {
                    // Récupère la couleur de la ville, état 1, nom de la ville
                    Disease d = new Disease(g.getCityCoord().get(entry.getKey()).get('C'), 1, entry.getKey());

                    entry.setValue(d);
                    gb.placeOnCity(entry.getKey(), d.getImage());
                } else {
                    entry.getValue().explode(gb);
                }
            }
        }

        return true;
    }
}
