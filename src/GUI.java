import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{
    private final Color black = Color.BLACK;
    private LineBorder selectedBorder = new LineBorder(black, 2);
    private ImageIcon dice1_image = new ImageIcon("src/dice_images/dice1.jpg");
    private ImageIcon dice2_image = new ImageIcon("src/dice_images/dice2.jpg");
    private ImageIcon dice3_image = new ImageIcon("src/dice_images/dice3.jpg");
    private ImageIcon dice4_image = new ImageIcon("src/dice_images/dice4.jpg");
    private ImageIcon dice5_image = new ImageIcon("src/dice_images/dice5.jpg");
    private ImageIcon dice6_image = new ImageIcon("src/dice_images/dice6.jpg");

    private ArrayList <ImageIcon> dice_images = new ArrayList<>();
    private ArrayList <JButton> dices = new ArrayList<>();
    private ArrayList<JButton> selectedDices = new ArrayList<>();
    private JButton dice1 = new JButton();
    private JButton dice2 = new JButton();
    private JButton dice3 = new JButton();
    private JButton dice4 = new JButton();
    private JButton dice5 = new JButton();
    private JPanel categoryPanel = new JPanel(new GridLayout(9, 2, 5, 5));
    private JButton rollDiceButton = new JButton("Kasta");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel dicePanel = new JPanel();
    private Category ettor = new Category("Ettor", 1);
    private Category tvåor = new Category("Tvåor", 2);
    private Category treor = new Category("Treor", 3);
    private Category fyror = new Category("Fyror", 4);
    private Category femmor = new Category("Femmor", 5);
    private Category sexor = new Category("Sexor", 6);
    private Category ettPar = new Category("Ett par");
    private Category tvåPar = new Category("Två par");
    private Category tretal = new Category("Tretal");
    private Category fyrtal = new Category("Fyrtal");
    private Category litenStege = new Category("Liten stege");
    private Category storStege = new Category("Stor stege");
    private Category kåk = new Category("Kåk");
    private Category chans = new Category("Chans");
    private Category yatzy = new Category("Yatzy");
    private ArrayList<Category> allCategories = new ArrayList<>();
    private int [] diceResults = {0, 0, 0, 0, 0};
    private int diceRolledTimes = 0;

    public GUI() {
        dices.add(dice1);dices.add(dice2);dices.add(dice3);dices.add(dice4);dices.add(dice5);
        allCategories.add(ettor);allCategories.add(tvåor);allCategories.add(treor);allCategories.add(fyror);
        allCategories.add(femmor);allCategories.add(sexor);allCategories.add(ettPar);allCategories.add(tvåPar);
        allCategories.add(tretal);allCategories.add(fyrtal);allCategories.add(litenStege);allCategories.add(storStege);
        allCategories.add(kåk);allCategories.add(chans);allCategories.add(yatzy);
        dice_images.add(dice1_image);dice_images.add(dice2_image);dice_images.add(dice3_image);
        dice_images.add(dice4_image);dice_images.add(dice5_image);dice_images.add(dice6_image);

        for (JButton dice : dices) {
            dice.setPreferredSize(new Dimension(100, 100));
            dice.setIcon(dice_images.get(0));
            dice.setBorder(selectedBorder);
            dice.setBorderPainted(false);
            dicePanel.add(dice);
            dice.addActionListener(this);
        }
        dicePanel.add(rollDiceButton);
        rollDiceButton.addActionListener(this);
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
        Category[] categories = {ettor, tvåor, treor, fyror, femmor, sexor, ettPar, tvåPar, tretal,
        fyrtal, litenStege, storStege, kåk, chans, yatzy};

        for (Category category : categories) {
            categoryPanel.addMouseListener(new MouseAdapter() {
                int score = category.getScore(diceResults);
                String stringScore = String.valueOf(score);
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    category.setCategoryScore(stringScore);
                    category.setThisCategoryScore(score);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    category.setCategoryScore(stringScore);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    category.setCategoryScore("");
                }
            });
            categoryPanel.add(category.getCategoryPanel());
        }
    }
    public void rollDices () {
        for (JButton dice : selectedDices) {
            dice.setIcon(dice_images.get(getRandomInt()));
        }
        diceRolledTimes++;

        if (diceRolledTimes == 3) {
            int count1 = 0;
            for (JButton dice : dices) {
                int count2 = 1;
                for (ImageIcon image : dice_images) {
                    if (dice.getIcon() == image) {
                        diceResults[count1] = count2;
                    }
                }
            }

        }
    }
    public int getRandomInt () {
        int randomInt = (int) (Math.random() * 6);
        return randomInt;
    }
    public int [] getDiceResults () {
        return diceResults;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton dice : dices) {
            if (e.getSource() == dice) {
                if (selectedDices.contains(dice)) {
                    selectedDices.remove(dice);
                    dice.setBorderPainted(false);
                } else {
                    selectedDices.add(dice);
                    dice.setBorderPainted(true);
                }
            }
        }
        if (e.getSource() == rollDiceButton) {
            rollDices();
            for (JButton dice : dices) {
                dice.setBorderPainted(false);
                selectedDices.remove(dice);
            }
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