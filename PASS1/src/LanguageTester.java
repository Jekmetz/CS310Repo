import java.util.Scanner;

public class LanguageTester {
	
	//REPL STATE ENUM
	private enum State {
		UNRECOGNIZED_CMD,
		INPUT_FILE,
		INPUT_LANG,
		HELP,
		EXIT
	}
	
	public static void main(String [] args)
	{
		//Initialize variables
		LanguageRecognizer lr;
		String[] languages = {"Language 1", "Language 2", "Language 3"};
		
		//REPL vars
		boolean cont = true;
		Scanner scanner = new Scanner(System.in);
		String cmd;
		State progstate;
		
		/*lr.loadSentence("a=1+2-4-5");
		System.out.println(lr.analyzeSentence());
		*/
		System.out.println(
			"Hello and welcome to our fancy dancy language recognizer! " +
			"Courtesy of Jay Kmetz, Ashly Lovings, and Aron Ludwinski.\n\n" +
			"Type 'help' for a list of commands or 'exit' to quit!"
		);
		while(cont)
		{
			System.out.print("\n> ");
			cmd = scanner.nextLine();
			
			/*SET STATE*/
			switch(cmd.toLowerCase())
			{
			//HELP
			case "h":
			case "help": 	   progstate = State.HELP; 				break;
			
			//EXIT
			case "e":
			case "exit": 	   progstate = State.EXIT; 				break;
			
			//INPUT FILE
			case "if":
			case "input file": progstate = State.INPUT_FILE;		break;
			
			//INPUT LANGUAGE
			case "il":
			case "input lang": progstate = State.INPUT_LANG;		break;
			
			//BAD COMMAND
			default:	       progstate = State.UNRECOGNIZED_CMD; 	break;
			}
			/**********/
			
			/*EVALUATE AND PRINT*/
			switch(progstate)
			{
			case INPUT_FILE:
				System.out.println("INPUT_FILE Not implemented yet!");
				break;
				
			case INPUT_LANG:
				System.out.println("INPUT_LANG Not implemented yet!");
				break;
			
			case EXIT:
				cont = false;
				break;
				
			case HELP:
				printHelp();
				break;
				
			case UNRECOGNIZED_CMD:
				System.out.println("The command '" + cmd + "' was unrecognized!");
				break;
				
			default:
				System.out.println("This should never get here, check your facts! (Mainly the state of the machine after that last command you typed)");
				break;
			}
			/*******************/
		}
		
		System.out.println("Thank you for using this lovely lanaguage recognizer and have a splendiferous day!");
		
		scanner.close();
	}
	
	public static void printHelp()
	{
		System.out.println(
				"help       - Display this help message\n" + 
				"exit       - Exit out of the program\n" +
				"input file - Input the name of a file and the language to check it against\n" +
				"input lang - Input the language and then your own string to parse"
				);
	}
}
