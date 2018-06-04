package ucb.voicemail.presentation.tactil.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.presentation.tactil.viewmodel.MessageViewModel;

import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DefaultTactilMessageView extends JFrame implements TactilMessageView {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JEditorPane editorPane;
    private JButton btnAccept;
    
    public DefaultTactilMessageView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 195);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        editorPane = new JEditorPane();
        editorPane.setForeground(SystemColor.activeCaption);
        editorPane.setEditable(false);
        editorPane.setBackground(SystemColor.controlHighlight);
        editorPane.setBounds(10, 11, 414, 73);
        contentPane.add(editorPane);
        
        btnAccept = new JButton("");
        btnAccept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        btnAccept.setBounds(133, 95, 165, 51);
        contentPane.add(btnAccept);
    }

    @Override
    public void display(MessageViewModel model) {
        setVisible(true);
        editorPane.setText(model.getText());
        btnAccept.setText(model.getButtonAcceptName());
    }
    
    
}
