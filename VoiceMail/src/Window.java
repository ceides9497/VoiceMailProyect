
public class Window implements UserInterface {
	
	public Window(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		mainMenu.show();
	}
	
	@Override
	public void updateInterface(String output) {
		mainMenu.changeMainLabel(output);
	}
	
	private MainMenu mainMenu;
}
