import java.util.Scanner;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What should the input be? Only use characters that are contained in the ASCII Table, please. \nOtherwise they´ll be removed in the result");
		String input = scanner.nextLine();
	
		String originalBinaryString = stringToBinary(input);
		String inputStringWithOnlyAsciiCharacters = binaryToString(originalBinaryString);
		String binaryStringOfOnlyAsciiCharacters = stringToBinary(inputStringWithOnlyAsciiCharacters);
		
		printOutputStrings(originalBinaryString, inputStringWithOnlyAsciiCharacters, binaryStringOfOnlyAsciiCharacters);
		
		System.out.println("Do you want to copy one of the binary String to your clipboard? yes(y) or no(n)?");
		String userInput = scanner.nextLine();
		
		while(!userInput.equals("y") && !userInput.equals("n")) {
			System.out.println("Please type 'y' or 'n'");
			userInput = scanner.nextLine();
		}
		
		if(userInput.equals("y")) {
			copyToClipboard(scanner, originalBinaryString, binaryStringOfOnlyAsciiCharacters);
		}

	}
	
	public static String stringToBinary(String input) {
		byte[] bytes = input.getBytes();		
		String output = "";
		
		for(int i = 0; i < bytes.length; i++) {
			output += Integer.toBinaryString(bytes[i]) + " ";
		}
		return output;
	}
	
	public static String binaryToString(String input) {
		String[] binaryNumbers = input.split(" ");
		String output = "";
		
		boolean containsIllegalCharacter_s = false;
		
		for(int i = 0; i < binaryNumbers.length; i++) {
			try {
				output += (char) Integer.parseInt(binaryNumbers[i], 2);
			} catch(NumberFormatException nfe) {
				containsIllegalCharacter_s = true;			
			}
		}
		
		if(containsIllegalCharacter_s) {
			System.out.println("\nOne or more characters that are not included in the ASCII-Table were used in the input String. \nThey are included in the Original Binary String as an ANSI-representation, but were removed in the result! \n");
		}
		return output;
	}
	
	public static void printOutputStrings(String binaryString, String inputStringWithOnlyAsciiCharacters, String binaryStringOfOnlyAsciiCharacters) {
		System.out.println("\nOriginal Binary String: \t\t\t " + binaryString);
		System.out.println("Input String with non-ascii characters removed:  " + inputStringWithOnlyAsciiCharacters);
		System.out.println("Binary String with non-ascii characters removed: " + binaryStringOfOnlyAsciiCharacters  + "\n");
	}
	
	public static void copyToClipboard(Scanner scanner, String originalBinaryString, String binaryStringOfOnlyAsciiCharacters) {
		String userInputBinaryString = "";
		System.out.println("\nWhich of the binary Strings do you want to copy to your clipboard?");
		System.out.println("Original Binary String (1)");
		System.out.println("Binary String with non-ascii characters removed (2)");
		
		userInputBinaryString = scanner.nextLine();
		
		while(!userInputBinaryString.equals("1") && !userInputBinaryString.equals("2")) {
			System.out.println("Please type '1' or '2'");
			userInputBinaryString = scanner.nextLine();
		}
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		if(userInputBinaryString.equals("1")) {
			clipboard.setContents(new StringSelection(originalBinaryString), null);
			System.out.println("\"Original Binary String\" copied to your Clipboard.");
		} else if(userInputBinaryString.equals("2")){
			clipboard.setContents(new StringSelection(binaryStringOfOnlyAsciiCharacters), null);
			System.out.println("\"Binary String with non-ascii characters removed\" copied to your Clipboard.");
		} else System.out.println("An error occured!");
	}
}
