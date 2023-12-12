public class SimpleCategory extends Category {
    private int categoryNumber;

    public SimpleCategory(String name, int categoryNumber) {
        super(name);
        this.categoryNumber = categoryNumber;
    }

    @Override
    public int getScore (int [] diceScores) {
        int score = 0;
        for (int diceScore : diceScores) {
            if (diceScore == categoryNumber) {
                score = score + categoryNumber;
            }
        }
        return score;
    }

}
