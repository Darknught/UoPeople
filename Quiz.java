/*
 * Java Multiple-Choice Quiz Program
 * ----------------------------------
 * This is a basic Java program that asks the user five multiple-choice questions.
 * Each question has four options labeled A, B, C, and D. The user is prompted to
 * enter their answer by typing one of these letters. The program compares each answer
 * with the correct one, keeps track of the number of correct responses, and finally
 * calculates and displays the user’s score as a percentage.
 *
 * This version uses only basic input via System.in.read() to demonstrate how
 * raw input can be handled without using any additional libraries like Scanner.
 *
 * Expected result: The program displays the questions one after another, reads user input,
 * checks correctness, and prints the number of correct answers and the percentage score.
 */

// Java program to run a 5-question multiple-choice quiz using only basic Java input
public class Quiz{
    public static void main(String[] args) throws java.io.IOException {
        // Correct answers
        char[] correctAnswers = {'B', 'A', 'C', 'D', 'A'};
        int score = 0;
        byte[] buffer = new byte[100];

        for (int i = 0; i < 5; i++) {
            System.out.println("Question " + (i + 1) + ":");
            switch (i) {
                case 0:
                    System.out.println("What is the capital of France?");
                    System.out.println("A. Berlin\nB. Paris\nC. Madrid\nD. Rome");
                    break;
                case 1:
                    System.out.println("Which data type is used to store whole numbers in Java?");
                    System.out.println("A. int\nB. float\nC. boolean\nD. char");
                    break;
                case 2:
                    System.out.println("What is the output of 10 % 3?");
                    System.out.println("A. 2\nB. 1\nC. 1\nD. 0");
                    break;
                case 3:
                    System.out.println("Which symbol is used for logical AND in Java?");
                    System.out.println("A. ||\nB. !\nC. ==\nD. &&");
                    break;
                case 4:
                    System.out.println("Which keyword is used to define a class in Java?");
                    System.out.println("A. class\nB. def\nC. function\nD. struct");
                    break;
            }

            // Prompt user for input
            System.out.print("Enter your answer (A, B, C, or D): ");
            int bytesRead = System.in.read(buffer); // Read raw input bytes
            char userAnswer = (char) buffer[0];     // Use first character only

            // Convert lowercase to uppercase
            if (userAnswer >= 'a' && userAnswer <= 'z') {
                userAnswer = (char)(userAnswer - 32);
            }

            // Compare answer
            if (userAnswer == correctAnswers[i]) {
                score++;
            }

            // Clear remaining input
            while (System.in.available() > 0) {
                System.in.read();
            }

            System.out.println();
        }

        // Display result
        double percentage = (score / 5.0) * 100;
        System.out.println("You got " + score + " out of 5 correct.");
        System.out.println("Your score is: " + percentage + "%");
    }
}
