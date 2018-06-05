package ucb.voicemail.presentation.tactil.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.presentation.tactil.viewmodel.MessageMenuViewModel;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultTactilMessageMenuView extends JFrame implements TactilMessageMenuView {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnQuit;
    private JButton btnLastMessage;
    private JButton btnSaveMessage;
    private JButton btnDeleteMessage;
    private JButton btnReturn;
    
    public DefaultTactilMessageMenuView(Connection connection) {
        this.connection = connection;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 265);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        btnQuit = new JButton("");
        btnQuit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.hangup();
                setVisible(false);
            }
        });
        btnQuit.setBounds(335, 11, 89, 23);
        contentPane.add(btnQuit);
        
        btnLastMessage = new JButton("");
        btnLastMessage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.dial("1");
            }
        });
        btnLastMessage.setBounds(10, 59, 414, 32);
        contentPane.add(btnLastMessage);
        
        btnSaveMessage = new JButton("");
        btnSaveMessage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.dial("2");
            }
        });
        btnSaveMessage.setBounds(10, 102, 414, 32);
        contentPane.add(btnSaveMessage);
        
        btnDeleteMessage = new JButton("");
        btnDeleteMessage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.dial("3");
            }
        });
        btnDeleteMessage.setBounds(10, 145, 414, 32);
        contentPane.add(btnDeleteMessage);
        
        btnReturn = new JButton("");
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connection.dial("4");
                setVisible(false);
            }
        });
        btnReturn.setBounds(10, 188, 414, 32);
        contentPane.add(btnReturn);
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
    public void display(MessageMenuViewModel model) {
        setVisible(true);
        btnLastMessage.setText(model.getGetLastMessageName());
        btnSaveMessage.setText(model.getSaveCurrentMessageName());
        btnDeleteMessage.setText(model.getDeleteCurrentMessageName());
        btnReturn.setText(model.getReturnName());
        btnQuit.setText(model.getQuitName());
    }
}
