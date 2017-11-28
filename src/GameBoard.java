
public interface GameBoard {
    	public void setCase(String cityname, int x, int y);
       	public void placeOnCity(String cityname, SimpleImage p);
       	public void move(SimpleImage p, String startCity, String endCity);
}
