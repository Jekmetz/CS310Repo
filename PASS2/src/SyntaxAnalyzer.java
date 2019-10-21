/* Operation Pass the Class:
 * Jay Kmetz, Ashly Lovings, Aron Ludwinski
 * Author(s): Jay Kmetz, Ashly Lovings Aron Ludwinski
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
	Token nextToken;
	
	public SyntaxAnalyzer() {};
	public SyntaxAnalyzer(LexicalAnalyzer lexAn)
	{
		this.lexAn = lexAn;
	}
	
	public void setLexAn(LexicalAnalyzer lexAn) { this.lexAn = lexAn; }

	private void lex() { nextToken = lexAn.lex(); }
	
	public boolean fileIsInLanguage(File filename) throws IOException
	{
		lexAn.setFile(filename);
		lex();
		return sa_if();
	}
	
	private boolean program()
	{
		//TODO: Aron
		
		return true;
	}
	
	private boolean stmt_list()
	{
		//TODO: Aron

		return true;
	}
	
	private boolean stmt()
	{
		//TODO: Aron
		
		return true;
	}
	
	private boolean sa_if()
	{
		if(nextToken != Token.IF_KEY) return false;		//if
		lex();
		if(nextToken != Token.L_PAREN) return false; 	// (
		lex();
		if(!bool()) return false;						// boolean expression
		if(nextToken != Token.R_PAREN) return false;	// )
		lex();
		if(nextToken != Token.THEN_KEY) return false;	// then
		lex();
		if(!stmt_list()) return false;					// statement list
		
		if(nextToken == Token.ELSE_KEY)					// else statement
		{
			lex();
			if(!stmt_list()) return false;				// statement list
		}
		
		if(nextToken != Token.ENDIF_KEY) return false;	// endif
		
		return true;
	}
	
	private boolean assign()
	{
		//TODO: Ashly
		
		return true;
	}
	
	private boolean expr()
	{
		//TODO: Jay
		
		return true;
	}
	
	private boolean term()
	{
		//TODO: Ashly
		
		return true;
	}
	
	private boolean bool()
	{
		//TODO: Ashly

		return true;
	}

}
