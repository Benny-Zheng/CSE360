package application;

import databasePart1.DatabaseHelper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This page displays a simple welcome message for the user.
 */
public class UserHomePage {
    private DatabaseHelper databaseHelper; 

    public UserHomePage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage) {
        VBox layout = new VBox();
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Label to display Hello user
        Label userLabel = new Label("Hello, User!");
        userLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Button to navigate to the Ask Question page
        Button askQuestionButton = new Button("Ask a Question");
        askQuestionButton.setOnAction(event -> {
            new AskQuestionPage().show(primaryStage);
        });

        // Button to navigate to the Search Answers page
        Button searchAnswersButton = new Button("Search Answers");
        searchAnswersButton.setOnAction(event -> {
            new SearchAnswersPage().show(primaryStage);
        });

        // Button to log out
        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(a -> {
            System.out.println("Logout clicked");
            new StartCSE360().start(primaryStage); // reroute user back to start page
        });


        Button viewQuestionsButton = new Button("View Questions");
        viewQuestionsButton.setOnAction(event -> {
            new ViewQuestionsPage(databaseHelper).show(primaryStage);
        });

        
        layout.getChildren().addAll(userLabel, askQuestionButton, searchAnswersButton, viewQuestionsButton, logoutButton);
        
        Scene userScene = new Scene(layout, 800, 400);

        // Set the scene to primary stage
        primaryStage.setScene(userScene);
        primaryStage.setTitle("User Page");
        primaryStage.show(); 
    }
}
