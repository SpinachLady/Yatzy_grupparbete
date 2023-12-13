import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public abstract class Category {
    protected String name;
    protected JPanel categoryPanel = new JPanel(new BorderLayout());
    protected JLabel categoryLabel;
    protected JLabel categoryScore = new JLabel();
    protected int thisCategoryScore = 0;
    private LineBorder categoryScoreBorder = new LineBorder(Color.red, 1);


    public Category(String name) {
        this.name = name;
        categoryLabel = new JLabel("    " + name);
        setCategoryPanel();
    }

    private void setCategoryPanel() {
        categoryLabel.setPreferredSize(new Dimension(100, 40));
        categoryScore.setPreferredSize(new Dimension(50,40));
        categoryScore.setBorder(categoryScoreBorder);
        categoryPanel.add(categoryLabel, BorderLayout.WEST);
        categoryPanel.add(categoryScore, BorderLayout.EAST);
    }
    public JPanel getCategoryPanel () {
        return categoryPanel;
    }

    public void setCategoryScoreLabel (String score) {
        categoryScore.setText(score);
    }
    public void setThisCategoryScore(int score) {
        thisCategoryScore = score;
    }
    public int getThisCategoryScore () {
        return thisCategoryScore;
    }
    public abstract int getScore(ArrayList<Integer> diceScores);
}
