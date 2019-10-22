/* Operation Pass the Class:
 * Jay Kmetz, Ashly Lovings, Aron Ludwinski
 * Author(s): Jay Kmetz
 * Programming Assignment 2: LexTester
 * 
 * Description: Entry point for program
 * 
 * Due: 10/25/2019
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LexTester {
	private enum State
	{
		HELP,
		EXIT,
		INPUT_FILE,
		UNRECOGNIZED_CMD
	}
	
	public static void main(String [] args)
	{
		LexicalAnalyzer lexAn = new LexicalAnalyzer();
		SyntaxAnalyzer synAn = new SyntaxAnalyzer();
		synAn.setLexAn(lexAn);
		
		// REPL vars
		boolean cont = true;
		Scanner scanner = new Scanner(System.in);
		String cmd;
		State progstate;
		
		// Functional vars
		File langFile;
		
		System.out.println("Hello and welcome to our rather lovely Syntax Analyzer! "
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
				processFile(synAn,langFile);
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

		System.out.println("Thank you for using this lovely Syntax Analyzer and have a splendiferous day!");
	}
	
	/**
	 * Prints help for this specific application
	 */
	public static void printHelp() {
		System.out.println(
				  "help       - Display this help message\n" 
		        + "exit       - Exit out of the program\n"
				+ "input file - Input the name of a file and the language to check it against");
	}
	
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
	
	public static void processFile(SyntaxAnalyzer synAn, File file)
	{
		String sent = null;

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			System.out.println("File: " + file.getName());
			System.out.println("Language:");
			
			while ((sent = br.readLine()) != null) // While has next... Also makes sent the new line
				System.out.println(sent);

			br.close();
			
			System.out.println(String.format("File Exists in Language?: %s",
						synAn.fileIsInLanguage(file) ? "Yes" : "No"));
		} catch (IOException ex) {
			System.out.println("There was an error reading from the file!");
		}
	}
}
