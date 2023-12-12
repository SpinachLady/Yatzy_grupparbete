import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public abstract class Category {
    protected String name;
    protected JPanel categoryPanel = new JPanel(new BorderLayout());
    protected JLabel categoryLabel;
    protected JLabel categoryScore = new JLabel();
    protected int thisCategoryScore = 0;

    public Category(String name) {
        this.name = name;
        categoryLabel = new JLabel(name);
        setCategoryPanel();
    }

    private void setCategoryPanel() {
        categoryLabel.setPreferredSize(new Dimension(100, 40));
        categoryScore.setPreferredSize(new Dimension(50,40));
        categoryPanel.add(categoryLabel, BorderLayout.WEST);
        categoryPanel.add(categoryScore, BorderLayout.EAST);
    }
    public JPanel getCategoryPanel () {
        return categoryPanel;
    }

    public void setCategoryScore (String score) {
        categoryScore.setText(score);
    }
    public void setThisCategoryScore(int score) {
        thisCategoryScore = score;
    }
    public abstract int getScore(int [] diceScores);
}
