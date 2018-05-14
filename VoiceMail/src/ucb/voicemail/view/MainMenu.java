package ucb.voicemail.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucb.voicemail.main.Connection;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.SystemColor;

public class MainMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel board;
	private JTextField txtInput;
	private JEditorPane dtrpnNothing;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
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
		btnEnter.setBounds(194, 169, 30, 33);
		board.add(btnEnter);
		
		JButton btnH = new JButton("H");
		btnH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.hangup();
			}
		});
		btnH.setBounds(225, 169, 30, 33);
		board.add(btnH);
		
		JButton btnQ = new JButton("Q");
		btnQ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeFrame();
			}
		});
		btnQ.setBounds(258, 169, 30, 33);
		board.add(btnQ);
		
		JButton btn1 = new JButton("1");
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("1");
			}
		});
		btn1.setBounds(62, 214, 52, 42);
		board.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("2");
			}
		});
		btn2.setBounds(124, 214, 52, 42);
		board.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("3");
			}
		});
		btn3.setBounds(186, 214, 52, 42);
		board.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("4");
			}
		});
		btn4.setBounds(62, 267, 52, 42);
		board.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("5");
			}
		});
		btn5.setBounds(124, 267, 52, 42);
		board.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("6");
			}
		});
		btn6.setBounds(186, 267, 52, 42);
		board.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("7");
			}
		});
		btn7.setBounds(62, 320, 52, 42);
		board.add(btn7);
			
		JButton btn8 = new JButton("8");
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("8");
			}
		});
		btn8.setBounds(124, 320, 52, 42);
		board.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("9");
			}
		});
		btn9.setBounds(186, 320, 52, 42);
		board.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("0");
			}
		});
		btn0.setBounds(124, 373, 52, 42);
		board.add(btn0);
		
		txtInput = new JTextField();
		txtInput.setBounds(17, 97, 270, 66);
		board.add(txtInput);
		txtInput.setColumns(10);
		
		JButton btnAccept = new JButton("Enter");
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(txtInput.getText());
				connection.record(txtInput.getText());
				txtInput.setText("");
			}
		});
		btnAccept.setBounds(17, 175, 176, 23);
		board.add(btnAccept);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(17, 88, 268, 10);
		board.add(separator);
		
		dtrpnNothing = new JEditorPane();
		dtrpnNothing.setText("nothing");
		dtrpnNothing.setToolTipText("");
		dtrpnNothing.setBackground(SystemColor.controlHighlight);
		dtrpnNothing.setBounds(17, 6, 267, 85);
		board.add(dtrpnNothing);
	}
	
	public void changeMainLabel(String output) {
		dtrpnNothing.setText(output);
		output = "<html>" + output.replaceAll("\n", "<br/>") + "</html>";
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	private void closeFrame() {
		super.dispose();
	}
	
	private Connection connection;
}
