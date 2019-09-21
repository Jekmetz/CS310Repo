
public abstract class LanguageRecognizer {
	String sentence;
	int curIndex;
	
	public LanguageRecognizer()
	{
		sentence = "";
		curIndex = 0;
	}
	
	public abstract boolean analyzeSentence();
	
	protected char getCurrentToken() throws EndOfSentenceException
	{
		if(curIndex < sentence.length())
			return sentence.charAt(curIndex);
		else throw new EndOfSentenceException();
	}
	
	protected void getNextToken() { curIndex++; }
	
	protected boolean isEndOfSentence() { return curIndex == sentence.length(); }

	public void loadSentence(String string) {
		sentence = string;
		curIndex = 0;
	}

}
