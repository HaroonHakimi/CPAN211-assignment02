package assignment02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class WorkHoursManagement extends Application {
    private TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        // Create main layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        
        // Create input fields 
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter name");
        
        Label roleLabel = new Label("Role:");
        TextField roleField = new TextField();
        roleField.setPromptText("Enter role (Professor, TA, Student)");
        
        Label hoursLabel = new Label("Hours Worked:");
        TextField hoursField = new TextField();
        hoursField.setPromptText("Enter hours");
        
        Button submitButton = new Button("Submit");
        
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(300);
        
        root.getChildren().addAll(
            nameLabel, nameField,
            roleLabel, roleField,
            hoursLabel, hoursField,
            submitButton,
            outputArea
        );
        
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Work Hours Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}