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
			
			  reader.lines().forEach(line -> processLine(line));
			}
		generateMap();
	//	System.out.println("=================================");
	//	printLogger();
		}

	private static void printLogger() {
		for (String  keyLine : encodedLine.keySet()) {
			Integer value= encodedLine.get(keyLine);
		    System.out.println(keyLine +  value );
		}		
	}

	private static void processLine(String line) {
		
		String format = "dd-mm-yyyy hh:mm:ss";	
		String text = line.substring(format.length()+1);
		
		String regex = "(\\d{2}-\\d{2}-\\d{4}\\s+)";
		Pattern pattern = Pattern.compile(regex);
		//text= "current date: 01-05-2017. Naomi is ";
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
		   int start = matcher.start(); // start index of match
		  int  end = matcher.end(); // end index of match
		  String  result = matcher.group();
		  System.out.println(result);
		  System.out.println("-----------------------------------");
		}
		content.add(line);
	//	System.out.println(text);
	}
	
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

	private static String findChangingWord(String keyLine, String text) {
		String gap= "";
		String[] first= keyLine.split(" ");
		String[] second= text.split(" ");
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

