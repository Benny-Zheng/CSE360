package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import databasePart1.DatabaseHelper;
import java.util.List;

public class ViewAnswersPage {
    private final DatabaseHelper databaseHelper;
    private final QuestionAnswerManager qaManager;
    private final int questionId;

    public ViewAnswersPage(DatabaseHelper databaseHelper, int questionId) {
        this.databaseHelper = databaseHelper;
        this.qaManager = new QuestionAnswerManager(databaseHelper);
        this.questionId = questionId;
    }

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label titleLabel = new Label("Answers for Question ID: " + questionId);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> answerListView = new ListView<>();
        List<Answer> answers = qaManager.getAnswersForQuestion(questionId);
        for (Answer a : answers) {
            answerListView.getItems().add(a.getContent() + " - by " + a.getAuthor());
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> new ViewQuestionsPage(databaseHelper).show(primaryStage));

        layout.getChildren().addAll(titleLabel, answerListView, backButton);
        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("View Answers");
        primaryStage.show();
    }
}
