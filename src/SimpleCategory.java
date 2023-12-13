import java.util.ArrayList;

public class SimpleCategory extends Category {
    private int categoryNumber;

    public SimpleCategory(String name, int categoryNumber) {
        super(name);
        this.categoryNumber = categoryNumber;
    }

    @Override
    public int getScore (ArrayList <Integer> diceScores) {
        int score = 0;
        for (int diceScore : diceScores) {
            if (diceScore == categoryNumber) {
                score = score + categoryNumber;
            }
        }
        return score;
    }

}
