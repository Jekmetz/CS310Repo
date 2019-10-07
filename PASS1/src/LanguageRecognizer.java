
public abstract class LanguageRecognizer {
	private String sentence, name;
	private int curIndex;

	// ctor
	public LanguageRecognizer() {
		sentence = "";
		name = "Language Recognizer";
		curIndex = 0;
	}

	// Dad functions
	/**
	 * @pre-conditions: sentence must be initialized;
	 * @return: The current character the recognizer is on
	 * @throws EndOfSentenceException
	 */
	protected char getCurrentToken() throws EndOfSentenceException {
		if (curIndex < sentence.length())
			return sentence.charAt(curIndex);
		else
			throw new EndOfSentenceException();
	}

	/**
	 * Increments the pointer in the sentence by one
	 */
	protected void getNextToken() {
		curIndex++;
	}

	/**
	 * @return: true if the current character is past the last one in the sentence
	 */
	protected boolean isEndOfSentence() {
		return curIndex >= sentence.length();
	}

	/**
	 * Loads the sentence to the recognizer
	 * 
	 * @param string: sentence to load to the recognizer
	 */
	public void loadSentence(String string) {
		sentence = string.trim();
		curIndex = 0;
	}

	/**
	 * @return Name of the language
	 */
	protected String getName() {
		return name;
	}

	/**
	 * @param name Sets the name of the language
	 */
	protected void setName(String name) {
		this.name = name;
	}

	// Implemented Functions
	/**
	 * @return true if current loaded sentence is in language and false otherwise
	 */
	public abstract boolean analyzeSentence();
}
