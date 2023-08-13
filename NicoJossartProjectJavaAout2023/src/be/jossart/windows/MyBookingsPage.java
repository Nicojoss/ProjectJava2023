package be.jossart.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jossart.pojo.Booking;
import be.jossart.pojo.Player;

public class MyBookingsPage extends JFrame {

	private static final long serialVersionUID = 2699718386699281761L;
	private JPanel contentPane;
	JLabel lblTitle = new JLabel("Your bookings");
	JButton backButton = new JButton("Return PlayerPage");
	private JTable table;
	List<Booking> bookingList = new ArrayList<>();

	public MyBookingsPage(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblTitle.setBounds(20, 41, 555, 20);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);
		
		backButton.setBounds(10, 10, 168, 21);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        PlayerPage playerPage = new PlayerPage(player);
		        playerPage.setVisible(true);
		        dispose();
		    }
		});
		contentPane.setLayout(null);
		getContentPane().add(backButton);
		
		table = new JTable();
		table.setBounds(30, 71, 634, 398);
		contentPane.add(table);
		
		bookingList = Booking.FindAll();
		updateTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 71, 634, 398);
		contentPane.add(scrollPane);
	}
	
	private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Date booking", "game", "Number of weeks to rent the games"});
        
        for (Booking booking : bookingList) {
            model.addRow(new Object[]{booking.getBookingDate(), booking.getGame().getName(), booking.getNbrWeeksRent()});
        }

        table.setModel(model);
    }

}
