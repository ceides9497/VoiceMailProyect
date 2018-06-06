package ucb.voicemail.presentation.graphical.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.presentation.Connection;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;

public class MainGraphicalView extends JFrame implements GraphicalView {

    private static final long serialVersionUID = 1L;
    private JPanel board;
    private JTextField txtInput;
    private JEditorPane dtrpnNothing;

    public MainGraphicalView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 307, 492);
        board = new JPanel();
        board.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(board);
        board.setLayout(null);
        
        JButton btnEnter = new JButton("#");
        btnEnter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                connection.dial("#");
            }
        });
        btnEnter.setBounds(186, 373, 52, 42);
        board.add(btnEnter);
        
        JButton btnH = new JButton("Colgar");
        btnH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                connection.hangup();
            }
        });
        btnH.setBounds(145, 169, 139, 23);
        board.add(btnH);
        
        JButton btn1 = new JButton("1");
        btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("1");
            }
        });
        btn1.setBounds(62, 214, 52, 42);
        board.add(btn1);
        
        JButton btn2 = new JButton("2");
        btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("2");
            }
        });
        btn2.setBounds(124, 214, 52, 42);
        board.add(btn2);
        
        JButton btn3 = new JButton("3");
        btn3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("3");
            }
        });
        btn3.setBounds(186, 214, 52, 42);
        board.add(btn3);
        
        JButton btn4 = new JButton("4");
        btn4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("4");
            }
        });
        btn4.setBounds(62, 267, 52, 42);
        board.add(btn4);
        
        JButton btn5 = new JButton("5");
        btn5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("5");
            }
        });
        btn5.setBounds(124, 267, 52, 42);
        board.add(btn5);
        
        JButton btn6 = new JButton("6");
        btn6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("6");
            }
        });
        btn6.setBounds(186, 267, 52, 42);
        board.add(btn6);
        
        JButton btn7 = new JButton("7");
        btn7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("7");
            }
        });
        btn7.setBounds(62, 320, 52, 42);
        board.add(btn7);
            
        JButton btn8 = new JButton("8");
        btn8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("8");
            }
        });
        btn8.setBounds(124, 320, 52, 42);
        board.add(btn8);
        
        JButton btn9 = new JButton("9");
        btn9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("9");
            }
        });
        btn9.setBounds(186, 320, 52, 42);
        board.add(btn9);
        
        JButton btn0 = new JButton("0");
        btn0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                refreshScreen("0");
            }
        });
        btn0.setBounds(124, 373, 52, 42);
        board.add(btn0);
        
        txtInput = new JTextField();
        txtInput.setToolTipText("");
        txtInput.setBounds(17, 97, 270, 66);
        board.add(txtInput);
        txtInput.setColumns(10);
        
        JButton btnAccept = new JButton("Aceptar");
        btnAccept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.out.println(txtInput.getText());
                connection.record(txtInput.getText());
                dtrpnNothing.setText(txtInput.getText());
                txtInput.setText("");
            }
        });
        btnAccept.setBounds(17, 169, 126, 23);
        board.add(btnAccept);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(17, 88, 268, 10);
        board.add(separator);
        
        dtrpnNothing = new JEditorPane();
        dtrpnNothing.setEditable(false);
        dtrpnNothing.setForeground(SystemColor.activeCaption);
        dtrpnNothing.setToolTipText("");
        dtrpnNothing.setBackground(SystemColor.controlHighlight);
        dtrpnNothing.setBounds(17, 6, 267, 85);
        board.add(dtrpnNothing);
    }
    
    @Override
    public void changeMainLabel(GraphicalViewModel model) {
        dtrpnNothing.setText(model.getText());
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    private void refreshScreen(String text) {
        dtrpnNothing.setText(text);
        connection.dial(text);
    }
    
    private Connection connection;
}
