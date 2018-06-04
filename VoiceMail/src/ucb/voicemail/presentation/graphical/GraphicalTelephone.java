package ucb.voicemail.presentation.graphical;

import java.util.HashMap;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.Telephone;
import ucb.voicemail.presentation.graphical.view.MainGraphicalView;

public class GraphicalTelephone implements Telephone {
	
	public GraphicalTelephone(MainGraphicalView view) {
		this.view = view;
		view.setVisible(true);
		presentersRoutes = new HashMap<>();
	}
	
	public void run(Connection c) {
	    view.setConnection(c);
	    view.setVisible(true);
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
	
	private MainGraphicalView view;
	private HashMap<String, Object> presentersRoutes;
}
