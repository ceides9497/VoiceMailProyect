package ucb.voicemail.domain;

public interface Telephone {
	void speak(String output);
	void addRoute(String route, Object presenter);
	Object routePresenter(String route);
}
