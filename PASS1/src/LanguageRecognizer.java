
public abstract class LanguageRecognizer {
	private String sentence,name;
	private int curIndex;
	
	public LanguageRecognizer()
	{
		sentence = "";
		name = "Language Recognizer";
		curIndex = 0;
	}
		
	//Dad functions
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
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	//Implemented Functions
	public abstract boolean analyzeSentence();
}
