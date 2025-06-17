package assignment02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        // Set the action
        submitButton.setOnAction(new SubmitHandler(nameField, roleField, hoursField));


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

    // Validation method
    // valid name
    public boolean isValidName(String name) {
         return name != null && !name.isEmpty() && name.matches("[A-Za-z]+");
    }

    // valid role
    public boolean isValidRole(String role) {
        return role != null && !role.isEmpty() && role.matches("[A-Za-z]+");
    }

    // valid hours
    public boolean isValidHours(String hoursStr) {
        // Try-catch exception
        try {
            int hours = Integer.parseInt(hoursStr);
            return hours > 0;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    // Handle submit
    class SubmitHandler implements EventHandler<ActionEvent> {
        TextField nameField;
        TextField roleField;
        TextField hoursField;

        public SubmitHandler(TextField nameField, TextField roleField, TextField hoursField) {
            this.nameField = nameField;
            this.roleField = roleField;
            this.hoursField = hoursField;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            String name = nameField.getText().trim();
            String role = roleField.getText().trim();
            String hours = hoursField.getText().trim();
            Alert showAlert = new Alert(Alert.AlertType.ERROR);

            if (!isValidName(name)) {
                showAlert.setContentText("Invalid name! Name must only contain alphabet characters.");
                showAlert.showAndWait();
                return;
            }

            if (!isValidRole(role)) {
                showAlert.setContentText("Invalid role! Role must only contain alphabet characters.");
                showAlert.showAndWait();
                return;
            }

            if (!isValidHours(hours)) {
                showAlert.setContentText("Invalid hours! Must be a integer number.");
                showAlert.showAndWait();
                return;
            }

            String entry = role + " " + name + " " + hours;

            //Append to text
            outputArea.appendText(entry + "\n");

            //Save the entry to the file
            try {
                FileWriter fileWriter = new FileWriter("work_hours.txt", true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(entry);
                printWriter.close();
            } catch (IOException e) {
                outputArea.appendText("Saving failed! Here's why: " + e.getMessage() + "\n");
            }


            // clear input fields
            nameField.clear();
            roleField.clear();
            hoursField.clear();
        }
    }
    // Main
    public static void main(String[] args) {
        launch(args);
    }
    }
