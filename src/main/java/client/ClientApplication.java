package client;


import java.util.Scanner;

import utils.Protocol;

public class ClientApplication {

	private static Scanner inputReader;
	private static OperationClient client;
	
	private static boolean bExitProgram = false;
	
	public static void main(String[] args) {
		System.out.println("Client started..");
		System.out.println("Trying to connect to: "+Protocol.URL+":"+Protocol.PORT);

		client = new OperationClient();
		client.connect();
		
		if (args.length > 1){
			readCommandLineArguments(args);
		}else{
			while (!bExitProgram){
				startUserInput();
			}
		}
	}
	
	/** 
	 * COMMAND LINE ARGUMENTS METHODS
	 */
	
	private static void readCommandLineArguments(String[] args){
		switch (args[0]){
		case "+": readAdditionCommandLineArguments(args);  break;
		case "-": readSubtractionCommandLineArguments(args); break;
		case "*": readMultiplicationCommandLineArguments(args); break;
		case "lucas": readLucasNumbersCommandLineArguments(args);break;
		default: printHelpText(); break;
		}
	}
	
	private static void readAdditionCommandLineArguments(String[] args){
		if (args.length < 3){
			System.out.println("Not enough arguemnents found!");
			printHelpText();
		}
		Integer arg0 = Integer.parseInt(args[1]);
		Integer arg1 = Integer.parseInt(args[2]);
		client.clientRequestAddition(client.getConnectedSocket(), arg0, arg1);
	}
	
	private static void readSubtractionCommandLineArguments(String[] args){
		if (args.length < 3){
			System.out.println("Not enough arguemnents found!");
			printHelpText();
		}
		Integer arg0 = Integer.parseInt(args[1]);
		Integer arg1 = Integer.parseInt(args[2]);
		client.clientRequestSubtraction(client.getConnectedSocket(), arg0, arg1);
	}
	
	private static void readMultiplicationCommandLineArguments(String[] args){
		if (args.length < 3){
			System.out.println("Not enough arguemnents found!");
			printHelpText();
		}
		Integer arg0 = Integer.parseInt(args[1]);
		Integer arg1 = Integer.parseInt(args[2]);
		client.clientRequestMultiplication(client.getConnectedSocket(), arg0, arg1);
	}
	
	private static void readLucasNumbersCommandLineArguments(String[] args){
		if (args.length < 2){
			System.out.println("Not enough arguemnents found!");
			printHelpText();
		}
		Integer arg0 = Integer.parseInt(args[1]);
		client.clientRequestLucasNumbers(client.getConnectedSocket(), arg0);
	}
	
	private static void printHelpText(){
		System.out.println("HELP TEXT");
		System.out.println("Example ./client.jar * 1 2");
		System.out.println("Example ./client.jar + 2 5");
		System.out.println("Example ./client.jar lucas 5");
		System.out.println("Example ./client.jar - 5 3");
	}
	
	
	
	
	
	/**
	 * USER INPUT METHODS
	 */
	
	private static void startUserInput(){
		inputReader = new Scanner(System.in);  // Reading from System.in
		startOperationSelect();

	}
	
	private static void startOperationSelect(){
		System.out.println("Select an operation:");
		System.out.println("0. Addition");
		System.out.println("1. Subtraction");
		System.out.println("2. Multiplication");
		System.out.println("3. Lucas Numbers");
		System.out.println("4. Exit");
		
		System.out.println("Enter a number: ");

		int operationSelection = inputReader.nextInt();
		
		
		switch(operationSelection){
		case 0: startAdditionUserInput(); break;
		case 1: startSubtractionUserInput();break;
		case 2: startMultiplicationUserInput();break;
		case 3: startLucasNumbersUserInput();break;
		case 4: endProgram(); break;
		default: System.out.println("Unknown input. Please try again."); break;
		}
	}
	
	private static void startAdditionUserInput(){
		System.out.println("You selected the Addition operation.");
		System.out.println("Please enter two (integer) arguments:");
		System.out.println("First argument:");
		Integer arg0 = inputReader.nextInt();
		System.out.println("Second argument:");
		Integer arg1 = inputReader.nextInt();
		client.clientRequestAddition(client.getConnectedSocket(), arg0, arg1);

	}
	
	private static void  startSubtractionUserInput(){
		System.out.println("You selected the Subtraction operation.");
		System.out.println("Please enter two (integer) arguments:");
		System.out.println("First argument:");
		Integer arg0 = inputReader.nextInt();
		System.out.println("Second argument:");
		Integer arg1 = inputReader.nextInt();
		client.clientRequestSubtraction(client.getConnectedSocket(), arg0, arg1);
		}
	
	private static void startMultiplicationUserInput(){
		System.out.println("You selected the Multiplication operation.");
		System.out.println("Please enter two (integer) arguments:");
		System.out.println("First argument:");
		Integer arg0 = inputReader.nextInt();
		System.out.println("Second argument:");
		Integer arg1 = inputReader.nextInt();
		client.clientRequestMultiplication(client.getConnectedSocket(), arg0, arg1);
	}
	
	private static void startLucasNumbersUserInput(){
		System.out.println("You selected the Lucas Numbers operation.");
		System.out.println("Please enter one (integer) arguments:");
		System.out.println("First argument:");
		Integer arg0 = inputReader.nextInt();
	
		client.clientRequestLucasNumbers(client.getConnectedSocket(), arg0);
	}
	
	private static void endProgram(){
		if (!client.getConnectedSocket().isClosed()){
			client.disconnect();
		}
		bExitProgram = true;
	}

}
