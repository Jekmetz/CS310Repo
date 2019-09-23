

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
	 *  <B> -> a<B>  | null
	 */
	public Lang3Recognizer() 
	{ 
		
	}

	@Override
	public boolean analyzeSentence() {
		// TODO Auto-generated method stub
		return false;
	}
}
