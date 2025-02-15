package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * AdminHomePage class represents the user interface for the admin.
 * This page provides navigation options for managing users and viewing activity logs.
 */
public class AdminHomePage {

    /**
     * Displays the Admin Home Page in the provided primary stage.
     * @param primaryStage The primary stage where the scene will be displayed.
     */
    public void show(Stage primaryStage) {
        VBox layout = new VBox();
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
        
        // Label to display a welcome message for the admin
        Label adminLabel = new Label("Hello, Admin!");
        adminLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        // Automated Test: Verify Welcome Message
        System.out.println("Test: Verify Admin Welcome Message - Expected: 'Hello, Admin!', Actual: " + adminLabel.getText());

        // Button to navigate to the Manage Users page
        Button manageUsersButton = new Button("Manage Users");
        manageUsersButton.setOnAction(event -> {
            System.out.println("Test: Manage Users Button Clicked - Navigating to Manage Users Page");
            new ManageUsersPage().show(primaryStage);
        });

        // Button to navigate to the Activity Logs page
        Button activityLogsButton = new Button("View Activity Logs");
        activityLogsButton.setOnAction(event -> {
            System.out.println("Test: View Activity Logs Button Clicked - Navigating to Activity Logs Page");
            new ActivityLogsPage().show(primaryStage);
        });

        // Button to log out
        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(a -> {
            System.out.println("Test: Logout Button Clicked - Navigating to Start Page");
            new StartCSE360().start(primaryStage); // Reroute user back to start page
        });

        // Add all UI elements to the layout
        layout.getChildren().addAll(adminLabel, manageUsersButton, activityLogsButton, logoutButton);
        
        // Create and set the scene
        Scene adminScene = new Scene(layout, 800, 400);
        primaryStage.setScene(adminScene);
        primaryStage.setTitle("Admin Page");

        // Automated Test: Scene Loaded
        System.out.println("Test: Admin Home Page Loaded - Scene Title: " + primaryStage.getTitle());
    }
}
