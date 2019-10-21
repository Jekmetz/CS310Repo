/* Operation Pass the Class:
 * Jay Kmetz, Ashly Lovings, Aron Ludwinski
 * Author(s): Jay Kmetz
 * Programming Assignment 2: LexicalAnalyzer
 * 
 * Description: Splits up input into lexemes and Token Types
 * 
 * Due: 10/25/2019
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class LexicalAnalyzer {
	//Init Global Vars
	private CharClass charClass;
	private char nextChar;
	private String lexeme;
	//IO
	private boolean fileOpen;
	private BufferedReader in;
	
	public LexicalAnalyzer()
	{ 
		charClass = null;
		nextChar = 0;
		lexeme = "";
		fileOpen = false;
		in = null;
	}
	
	public void setFile(File file) throws IOException
	{
		//If the file is able to be read...
		if(!file.canRead()) throw new IOException();
		
		//Initialize stream with the default keyset and file
		InputStream inStream = new FileInputStream(file);
		Reader reader= new InputStreamReader(inStream, Charset.defaultCharset());
		in = new BufferedReader(reader);
		fileOpen = true;
		getChar();	//Load the first character
	}
	
	public boolean isFileOpen() { return fileOpen; }
	public String getCurrentLexeme() { return lexeme; }
	
	public Token lex() {
		//Initialze function vars
		Token nextToken;
		lexeme = "";
		
		//Advance the stream until the first non-space
		getNonBlank();
		
		switch(charClass) {
		case DIGIT:
			lexeme += nextChar;					//Add the current character to the lexeme
			getChar();							//get the next char class
			while(charClass == CharClass.DIGIT)	//while it is a digit...
			{
				lexeme += nextChar;				//add the character to the lexeme
				getChar();						//get the next charater class
			}
			nextToken = Token.INT_LIT;			//Set the return value to integer literal
			break;
			
		case LETTER:
			lexeme += nextChar;			//Add the current character to the lexeme
			getChar();					//get the next character class
			while(charClass == CharClass.LETTER || charClass == CharClass.DIGIT)	//while it is a letter or a digit...
			{
				lexeme += nextChar;		//Add that to the current lexeme,
				getChar();				//add that to the char
			}
			//Now we know that it is an identifier... test to see what kind
			nextToken = lookupKeywords(lexeme);	//Get the correct keyword
			break;
		
		case UNKNOWN:
			lexeme += nextChar;	//Add the current character to the lexeme
			getChar();			//get the next char class
			
			//Yes, I know that it can only be one or two characters
			//but what if at some point we would like to add a three character op? (EX: js ===)
			//Regardless, while it is not a space or the end of the input...
			while(charClass != CharClass.SPACE && charClass != CharClass.EOI)
			{
				lexeme += nextChar;		//Add the current character to the lexeme
				getChar();				//get the next character class.
			}
			
			nextToken = lookupOps(lexeme);	//Get the correct operator
			break;
		
		case EOI:
			nextToken = Token.EOI;
			break;
			
		default:
			nextToken = Token.ERROR;
			break;
		
		}
		
		System.out.println("Next Token is: " + nextToken.name() + ". Next  lexeme is: " + lexeme);
		
		return nextToken;
	}
	
	private void getChar() {
		try{	
			if((nextChar = (char)in.read()) != (char)-1)
			{
				if(Character.isAlphabetic(nextChar))	//If the character is in the alphabet...
					charClass = CharClass.LETTER;		//character class is letter.
				else if (Character.isDigit(nextChar))	//If the character is a digit...
					charClass = CharClass.DIGIT;		//character class is a digit
				else if (Character.isWhitespace(nextChar))	//If the character is whitespace...
					charClass = CharClass.SPACE;			//character class is space
				else charClass = CharClass.UNKNOWN;		//otherwise, it is unknown
				
				//System.out.println("char: " + nextChar + "-" + (int)nextChar); //use to understand character stream
			} else	//If it is the end of the file
			{
				closeFile();
				charClass = CharClass.EOI;
			}
		} catch (IOException e) 
		{ 
			closeFile();
			charClass = CharClass.EOI;
		}
	}
	
	private void getNonBlank()
	{
		while(Character.isWhitespace(nextChar))
			getChar();
	}
	
	//HELPERS
	private Token lookupKeywords(String str)
	{
		Token t;
		
		switch(str)	//is case sensitive
		{
		case "procedure": 	t = Token.PROCEDURE_KEY; 	break;
		case "begin":		t = Token.BEGIN_KEY; 		break;
		case "end":			t = Token.END_KEY;			break;
		case "if":			t = Token.IF_KEY;			break;
		case "then":		t = Token.THEN_KEY;			break;
		case "else":		t = Token.ELSE_KEY;			break;
		case "endif":		t = Token.ENDIF_KEY;		break;
		
		default:			t = Token.IDENT;			break;
		}
		
		return t;
	}
	
	private Token lookupOps(String op)
	{
		Token t;
		switch(op)
		{
		case ";":	t = Token.SCOLON_OP; 	break;
		case ":=":	t = Token.ASSIGN_OP;	break;
		case "+":	t = Token.ADD_OP;		break;
		case "-":	t = Token.SUB_OP;		break;
		case "*":	t = Token.MULT_OP;		break;
		case "/":	t = Token.DIV_OP;		break;
		case "=":	t = Token.EQ_OP;		break;
		case "!=": 	t = Token.NEQ_OP;		break;
		case "(":	t = Token.L_PAREN;		break;
		case ")":	t = Token.R_PAREN;		break;
		
		default:	t = Token.ERROR;
		}
		
		return t;
	}
	
	private void closeFile()
	{
		try {
			in.close();
		} catch (IOException e) 
		{ }
		fileOpen = false;
	}
}
