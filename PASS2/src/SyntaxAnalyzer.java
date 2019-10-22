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
		return program();
	}
	
	private boolean program()
	{
		//TODO: Aron
		if(nextToken != Token.PROCEDURE_KEY) return false;	//procedure
		lex();
		if(nextToken != Token.IDENT) return false;			//name
		lex();
		if(nextToken != Token.BEGIN_KEY) return false; 		//begin
		lex();
		if(!stmt_list()) return false;						//statement list
		if(nextToken != Token.END_KEY) return false;		//end
		lex();
		if(nextToken != Token.SCOLON_OP) return false;		//;
		return true;
	}
	
	private boolean stmt_list()
	{
		//TODO: Aron
		if(!stmt()) return false;							//statement
		if(nextToken != Token.SCOLON_OP) return false;		//;
		lex();
		if(nextToken != Token.END_KEY)						//end
			if (!stmt_list()) return false;					//statement list
		return true;
	}
	
	private boolean stmt()
	{
		//TODO: Aron
		if(!assign() && !sa_if()) return false;	//<var>:=<expr> or if statement
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
		if(!term()) return false;	// term
		
		// {(+ | - | * | /)<term>}
		while(nextToken == Token.ADD_OP || nextToken == Token.SUB_OP || 
				nextToken == Token.MULT_OP || nextToken == Token.DIV_OP)
		{
			lex();
			if(!term()) return false;
		}
		
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
