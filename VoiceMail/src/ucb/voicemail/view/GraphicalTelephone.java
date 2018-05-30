package ucb.voicemail.view;

import java.util.HashMap;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.Telephone;

public class GraphicalTelephone implements Telephone {
	
	public GraphicalTelephone(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		mainMenu.show();
		presentersRoutes = new HashMap<>();
	}
	
	@Override
	public void speak(String output) {
		mainMenu.changeMainLabel(output);
	}
	
	public void run(Connection c) {
		mainMenu.setConnection(c);
		mainMenu.show();
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
	
	private MainMenu mainMenu;
	private Connection connection;
	private HashMap<String, Object> presentersRoutes;
}
