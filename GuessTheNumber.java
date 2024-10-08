import javax.swing.*;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        int totalScore = 0;
        boolean playAgain;

        do {
            int randomNumber = new Random().nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10; // limit the number of attempts
            boolean guessedCorrectly = false;

            JOptionPane.showMessageDialog(null,
                    "Welcome to Guess the Number!\nTry to guess the number between 1 and 100.");

            while (attempts < maxAttempts && !guessedCorrectly) {
                String input = JOptionPane
                        .showInputDialog("Enter your guess (Attempt " + (attempts + 1) + " of " + maxAttempts + "):");

                // Validate input
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Game exited.");
                    return;
                }

                int userGuess;
                try {
                    userGuess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    continue;
                }

                attempts++;

                if (userGuess < 1 || userGuess > 100) {
                    JOptionPane.showMessageDialog(null, "Please guess a number between 1 and 100.");
                } else if (userGuess < randomNumber) {
                    JOptionPane.showMessageDialog(null, "Higher! Try again.");
                } else if (userGuess > randomNumber) {
                    JOptionPane.showMessageDialog(null, "Lower! Try again.");
                } else {
                    guessedCorrectly = true;
                    int score = calculateScore(attempts);
                    totalScore += score;
                    JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the number in " + attempts
                            + " attempts.\nScore for this round: " + score + "\nTotal Score: " + totalScore);
                }
            }

            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null,
                        "Sorry! You've used all your attempts. The number was " + randomNumber + ".");
            }

            playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        } while (playAgain);

        JOptionPane.showMessageDialog(null, "Thank you for playing! Your final score is: " + totalScore);
    }

    private static int calculateScore(int attempts) {
        // Score calculation: 10 points for correct guess, minus attempts
        return Math.max(0, 10 - (attempts - 1)); // 10 points for the first attempt, 0 for more than 10 attempts
    }
}
