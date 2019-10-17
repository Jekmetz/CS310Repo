/* Operation Pass the Class:
 * Jay Kmetz, Ashly Lovings, Aron Ludwinski
 * Programming Assignment 2: LexTester
 * Due: 10/25/2019
 */

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
