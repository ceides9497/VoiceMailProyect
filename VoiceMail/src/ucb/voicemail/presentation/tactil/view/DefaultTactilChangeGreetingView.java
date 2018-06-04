package ucb.voicemail.presentation.tactil.view;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.presentation.tactil.viewmodel.ChangeGreetingViewModel;

public class DefaultTactilChangeGreetingView extends JFrame implements TactilChangeGreetingView {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JEditorPane editorPane;
    private JButton btnAccept;
    private JButton btnCancel;
    
    public DefaultTactilChangeGreetingView(Connection connection) {
        this.connection = connection;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 234);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        editorPane = new JEditorPane();
        editorPane.setForeground(SystemColor.activeCaption);
        editorPane.setEditable(false);
        editorPane.setBackground(SystemColor.controlHighlight);
        editorPane.setBounds(10, 11, 414, 50);
        contentPane.add(editorPane);
        
        btnAccept = new JButton("");
        btnAccept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeGreeting(txtGreeting.getText());
            }
        });
        btnAccept.setBounds(10, 136, 102, 49);
        contentPane.add(btnAccept);
        
        btnCancel = new JButton("");
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        btnCancel.setBounds(322, 136, 102, 49);
        contentPane.add(btnCancel);
        
        txtGreeting = new JEditorPane();
        txtGreeting.setBounds(10, 72, 414, 53);
        contentPane.add(txtGreeting);
    }

    @Override
    public void display(ChangeGreetingViewModel model) {
        setVisible(true);
        btnAccept.setText(model.getButtonAcceptName());
        btnCancel.setText(model.getButtonCancelName());
        editorPane.setText(model.getTitle());
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void changeGreeting(String text) {
        connection.record(text);
        connection.dial("#");
    }
    
    private Connection connection;
    private JEditorPane txtGreeting;

}
