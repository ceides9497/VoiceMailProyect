package ucb.voicemail.presentation.tactil.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class TactilLoginMailboxDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private DefaultTactilShowGreetingView parentView;
    public JEditorPane lblInitialPromptLoginMailbox;
    public JButton btnLogin;
    public JButton btnCancel;
    private TactilLoginMailboxDialog ownWindow;
    private JPasswordField passwordField;
    
    public TactilLoginMailboxDialog(DefaultTactilShowGreetingView parentView) {
        this.ownWindow = this;
        this.parentView = parentView;
        setBounds(100, 100, 450, 215);
        getContentPane().setLayout(null);
        {
            lblInitialPromptLoginMailbox = new JEditorPane();
            lblInitialPromptLoginMailbox.setForeground(SystemColor.activeCaption);
            lblInitialPromptLoginMailbox.setEditable(false);
            lblInitialPromptLoginMailbox.setBackground(SystemColor.controlHighlight);
            lblInitialPromptLoginMailbox.setBounds(10, 11, 414, 46);
            getContentPane().add(lblInitialPromptLoginMailbox);
        }
        {
            btnLogin = new JButton("");
            btnLogin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentView.loginMailbox(passwordField.getPassword());
                    passwordField.setText("");
                }
            });
            btnLogin.setBounds(10, 99, 137, 72);
            getContentPane().add(btnLogin);
        }
        {
            btnCancel = new JButton("");
            btnCancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    passwordField.setText("");
                    ownWindow.setVisible(false);
                    parentView.setVisible(true);
                }
            });
            btnCancel.setBounds(287, 99, 137, 72);
            getContentPane().add(btnCancel);
        }
        
        passwordField = new JPasswordField();
        passwordField.setBounds(10, 68, 414, 20);
        getContentPane().add(passwordField);
    }
}
