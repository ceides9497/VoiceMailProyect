package ucb.voicemail.presentation.tactil.view;

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.presentation.Connection;
import ucb.voicemail.presentation.tactil.viewmodel.InitialPromptViewModel;

import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultTactilInitialPromptView extends JFrame implements TactilInitialPromptView {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JEditorPane initialPrompt;
    private JButton btnConnect;
    private JButton btnQuit;
    private JEditorPane txtMailboxNumber;
    private DefaultTactilInitialPromptView ownView;
    
    public DefaultTactilInitialPromptView(Connection connection) {
        ownView = this;
    	this.connection = connection;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        initialPrompt = new JEditorPane();
        initialPrompt.setBounds(10, 11, 414, 50);
        initialPrompt.setEditable(false);
        initialPrompt.setBackground(SystemColor.controlHighlight);
        initialPrompt.setForeground(SystemColor.activeCaption);
        contentPane.add(initialPrompt);
        
        btnConnect = new JButton("");
        btnConnect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connect(txtMailboxNumber.getText());
            }
        });
        btnConnect.setBounds(10, 103, 137, 72);
        contentPane.add(btnConnect);
        
        btnQuit = new JButton("");
        btnQuit.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		setVisible(false);
        		System.exit(0);
        	}
        });
        btnQuit.setBounds(287, 103, 137, 72);
        contentPane.add(btnQuit);
        
        txtMailboxNumber = new JEditorPane();
        txtMailboxNumber.setBounds(10, 72, 414, 20);
        contentPane.add(txtMailboxNumber);
    }

    @Override
    public void display(InitialPromptViewModel model) {
        setVisible(true);
        this.setTitle(model.getWindowTitle());
        btnConnect.setText(model.getConnectButtonText());
        btnQuit.setText(model.getQuitButtonText());
        initialPrompt.setText(model.getInitialPromptMessage());
    }
    
    public void connect(String text) {
        for(char letter : text.toCharArray()) {
            if(letter != '#') {
                connection.dial("" + letter);
            }
        }
        connection.dial("#");
    }
    
    @Override
	public void hideWiew() {
		this.setVisible(false);
	}
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    private Connection connection;
}
