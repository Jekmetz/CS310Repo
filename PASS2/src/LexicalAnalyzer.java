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
	//IO
	private boolean fileOpen;
	private BufferedReader in;
	
	public LexicalAnalyzer()
	{ 
		charClass = null;
		nextChar = 0;
		fileOpen = false;
		in = null;
	}
	
	public void setFile(File file) throws IOException
	{
		if(!file.canRead()) throw new IOException();
		InputStream inStream = new FileInputStream(file);
		Reader reader= new InputStreamReader(inStream, Charset.defaultCharset());
		in = new BufferedReader(reader);
		fileOpen = true;
		getChar();	//Load the first character
	}
	
	public boolean isFileOpen() { return fileOpen; }
	
	public Token lex() {
		Token nextToken;
		String lexeme = "";
		getNonBlank();
		switch(charClass) {
		case DIGIT:
			lexeme += nextChar;
			getChar();
			while(charClass == CharClass.DIGIT)
			{
				lexeme += nextChar;
				getChar();
			}
			nextToken = Token.INT_LIT;
			break;
			
		case LETTER:
			lexeme += nextChar;
			getChar();
			while(charClass == CharClass.LETTER || charClass == CharClass.DIGIT)
			{
				lexeme += nextChar;
				getChar();
			}
			//Now we know that it is an identifier... test to see what kind
			nextToken = lookupKeywords(lexeme);
			break;
		
		case UNKNOWN:
			lexeme += nextChar;
			getChar();
			
			//Yes, I know that it can only be one or two characters
			//but what if at some point we would like to add a three character op? (EX: js ===)
			while(charClass != CharClass.SPACE && charClass != CharClass.EOI)
			{
				lexeme += nextChar;
				getChar();
			}
			
			nextToken = lookupOps(lexeme);
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
				if(Character.isAlphabetic(nextChar))
					charClass = CharClass.LETTER;
				else if (Character.isDigit(nextChar))
					charClass = CharClass.DIGIT;
				else if (Character.isWhitespace(nextChar))
					charClass = CharClass.SPACE;
				else charClass = CharClass.UNKNOWN;
				
				//System.out.println("char: " + nextChar + "-" + (int)nextChar); //use to understand character stream
			} else
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
		case "(":	t = Token.R_PAREN;		break;
		case ")":	t = Token.L_PAREN;		break;
		
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
