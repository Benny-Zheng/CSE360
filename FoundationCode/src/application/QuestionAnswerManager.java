package application;

import databasePart1.DatabaseHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The QuestionAnswerManager class is responsible for handling user questions and answers.
 * It interacts with DatabaseHelper to manage question-answer related database operations.
 */
public class QuestionAnswerManager {
    private final DatabaseHelper databaseHelper;

    public QuestionAnswerManager(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    /**
     * Creates the necessary tables for managing questions and answers.
     * This ensures tables exist before performing any CRUD operations.
     */
    public void initializeTables() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/FoundationDatabase", "sa", "");
             Statement statement = connection.createStatement()) {
            // Create Questions table
            String questionsTable = "CREATE TABLE IF NOT EXISTS Questions ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "content TEXT NOT NULL, "
                    + "author VARCHAR(255) NOT NULL)";
            statement.execute(questionsTable);

            // Create Answers table
            String answersTable = "CREATE TABLE IF NOT EXISTS Answers ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "questionId INT NOT NULL, "
                    + "content TEXT NOT NULL, "
                    + "author VARCHAR(255) NOT NULL, "
                    + "FOREIGN KEY (questionId) REFERENCES Questions(id) ON DELETE CASCADE)";
            statement.execute(answersTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new question to the database.
     * @param content The text of the question.
     * @param author The user who posted the question.
     */
    public void addQuestion(String content, String author) {
        String insertQuestion = "INSERT INTO Questions (content, author) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/FoundationDatabase", "sa", "");
             PreparedStatement pstmt = connection.prepareStatement(insertQuestion)) {
            pstmt.setString(1, content);
            pstmt.setString(2, author);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all questions from the database.
     * @return List of questions.
     */
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Questions";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/FoundationDatabase", "sa", "");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                questions.add(new Question(rs.getInt("id"), rs.getString("content"), rs.getString("author")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Adds an answer to a specific question.
     * @param questionId The ID of the question being answered.
     * @param content The text of the answer.
     * @param author The user who posted the answer.
     */
    public void addAnswer(int questionId, String content, String author) {
        String insertAnswer = "INSERT INTO Answers (questionId, content, author) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/FoundationDatabase", "sa", "");
             PreparedStatement pstmt = connection.prepareStatement(insertAnswer)) {
            pstmt.setInt(1, questionId);
            pstmt.setString(2, content);
            pstmt.setString(3, author);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all answers for a specific question.
     * @param questionId The ID of the question.
     * @return List of answers.
     */
    public List<Answer> getAnswersForQuestion(int questionId) {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM Answers WHERE questionId = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/FoundationDatabase", "sa", "");
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, questionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    answers.add(new Answer(rs.getInt("id"), rs.getInt("questionId"), rs.getString("content"), rs.getString("author")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
