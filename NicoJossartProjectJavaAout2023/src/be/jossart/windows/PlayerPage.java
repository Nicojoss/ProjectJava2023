package be.jossart.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jossart.pojo.Player;
import be.jossart.pojo.VideoGame;

public class PlayerPage extends JFrame {
	private static final long serialVersionUID = -8556199685470371058L;
	private JPanel contentPane;
	private JTable table;
	List<VideoGame> videoGameList = new ArrayList<>();
	JButton backButton = new JButton("Return HomePage");
	JLabel lblTitle = new JLabel("Welcome to the site here are the games you can add a copy or make a reservation");
	JButton addCopyButton = new JButton("Add Copy");
	JButton rentCopyButton = new JButton("See available copy");
	JButton myBorrowingsButton = new JButton("My Borrowings");
	JButton myBookingsButton = new JButton("My Bookings");
	LocalDate today = LocalDate.now();
	JLabel birthdayLabel = new JLabel("");

	public PlayerPage(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		backButton.setBounds(10, 10, 168, 21);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Home homePage = new Home();
		        homePage.setVisible(true);
		        dispose();
		    }
		});
		contentPane.setLayout(null);
		getContentPane().add(backButton);
		
		lblTitle.setBounds(20, 41, 555, 20);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);
		
		table = new JTable();
		table.setBounds(30, 71, 634, 398);
		contentPane.add(table);
		
		videoGameList = VideoGame.FindAll();
		updateTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 71, 634, 398);
		contentPane.add(scrollPane);
		
		addCopyButton.setBounds(20, 480, 100, 25);
        addCopyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddCopyPage addCopyPage = new AddCopyPage(videoGameList, player);
            	addCopyPage.setVisible(true);
                dispose();
            }
        });
        contentPane.add(addCopyButton);
        
        rentCopyButton.setBounds(140, 480, 150, 25);
        rentCopyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RentCopyPage rentCopyPage = new RentCopyPage(player);
                rentCopyPage.setVisible(true);
                dispose();
            }
        });
        contentPane.add(rentCopyButton);
        
        myBorrowingsButton.setBounds(342, 480, 121, 25);
        myBorrowingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyBorrowingsPage myBorrowingsPage = new MyBorrowingsPage(player);
                myBorrowingsPage.setVisible(true);
                dispose();
            }
        });
        contentPane.add(myBorrowingsButton);
        
        myBookingsButton.setBounds(482, 480, 121, 25);
        myBookingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyBookingsPage myBorrowingsPage = new MyBookingsPage(player);
                myBorrowingsPage.setVisible(true);
                dispose();
            }
        });
        contentPane.add(myBookingsButton);
        
        birthdayLabel.setForeground(Color.GREEN);
        birthdayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        birthdayLabel.setBounds(188, 10, 546, 20);
        contentPane.add(birthdayLabel);
        getContentPane().add(birthdayLabel);
        
        LocalDate lastBirthdayDate = player.getLastBirthdayDate();
        if(lastBirthdayDate == null || !lastBirthdayDate.isEqual(today)) {
        	if (player.getDateRegistration().getDayOfMonth() == today.getDayOfMonth() && player.getDateRegistration().getMonth() == today.getMonth()) {
        		player.setLastBirthdayDate(today);
            	Player.UpdateLastBirthdayDate(player);
            	
            	player.setCredit(player.getCredit() + 2);
            	player.UpdateCredit(player);
            	birthdayLabel.setText("Happy Birthday (register date)! You have received 2 credits as a gift!");
        	}   
        }
		
	}
	
	private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Nom", "Console", "Crédits"});

        for (VideoGame game : videoGameList) {
            model.addRow(new Object[]{game.getName(), game.getConsole(), game.getCreditCost()});
        }

        table.setModel(model);
    }
}

