package ucb.voicemail.presentation.view;

import ucb.voicemail.presentation.viewmodel.ConsoleViewModel;

public class ConsoleView {
    
    public void display(ConsoleViewModel model) {
        System.out.println(model.getText());
    }
    
}
