package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import databasePart1.DatabaseHelper;
import java.util.List;

public class ViewQuestionsPage {
    private final DatabaseHelper databaseHelper;
    private final QuestionAnswerManager qaManager;

    public ViewQuestionsPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        this.qaManager = new QuestionAnswerManager(databaseHelper);
    }

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label titleLabel = new Label("All Questions");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> questionListView = new ListView<>();
        List<Question> questions = qaManager.getAllQuestions();
        for (Question q : questions) {
            questionListView.getItems().add(q.getContent() + " - by " + q.getAuthor());
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> new UserHomePage(databaseHelper).show(primaryStage));

        layout.getChildren().addAll(titleLabel, questionListView, backButton);
        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("View Questions");
        primaryStage.show();
    }
}
