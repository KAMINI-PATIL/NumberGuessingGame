import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        int roundsPlayed = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            roundsPlayed++;
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int attemptsLeft = 5; // Limit to 5 attempts per round
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + roundsPlayed + " starts!");
            System.out.println("I have generated a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts to guess it.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number!");
                    totalScore += attemptsLeft; // Score based on remaining attempts
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("No attempts left! The correct number was: " + numberToGuess);
                }
            }

            if (guessedCorrectly) {
                System.out.println("You finished this round with " + attemptsLeft + " attempts remaining.");
            } else {
                System.out.println("Better luck next time!");
            }

            System.out.print("Would you like to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("\nGame over! You played " + roundsPlayed + " round(s).");
        System.out.println("Your total score is: " + totalScore);
        System.out.println("Thank you for playing!");

        scanner.close();
    }
}
