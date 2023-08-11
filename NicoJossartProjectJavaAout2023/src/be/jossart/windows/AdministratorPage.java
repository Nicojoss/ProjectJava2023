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

public class AdministratorPage extends JFrame {
	private static final long serialVersionUID = 4820910553286228185L;
	private JPanel contentPane;
	JLabel lblTitle = new JLabel("Admin page");
	JButton backButton = new JButton("Return HomePage");
	JButton btnAddGame = new JButton("Add Game");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorPage frame = new AdministratorPage();
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
	public AdministratorPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle.setBounds(20, 39, 105, 20);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);
		
		backButton.setBounds(10, 8, 115, 21);
	    backButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Home homePage = new Home();
	            homePage.setVisible(true);
	            dispose();
	        }
	    });
	    getContentPane().add(backButton);
	    
	    btnAddGame.setBounds(10, 58, 96, 25);
	    btnAddGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddGamePage addGame = new AddGamePage();
                addGame.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(btnAddGame);
	}

}
