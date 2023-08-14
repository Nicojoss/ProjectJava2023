package be.jossart.windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import be.jossart.pojo.Booking;
import be.jossart.pojo.Copy;
import be.jossart.pojo.Loan;
import be.jossart.pojo.Player;

public class ReturnGamePage extends JFrame {
    private static final long serialVersionUID = -412457287625258396L;
    private JPanel contentPane;
    private JLabel titleLabel = new JLabel("Return Game");
    private JButton returnButton = new JButton("Return Game");
    private JComboBox<Loan> loanComboBox = new JComboBox<>();
    JLabel lb_error = new JLabel("");

    public ReturnGamePage(Player player, List<Loan> myLoans) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        titleLabel.setBounds(10, 69, 86, 13);

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel);
        
        lb_error.setForeground(Color.RED);
		lb_error.setHorizontalAlignment(SwingConstants.CENTER);
		lb_error.setBounds(10, 161, 414, 14);
		getContentPane().add(lb_error);
		
        if(!myLoans.isEmpty()) {
        	for (Loan loan : myLoans) {
            	loanComboBox.addItem(loan);
            }
        }else {
        	lb_error.setText("Your list of borrowings is empty!");
        }
        loanComboBox.setBounds(80, 87, 346, 21);
        contentPane.add(loanComboBox);
        returnButton.setBounds(80, 129, 172, 21);

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Loan selectedLoan = (Loan) loanComboBox.getSelectedItem();
                if (selectedLoan != null) {
                    selectedLoan.setOngoing(false);
                    selectedLoan.setBorrower(player);
                    if(selectedLoan.UpdateLoan(selectedLoan)) {
                    	selectedLoan.getCopy().setAvailable(true);
                    	if(Copy.Update(selectedLoan.getCopy())){
                    		Booking booking = Booking.Find(selectedLoan.getCopy().getVideoGame().getId_videogame());
                        	
                        	if(booking != null) {
                        		int creditCost = Copy.CalculateCreditCost(selectedLoan.getCopy(), booking.getNbrWeeksRent());
                        		LocalDate startDate = LocalDate.now();
                        		LocalDate EndDate = startDate.plusWeeks(booking.getNbrWeeksRent());
                        		
                        		if (booking.getPlayer().getCredit() >= creditCost) {
                    				// Decrease credits to borrower
                        			booking.getPlayer().setCredit(booking.getPlayer().getCredit() - creditCost);
                        			booking.getPlayer().UpdateCredit(booking.getPlayer());
                    				
                        			selectedLoan.getCopy().setAvailable(false);
                        			selectedLoan.getCopy().UpdateAvailable(selectedLoan.getCopy());

                    				
                    				// Add credits to lender
                        			selectedLoan.getCopy().getOwner().setCredit(selectedLoan.getCopy().getOwner().getCredit() + creditCost);
                        			selectedLoan.getCopy().getOwner().UpdateCredit(selectedLoan.getCopy().getOwner());

                    				// Add loan
                    				Loan loan = new Loan(startDate, EndDate, booking.getPlayer(), selectedLoan.getCopy());
                    				loan.setOngoing(true);
                    				if(Loan.AddLoan(loan)) {
                    					Booking.Delete(booking);
                    				}
                    			}
                        	}
                    		MyBorrowingsPage myBorrowingsPage = new MyBorrowingsPage(player);
                            myBorrowingsPage.setVisible(true);
                            dispose();
                    	}else {
                    		lb_error.setText("Error while updating the copy");
                    	}
                    	lb_error.setText("Error while updating the loan");
                    }  
                }
            }
        });
        contentPane.add(returnButton);

        JButton backButton = new JButton("Back to My Borrowings Page");
        backButton.setBounds(10, 10, 292, 21);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyBorrowingsPage myBorrowingsPage = new MyBorrowingsPage(player);
                myBorrowingsPage.setVisible(true);
                dispose();
            }
        });
        contentPane.add(backButton);
    }
}
