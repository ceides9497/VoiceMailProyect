package ucb.voicemail.presentation.presenter;

import java.util.ArrayList;

import ucb.voicemail.domain.Telephone;
import ucb.voicemail.presentation.connection.state.*;
import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;
import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;
import ucb.voicemail.domain.dto.response.DeleteCurrentMessageResponse;
import ucb.voicemail.domain.dto.response.GetLastMessageResponse;
import ucb.voicemail.domain.dto.response.GetMailboxGreetingResponse;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;
import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;
import ucb.voicemail.domain.dto.response.SendMessageResponse;
import ucb.voicemail.presentation.Connection;
import ucb.voicemail.domain.boundary.output.*;

public class ConnectionPrensenter implements BasicPresenter, ChangeGreetingPresenter, ChangePasscodePresenter,
        DeleteCurrentMessagePresenter, GetLastMessagePresenter, GetMailboxGreetingPresenter,
        LoginMailboxPresenter, SaveCurrentMessagePresenter, SendMessagePresenter {

    private Connection connection;
    private ArrayList<Telephone> telephoneList;

    public ConnectionPrensenter(Connection connection) {
        telephoneList = new ArrayList<Telephone>();
        this.connection = connection;
    }

    public void addTelephone(Telephone newTelephone) {
        telephoneList.add(newTelephone);
    }

    // ------------------------------------------------ SendMessageInteractorOutput

    @Override
    public void displayConfirmSendMessage(SendMessageResponse response) {
        connection.setConnectionState(new ConnectedState());
        for (Telephone telephone : telephoneList) {
            try {
                SendMessagePresenter output = (SendMessagePresenter) telephone
                        .routePresenter("SendMessage");
                output.displayConfirmSendMessage(response);
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------
    // SaveCurrentMessageInteractorOutput

    @Override
    public void displayConfirmSaveCurrentMessage(SaveCurrentMessageResponse response) {
        for (Telephone telephone : telephoneList) {
            try {
                SaveCurrentMessagePresenter output = (SaveCurrentMessagePresenter) telephone
                        .routePresenter("SaveCurrentMessage");
                output.displayConfirmSaveCurrentMessage(response);
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------ LoginMailboxInteractorOutput

    @Override
    public void displayMailboxMenu(LoginMailboxResponse response) {
        connection.setConnectionState(new MailboxMenuState());
        for (Telephone telephone : telephoneList) {
            try {
                LoginMailboxPresenter output = (LoginMailboxPresenter) telephone
                        .routePresenter("LoginMailbox");
                output.displayMailboxMenu(response);
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------ LoginMailboxInteractorOutput

    @Override
    public void displayLoginFailed() {
        for (Telephone telephone : telephoneList) {
            try {
                LoginMailboxPresenter output = (LoginMailboxPresenter) telephone
                        .routePresenter("LoginMailbox");
                output.displayLoginFailed();
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------
    // GetMailboxGreetingInteractorOutput

    @Override
    public void displayMailboxGreeting(GetMailboxGreetingResponse response) {
        connection.setConnectionState(new RecordingState());
        for (Telephone telephone : telephoneList) {
            try {
                GetMailboxGreetingPresenter output = (GetMailboxGreetingPresenter) telephone
                        .routePresenter("GetMailboxGreeting");
                output.displayMailboxGreeting(response);
            } catch (Exception e) {}
        }
    }

    // ------------------------------------------------
    // GetMailboxGreetingInteractorOutput

    @Override
    public void displayGreetingError() {
        for (Telephone telephone : telephoneList) {
            try {
                GetMailboxGreetingPresenter output = (GetMailboxGreetingPresenter) telephone
                        .routePresenter("GetMailboxGreeting");
                output.displayGreetingError();
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------
    // GetLastMessageInteractorOutput

    @Override
    public void presentMessage(GetLastMessageResponse response) {
        for (Telephone telephone : telephoneList) {
            try {
                GetLastMessagePresenter output = (GetLastMessagePresenter) telephone
                        .routePresenter("GetLastMessage");
                if (output != null) {
                    output.presentMessage(response);
                }
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------
    // GetLastMessageInteractorOutput

    @Override
    public void presentNotFoundMessage() {
        for (Telephone telephone : telephoneList) {
            try {
                GetLastMessagePresenter output = (GetLastMessagePresenter) telephone
                        .routePresenter("GetLastMessage");
                if (output != null) {
                    output.presentNotFoundMessage();
                }
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------
    // DeleteCurrentMessageInteractorOutput

    @Override
    public void displayConfirmDeleteCurrentMessage(DeleteCurrentMessageResponse response) {
        for (Telephone telephone : telephoneList) {
            try {
                DeleteCurrentMessagePresenter output = (DeleteCurrentMessagePresenter) telephone
                        .routePresenter("DeleteCurrentMessage");
                if (output != null) {
                    output.displayConfirmDeleteCurrentMessage(response);
                }
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------
    // ChangePasscodeInteractorOutput

    @Override
    public void displayConfirmChangePasscode(ChangePasscodeResponse response) {
        connection.setConnectionState(new MailboxMenuState());
        for (Telephone telephone : telephoneList) {
            try {
                ChangePasscodePresenter output = (ChangePasscodePresenter) telephone
                        .routePresenter("ChangePasscode");
                if (output != null) {
                    output.displayConfirmChangePasscode(response);
                }
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------
    // ChangeGreetingInteractorOutput

    @Override
    public void displayConfirmChangeGreeting(ChangeGreetingResponse response) {
        connection.setConnectionState(new MailboxMenuState());
        for (Telephone telephone : telephoneList) {
            try {
                ChangeGreetingPresenter output = (ChangeGreetingPresenter) telephone
                        .routePresenter("ChangeGreeting");
                if (output != null) {
                    output.displayConfirmChangeGreeting(response);
                }
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------ PRESENTER

    @Override
    public void displayPasscodeForm() {
        connection.setConnectionState(new ChangePasscodeState());
        for (Telephone telephone : telephoneList) {
            try {
                BasicPresenter output = (BasicPresenter) telephone.routePresenter("BasicPresenter");
                if (output != null) {
                    output.displayPasscodeForm();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void displayGreetingForm() {
        connection.setConnectionState(new ChangeGreetingState());
        for (Telephone telephone : telephoneList) {
            try {
                BasicPresenter output = (BasicPresenter) telephone.routePresenter("BasicPresenter");
                if (output != null) {
                    output.displayGreetingForm();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void displayInitialPrompt() {
        connection.setConnectionState(new ConnectedState());
        for (Telephone telephone : telephoneList) {
            try {
                BasicPresenter output = (BasicPresenter) telephone.routePresenter("BasicPresenter");
                if (output != null) {
                    output.displayInitialPrompt();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void displayMailboxMenu() {
        connection.setConnectionState(new MailboxMenuState());
        for (Telephone telephone : telephoneList) {
            try {
                BasicPresenter output = (BasicPresenter) telephone.routePresenter("BasicPresenter");
                if (output != null) {
                    output.displayMailboxMenu();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void displayMessageMenu() {
        connection.setConnectionState(new MessageMenuState());
        for (Telephone telephone : telephoneList) {
            try {
                BasicPresenter output = (BasicPresenter) telephone.routePresenter("BasicPresenter");
                if (output != null) {
                    output.displayMessageMenu();
                }
            } catch (Exception e) {
            }
        }
    }

}
