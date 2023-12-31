import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class AdvancedCategory extends Category {

    public AdvancedCategory(String name) {
        super(name);
    }

    @Override
    public int getScore(ArrayList<Integer> diceScores) {
        int score = 0;
        Collections.sort(diceScores);
        if (name.equals("Ett par")) {
            for (int i = diceScores.size() - 1; i > 0; i--) {
                if (Objects.equals(diceScores.get(i), diceScores.get(i - 1))) {
                    score = diceScores.get(i) * 2;
                    break;
                }
            }
        }
        else if (name.equals("Två par")) {
            int count = 0;
            for (int i = diceScores.size() - 1; i > 0; i--) {
                if (Objects.equals(diceScores.get(i), diceScores.get(i - 1))) {
                    count++;
                    score = score + (diceScores.get(i) * 2);
                }
            }
            if (count < 2) {
                score = 0;
            }
        }
        else if (name.equals("Tretal")) {
            for (int i = diceScores.size() - 1; i > 0; i--) {
                if (Objects.equals(diceScores.get(i), diceScores.get(i - 1)) && Objects.equals(diceScores.get(i), diceScores.get(i - 2))) {
                    score = diceScores.get(i) * 3;
                    break;
                }
            }
        }
        else if (name.equals("Fyrtal")) {
            for (int i = diceScores.size() - 1; i > 2; i--) {
                if (Objects.equals(diceScores.get(i), diceScores.get(i - 1)) && Objects.equals(diceScores.get(i), diceScores.get(i - 2)) && Objects.equals(diceScores.get(i), diceScores.get(i - 3))) {
                    score = diceScores.get(i) * 4;
                    break;
                }
            }
        }
        else if (name.equals("Kåk")) {
            boolean hasPair = false;
            boolean hasThree = false;
            int pairPoints = 0;
            int threePoints = 0;

            int countFrequency1 = Collections.frequency(diceScores, diceScores.get(diceScores.size()-1));
            if (countFrequency1 == 2) {
                hasPair = true;
                pairPoints = diceScores.get(diceScores.size()-1) * 2;
            }
            else if (countFrequency1 == 3) {
                hasThree = true;
                threePoints = diceScores.get(diceScores.size()-1) * 3;
            }

            int countFrequency2 = Collections.frequency(diceScores, diceScores.get(0));
            if (countFrequency2 == 2) {
                hasPair = true;
                pairPoints = diceScores.get(0) * 2;
            }
            else if (countFrequency2 == 3) {
                hasThree = true;
                threePoints = diceScores.get(0) * 3;
            }
            if (hasPair && hasThree) {
                score = pairPoints + threePoints;
            }



        }
        else if (name.contains("stege")) {
            int start = 1;
            if (name.contains("Stor")) {
                start = 2;
            }
            for (int i = 0; i < diceScores.size(); i++) {
                if (diceScores.get(i) != start + i) {
                    score = 0;
                    break;
                } else {
                    score = score + diceScores.get(i);
                }
            }
        }
        else if (name.equals("Chans")) {
            for (int diceScore : diceScores) {
                score = score + diceScore;
            }
        }
        else if (name.equals("Yatzy")) {
            for (int i = diceScores.size() - 1; i > 0; i--) {
                if (!Objects.equals(diceScores.get(i), diceScores.get(i-1))) {
                    score = 0;
                    break;
                }
                score = 50;
            }
        } else {
            System.out.println("unexpected error");
            System.exit(0);
        }
        return score;
    }


}
