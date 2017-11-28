import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConstructGraphe implements Graphe{

	private String content;
	private String [] contentLines;
	private int nbCities;
	private boolean [][] adjMatrix;
	
	private String readFile(String filename) {
		
	    String content = null;
	    File file = new File(filename);
	    FileReader reader = null;
	    try {
	        reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        content = new String(chars);
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        	try {
	        		if(reader != null){
	        			reader.close();
	        		}
				} catch (IOException e) {
					e.printStackTrace();
				}
	    }
	    return content;
	}
	
	/**
	 *Génère un dictionnaire {ville : indice dans la matrice}
	 */
	public Map<String, Integer> getNumNoeuds() {
		
		Map<String, Integer> mapCitytoNum = new HashMap<String, Integer>();
	
		for(int i=0; i<nbCities; i++) {
			String [] line = contentLines[i].split("#");
			mapCitytoNum.put(line[0], i);
		}
		return mapCitytoNum;
	}
	
	
	public boolean [][] fillMatrix(String fileContent){
		
		boolean [][] matrix = new boolean[nbCities][nbCities];
		
		return matrix;
	}
	
	
	public ConstructGraphe(String fileName) {
	
		String content = readFile(fileName);
		String [] lines = content.split("\n");
		Map<String, Integer> mapCitytoNum = getNumNoeuds();
	}
	
	@Override
	public int getNbNoeud() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Integer> getNoeudsVoisin(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putEtiquette(String nomVille, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVoisins(int num1, int num2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumNoeud(String nomVille) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<String> getVillesVoisines(String ville) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
