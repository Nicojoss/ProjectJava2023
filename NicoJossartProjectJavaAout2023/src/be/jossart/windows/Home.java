package be.jossart.windows;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Home extends JFrame {
	private static final long serialVersionUID = -1172948464054094842L;
	private JPanel contentPane;
	JButton LogInButton = new JButton("LogIn");
	JButton RegisterButton = new JButton("Register");
	JLabel lblTitle = new JLabel("Welcome on my application");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		LogInButton.setBounds(98, 132, 69, 21);
		LogInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginPage = new LoginPage();
		        loginPage.setVisible(true);
		        dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(LogInButton);
		
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterPage registerpage = new RegisterPage();
				registerpage.setVisible(true);
		        dispose();
			}
		});
		RegisterButton.setBounds(226, 132, 76, 21);
		contentPane.add(RegisterButton);
		
		lblTitle.setBounds(115, 36, 187, 29);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);
	}

}
