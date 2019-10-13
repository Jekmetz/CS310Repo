import java.io.File;
import java.io.IOException;

public class LexTester {
	public static void main(String [] args)
	{
		LexicalAnalyzer lexAn = new LexicalAnalyzer();
		
		try
		{
			lexAn.setFile(new File("test.txt"));
		} catch (IOException e)
		{
			System.out.println("IOException: " + e.getLocalizedMessage());
		}
		
		while(lexAn.isFileOpen())
			lexAn.lex();	//demonstration. Do not use like this :)
	}
}
