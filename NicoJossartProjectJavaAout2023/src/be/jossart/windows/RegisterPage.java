package be.jossart.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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

public class RegisterPage extends JFrame{
	private static final long serialVersionUID = -6923144167105542300L;
	JPanel contentPane = new JPanel();
    private JTextField tfPseudo;
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JLabel lbError;
    JButton registerButton = new JButton("Register");
    JButton backButton = new JButton("Back to Home");
	
	public RegisterPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 421);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Register page");
		lblTitle.setBounds(23, 35, 87, 20);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setBounds(23, 111, 46, 13);
        contentPane.add(lblPseudo);

        tfPseudo = new JTextField();
        tfPseudo.setBounds(96, 108, 96, 19);
        contentPane.add(tfPseudo);
        tfPseudo.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(23, 85, 62, 16);
        contentPane.add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(96, 84, 96, 19);
        contentPane.add(tfUsername);
        tfUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(23, 134, 62, 13);
        contentPane.add(lblPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(96, 132, 96, 19);
        tfPassword.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(tfPassword);
        
        lbError = new JLabel("");
        lbError.setBounds(23, 55, 200, 20);
        lbError.setForeground(Color.RED);
        contentPane.add(lbError);
        
        registerButton.setBounds(94, 208, 71, 21);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pseudo = tfPseudo.getText();
                String username = tfUsername.getText();
                String password = new String(tfPassword.getPassword());

                if (pseudo.isEmpty() || username.isEmpty() ||  password.isEmpty()) {
                    lbError.setText("Please fill in all fields");
                } else {
                    try {    
    	                String stringPassword = new String(tfPassword.getPassword());
                    	LocalDate birthday= LocalDate.now();
                    	Users user = new Player(tfPseudo.getText(),tfUsername.getText(), stringPassword,  birthday);
                    	if(user.Register((Player) user)){
                    		LoginPage loginPage = new LoginPage();
                    		loginPage.setVisible(true);
	                        dispose();
    					}else {
    						lbError.setText("invalid information please try again");
    					}
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        contentPane.add(registerButton);
		
		backButton.setBounds(10, 8, 95, 21);
	    backButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Home homePage = new Home();
	            homePage.setVisible(true);
	            dispose();
	        }
	    });
	    getContentPane().add(backButton);
	}
}
