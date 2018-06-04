package ucb.voicemail.presentation.tactil;

import java.util.HashMap;

import ucb.voicemail.domain.Telephone;
import ucb.voicemail.presentation.tactil.view.DefaultTactilInitialPromptView;

public class TactilTelephone implements Telephone {

    private DefaultTactilInitialPromptView view;
    private HashMap<String, Object> presentersRoutes;
    
    public TactilTelephone(DefaultTactilInitialPromptView view) {
        this.view = view;
        presentersRoutes = new HashMap<>();
    }
    
    public void run() {
        view.setVisible(true);
    }
    
    @Override
    public void speak(String output) {
        System.out.println(output);
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
