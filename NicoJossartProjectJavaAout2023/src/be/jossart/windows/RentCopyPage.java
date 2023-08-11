package be.jossart.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import be.jossart.pojo.Copy;
import be.jossart.pojo.Loan;
import be.jossart.pojo.Player;

public class RentCopyPage extends JFrame {
	private static final long serialVersionUID = -7958300857008763604L;
	private JPanel contentPane;
	JLabel lblTitle = new JLabel("Choose the game you want to rent pay attention to your credits!");
	private JComboBox<Copy> copyComboBox = new JComboBox<>();
	JLabel lb_error = new JLabel("");
	LocalDate StartDate = LocalDate.now();
	LocalDate EndDate;
	int nbrWeeksRent;
	private JTextField weeksTextField = new JTextField();
	JButton backButton = new JButton("Return PlayerPage");
	
	public RentCopyPage(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		backButton.setBounds(0, 10, 168, 21);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        PlayerPage playerPage = new PlayerPage(player);
		        playerPage.setVisible(true);
		        dispose();
		    }
		});
		contentPane.setLayout(null);
		getContentPane().add(backButton);
		
		
		JLabel lblAvailableCredits = new JLabel(player.getPseudo() + " Available Credits: " + player.getCredit());
        lblAvailableCredits.setBounds(0, 58, 426, 13);
        contentPane.add(lblAvailableCredits);
        
        lblTitle.setBounds(0, 81, 555, 20);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);  
		
		List<Copy> availableCopies = Copy.findAll();
		
		for (Copy copy : availableCopies) {
			copyComboBox.addItem(copy);
        }
		copyComboBox.setBounds(0, 111, 404, 21);
        contentPane.add(copyComboBox);
        
        weeksTextField.setBounds(342, 158, 100, 21);
        contentPane.add(weeksTextField);
        
        JButton rentButton = new JButton("Rent Copy");
        rentButton.setBounds(0, 185, 100, 25);
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Copy selectedCopy = (Copy) copyComboBox.getSelectedItem();
                String weeksStr = weeksTextField.getText();
                int nbrWeeksRent = Integer.parseInt(weeksStr);
                
             // 1: Check if the copy belongs to the player
                if (selectedCopy.getOwner().equals(player)) {
                    lb_error.setText("You cannot rent a copy that belongs to you!");
                    return;
                }else {
                	// 2: Check if he enters a number of week between 1 and 4
                	if (nbrWeeksRent >= 1 && nbrWeeksRent <= 4) {
                		int creditCost = (selectedCopy.getVideoGame().getCreditCost()) * nbrWeeksRent;
                		EndDate = StartDate.plusWeeks(nbrWeeksRent);
                		// 3: check if the copy is available
                		if (selectedCopy.getAvailable()) {
                			if (player.getCredit() >= creditCost) {
                				player.setCredit(player.getCredit() - creditCost);
                				player.UpdateCredit(player);

                				selectedCopy.setAvailable(false);
                				selectedCopy.UpdateAvailable(selectedCopy);

                				// Add credits to lender
                				selectedCopy.getOwner().setCredit(selectedCopy.getOwner().getCredit() + creditCost);
                				selectedCopy.getOwner().UpdateCredit(selectedCopy.getOwner());

                				// Add loan
                				Loan loan = new Loan(StartDate, EndDate, player, selectedCopy);
                				if(loan.AddLoan(loan)) {
                					PlayerPage playerPage = new PlayerPage(player);
        	                        playerPage.setVisible(true);
        	                        dispose();
                				}
                				dispose();
                			} else {
                				lb_error.setText("You don't have enough credit!" + player.getCredit());
                			}
                		} else {
                			lb_error.setText("Unfortunately the copy is already rented!");
                			// Rajouter la possiblité de faire une reservation pour ce jeux.
                		}
                	} else {
                    lb_error.setText("Please enter a valid number of weeks (1 to 4)");
                	}
                }
            }
        });
        contentPane.add(rentButton);
        
        lb_error.setForeground(Color.RED);
		lb_error.setHorizontalAlignment(SwingConstants.CENTER);
		lb_error.setBounds(0, 142, 426, 14);
		getContentPane().add(lb_error);
		
		JLabel lblEnterTheNumber = new JLabel("Enter the number of weeks you want to rent the game");
		lblEnterTheNumber.setBounds(0, 162, 426, 13);
		contentPane.add(lblEnterTheNumber);
	}
}