
public class Lang1Recognizer extends LanguageRecognizer {


	public Lang1Recognizer() {
		super();
		setName("Language1");

	}// end of constructor

	public boolean S() {
		try {
//			if (getCurrentToken() == ('a')) {
//				getNextToken();
//				if (S()) {
//					if (getCurrentToken() == ('c')) {
//						getNextToken();
//						if (!B()) {
//							return false;
//						}
//					} // checking for c
//					else return false;
//				} // if it fails
//				else return false;
//
//			} else if (A()) {
//				return true;
//			} else if (getCurrentToken() == 'b') {
//				getNextToken();
//				return true;
//			}
//			
//			if (isEndOfSentence()) {
//				return true;
//			} else {
//				return false;
//			}
			if(getCurrentToken() == 'b')
			{
				getNextToken();
				if(isEndOfSentence()) return true;
				if(getCurrentToken() == 'c')
				{
					getNextToken();
					if(!B()) return false;
					if(isEndOfSentence()) return true;
				}
				return false;
			} else if(getCurrentToken() == 'a')
			{
				getNextToken();
				if(S()) 
					if(isEndOfSentence()) return true;
				return false;
			} else if(A())
			{
				if(getCurrentToken() == 'c')
				{
					getNextToken();
					if(isEndOfSentence()) return true;
					if(B())
						if(isEndOfSentence()) return true;
					return false;
				}
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
		}
		if (getCurrentToken() == 'd') {
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


