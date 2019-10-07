
public class Lang1Recognizer extends LanguageRecognizer {
	
	private boolean firstCase;

	public Lang1Recognizer() {
		super();
		setName("Language1");
		firstCase = false;

	}// end of constructor
	/* <S> -> a<S>c<B> | <A> | b
	 * <A> ->  c<A>  | d
	 * <B> -> d | a<A>
	 */

	public boolean S() {
		try {
			if(getCurrentToken() == 'a')
			{
				getNextToken();
				firstCase = true;
				if(S())
				{
					if(getCurrentToken() == 'c') {
						getNextToken();
						if(B())
							if(isEndOfSentence())
								return true;
					}
				}
				return false;
			} else if(A())
			{
				if(firstCase)
				{
					firstCase = false;
					return true;
				}else if(isEndOfSentence()) return true;
				else return false;
			} else if(getCurrentToken() == 'b')
			{
				getNextToken();
				if(firstCase) 	//If we are returning to the first case...
				{
					firstCase = false;	//Set the first case to false for future iterations...
					return true;
				} else if(isEndOfSentence()) return true;	//If we are not from the first case and it is the end of sentence...
				else return false;	//If we are not in the first case and it is not the end of the sentence...
			}
			return false;

		} catch (EndOfSentenceException e) {
			// TODO Auto-generated catch block
			return false;
		} // end of catch

	}// end of method

	public boolean A() throws EndOfSentenceException {

		if (getCurrentToken() == 'c') {
			getNextToken();
			if (!A()) {
				return false;
			} // to check if there is a C and to see if the next token is nonTerminal
			return true;
		}else if (getCurrentToken() == 'd') {
			getNextToken();
			return true;
			
		} // to check if there is a D there

		return false;
	}// end of nonterminal A

	public boolean B() throws EndOfSentenceException {

		if (getCurrentToken() == 'd') {
			getNextToken();
			return true;
		}

		if (getCurrentToken() == 'a') {
			getNextToken();
			if (!A()) {
				return false;
			}else {
				return true;
			}
		}

		return false;
	}// end of nonterminal B

	@Override
	public boolean analyzeSentence() {
		return S();
	}
}


