package ua.edu.sumdu.j2se.bubenshchykov.tasks.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Constants;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Class that is responsible for the work of the "Add Task" window
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class AddController
{
    @FXML
    private Button closeAddButton;
    @FXML
    private Button createButton;
    @FXML
    private TextField titleTextBox;
    @FXML
    private CheckBox activeCheck;
    @FXML
    private CheckBox repeatCheck;
    @FXML
    private TextField timeTextBox;
    @FXML
    private TextField startTextBox;
    @FXML
    private TextField endTextBox;
    @FXML
    private TextField intervalTextBox;
    @FXML
    private Label timeLabel;
    @FXML
    private Label startLabel;
    @FXML
    private Label endLabel;
    @FXML
    private Label intervalLabel;
    /**
     * Add operation (create a new task)
     * @exception catch DateTimeException (log output)
     * */
    @FXML
    void createTask()
    {
        if (!repeatCheck.isSelected()) {
            try {
                if(Constants.EMPTY.equals(titleTextBox.getText())) {
                    Main.logger.error(Constants.INPUT_ERROR);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Помилка вводу даних");
                    alert.setHeaderText("Неправильні введені дані");
                    alert.setContentText(Constants.INPUT_ERROR_CONTENT);
                    alert.showAndWait();
                    return;
                }
                String title = titleTextBox.getText();
                String time = timeTextBox.getText();
                Task task = new Task(title, LocalDateTime.parse(time, Main.formatter));
                if (activeCheck.isSelected()) {
                    task.setActive(true);
                }
                Main.taskList.add(task);
                Main.list.add(task);
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();
                Main.logger.info("Неповторювана задача " + titleTextBox.getText() + " додана до списку.");
            }
            catch (DateTimeException e) {
                Main.logger.error(Constants.DATE_INPUT_ERROR);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка вводу даних");
                alert.setHeaderText("Неправильні введені дані");
                alert.setContentText(Constants.INPUT_ERROR_CONTENT);
                alert.showAndWait();
            }
        }
        else {
            try {
                if(Constants.EMPTY.equals(titleTextBox.getText())) {
                    Main.logger.error(Constants.INPUT_ERROR);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Помилка вводу даних");
                    alert.setHeaderText("Неправильні введені дані");
                    alert.setContentText(Constants.INPUT_ERROR_CONTENT);
                    alert.showAndWait();
                    return;
                }
                String title = titleTextBox.getText();
                String start = startTextBox.getText();
                String end = endTextBox.getText();
                int interval = Integer.parseInt(intervalTextBox.getText());
                Task task = new Task(title, LocalDateTime.parse(start, Main.formatter),
                        LocalDateTime.parse(end, Main.formatter), interval);
                if (activeCheck.isSelected()) {
                    task.setActive(true);
                }
                Main.taskList.add(task);
                Main.list.add(task);
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();
                Main.logger.info("Повторювана задача " + titleTextBox.getText() + " додана до списку.");
            }
            catch (DateTimeException e) {
                Main.logger.error(Constants.DATE_INPUT_ERROR);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка вводу даних");
                alert.setHeaderText("Неправильні введені дані");
                alert.setContentText(Constants.INPUT_ERROR_CONTENT);
                alert.showAndWait();
            }
        }
    }
    /**
     * RepeatCheck processing (repeated/not repeated task)
     * */
    @FXML
    void repeatTask()
    {
        if (!repeatCheck.isSelected()) {
            startLabel.setVisible(false);
            endLabel.setVisible(false);
            intervalLabel.setVisible(false);
            startTextBox.setVisible(false);
            endTextBox.setVisible(false);
            intervalTextBox.setVisible(false);
            timeLabel.setVisible(true);
            timeTextBox.setVisible(true);
        }
        if (repeatCheck.isSelected()) {
            startLabel.setVisible(true);
            endLabel.setVisible(true);
            intervalLabel.setVisible(true);
            startTextBox.setVisible(true);
            endTextBox.setVisible(true);
            intervalTextBox.setVisible(true);
            timeLabel.setVisible(false);
            timeTextBox.setVisible(false);
        }
    }
    /**
     * Closing the window when interacting with the "closeAddButton" button ("Close window")
     * */
    @FXML
    void closeAdd()
    {
        Stage stage = (Stage) closeAddButton.getScene().getWindow();
        stage.close();
        Main.logger.info("Закриття вікна для додавання задачі.");
    }
    /**
     * Initialization the elements of the "Add task"
     * */
    @FXML
    void initialize()
    {
        startLabel.setVisible(false);
        endLabel.setVisible(false);
        intervalLabel.setVisible(false);
        startTextBox.setVisible(false);
        endTextBox.setVisible(false);
        intervalTextBox.setVisible(false);
    }
}