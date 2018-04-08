import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {

	private JPanel board;
	private JTextField txtInput;
	private JLabel lblOutput;
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
		setBounds(100, 100, 400, 351);
		board = new JPanel();
		board.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(board);
		board.setLayout(null);
		
		lblOutput = new JLabel("nothing");
		lblOutput.setBounds(5, 5, 369, 85);
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		board.add(lblOutput);
		
		JButton btnEnter = new JButton("#");
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("#");
			}
		});
		btnEnter.setBounds(198, 260, 52, 42);
		board.add(btnEnter);
		
		JButton btnH = new JButton("H");
		btnH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.hangup();
			}
		});
		btnH.setBounds(260, 260, 52, 42);
		board.add(btnH);
		
		JButton btnQ = new JButton("Q");
		btnQ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeFrame();
			}
		});
		btnQ.setBounds(322, 260, 52, 42);
		board.add(btnQ);
		
		JButton btn1 = new JButton("1");
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("1");
			}
		});
		btn1.setBounds(10, 101, 52, 42);
		board.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("2");
			}
		});
		btn2.setBounds(72, 101, 52, 42);
		board.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("3");
			}
		});
		btn3.setBounds(134, 101, 52, 42);
		board.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("4");
			}
		});
		btn4.setBounds(10, 154, 52, 42);
		board.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("5");
			}
		});
		btn5.setBounds(72, 154, 52, 42);
		board.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("6");
			}
		});
		btn6.setBounds(134, 154, 52, 42);
		board.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("7");
			}
		});
		btn7.setBounds(10, 207, 52, 42);
		board.add(btn7);
			
		JButton btn8 = new JButton("8");
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("8");
			}
		});
		btn8.setBounds(72, 207, 52, 42);
		board.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("9");
			}
		});
		btn9.setBounds(134, 207, 52, 42);
		board.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				connection.dial("0");
			}
		});
		btn0.setBounds(72, 260, 52, 42);
		board.add(btn0);
		
		txtInput = new JTextField();
		txtInput.setBounds(198, 101, 176, 148);
		board.add(txtInput);
		txtInput.setColumns(10);
	}
	
	public void changeMainLabel(String output) {
		lblOutput.setText(output);
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	private void closeFrame() {
		super.dispose();
	}
	
	private Connection connection;
}
