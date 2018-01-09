public class Pass implements Action {
    @Override
    public boolean effet(GameBoard gb, GameParticipant p, Graphe g, InitGameBoard initGb) {
        return false;
    }

    @Override
    public String toString() {
        return "Passer son tour: Vous ne pouvez rien faire, c'est au tour du joueur suivant";
    }
}

