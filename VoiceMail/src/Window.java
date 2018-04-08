
public class Window implements UserInterface {
	
	public Window(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		mainMenu.show();
	}
	
	@Override
	public void updateInterface(String output) {
		mainMenu.changeMainLabel(output);
	}
	
	public void run(Connection c) {
		mainMenu.setConnection(c);
		mainMenu.show();
	}
	
	private MainMenu mainMenu;
	private Connection connection;
}
