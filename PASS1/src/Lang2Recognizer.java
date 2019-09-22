public class Lang2Recognizer extends LanguageRecognizer {

	/*
	 * GRAMMAR 2: <ASSGN> -> <ID>=<EXPR> <EXPR> -> <DIGIT>+<EXPR> | <DIGIT>-<EXPR> |
	 * <DIGIT> <DIGIT> -> 0 | 1 | 2 | … | 9| <ID> -> a | b
	 */
	public Lang2Recognizer() {
		super();
		setName("Language 2");
	}

	@Override
	public boolean analyzeSentence() {
		return ASSGN();
	}

	// LANGUAGE INDETERMINATES
	private boolean ASSGN() {
		try {
			if (!ID())
				return false; // parse ID
			if (getCurrentToken() != '=')
				return false; // make sure the token is now '='
			getNextToken(); // get next token
			if (!EXPR())
				return false; // parse EXPR

			if (isEndOfSentence())
				return true; // if this is the end of the string... We did it!
			else
				return false; // Otherwise, yell at them
		} catch (EndOfSentenceException ex) {
			return false;
		}
	}

	private boolean ID() throws EndOfSentenceException {
		if (getCurrentToken() == 'a' || getCurrentToken() == 'b') {
			getNextToken();
			return true;
		}

		return false;
	}

	private boolean EXPR() throws EndOfSentenceException {
		if (DIGIT()) // parse DIGIT
		{
			if (!isEndOfSentence() && (getCurrentToken() == '+' || getCurrentToken() == '-')) // if '+' or '-'...
			{
				getNextToken(); // Get next token
				if (EXPR())
					return true; // if EXPR return true, else fall through
			} else
				return true;
		}

		// If none of that, return false;
		return false;
	}

	private boolean DIGIT() throws EndOfSentenceException {
		// return true if it is a digit
		if (getCurrentToken() >= '0' && getCurrentToken() <= '9') {
			getNextToken();
			return true;
		}

		// return false otherwise
		return false;
	}
}
