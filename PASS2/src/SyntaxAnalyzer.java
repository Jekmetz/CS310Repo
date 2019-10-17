/* Operation Pass the Class:
 * Jay Kmetz, Ashly Lovings, Aron Ludwinski
 * Author(s): Jay Kmetz
 * Programming Assignment 2: SyntaxAnalyzer
 * 
 * Description: Uses the lexical analyzer to see if a specified block of 
 * 				code is in a language
 * 
 * Due: 10/25/2019
 */

import java.io.File;
import java.io.IOException;

public class SyntaxAnalyzer {
	//Init vars
	LexicalAnalyzer lexAn;
	
	public SyntaxAnalyzer() {};
	public SyntaxAnalyzer(LexicalAnalyzer lexAn)
	{
		this.lexAn = lexAn;
	}
	
	public void setLexAn(LexicalAnalyzer lexAn) { this.lexAn = lexAn; }

	public boolean fileIsInLanguage(File filename) throws IOException
	{
		lexAn.setFile(filename);
		
		return program();
	}
	
	private boolean program()
	{
		//TODO: Aron
		
		return false;
	}
	
	private boolean stmt_list()
	{
		//TODO: Aron
		
		return false;
	}
	
	private boolean stmt()
	{
		//TODO: Aron
		
		return false;
	}
	
	private boolean sa_if()
	{
		//TODO: Jay
		
		return false;
	}
	
	private boolean assign()
	{
		//TODO: Ashly
		
		return false;
	}
	
	private boolean expr()
	{
		//TODO: Jay
		
		return false;
	}
	
	private boolean term()
	{
		//TODO: Ashly
		
		return false;
	}
	
	private boolean bool()
	{
		//TODO: Ashly
		
		return false;
	}

}
