package ucb.voicemail.presentation.presenter;

import java.util.ArrayList;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.Telephone;

import ucb.voicemail.domain.boundary.output.ChangeGreetingInteractorOutput;
import ucb.voicemail.domain.boundary.output.ChangePasscodeInteractorOutput;
import ucb.voicemail.domain.boundary.output.DeleteCurrentMessageInteractorOutput;
import ucb.voicemail.domain.boundary.output.GetLastMessageInteractorOutput;
import ucb.voicemail.domain.boundary.output.GetMailboxGreetingInteractorOutput;
import ucb.voicemail.domain.boundary.output.LoginMailboxInteractorOutput;
import ucb.voicemail.domain.boundary.output.SaveCurrentMessageInteractorOutput;
import ucb.voicemail.domain.boundary.output.SendMessageInteractorOutput;
import ucb.voicemail.domain.connection.state.ChangeGreetingState;
import ucb.voicemail.domain.connection.state.ChangePasscodeState;
import ucb.voicemail.domain.connection.state.ConnectedState;
import ucb.voicemail.domain.connection.state.MailboxMenuState;
import ucb.voicemail.domain.connection.state.MessageMenuState;
import ucb.voicemail.domain.connection.state.RecordingState;
import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;
import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;
import ucb.voicemail.domain.dto.response.DeleteCurrentMessageResponse;
import ucb.voicemail.domain.dto.response.GetLastMessageResponse;
import ucb.voicemail.domain.dto.response.GetMailboxGreetingResponse;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;
import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;
import ucb.voicemail.domain.dto.response.SendMessageResponse;

public class ConnectionPrensenter implements Presenter, ChangeGreetingInteractorOutput, ChangePasscodeInteractorOutput,
        DeleteCurrentMessageInteractorOutput, GetLastMessageInteractorOutput, GetMailboxGreetingInteractorOutput,
        LoginMailboxInteractorOutput, SaveCurrentMessageInteractorOutput, SendMessageInteractorOutput {

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
                SendMessageInteractorOutput output = (SendMessageInteractorOutput) telephone
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
                SaveCurrentMessageInteractorOutput output = (SaveCurrentMessageInteractorOutput) telephone
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
                LoginMailboxInteractorOutput output = (LoginMailboxInteractorOutput) telephone
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
                LoginMailboxInteractorOutput output = (LoginMailboxInteractorOutput) telephone
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
                GetMailboxGreetingInteractorOutput output = (GetMailboxGreetingInteractorOutput) telephone
                        .routePresenter("GetMailboxGreeting");
                output.displayMailboxGreeting(response);
            } catch (Exception e) {
            }
        }
    }

    // ------------------------------------------------
    // GetMailboxGreetingInteractorOutput

    @Override
    public void displayGreetingError() {
        for (Telephone telephone : telephoneList) {
            try {
                GetMailboxGreetingInteractorOutput output = (GetMailboxGreetingInteractorOutput) telephone
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
                GetLastMessageInteractorOutput output = (GetLastMessageInteractorOutput) telephone
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
                GetLastMessageInteractorOutput output = (GetLastMessageInteractorOutput) telephone
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
                DeleteCurrentMessageInteractorOutput output = (DeleteCurrentMessageInteractorOutput) telephone
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
                ChangePasscodeInteractorOutput output = (ChangePasscodeInteractorOutput) telephone
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
                ChangeGreetingInteractorOutput output = (ChangeGreetingInteractorOutput) telephone
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
                Presenter output = (Presenter) telephone.routePresenter("Presenter");
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
                Presenter output = (Presenter) telephone.routePresenter("Presenter");
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
                Presenter output = (Presenter) telephone.routePresenter("Presenter");
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
                Presenter output = (Presenter) telephone.routePresenter("Presenter");
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
                Presenter output = (Presenter) telephone.routePresenter("Presenter");
                if (output != null) {
                    output.displayMessageMenu();
                }
            } catch (Exception e) {
            }
        }
    }

}
