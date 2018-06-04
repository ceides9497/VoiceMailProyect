package ucb.voicemail.domain;

public interface Telephone {
	void addRoute(String route, Object presenter);
	Object routePresenter(String route);
}
