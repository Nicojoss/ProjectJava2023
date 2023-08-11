package be.jossart.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import be.jossart.pojo.Copy;
import be.jossart.pojo.Player;
import be.jossart.pojo.VideoGame;

public class AddCopyPage extends JFrame {

	private static final long serialVersionUID = 7280997125810779247L;
	private JPanel contentPane;
	private JComboBox<VideoGame> gameComboBox = new JComboBox<>();
    private JButton addButton = new JButton("Add Copy");
    JLabel lblGameDetails = new JLabel("Game Details:");
    JTextArea txtGameDetails = new JTextArea();
    JButton backButton = new JButton("Return HomePage");

	public AddCopyPage(List<VideoGame> videoGameList, Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	    contentPane.setLayout(null);
		
	    backButton.setBounds(10, 10, 146, 21);
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        PlayerPage playerPage = new PlayerPage(player);
		        playerPage.setVisible(true);
		        dispose();
		    }
		});
		contentPane.setLayout(null);
		getContentPane().add(backButton);
		
	    lblGameDetails.setBounds(24, 24, 108, 13);
	    contentPane.add(lblGameDetails);

	    
	    txtGameDetails.setBounds(24, 90, 402, 22);
	    txtGameDetails.setEditable(false);
	    contentPane.add(txtGameDetails);
	    
		for (VideoGame game : videoGameList) {
            gameComboBox.addItem(game);
        }
        gameComboBox.setBounds(22, 47, 404, 21);
        contentPane.add(gameComboBox);
        addButton.setBounds(22, 116, 152, 21);
        
        gameComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VideoGame selectedGame = (VideoGame) gameComboBox.getSelectedItem();
                txtGameDetails.setText("Name: " + selectedGame.getName() + " Console: " + selectedGame.getConsole() + "Credit Cost: " + selectedGame.getCreditCost());
            }
        });

        contentPane.add(addButton);
        
        JLabel lblSelectedGame = new JLabel("Selected Game");
        lblSelectedGame.setBounds(22, 78, 108, 13);
        contentPane.add(lblSelectedGame);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VideoGame selectedGame = (VideoGame) gameComboBox.getSelectedItem();
                Copy copy = new Copy(selectedGame, player);
                if(copy.AddCopy(copy)) {
                	PlayerPage playerPage = new PlayerPage(player);
                	playerPage.setVisible(true);
                    dispose();
                }
				
                dispose();
            }
        });
	}
}
