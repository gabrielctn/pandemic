public class GameParticipant {
    private String city;
    private SimpleImage image;

    // CONSTRUCTEUR
    public GameParticipant(String c) {
        city = c;
    }

    // GETTER - SETTER
    public String getCity() {
        return city;
    }

    public void setCity(String c) {
        this.city = c;
    }

    public SimpleImage getImage() {
        return image;
    }

    public void setImage(SimpleImage i) {
        image = i;
    }
}
