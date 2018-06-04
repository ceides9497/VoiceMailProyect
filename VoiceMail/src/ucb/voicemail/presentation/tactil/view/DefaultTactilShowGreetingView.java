package ucb.voicemail.presentation.tactil.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.presentation.tactil.viewmodel.ShowGreetingViewModel;

import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultTactilShowGreetingView extends JFrame implements TactilShowGreetingView {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private TactilSendMessageDialog sendMessageDialog;
    private TactilLoginMailboxDialog loginMailboxDialog;
    private JEditorPane editorGreeting;
    private JButton btnLogin;
    private JButton btnSendMessage;
    private JButton btnQuit;
    
    public DefaultTactilShowGreetingView(Connection connection) {
        this.connection = connection;
        sendMessageDialog = new TactilSendMessageDialog(this);
        loginMailboxDialog = new TactilLoginMailboxDialog(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 205);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        editorGreeting = new JEditorPane();
        editorGreeting.setForeground(SystemColor.activeCaption);
        editorGreeting.setEditable(false);
        editorGreeting.setBackground(SystemColor.controlHighlight);
        editorGreeting.setBounds(10, 11, 317, 50);
        contentPane.add(editorGreeting);
        
        btnLogin = new JButton("");
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                loginMailboxDialog.setVisible(true);
            }
        });
        btnLogin.setBounds(10, 86, 137, 72);
        contentPane.add(btnLogin);
        
        btnSendMessage = new JButton("");
        btnSendMessage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                sendMessageDialog.setVisible(true);
            }
        });
        btnSendMessage.setBounds(287, 86, 137, 72);
        contentPane.add(btnSendMessage);
        
        btnQuit = new JButton("");
        btnQuit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.hangup();
            }
        });
        btnQuit.setBounds(337, 21, 87, 30);
        contentPane.add(btnQuit);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void sendMessage(String text) {
        connection.record(text);
        connection.hangup();
    }
    
    public void loginMailbox(char[] text) {
        for(char letter : text) {
            if(letter != '#') {
                connection.dial("" + letter);
            }
        }
        connection.dial("#");
    }
    
    private Connection connection;

    @Override
    public void display(ShowGreetingViewModel model) {
        setVisible(true);
        editorGreeting.setText(model.getGreeting());
        btnLogin.setText(model.getLoginButtonName());
        btnSendMessage.setText(model.getSendMessageButtonName());
        btnQuit.setText(model.getQuitButtonName());
        
        loginMailboxDialog.lblInitialPromptLoginMailbox.setText(model.getDefaultMessageLoginMailbox());
        loginMailboxDialog.btnLogin.setText(model.getLoginMailboxAcceptButtonName());
        loginMailboxDialog.btnCancel.setText(model.getLoginMailboxCancelButtonName());
        
        sendMessageDialog.lblInitialPromptSendMessage.setText(model.getDefaultMessageSendMessage());
        sendMessageDialog.btnSend.setText(model.getSendMessageAcceptButtonName());
        sendMessageDialog.btnCancel.setText(model.getSendMessageCancelButtonName());
    }
}
