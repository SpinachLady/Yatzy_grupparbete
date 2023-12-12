import java.util.Arrays;

public class AdvancedCategory extends Category {

    public AdvancedCategory(String name) {
        super(name);
    }

    @Override
    public int getScore(int[] diceScores) {
        int score = 0;
        Arrays.sort(diceScores);
        if (name.equals("Ett par")) {
            for (int i = diceScores.length - 1; i > 0; i--) {
                if (diceScores[i] == diceScores[i - 1]) {
                    score = diceScores[i] * 2;
                    break;
                }
            }
        }
        if (name.equals("Två par")) {
            int count = 0;
            for (int i = diceScores.length - 1; i > 0; i--) {
                if (diceScores[i] == diceScores[i - 1]) {
                    count++;
                    score = score + (diceScores[i] * 2);
                }
            }
            if (count != 2) {
                score = 0;
            }

        }
        if (name.equals("Tretal")) {
            for (int i = diceScores.length - 1; i > 0; i--) {
                if (diceScores[i] == diceScores[i - 1] && diceScores[i] == diceScores[i - 2]) {
                    score = diceScores[i] * 3;
                    break;
                }
            }
        }
        if (name.equals("Fyrtal")) {
            for (int i = diceScores.length - 1; i > 0; i--) {
                if (diceScores[i] == diceScores[i - 1] && diceScores[i] == diceScores[i - 2] && diceScores[i] == diceScores[i - 3]) {
                    score = diceScores[i] * 4;
                    break;
                }
            }
        }
        if (name.equals("Kåk")) {
            boolean hasPair = false;
            boolean hasThree = false;
            for (int i = diceScores.length - 1; i > 0; i--) {
                if (diceScores[i] == diceScores[i - 1] && diceScores[i] == diceScores[i - 2]) {
                    score = score + (diceScores[i] * 3);
                    hasThree = true;

                } else if (diceScores[i] == diceScores[i - 1]) {
                    score = score + (diceScores[i] * 2);
                    hasPair = true;
                }
            }
            if (!hasThree || !hasPair) {
                score = 0;
            }
        }
        if (name.contains("stege")) {
            int i = 0;
            if (name.contains("Stor")) {
                i = 1;
            }
            int j = i + 5;
            while (i < j) {
                if (diceScores[i] != i + 1) {
                    score = 0;
                    break;
                } else {
                    score = score + diceScores[i];
                    i++;
                }
            }
        }
        if (name.equals("Chans")) {
            for (int diceScore : diceScores) {
                score = score + diceScore;
            }
        }
        if (name.equals("Yatzy")) {
            for (int i = diceScores.length - 1; i > 0; i--) {
                if (diceScores[0] != diceScores[i]) {
                    score = 0;
                    break;
                }
                score = 50;
            }
        } else {
            System.out.println("unexpected error");
            //System.exit(0);
        }
        return score;
    }


}
