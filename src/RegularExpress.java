import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpress {

	public static void main(String[] args) {
	 final String text = "The rain was  Spain on 25/10/2019 but on 05/01/2020 already had snowed ";
     findPattern(text);
	}
	private static void findPattern(String text) {
		
	//	String format = "dd-mm-yyyy hh:mm:ss";	
	//	String regex = "(\\d{2}-\\d{2}-\\d{4}\\s+)";
		String regex = "[0-3][0-9]";
		Pattern pattern = Pattern.compile(regex);
		//text= "current date: 01-05-2017. Naomi is ";
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
		 //  int start = matcher.start(); // start index of match
		 // int  end = matcher.end(); // end index of match
		  String  result = matcher.group();
		  System.out.println(result);
		  System.out.println("-----------------------------------");
		}
  }
}
