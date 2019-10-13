
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
