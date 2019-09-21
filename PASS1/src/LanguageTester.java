public class LanguageTester {
	public static void main(String [] args)
	{
		LanguageRecognizer lr = new Lang2Recognizer();
		
		lr.loadSentence("a=1+2-4-5");
		System.out.println(lr.analyzeSentence());

		System.out.println("Hello friends, this is the entry point for our language recognizers!");
		
		/*TODO: REPL TIME BABY*/

	}
}
