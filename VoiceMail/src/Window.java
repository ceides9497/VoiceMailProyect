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

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField txtInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
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
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOutput = new JLabel("nothing");
		lblOutput.setBounds(5, 5, 369, 85);
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblOutput);
		
		JButton btnEnter = new JButton("#");
		btnEnter.setBounds(198, 260, 52, 42);
		contentPane.add(btnEnter);
		
		JButton btnH = new JButton("H");
		btnH.setBounds(260, 260, 52, 42);
		contentPane.add(btnH);
		
		JButton btnQ = new JButton("Q");
		btnQ.setBounds(322, 260, 52, 42);
		btnQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnQ);
		
		JButton btn1 = new JButton("1");
		btn1.setBounds(10, 101, 52, 42);
		contentPane.add(btn1);
		
		JButton btn4 = new JButton("4");
		btn4.setBounds(10, 154, 52, 42);
		contentPane.add(btn4);
		
		JButton btn7 = new JButton("7");
		btn7.setBounds(10, 207, 52, 42);
		contentPane.add(btn7);
		
		JButton btn2 = new JButton("2");
		btn2.setBounds(72, 101, 52, 42);
		contentPane.add(btn2);
		
		JButton btn5 = new JButton("5");
		btn5.setBounds(72, 154, 52, 42);
		contentPane.add(btn5);
		
		JButton btn8 = new JButton("8");
		btn8.setBounds(72, 207, 52, 42);
		contentPane.add(btn8);
		
		JButton btn3 = new JButton("3");
		btn3.setBounds(134, 101, 52, 42);
		contentPane.add(btn3);
		
		JButton btn6 = new JButton("6");
		btn6.setBounds(134, 154, 52, 42);
		contentPane.add(btn6);
		
		JButton btn9 = new JButton("9");
		btn9.setBounds(134, 207, 52, 42);
		contentPane.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.setBounds(72, 260, 52, 42);
		contentPane.add(btn0);
		
		txtInput = new JTextField();
		txtInput.setBounds(198, 101, 176, 148);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
	}
}
