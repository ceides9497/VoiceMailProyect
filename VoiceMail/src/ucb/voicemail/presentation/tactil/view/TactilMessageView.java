package ucb.voicemail.presentation.tactil.view;

import ucb.voicemail.presentation.tactil.viewmodel.MessageViewModel;

public interface TactilMessageView {
    void display(MessageViewModel model);
    void hideWiew();
}
