package ucb.voicemail.presentation.console;

import java.util.HashMap;
import java.util.Scanner;

import ucb.voicemail.domain.Telephone;
import ucb.voicemail.presentation.Connection;

public class ConsoleTelephone implements Telephone {

	private Scanner scanner;
	private HashMap<String, Object> presentersRoutes;
	
	private final static String HANGUP = "H";
	private final static String QUIT = "Q";
	private final static String OPTIONS_TO_MARK = "1234567890#";
	
	public ConsoleTelephone() {
		scanner = new Scanner(System.in);
		presentersRoutes = new HashMap<>();
	}
	
	public ConsoleTelephone(Scanner aScanner) {
		scanner = aScanner;
		presentersRoutes = new HashMap<>();
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
				System.exit(0);
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
	
	@Override
	public void addRoute(String route, Object presenter) {
		presentersRoutes.put(route, presenter);
	}
	
	@Override
	public Object routePresenter(String route) {
		if (presentersRoutes.containsKey(route)) {
			return presentersRoutes.get(route);
		}
		return null;
	}
}
