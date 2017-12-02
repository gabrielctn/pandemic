
import java.io.IOException;

public class test {

	public static void main(String[] args) {
		
	    String fileName = "graph.txt";
	    ConstructGraphe g = null;
	    try {
	    	g = new ConstructGraphe(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	   
	    
	}

}
