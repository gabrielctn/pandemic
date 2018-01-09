public class Disease extends GameParticipant {
    private Action a = new Explosion();
    private int color;
    private int state;

    // CONSTRUCTEUR
    public Disease(int co, int s, String c) {
        super(c);
        color = co;
        state = s;
        setImage(new SimpleImage("Images/" + s + co + ".png", 30, 30));
    }

    // METHODES
    
    /* Une ville subit une explosion */
    public void explode(GameBoard gb) {
        if (state < 3) {
            this.state += 1;
        }

        replaceImage(gb);
    }

    /* Diminution du niveau de la maladie */
    public void heal(GameBoard gb) {
        this.state -= 1;
        replaceImage(gb);
    }

    /* Remplace l'image de la maladie */
    private void replaceImage(GameBoard gb) {
        // L'image du virus actuel est cachée
        getImage().setVisible(false);
        if (state != 0) {
            // Nouvelle image, avec le nouveau niveau
            setImage(new SimpleImage("Images/" + state + color + ".png", 30, 30));
            gb.placeOnCity(getCity(), getImage());
        }
    }

    /* Augmentation du niveau de la maladie */
    public boolean spread(GameBoard gb, Player p, Graphe g, InitGameBoard initGb) {
        // Si le niveau est au maximum, explosion
        if (state == 3) {
            System.out.println("\nEXPLOSION de la maladie dans la ville " + getCity());
            a.effet(gb, this, g, initGb);

            return true;
        } else {
            this.state += 1;
            replaceImage(gb);

            return true;
        }
    }

    // GETTER - SETTER
    public int getColor() {
        return color;
    }

    public int getState() {
        return state;
    }
}



