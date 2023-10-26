package Managers;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TitleScreen class for the game.
 */
public class TitleScreen extends JPanel {
    GameManager gameManager;

    /**
     * Constructor for the TitleScreen class.
     * 
     * @param gameManager The game manager.
     */
    public TitleScreen(GameManager gameManager) {
        this.gameManager = gameManager;

        this.setBackground(Color.BLACK);

        this.init();
    }

    private void init() {
        // Set layout to GridBagLayout
        this.setLayout(new GridBagLayout());

        // Show title label
        JLabel titleLabel = new JLabel("COSMIC CONVERGENCE");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        // Add title label to panel with center constraints
        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = GridBagConstraints.CENTER;
        titleGbc.gridy = GridBagConstraints.CENTER - 1;
        titleGbc.insets = new Insets(0, 0, 20, 0);

        this.add(titleLabel, titleGbc);

        // Show start button and add action listener. 
        // make it in the center of the screen, and have it be customized and look nice.
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(150, 75));
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGameThread();
            }
        });
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Add start button to panel with center constraints
        GridBagConstraints startGbc = new GridBagConstraints();
        startGbc.gridx = GridBagConstraints.CENTER;
        startGbc.gridy = GridBagConstraints.CENTER;
        startGbc.insets = new Insets(0, 0, 20, 0);

        this.add(startButton, startGbc);

        // Show exit button and add action listener. 
        // make it in the center of the screen, and have it be customized and look nice.
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(150, 75));
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Add exit button to panel with center constraints
        GridBagConstraints exitGbc = new GridBagConstraints();
        exitGbc.gridx = GridBagConstraints.CENTER;
        exitGbc.gridy = GridBagConstraints.CENTER + 1;
        exitGbc.insets = new Insets(20, 0, 0, 0);

        this.add(exitButton, exitGbc);
    }

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        this.setVisible(false);

        this.gameManager.startGameThread();
    }
}
