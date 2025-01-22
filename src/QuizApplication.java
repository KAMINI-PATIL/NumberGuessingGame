import java.util.*;
import java.util.concurrent.*;

// Class to represent a single Quiz Question
class Question {
    String questionText;
    String[] options;
    int correctOption; // Index of the correct option (0-based)

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

// Main Quiz Application Class
public class QuizApplication {

    private static final int QUESTION_TIME_LIMIT = 10; // Time limit in seconds per question
    private List<Question> questions;
    private int score;
    private List<Boolean> results; // Tracks if answers are correct or incorrect

    public QuizApplication() {
        this.questions = new ArrayList<>();
        this.results = new ArrayList<>();
        this.score = 0;
        initializeQuestions();
    }

    // Initialize the quiz questions
    private void initializeQuestions() {
        questions.add(new Question("What is the capital of France?",
                new String[]{"1. Paris", "2. London", "3. Rome", "4. Berlin"}, 0));
        questions.add(new Question("What is 5 + 3?",
                new String[]{"1. 6", "2. 7", "3. 8", "4. 9"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"1. Earth", "2. Venus", "3. Mars", "4. Jupiter"}, 2));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?",
                new String[]{"1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. J.K. Rowling"}, 1));
        questions.add(new Question("What is the largest mammal?",
                new String[]{"1. Elephant", "2. Blue Whale", "3. Giraffe", "4. Polar Bear"}, 1));
    }

    // Display a question and handle user input with a timer
    private void askQuestion(Question question) {
        System.out.println("\n" + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice (1-4): ");
            return scanner.nextInt() - 1;
        });

        try {
            int userAnswer = future.get(QUESTION_TIME_LIMIT, TimeUnit.SECONDS);
            if (userAnswer == question.correctOption) {
                System.out.println("Correct!");
                score++;
                results.add(true);
            } else {
                System.out.println("Incorrect! The correct answer was: " + (question.correctOption + 1));
                results.add(false);
            }
        } catch (TimeoutException e) {
            System.out.println("Time's up! The correct answer was: " + (question.correctOption + 1));
            results.add(false);
        } catch (Exception e) {
            System.out.println("Invalid input! The correct answer was: " + (question.correctOption + 1));
            results.add(false);
        } finally {
            executor.shutdownNow();
        }
    }

    // Start the quiz
    public void startQuiz() {
        System.out.println("Welcome to the Quiz! You have " + QUESTION_TIME_LIMIT + " seconds to answer each question.");

        for (Question question : questions) {
            askQuestion(question);
        }

        displayResults();
    }

    // Display final results
    private void displayResults() {
        System.out.println("\nQuiz Over! Here are your results:");
        System.out.println("Score: " + score + " / " + questions.size());

        for (int i = 0; i < questions.size(); i++) {
            String status = results.get(i) ? "Correct" : "Incorrect";
            System.out.println("Question " + (i + 1) + ": " + status);
        }

        System.out.println("Thank you for playing!");
    }

    // Main method
    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();
        quiz.startQuiz();
    }
}

