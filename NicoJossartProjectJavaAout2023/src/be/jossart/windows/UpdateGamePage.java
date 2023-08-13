package be.jossart.windows;

import java.awt.Color;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import be.jossart.pojo.VideoGame;

public class UpdateGamePage extends JFrame {

    private static final long serialVersionUID = -3700180410884782607L;
    private JPanel contentPane;
    JButton backButton = new JButton("Back To Admin Page");
    private JLabel lbError;
    
    public UpdateGamePage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 516, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("This is the available games");
        titleLabel.setBounds(10, 12, 269, 13);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel);
        
        lbError = new JLabel("");
        lbError.setBounds(30, 175, 415, 13);
        lbError.setForeground(Color.RED);
        contentPane.add(lbError);

        List<VideoGame> videoGames = VideoGame.FindAll();
        DefaultListModel<VideoGame> gameListModel = new DefaultListModel<>();
        for (VideoGame game : videoGames) {
            gameListModel.addElement(game);
        }

        JList<VideoGame> gameList = new JList<>(gameListModel);
        JScrollPane scrollPane = new JScrollPane(gameList);
        scrollPane.setBounds(20, 35, 425, 130);
        contentPane.add(scrollPane);

        JLabel updateLabel = new JLabel("Update selected game (1-5):");
        JTextField updateTextField = new JTextField(10);
        JButton updateButton = new JButton("Update Game");

        JPanel updatePanel = new JPanel();
        updatePanel.setBounds(10, 197, 452, 31);
        updatePanel.add(updateLabel);
        updatePanel.add(updateTextField);
        updatePanel.add(updateButton);
        contentPane.add(updatePanel);

        updateButton.addActionListener(e -> {
            int selectedValue;
            try {
                selectedValue = Integer.parseInt(updateTextField.getText());
            } catch (NumberFormatException ex) {
            	lbError.setText("Please enter a number between 1 and 5");
                return;
            }

            if (selectedValue >= 1 && selectedValue <= 5) {
                VideoGame selectedGame = gameList.getSelectedValue();
                if (selectedGame != null) {
                    selectedGame.setCreditCost(selectedValue);
                    if(VideoGame.UpdateCredit(selectedGame)) {
                    	UpdateGamePage updateGame = new UpdateGamePage();
                        updateGame.setVisible(true);
                        dispose();
                    }
                }
            } else {
            	lbError.setText("Please enter a number between 1 and 5");
            }
        });
        backButton.setBounds(152, 238, 161, 21);

        backButton.addActionListener(e -> {
            AdministratorPage adminPage = new AdministratorPage();
            adminPage.setVisible(true);
            dispose();
        });
        contentPane.add(backButton);
    }
}