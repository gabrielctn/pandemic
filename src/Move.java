import java.util.List;

public class Move implements Action {
    @Override
    /*
     *  Cherche les voisins de la ville actuelle et déplace
     * le pion sur le voisin désiré
     */
    public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
        System.out.println("Ville actuelle : " + p.getCity());

        // Cherche les voisins de la ville
        List<String> neighbours = g.getNeighbours(p.getCity());

        if (neighbours.isEmpty()) {
            System.out.println("Votre ville actuelle n'a pas de voisins, aucun déplacement n'est donc possible!");

            return false;
        }

        System.out.println("Villes voisines accessibles: " + neighbours);

        String city;

        // Tant que la ville demandée n'existe pas dans les voisins
        do {
            System.out.println("Ville d'arrivée : ");
            city = PandemicGame.s.nextLine();
        } while (!neighbours.contains(city));

        // Déplace le pion vers la ville voisine
        gb.move(((Player)p).getImage(), p.getCity(), city);
        p.setCity(city);

        return true;
    }

    @Override
    public String toString() {
        return "Déplacement: Se déplacer dans une ville voisine";
    }
}
