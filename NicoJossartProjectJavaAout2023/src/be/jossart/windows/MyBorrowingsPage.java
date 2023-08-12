package be.jossart.windows;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import be.jossart.pojo.Loan;
import be.jossart.pojo.Player;

public class MyBorrowingsPage extends JFrame {

	private static final long serialVersionUID = 8160375200048296262L;
	private JPanel contentPane;

	public MyBorrowingsPage(Player player) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 532, 489);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        List<Loan> myLoans = Loan.FindMyBorrowings(player);

        String[] columnNames = {"Game", "Start Date", "End Date", "DaysLate", "Credit penalty", "You rent the game"};
        Object[][] data = new Object[myLoans.size()][6];
        
        for (int i = 0; i < myLoans.size(); i++) {
            Loan loan = myLoans.get(i);
            
            data[i][0] = loan.getCopy().getVideoGame().getName();
            data[i][1] = loan.getStartDate();
            data[i][2] = loan.getEndDate();
            
            long daysLate = ChronoUnit.DAYS.between(loan.getEndDate(), LocalDate.now());
            data[i][3] = daysLate > 0 ? daysLate : "0";
            data[i][4] = daysLate > 0 ? (loan.getCopy().getVideoGame().getCreditCost()) * daysLate : "0";
            data[i][5] = loan.isOngoing();
            
            if(loan.isOngoing()) {
            	if(daysLate > 0) {
                	// Decrease the player's credits by the value of the game
                	player.setCredit(player.getCredit() - loan.getCopy().getVideoGame().getCreditCost());
                	player.UpdateCredit(player);
                	
                	// Increase game owner credits by game value
                	loan.getCopy().getOwner().setCredit(loan.getCopy().getOwner().getCredit() + loan.getCopy().getVideoGame().getCreditCost());
                	loan.getCopy().getOwner().UpdateCredit(loan.getCopy().getOwner());
                }
            }
            
        }
        contentPane.setLayout(null);

        JTable borrowingsTable = new JTable(data, columnNames);
        borrowingsTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(borrowingsTable);
        scrollPane.setBounds(23, 31, 477, 371);
        contentPane.add(scrollPane);

        JButton backButton = new JButton("Return to Player Page");
        backButton.setBounds(0, 0, 261, 21);
        backButton.addActionListener(e -> {
            PlayerPage playerPage = new PlayerPage(player);
            playerPage.setVisible(true);
            dispose();
        });
        contentPane.add(backButton);
        
        JButton returnGameButton = new JButton("Return selected game");
        returnGameButton.setBounds(10, 412, 261, 21);
        returnGameButton.addActionListener(e -> {
                ReturnGamePage returnPage = new ReturnGamePage(player, myLoans);
                returnPage.setVisible(true);
                dispose();
        });
        contentPane.add(returnGameButton);
    }
}
