package ucb.voicemail.presentation.tactil.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.presentation.tactil.viewmodel.ChangePasscodeViewModel;

import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultTactilChangePasscodeView extends JFrame implements TactilChangePasscodeView {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JEditorPane editorPane;
    private JButton btnAccept;
    private JButton btnCancel;
    
    public DefaultTactilChangePasscodeView(Connection connection) {
        this.connection = connection;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 194);
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
        
        passwordField = new JPasswordField();
        passwordField.setBounds(10, 72, 414, 20);
        contentPane.add(passwordField);
        
        btnAccept = new JButton("");
        btnAccept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePassword(passwordField.getPassword());
            }
        });
        btnAccept.setBounds(10, 103, 102, 49);
        contentPane.add(btnAccept);
        
        btnCancel = new JButton("");
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        btnCancel.setBounds(322, 103, 102, 49);
        contentPane.add(btnCancel);
    }

    @Override
    public void display(ChangePasscodeViewModel model) {
        setVisible(true);
        btnAccept.setText(model.getButtonAcceptName());
        btnCancel.setText(model.getButtonCancelName());
        editorPane.setText(model.getTitle());
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void changePassword(char[] text) {
        for(char letter : text) {
            if(letter != '#') {
                connection.dial("" + letter);
            }
        }
        connection.dial("#");
    }
    
    private Connection connection;

}
