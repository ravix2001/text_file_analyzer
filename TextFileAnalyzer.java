import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TextFileAnalyzer {

    // Method to analyze text file
    public static void analyzeFile(String filePath) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineCount++;
                charCount += line.length();

                // Split words by spaces
                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }
            }

            System.out.println("\n===== File Analysis Results =====");
            System.out.println("Total Lines: " + lineCount);
            System.out.println("Total Words: " + wordCount);
            System.out.println("Total Characters: " + charCount);

        } catch (IOException e) {
            System.out.println("Error: Unable to read the file. " + e.getMessage());
        }
    }

    // Method to search for a word in file
    public static void searchWord(String filePath, String wordToSearch) {
        int lineNum = 0;
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineNum++;
                if (line.toLowerCase().contains(wordToSearch.toLowerCase())) {
                    System.out.println("Found '" + wordToSearch + "' in line " + lineNum + ": " + line);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Word '" + wordToSearch + "' not found in the file.");
            }

        } catch (IOException e) {
            System.out.println("Error: Unable to read the file. " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Text File Analyzer =====");
        System.out.print("Enter file path: ");
        String filePath = sc.nextLine();

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Analyze File (Count words, lines, characters)");
            System.out.println("2. Search for a Word");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    analyzeFile(filePath);
                    break;

                case 2:
                    System.out.print("Enter word to search: ");
                    String word = sc.nextLine();
                    searchWord(filePath, word);
                    break;

                case 3:
                    running = false;
                    System.out.println("Exiting Text File Analyzer... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        sc.close();
    }
}
