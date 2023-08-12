package be.jossart.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import be.jossart.pojo.Loan;
import be.jossart.pojo.Player;

public class ReturnGamePage extends JFrame {
	private static final long serialVersionUID = -412457287625258396L;
	private JPanel contentPane;
    private JLabel titleLabel = new JLabel("Return Game");
    private JButton returnButton = new JButton("Return Game");
    private JComboBox<Loan> loanComboBox;

	public ReturnGamePage(Player player, List<Loan> myLoans) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel);

        loanComboBox = new JComboBox<>(myLoans.toArray(new Loan[0]));
        contentPane.add(loanComboBox);

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Loan selectedLoan = (Loan) loanComboBox.getSelectedItem();
                if (selectedLoan != null) {
                	selectedLoan.setOngoing(false);
                	selectedLoan.setBorrower(player);
                	selectedLoan.UpdateLoan(selectedLoan);
                	
                	MyBorrowingsPage myBorrowingsPage = new MyBorrowingsPage(player);
                    myBorrowingsPage.setVisible(true);
                    dispose();
                }
            }
        });
        contentPane.add(returnButton);
	}
}
