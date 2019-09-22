import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
		/*TODO:
		 * UNCOMMENT LANG1 AND LANG2 RECOGNIZERS WHEN IMPLEMENTED
		 */
		LanguageRecognizer[] recognizers = 
		{
			//new Lang1Recognizer(),
			new Lang2Recognizer(),
			//new Lang3Recognizer(),
		};
		
		//REPL vars
		boolean cont = true;
		Scanner scanner = new Scanner(System.in);
		String cmd;
		State progstate;
		
		//Functional variables
		File langFile;
		int languageIndex;
		
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
			System.out.print("\n>> ");
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
				langFile = getFileFromUser(scanner);
				languageIndex = getLanguageFromUser(scanner, recognizers);
				processLanguageFromFile(langFile, recognizers[languageIndex]);
				scanner.nextLine();	//Clear buffer
				break;
				
				
			case INPUT_LANG:
				languageIndex = getLanguageFromUser(scanner, recognizers);
				scanner.nextLine();
				processLanguageFromUser(scanner, recognizers[languageIndex]);
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
	
	//REPL FUNCTIONS
	public static File getFileFromUser(Scanner inStream)
	{
		File outputFile = null;
		
		//REPL
		boolean cont = true;
		String fileName = null;
		
		while(cont)
		{
			System.out.print("Please input the name of the file that you would like to import\ngetfile>> ");

			fileName = inStream.nextLine();
			outputFile = new File(fileName);
			
			if(outputFile.canRead())	//validate input
				cont = false;
			else
				System.out.println("The file '" + fileName + "' cannot be read or found! Make sure it exists with the right permissions!");
		}
		
		return outputFile;
	}
	
	public static int getLanguageFromUser(Scanner inStream, LanguageRecognizer[] arr)
	{
		int langIndex = -1;
		
		//REPL
		boolean cont = true;
		
		if(arr.length == 0)
		{
			System.out.println("Houstan, we have a problem. There are no languages built in!");
			cont = false;
		}
		
		while(cont)
		{
			for(int i = 0; i < arr.length; i++)
				System.out.print("[" + i + "] " + arr[i].getName() + "\n");

			System.out.print("Please input the number of the language you would like.\ngetnumber>> ");

			langIndex = inStream.nextInt();
						
			if(langIndex >=0 && langIndex < arr.length) //validate input
				cont = false;
			else
				System.out.println("The index '" + langIndex + "' is not valid. Make sure it exists in the list!!");
		}
		
		return langIndex;
	}
	
	/**
	 * preconditions: inputFile is valid
	 * @param inputFile
	 * @param recognizer
	 */
	public static void processLanguageFromFile(File inputFile, LanguageRecognizer recognizer)
	{
		String sent = null;
		
		try {
			FileReader fr = new FileReader(inputFile);
			BufferedReader br = new BufferedReader(fr);
			
			while((sent = br.readLine()) != null)	//While has next... Also makes sent the new line
			{
				recognizer.loadSentence(sent);
				System.out.println(String.format(
						"Sentence: '%s'; Exists In Language?: %s", 
						sent,
						recognizer.analyzeSentence() ? "Yes" : "No"
						));
			}
			
			br.close();
		} catch (IOException ex)
		{
			System.out.println("There was an error reading from the file!");
		}
				
	}
	
	public static void processLanguageFromUser(Scanner instream, LanguageRecognizer recognizer)
	{
		//REPL
		String cmd;
		boolean cont = true;
		
		while(cont)
		{
			System.out.print("Type in a sentence you would like tested or type 'back' to quit.\n" + recognizer.getName() + ">> ");
			cmd = instream.nextLine();
			
			if(cmd.toLowerCase().equals("back"))
				cont = false;
			else	//Process sentence 
			{
				recognizer.loadSentence(cmd);
				System.out.println(String.format(
						"Sentence: '%s'; Exists In Language?: %s", 
						cmd,
						recognizer.analyzeSentence() ? "Yes" : "No"
						));
			}
		}
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
