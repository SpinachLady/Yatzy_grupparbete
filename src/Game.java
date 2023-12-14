import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Game extends JFrame implements ActionListener {

    private final Color black = Color.BLACK;
    private final Color notChosenCategory = new Color(255, 255, 233);
    private final Color chosenCategory = new Color(255, 242, 198);
    private final Color resultPanelColor = new Color(204, 229, 255);
    private final LineBorder totalScoreBorder = new LineBorder(Color.blue, 1);
    private final LineBorder selectedBorder = new LineBorder(black, 2);
    private ImageIcon dice1_image = new ImageIcon("src/dice_images/dice1.jpg");
    private ImageIcon dice2_image = new ImageIcon("src/dice_images/dice2.jpg");
    private ImageIcon dice3_image = new ImageIcon("src/dice_images/dice3.jpg");
    private ImageIcon dice4_image = new ImageIcon("src/dice_images/dice4.jpg");
    private ImageIcon dice5_image = new ImageIcon("src/dice_images/dice5.jpg");
    private ImageIcon dice6_image = new ImageIcon("src/dice_images/dice6.jpg");

    private ArrayList<ImageIcon> dice_images = new ArrayList<>();
    private ArrayList<JButton> dices = new ArrayList<>();
    private ArrayList<JButton> selectedDices = new ArrayList<>();
    private JButton dice1 = new JButton();
    private JButton dice2 = new JButton();
    private JButton dice3 = new JButton();
    private JButton dice4 = new JButton();
    private JButton dice5 = new JButton();
    private final JPanel categoryPanel = new JPanel(new GridLayout(9, 2, 5, 5));
    private final JPanel resultPanel = new JPanel(new BorderLayout());
    private final JLabel resultLabel = new JLabel("    " + "Totalt");
    private JLabel totalScoreLabel = new JLabel("      " + "0");
    private final JButton rollDiceButton = new JButton("Kasta");
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel dicePanel = new JPanel();
    private Category ettor = new SimpleCategory("Ettor", 1);
    private Category tvåor = new SimpleCategory("Tvåor", 2);
    private Category treor = new SimpleCategory("Treor", 3);
    private Category fyror = new SimpleCategory("Fyror", 4);
    private Category femmor = new SimpleCategory("Femmor", 5);
    private Category sexor = new SimpleCategory("Sexor", 6);
    private Category ettPar = new AdvancedCategory("Ett par");
    private Category tvåPar = new AdvancedCategory("Två par");
    private Category tretal = new AdvancedCategory("Tretal");
    private Category fyrtal = new AdvancedCategory("Fyrtal");
    private Category litenStege = new AdvancedCategory("Liten stege");
    private Category storStege = new AdvancedCategory("Stor stege");
    private Category kåk = new AdvancedCategory("Kåk");
    private Category chans = new AdvancedCategory("Chans");
    private Category yatzy = new AdvancedCategory("Yatzy");
    private ArrayList<Category> allCategories = new ArrayList<>();
    private ArrayList<Integer> diceResults = new ArrayList<>();
    private int movesCompleted;
    private int score;
    private int totalScore = 0;
    private int totalPlayedCategories = 0;
    private String stringScore;
    private boolean categoryChosen = false;
    private boolean inChoosingMode = false;
    Category[] categories = {ettor, tvåor, treor, fyror, femmor, sexor, ettPar, tvåPar, tretal,
            fyrtal, litenStege, storStege, kåk, chans, yatzy};

    JButton rulesButton = new JButton("Rules");
    JButton startTheNewGame = new JButton("Start the Game");
    JFrame firstPageFrame = new JFrame("Yatzy");
    JPanel firstPagePanel = new JPanel();
    JFrame rulesFrame = new JFrame("Yatzy Rules");
    JPanel rulesPagePanel = new JPanel(new BorderLayout());

    public Game(){
        firstPageFrame.setSize(300, 200);
        firstPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        firstPageFrame.add(firstPagePanel);
        firstPageFrame.setVisible(true);

        firstPagePanel.add(rulesButton);
        firstPagePanel.add(startTheNewGame);

        rulesButton.addActionListener(this);
        startTheNewGame.addActionListener(this);
    }

    public void rulesPage(){
        rulesFrame.setSize(1050, 500);
        rulesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        firstPageFrame.setVisible(false);
        rulesFrame.setVisible(true);
        rulesFrame.add(rulesPagePanel);

        JTextArea rulesText = new JTextArea();
        rulesText.setEditable(false);
        Font textAreaFont = new Font("Arial", Font.PLAIN, 20);
        rulesText.setFont(textAreaFont);
        try (BufferedReader reader = new BufferedReader(new FileReader("src/rules.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rulesText.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle file reading errors
        }
        JScrollPane rulesScrollPane = new JScrollPane(rulesText);

        startTheNewGame.addActionListener(this);

        rulesPagePanel.add(rulesScrollPane, BorderLayout.CENTER);
        rulesPagePanel.add(startTheNewGame, BorderLayout.SOUTH);
    }

    public void StartTheGame() {
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

        for (Category category : categories) {
            category.getCategoryPanel().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!category.isChosen && inChoosingMode) {
                        super.mouseClicked(e);
                        score = category.getScore(diceResults);
                        stringScore = String.valueOf(score);
                        totalScore = totalScore + score;
                        category.setCategoryScoreLabel(stringScore);
                        category.setThisCategoryScore(score);
                        category.isChosen = true;
                        category.getCategoryPanel().setBackground(chosenCategory);
                        changeDiceEnabledStatus();
                        while (!diceResults.isEmpty()) {
                            diceResults.remove(0);
                        }
                        categoryChosen = true;
                        totalPlayedCategories++;
                        if (totalPlayedCategories == 15) {
                            finishGame();
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!category.isChosen && inChoosingMode) {
                        super.mouseEntered(e);
                        score = category.getScore(diceResults);
                        stringScore = String.valueOf(score);
                        category.setCategoryScoreLabel(stringScore);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!category.isChosen && inChoosingMode) {
                        super.mouseExited(e);
                        category.setCategoryScoreLabel("");
                    }
                }
            });
        }
        createCategoryPanel();
        // Konfigurera huvudfönstret
        setTitle("Yatzy Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstPageFrame.setVisible(false);
        rulesFrame.setVisible(false);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        selectAllDices();

    }

    private void createCategoryPanel() {
        for (Category category : categories) {
            category.getCategoryPanel().setBackground(notChosenCategory);
            categoryPanel.add(category.getCategoryPanel());

        }
        resultLabel.setPreferredSize(new Dimension(100, 40));
        totalScoreLabel.setPreferredSize(new Dimension(50, 40));
        totalScoreLabel.setBorder(totalScoreBorder);
        resultPanel.add(resultLabel, BorderLayout.WEST);
        resultPanel.add(totalScoreLabel, BorderLayout.EAST);
        resultPanel.setBackground(resultPanelColor);
        categoryPanel.add(resultPanel);
    }

    public void rollDices() {
        categoryChosen = false;
        for (JButton dice : selectedDices) {
            dice.setIcon(dice_images.get(getRandomInt()));
        }
        movesCompleted++;
        if (movesCompleted == 3) {
            letUserChooseCategory();
        }
    }

    public void letUserChooseCategory() {
        if (!categoryChosen) {
            inChoosingMode = true;
            movesCompleted = 0;
            for (JButton dice : dices) {
                int count2 = 1;
                for (ImageIcon image : dice_images) {
                    if (dice.getIcon() == image) {
                        diceResults.add(count2);
                    }
                    count2++;
                }
            }
            changeDiceEnabledStatus();
        }

    }

    public void changeDiceEnabledStatus() {
        if (rollDiceButton.isEnabled()) {
            rollDiceButton.setEnabled(false);
        } else {
            rollDiceButton.setEnabled(true);
            selectAllDices();
            inChoosingMode = false;
            totalScoreLabel.setText("      " + totalScore);
        }
    }

    public void selectAllDices() {
        for (JButton dice : dices) {
            dice.setBorderPainted(true);
            selectedDices.add(dice);
        }
    }

    public int getRandomInt() {
        int randomInt = (int) (Math.random() * 6);
        return randomInt;
    }

    public void addScoreToList() {
        String result = "Spelat den " + LocalDate.now() + "\nResultat: " + totalScore + " poäng";
        //lägg till result i fil

    }

    public void finishGame() {
        int addToList = JOptionPane.showConfirmDialog(null, "Bra spelat! Ditt resultat blev " + totalScore + "\nVill du spara ditt resultat i topplistan?");
        if (addToList == JOptionPane.YES_OPTION) {
            addScoreToList();
            System.exit(1);
        } else if (addToList == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            System.exit(2);
        }

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

        if (e.getSource() == rulesButton){
            rulesPage();
        }

        if (e.getSource() == startTheNewGame){
            StartTheGame();
        }
    }
}