import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		String content = null;
	    String filename = "graph.txt";
		File file = new File(filename );
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
	    System.out.println(content);
	    
	    
	    Scanner in = new Scanner(new FileReader(file));
	    int integer = in.nextInt();
	    System.out.println(integer);
	}

}
