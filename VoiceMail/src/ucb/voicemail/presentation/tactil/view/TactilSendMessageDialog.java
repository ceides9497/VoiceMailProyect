package ucb.voicemail.presentation.tactil.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TactilSendMessageDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private DefaultTactilShowGreetingView parentView;
    private JEditorPane editorMessage;
    public JEditorPane lblInitialPromptSendMessage;
    public JButton btnSend;
    public JButton btnCancel;
    private TactilSendMessageDialog ownWindow;
    
    public TactilSendMessageDialog(DefaultTactilShowGreetingView parentView) {
        this.ownWindow = this;
        this.parentView = parentView;
        setBounds(100, 100, 450, 265);
        getContentPane().setLayout(null);
        {
            editorMessage = new JEditorPane();
            editorMessage.setBounds(10, 68, 414, 70);
            getContentPane().add(editorMessage);
        }
        {
            lblInitialPromptSendMessage = new JEditorPane();
            lblInitialPromptSendMessage.setForeground(SystemColor.activeCaption);
            lblInitialPromptSendMessage.setEditable(false);
            lblInitialPromptSendMessage.setBackground(SystemColor.controlHighlight);
            lblInitialPromptSendMessage.setBounds(10, 11, 414, 46);
            getContentPane().add(lblInitialPromptSendMessage);
        }
        {
            btnSend = new JButton("");
            btnSend.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentView.sendMessage(editorMessage.getText());
                    editorMessage.setText("");
                    ownWindow.setVisible(false);
                }
            });
            btnSend.setBounds(10, 149, 137, 72);
            getContentPane().add(btnSend);
        }
        {
            btnCancel = new JButton("");
            btnCancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    editorMessage.setText("");
                    ownWindow.setVisible(false);
                    parentView.setVisible(true);
                }
            });
            btnCancel.setBounds(287, 149, 137, 72);
            getContentPane().add(btnCancel);
        }
    }
    
}
