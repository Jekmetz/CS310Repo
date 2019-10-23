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
	
	public void printOutError(String errMsg)
	{
		lexAn.closeFile();
		System.out.println(
			String.format(
				"Line: %d, Position: %d\nError: %s", 
			lexAn.getLineNo(),
			lexAn.getPosition(),
			errMsg
			)
		);
	}
	
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
		
		if(nextToken == Token.IDENT || nextToken == Token.IF_KEY)
			if(!stmt_list()) return false;
		
		return true;
	}
	
	private boolean stmt()
	{
		if(nextToken == Token.IDENT)
		{
			if(!assign()) return false;
		}
		else if (nextToken == Token.IF_KEY)
			if(!sa_if()) return false;

		return true;
	}
	
	private boolean sa_if()
	{
		if(nextToken != Token.IF_KEY)	//if
		{
			printOutError("Missing if keyword!");
			return false;
		}
		lex();
		
		if(nextToken != Token.L_PAREN)	// (
		{
			printOutError("Missing Left Parenthesis after if!");
			return false;
		}
		lex();
		
		if(!bool()) return false;		// boolean expression
		
		if(nextToken != Token.R_PAREN)  // )
		{
			printOutError("Missing Right Parenthesis after boolean expression!");
			return false;	
		}
		lex();
		
		if(nextToken != Token.THEN_KEY) // )
		{
			printOutError("Missing Then Keyword for If statement!");
			return false;
		}
		lex();
		
		if(!stmt_list()) return false;					// statement list
		
		if(nextToken == Token.ELSE_KEY)					// else statement
		{
			lex();
			if(!stmt_list()) return false;				// statement list
		}
		
		if(nextToken != Token.ENDIF_KEY)	// endif
		{
			printOutError("Missing End if Keyword!");
			return false;	
		}
		lex();
		
		if(nextToken != Token.SCOLON_OP)
		{
			printOutError("Missing Semicolon after Endif in if block!");
			return false;
		}
		lex();
		
		return true;
	}
	
	private boolean assign()
	{
		if(nextToken != Token.IDENT) { printOutError("Missing var"); return false;}
		lex();
		if(nextToken != Token.ASSIGN_OP) {printOutError("Missing assignment"); return false;}
		lex();
		if(!expr()) {return false;}
		if(nextToken != Token.SCOLON_OP) {printOutError("No Semicolon in assignment statement!"); return false;}
		lex();
		
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
		if(nextToken != Token.IDENT && nextToken != Token.INT_LIT) {printOutError("Missing var"); return false;} //AM I RIGHT????
		lex();	
		
		return true;
	}
	
	private boolean bool()
	{
		if(nextToken != Token.IDENT) {printOutError("Missing var"); return false;}
		lex();
		if(nextToken != Token.EQ_OP && nextToken != Token.NEQ_OP) {printOutError("Missing equals or not equals operation"); return false;}
		lex();
		if(nextToken != Token.INT_LIT && nextToken != Token.IDENT) {return false;}
		lex();
		
		return true;
	}

}
