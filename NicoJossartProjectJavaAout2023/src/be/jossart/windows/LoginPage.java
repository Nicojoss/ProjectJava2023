package be.jossart.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import be.jossart.pojo.Player;
import be.jossart.pojo.Users;

public class LoginPage extends JFrame{
	private static final long serialVersionUID = 996993373253522885L;
	JPanel contentPane = new JPanel();
	JLabel lblTitle = new JLabel("LogIn page");
	JTextField TfPseudo = new JTextField();
	JPasswordField TfPassword = new JPasswordField();
	JLabel lb_error = new JLabel("");
	JButton confirmButton = new JButton("Confirm");
	JButton backButton = new JButton("Return HomePage");
	JLabel lbl_Pseudo = new JLabel("Pseudo");
	JLabel lbl_Password = new JLabel("Password");
	
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 465);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle.setBounds(20, 39, 76, 20);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);
		
		TfPseudo.setBounds(98, 100, 200, 25);
	    contentPane.add(TfPseudo);
	    
	    TfPassword.setBounds(98, 150, 200, 25);
	    contentPane.add(TfPassword);
	    
		lb_error.setForeground(Color.RED);
		lb_error.setHorizontalAlignment(SwingConstants.CENTER);
		lb_error.setBounds(98, 57, 414, 14);
		getContentPane().add(lb_error);
		
	    confirmButton.setBounds(160, 200, 80, 30);
	    confirmButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if (TfPseudo.getText().equals("") || new String(TfPassword.getPassword()).equals("")) {
	                lb_error.setText("Pseudo or Password Empty!");
	            } else {
	                String stringPassword = new String(TfPassword.getPassword());
	                try {
	                    Object user = Users.login(TfPseudo.getText(), stringPassword);
	                    if (user instanceof Player) {
	                        PlayerPage playerPage = new PlayerPage((Player) user);
	                        playerPage.setVisible(true);
	                        dispose();
	                    } else {
	                        AdministratorPage adminPage = new AdministratorPage();
	                        adminPage.setVisible(true);
	                        dispose();
	                    }
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }
	            }
	        }
	    });
	    contentPane.add(confirmButton);
	    
	    backButton.setBounds(10, 8, 115, 21);
	    backButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Home homePage = new Home();
	            homePage.setVisible(true);
	            dispose();
	        }
	    });
	    getContentPane().add(backButton);
	    
	    
	    lbl_Pseudo.setBounds(98, 88, 45, 13);
	    contentPane.add(lbl_Pseudo);
	    
	    lbl_Password.setBounds(98, 137, 76, 13);
	    contentPane.add(lbl_Password);
	}
}
