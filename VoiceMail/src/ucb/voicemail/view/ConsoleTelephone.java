package ucb.voicemail.view;

import java.util.Scanner;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.Telephone;

public class ConsoleTelephone implements Telephone {

	private Scanner scanner;
	
	private final static String HANGUP = "H";
	private final static String QUIT = "Q";
	private final static String OPTIONS_TO_MARK = "1234567890#";
	
	public ConsoleTelephone() {
		scanner = new Scanner(System.in);
	}
	
	public ConsoleTelephone(Scanner aScanner) {
		scanner = aScanner;
	}

	@Override
	public void speak(String output) {
		System.out.println(output);
	}

	public void run(Connection c) {
		boolean more = true;
		
		while (more) {
			String input = getScannerNextLine();
			
			if (isNull(input)) {
				return;
			}
			if (isHanging(input)) {
				c.hangup();				
			} else if (isFinished(input)) {
				more = false;
			} else if (isDialing(input)) {
				c.dial(input);
			} else {
				c.record(input);
			}
		}
	}

	public String getScannerNextLine() {
		return scanner.nextLine();
	}

	private boolean isNull(String input) {
		return input == null;
	}

	private boolean isDialing(String input) {
		return input.length() == 1 && OPTIONS_TO_MARK.indexOf(input) >= 0;
	}

	private boolean isFinished(String input) {
		return input.equalsIgnoreCase(QUIT);
	}

	private boolean isHanging(String input) {
		return input.equalsIgnoreCase(HANGUP);
	}

}
