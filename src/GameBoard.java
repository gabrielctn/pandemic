public interface GameBoard {
    public void move(SimpleImage p, String startCity, String endCity);

    public void placeOnCity(String cityname, SimpleImage p);

    public void setCase(String cityname, int x, int y);
}
