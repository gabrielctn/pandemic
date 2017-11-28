
/**
 *
 * @author yan jurski
 */
public class PandemicGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameBoard v;
        v = new ViewExample("Images/pandemicExemple.jpg",1200,800);
        v.setCase("Paris", 570,249);
        v.setCase("Madrid", 492,287);
        SimpleImage pion=new SimpleImage("Images/pin.png",40,40);
        v.placeOnCity("Paris", pion);
        v.move(pion, "Paris", "Madrid");
        v.move(pion, "Madrid", "Paris");

        
    }
    
}
