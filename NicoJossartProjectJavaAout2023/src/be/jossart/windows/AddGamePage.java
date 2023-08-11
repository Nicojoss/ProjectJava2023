package be.jossart.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import be.jossart.pojo.VideoGame;

public class AddGamePage extends JFrame {
	private static final long serialVersionUID = -4032899934201765629L;
	private JPanel contentPane;
	JLabel lblTitle = new JLabel("Add Game Page");
	JLabel lblName = new JLabel("Name");
	private JTextField tfName;
	JLabel lblConsole = new JLabel("Console");
    private JTextField tfConsole;
    JLabel lblCreditCost = new JLabel("Credit Cost");
    private JTextField tfCreditCost;
    private JLabel lbError;
    JButton AddGameButton = new JButton("Add Game");
    JButton backButton = new JButton("Back to Admin Page");

	public AddGamePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblTitle.setBounds(23, 35, 200, 20);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);
		
		lblName.setBounds(23, 111, 46, 13);
        contentPane.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(96, 108, 96, 19);
        contentPane.add(tfName);
        tfName.setColumns(10);
        
        
        lblConsole.setBounds(23, 85, 62, 16);
        contentPane.add(lblConsole);

        tfConsole = new JTextField();
        tfConsole.setBounds(96, 84, 96, 19);
        contentPane.add(tfConsole);
        tfConsole.setColumns(10);
        
        
        lblCreditCost.setBounds(23, 134, 62, 13);
        contentPane.add(lblCreditCost);

        tfCreditCost = new JTextField();
        tfCreditCost.setBounds(96, 132, 96, 19);
        contentPane.add(tfCreditCost);
        tfCreditCost.setColumns(10);
        
        lbError = new JLabel("");
        lbError.setBounds(23, 55, 200, 20);
        lbError.setForeground(Color.RED);
        contentPane.add(lbError);
        
        AddGameButton.setBounds(94, 172, 98, 21);
        AddGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText();
                String console = tfConsole.getText();
                String stringCreditCost = tfCreditCost.getText();
                int creditCost = 0;
                if(!stringCreditCost.isEmpty()) {
                	creditCost = Integer.parseInt(stringCreditCost);
                }
                 
                if (name.isEmpty() || console.isEmpty() ||  creditCost <=0) {
                    lbError.setText("Please fill in all fields and creditCost > 0");
                } else {
                    try {    
    	                
                    	VideoGame game = new VideoGame(name, console, creditCost);
                    	if(VideoGame.AddGame(game)){
                    		AdministratorPage adminPage = new AdministratorPage();
            	            adminPage.setVisible(true);
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
        contentPane.add(AddGameButton);
        
		backButton.setBounds(10, 8, 149, 21);
	    backButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            AdministratorPage adminPage = new AdministratorPage();
	            adminPage.setVisible(true);
	            dispose();
	        }
	    });
	    getContentPane().add(backButton);
	    
	}
}
