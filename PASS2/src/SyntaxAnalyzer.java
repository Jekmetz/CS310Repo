/* Operation Pass the Class:
 * Jay Kmetz, Ashly Lovings, Aron Ludwinski
 * Programming Assignment 2: SyntaxAnalyzer
 * Due: 10/25/2019
 */

public class SyntaxAnalyzer {
	//Init vars
	LexicalAnalyzer lexAn;
	
	public SyntaxAnalyzer() {};
	public SyntaxAnalyzer(LexicalAnalyzer lexAn)
	{
		this.lexAn = lexAn;
	}
	
	public void setLexAn(LexicalAnalyzer lexAn) { this.lexAn = lexAn; }

	//TODO: Implement syntax analyzer. Call lexAn.lex() for next token :)
}
