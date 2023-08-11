package be.jossart.windows;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.jossart.pojo.VideoGame;

public class PlayerPage extends JFrame {
	private static final long serialVersionUID = -8556199685470371058L;
	private JPanel contentPane;
	private JTable table;
	List<VideoGame> videoGameList = new ArrayList<>();
	JButton backButton = new JButton("Return HomePage");
	JLabel lblTitle = new JLabel("welcome to the player page here are the games available");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerPage frame = new PlayerPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PlayerPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		backButton.setBounds(10, 10, 146, 21);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Home homePage = new Home();
		        homePage.setVisible(true);
		        dispose();
		    }
		});
		contentPane.setLayout(null);
		getContentPane().add(backButton);
		
		lblTitle.setBounds(20, 41, 388, 20);
		lblTitle.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		contentPane.add(lblTitle);
		
		table = new JTable();
		table.setBounds(30, 71, 634, 398);
		contentPane.add(table);
		
		videoGameList = VideoGame.FindAll();
		updateTable();
		
	}
	private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nom");
        model.addColumn("Console");
        model.addColumn("Crédits");

        for (VideoGame game : videoGameList) {
            model.addRow(new Object[]{game.getName(), game.getConsole(), game.getCreditCost()});
        }

        table.setModel(model);
    }
}

