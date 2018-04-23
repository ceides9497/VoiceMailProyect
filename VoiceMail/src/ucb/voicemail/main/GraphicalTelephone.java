package ucb.voicemail.main;

public class GraphicalTelephone implements Telephone {
	
	public GraphicalTelephone(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		mainMenu.show();
	}
	
	@Override
	public void speak(String output) {
		mainMenu.changeMainLabel(output);
	}
	
	public void run(Connection c) {
		mainMenu.setConnection(c);
		mainMenu.show();
	}
	
	private MainMenu mainMenu;
	private Connection connection;
}
