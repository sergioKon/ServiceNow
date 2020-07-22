import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoggerSercher {
    
	private static Integer rowIndex=-1;
	static Map<String,Integer> encodedLine = new HashMap<>();
	static List<String> content = new ArrayList<String>();
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (FileReader  in = new FileReader("Resource/sentenses.txt");
			 BufferedReader reader = new BufferedReader(in)) {
			  reader.lines().forEach(line -> content.add(line));
			}
		generateMap();
		}

	/**
	 * generate map contains  n*m other  keys 
	 *  n - number rows , 
	 * m - number words are reading from the file  
	 */
	private static void  generateMap() {
		for(String text : content) {
		String words[] = text.substring(20).split(" ");
		rowIndex++;
			for(int count=0; count< words.length; count++) {
				String keyLine = generateKey(words, count);
				Integer prevKey= encodedLine.get(keyLine.toString());
				if(prevKey!=null) {				 
				    System.out.println(content.get(prevKey)+  "\n" + text ) ;
					String chWorld= findChangingWord(content.get(prevKey),text);
					System.out.println("The changing word was: " + chWorld);
				}
				encodedLine.put(keyLine.toString(), rowIndex);
			}
		}	
	}
	
	/**
	 * 
	 * @param keyLine  - key from map which has been generate already
	 * @param text     - duplicate text without single world
	 * @return         - find single world from each line 
	 */
	private static String findChangingWord(String keyLine, String text) {
		String gap= "";
		String[] first= keyLine.split(" ");
		String[] second= text.split(" ");
		/* since text starts with date we have to parse string from third position */
		for(int i=2; i<first.length; i++) {
			if(first[i].equals(second[i])) {
				continue;
			}
			else {
				gap+= first[i] +"," + second[i];
				 return gap;
			}
		}
		return null;	
	}
 /**
  * 
  * @param words   - line from source file  
  * @param count   -  index of world which can be find as a differ 
  * @return        - unique key 
  */
	private static String generateKey(String[] words, int count) {
		StringBuffer keyLine= new StringBuffer();
		for( Integer i=0;i< count; i++) {
			keyLine.append(words[i]+" ");
		}
		 keyLine.append(" "+ count+" ");
		for(Integer i=count+1;i<words.length; i++) {
			keyLine.append(words[i]+" ");
		}
		return keyLine.toString();
	}
}

