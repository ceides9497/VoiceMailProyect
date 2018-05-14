package ucb.voicemail.presenters;

import java.util.ArrayList;
import java.util.List;

public class OptionsList {
	private List<String> list;
	
	public OptionsList() {
		list = new ArrayList<>();
	}
	
	public void add(String option) {
		list.add(option);
	}
	
	public int size() {
		return list.size();
	}
	
	public String get(int index) {
		return list.get(index);
	}
}
