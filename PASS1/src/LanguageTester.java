import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LanguageTester {

	// REPL STATE ENUM
	private enum State {
		UNRECOGNIZED_CMD, INPUT_FILE, INPUT_LANG, HELP, EXIT
	}

	public static void main(String[] args) {
		// Initialize variables
		/*
		 * TODO: UNCOMMENT LANG1 AND LANG2 RECOGNIZERS WHEN IMPLEMENTED
		 */
		LanguageRecognizer[] recognizers = {
				// new Lang1Recognizer(),
				new Lang2Recognizer(),
				new Lang3Recognizer(),
		};

		// REPL vars
		boolean cont = true;
		Scanner scanner = new Scanner(System.in);
		String cmd;
		State progstate;

		// Functional variables
		File langFile;
		int languageIndex;

		System.out.println("Hello and welcome to our fancy dancy language recognizer! "
				+ "Courtesy of Jay Kmetz, Ashly Lovings, and Aron Ludwinski.\n\n"
				+ "Type 'help' for a list of commands or 'exit' to quit!");

		while (cont) // While we should continue looping...
		{
			System.out.print("\n>> "); // Prompt user for input...
			cmd = scanner.nextLine(); // Grab input

			/* SET STATE */
			switch (cmd.toLowerCase()) {
			// HELP
			case "h":
			case "help":
				progstate = State.HELP;
				break;

			// EXIT
			case "e":
			case "q":
			case "exit":
			case "quit":
				progstate = State.EXIT;
				break;

			// INPUT FILE
			case "if":
			case "input file":
				progstate = State.INPUT_FILE;
				break;

			// INPUT LANGUAGE
			case "il":
			case "input lang":
				progstate = State.INPUT_LANG;
				break;

			// BAD COMMAND
			default:
				progstate = State.UNRECOGNIZED_CMD;
				break;
			}
			/**********/

			/* EVALUATE AND PRINT */
			switch (progstate) {
			case INPUT_FILE:
				langFile = getFileFromUser(scanner); // Get valid file to scan
				languageIndex = getLanguageFromUser(scanner, recognizers); // Get language to use
				processLanguageFromFile(langFile, recognizers[languageIndex]); // Process and print out to console
				scanner.nextLine(); // Clear buffer
				break;

			case INPUT_LANG:
				languageIndex = getLanguageFromUser(scanner, recognizers); // Get language to use
				scanner.nextLine();// Clear buffer
				if (languageIndex != -1) // If langauge Index is valid...
					processLanguageFromUser(scanner, recognizers[languageIndex]); // Process and print out to console
				break;

			case EXIT:
				cont = false;
				break;

			case HELP:
				printHelp();
				break;

			case UNRECOGNIZED_CMD:
				System.out.println("The command '" + cmd + "' was unrecognized! Type 'help' for a list of commands.");
				break;

			default:
				System.out.println(
						"This should never get here, check your facts! (Mainly the state of the machine after that last command you typed)");
				break;
			}
			/*******************/
		}

		System.out.println("Thank you for using this lovely lanaguage recognizer and have a splendiferous day!");

		scanner.close();
	}

	// REPL FUNCTIONS
	public static File getFileFromUser(Scanner inStream) {
		File outputFile = null;

		// REPL
		boolean cont = true;
		String fileName = null;

		while (cont) // While we should continue...
		{
			System.out.print("Please input the name of the file that you would like to import\ngetfile>> ");

			fileName = inStream.nextLine();
			outputFile = new File(fileName);

			if (outputFile.canRead()) // if file exists and can be read...
				cont = false;
			else // if file cannot be read...
				System.out.println("The file '" + fileName
						+ "' cannot be read or found! Make sure it exists with the right permissions!");
		}

		return outputFile;
	}

	public static int getLanguageFromUser(Scanner inStream, LanguageRecognizer[] arr) {
		int langIndex = -1;

		// REPL
		boolean cont = true;

		if (arr.length == 0) // If we don't have any languages loaded for them :(
		{
			System.out.println("Houston, we have a problem. There are no languages built in!");
			cont = false;
		}

		while (cont) {
			for (int i = 0; i < arr.length; i++)
				System.out.print("[" + (i + 1) + "] " + arr[i].getName() + "\n\n"); // [1] Language 1\n[2] Language 2\n...

			System.out.print("Please input the number of the language you would like.\ngetnumber>> ");

			langIndex = inStream.nextInt() - 1;

			if (langIndex >= 0 && langIndex < arr.length) // validate input
				cont = false;
			else
				System.out.println("The index '" + (langIndex + 1) + "' is not valid. Make sure it exists in the list!!\n");
		}

		return langIndex;
	}

	/**
	 * preconditions: inputFile is valid
	 * 
	 * @param inputFile: file to read sentences from
	 * @param recognizer: recognizer to try and recognize them
	 */
	public static void processLanguageFromFile(File inputFile, LanguageRecognizer recognizer) {
		String sent = null;

		try {
			FileReader fr = new FileReader(inputFile);
			BufferedReader br = new BufferedReader(fr);

			while ((sent = br.readLine()) != null) // While has next... Also makes sent the new line
			{
				recognizer.loadSentence(sent);
				System.out.println(String.format("Sentence: '%s'; Exists In Language?: %s", sent,
						recognizer.analyzeSentence() ? "Yes" : "No"));
			}

			br.close();
		} catch (IOException ex) {
			System.out.println("There was an error reading from the file!");
		}

	}

	/**
	 * Processes language from user input
	 * @param instream: Stream to prompt user with
	 * @param recognizer: recognizer to recognize language with
	 */
	public static void processLanguageFromUser(Scanner instream, LanguageRecognizer recognizer) {
		// REPL
		String cmd;
		boolean cont = true;

		while (cont) {
			System.out.print("\nType in a sentence you would like tested or type 'back' to quit.\n" + recognizer.getName()
					+ ">> ");
			cmd = instream.nextLine();

			if (cmd.toLowerCase().equals("back"))
				cont = false;
			else // Process sentence
			{
				recognizer.loadSentence(cmd);
				System.out.println(String.format("Sentence: '%s'; Exists In Language?: %s", cmd,
						recognizer.analyzeSentence() ? "Yes" : "No"));
			}
		}
	}

	/**
	 * Prints help for this specific application
	 */
	public static void printHelp() {
		System.out.println("help       - Display this help message\n" + "exit       - Exit out of the program\n"
				+ "input file - Input the name of a file and the language to check it against\n"
				+ "input lang - Input the language and then your own string to parse");
	}
}
