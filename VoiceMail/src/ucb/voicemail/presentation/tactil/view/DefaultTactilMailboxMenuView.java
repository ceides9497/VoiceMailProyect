package ucb.voicemail.presentation.tactil.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.presentation.tactil.viewmodel.MailboxMenuViewModel;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultTactilMailboxMenuView extends JFrame implements TactilMailboxMenuView {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnOpenMessageMenu;
    private JButton btnChangePasscode;
    private JButton btnChangeGreeting;
    private JButton btnQuit;
    
    public DefaultTactilMailboxMenuView(Connection connection) {
        this.connection = connection;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 255);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        btnOpenMessageMenu = new JButton("");
        btnOpenMessageMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.dial("1");
                setVisible(false);
            }
        });
        btnOpenMessageMenu.setBounds(10, 60, 414, 42);
        contentPane.add(btnOpenMessageMenu);
        
        btnChangePasscode = new JButton("");
        btnChangePasscode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.dial("2");
            }
        });
        btnChangePasscode.setBounds(10, 113, 414, 42);
        contentPane.add(btnChangePasscode);
        
        btnChangeGreeting = new JButton("");
        btnChangeGreeting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.dial("3");
            }
        });
        btnChangeGreeting.setBounds(10, 166, 414, 42);
        contentPane.add(btnChangeGreeting);
        
        btnQuit = new JButton("");
        btnQuit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	setVisible(false);
                connection.hangup();
            }
        });
        btnQuit.setBounds(335, 11, 89, 23);
        contentPane.add(btnQuit);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    @Override
	public void hideWiew() {
		this.setVisible(false);
	}
    
    private Connection connection;

    @Override
    public void display(MailboxMenuViewModel model) {
        setVisible(true);
        btnOpenMessageMenu.setText(model.getOpenMessageMenuName());
        btnChangePasscode.setText(model.getChangePasscodeName());
        btnChangeGreeting.setText(model.getChangeGreetingName());
        btnQuit.setText(model.getQuitName());
    }
    
}
