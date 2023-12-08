import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    private ArrayList <JButton> dices = new ArrayList<>();
    private JButton dice1 = new JButton("tärning1");
    private JButton dice2 = new JButton("tärning2");
    private JButton dice3 = new JButton("tärning3");
    private JButton dice4 = new JButton("tärning4");
    private JButton dice5 = new JButton("tärning5");
    JPanel categoryPanel = new JPanel(new GridLayout(9, 2, 5, 5));
    JButton rollDiceButton = new JButton("Kasta");
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel dicePanel = new JPanel();
    public GUI() {
        dices.add(dice1);dices.add(dice2);dices.add(dice3);dices.add(dice4);dices.add(dice5);
        for (JButton dice : dices) {
            dice.setPreferredSize(new Dimension(100, 100));
            dicePanel.add(dice);
        }
        dicePanel.add(rollDiceButton);

        // Skapa kastknappen
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lägg till logik för att kasta tärningarna här
                // Uppdatera tärningsbilderna och kategorierna efter kastet
            }
        });
        mainPanel.add(dicePanel, BorderLayout.SOUTH);
        mainPanel.add(categoryPanel, BorderLayout.CENTER);

        createCategoryPanel();
        // Konfigurera huvudfönstret
        setTitle("Yatzy Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // Centrera fönstret på skärmen
        setVisible(true);
    }
    private void createCategoryPanel() {
        String[] categories = {
                "Ettor", "Tvåor", "Treor", "Fyror", "Femmor",
                "Sexor", "Bonus", "Ett par", "Två par",
                "Tretal", "Fyrtal", "Liten stege", "Stor stege",
                "Kåk", "Chans", "Yatzy"
        };

        for (String category : categories) {
            JLabel categoryLabel = new JLabel(category);
            JButton categoryButton = new JButton("Välj");

            JPanel categoryEntryPanel = new JPanel(new BorderLayout());
            categoryEntryPanel.add(categoryLabel, BorderLayout.CENTER);
            categoryEntryPanel.add(categoryButton, BorderLayout.EAST);

            categoryPanel.add(categoryEntryPanel);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}