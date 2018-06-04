package ucb.voicemail.presentation.console.view;

import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;

public class ConsoleView {
    
    public void display(ConsoleViewModel model) {
        System.out.println(model.getText());
    }
    
}
