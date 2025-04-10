/**
 * StaffDashboardPage.java
 * 
 * This class provides a graphical interface for staff members to:
 * - View all submitted reviews
 * - Manage (edit/delete) flagged reviews
 * - Search/filter reviews by keyword or user
 * - Temporarily suspend or permanently ban users
 * - Restore mistakenly deleted reviews
 * 
 * Author: Yuke Zheng
 * Date: 2025-04-09
 */

package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import java.util.stream.Collectors;

public class StaffDashboardPage {
    private final User staffUser;
    private final QuestionsManager questionsManager = QuestionsManager.getInstance();
    private final ObservableList<String> reviewItems = FXCollections.observableArrayList();
    private final ObservableList<String> deletedReviews = FXCollections.observableArrayList();

    public StaffDashboardPage(User staffUser) {
        this.staffUser = staffUser;
    }

    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label title = new Label("Staff Dashboard - Manage Reviews and Users");

        ListView<String> reviewList = new ListView<>(reviewItems);
        reviewList.setPrefSize(600, 200);

        TextField searchField = new TextField();
        searchField.setPromptText("Search by keyword or username");

        TextField inputField = new TextField();
        inputField.setPromptText("New content or username to ban");

        Button refreshButton = new Button("Refresh Reviews");
        refreshButton.setOnAction(e -> loadReviews());

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            String keyword = searchField.getText().toLowerCase();
            List<Review> filtered = questionsManager.getAllReviews().stream()
                    .filter(r -> r.getReviewText().toLowerCase().contains(keyword) ||
                                 r.getReviewerUsername().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());
            reviewItems.setAll(filtered.stream().map(r -> formatReview(r)).collect(Collectors.toList()));
        });

        Button deleteButton = new Button("Delete Selected");
        deleteButton.setOnAction(e -> {
            String selected = reviewList.getSelectionModel().getSelectedItem();
            if (selected != null && selected.startsWith("[")) {
                int reviewId = extractReviewId(selected);
                deletedReviews.add(selected); // Add to restore list
                questionsManager.deleteReview(reviewId);
                loadReviews();
            }
        });

        Button restoreButton = new Button("Restore Last Deleted");
        restoreButton.setOnAction(e -> {
            if (!deletedReviews.isEmpty()) {
                String lastDeleted = deletedReviews.remove(deletedReviews.size() - 1);
                int reviewId = extractReviewId(lastDeleted);
                questionsManager.restoreReview(reviewId);
                loadReviews();
            }
        });

        Button banButton = new Button("Ban User");
        banButton.setOnAction(e -> {
            String userToBan = inputField.getText().trim();
            if (!userToBan.isEmpty()) {
                questionsManager.banUser(userToBan);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Banned");
                alert.setContentText("User " + userToBan + " has been banned.");
                alert.show();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new AdminHomePage(staffUser).show(primaryStage));

        layout.getChildren().addAll(title, searchField, searchButton, reviewList,
                inputField, deleteButton, restoreButton, banButton, refreshButton, backButton);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Staff Dashboard");
        primaryStage.show();

        loadReviews();
    }

    private void loadReviews() {
        List<Review> reviews = questionsManager.getAllReviews();
        reviewItems.setAll(reviews.stream().map(this::formatReview).collect(Collectors.toList()));
    }

    private String formatReview(Review r) {
        return "[" + r.getReviewId() + "] (" + r.getReviewerUsername() + "): " + r.getReviewText();
    }

    private int extractReviewId(String s) {
        try {
            int start = s.indexOf("[") + 1;
            int end = s.indexOf("]");
            return Integer.parseInt(s.substring(start, end));
        } catch (Exception e) {
            return -1;
        }
    }
} 
