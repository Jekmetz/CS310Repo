public class Lang3Recognizer extends LanguageRecognizer{
	
	/*  GRAMMAR 3 : 
	 *	<S> -> <A>a<B>b
	 *	<A> -> <A>b | b
	 *	<B> -> a<B> | a
	 *
	 *  FIXED IMMEDIATE LEFT RECURSION:
	 *  <S> -> <A>a<B>b
	 *  <A> -> b<A1>
	 *  <A1>-> b<A1> | null
	 *  <B> -> a<B>  | a
	 */
	public Lang3Recognizer() 
	{ 
		super();
		setName("Language 3");
	}

	@Override
	public boolean analyzeSentence() {
		return S();
	}
	
	private boolean S()
	{
		try
		{
			if(!A())
				return false;
			if(getCurrentToken() != 'a')
				return false;
			getNextToken();
			if(!B())
				return false;
			if(getCurrentToken() != 'b')
				return false;
			getNextToken();
			if (isEndOfSentence())
				return true;
			return false;
		}
		catch (EndOfSentenceException ex)
		{
			return false;
		}
	}
	
	private boolean A() throws EndOfSentenceException
	{
		if (getCurrentToken() == 'b')
		{
			getNextToken();
			if (A1())
				return true;
			return false;
		}
		return true;
	}
	
	private boolean A1() throws EndOfSentenceException
	{
		if (getCurrentToken() == 'b')
		{
			getNextToken();
			if (!A1())
				return false;
		}
		return true;
	}
	
	private boolean B() throws EndOfSentenceException
	{
		if (getCurrentToken() == 'a')
		{
			getNextToken();
			if (!B())
				return true;
			return true;
		}
		return false;
	}
}