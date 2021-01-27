package ua.edu.sumdu.j2se.bubenshchykov.tasks.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Constants;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Class that is responsible for the work of the "Edit Task" window
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class EditController
{
    @FXML
    private Button closeEditButton;
    @FXML
    private Button editButton;
    @FXML
    private TextField titleTextBox;
    @FXML
    private CheckBox activeCheck;
    @FXML
    private CheckBox repeatCheck;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField timeTextBox;
    @FXML
    private Label startLabel;
    @FXML
    private TextField startTextBox;
    @FXML
    private TextField endTextBox;
    @FXML
    private Label endLabel;
    @FXML
    private Label intervalLabel;
    @FXML
    private TextField intervalTextBox;
    @FXML
    private TextField searchTitleTextBox;
    /**
     * Index of the selected task in the existing list
     * */
    int selectedIndex;
    /**
     * Searching for the task for its further editing
     * */
    @FXML
    void searchForEdit()
    {
        if (Constants.EMPTY.equals(searchTitleTextBox.getText())) {
            Main.logger.error(Constants.INPUT_ERROR);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка вводу даних");
            alert.setHeaderText("Неправильні введені дані");
            alert.setContentText(Constants.INPUT_ERROR_CONTENT);
            alert.showAndWait();
            return;
        }
        String title = searchTitleTextBox.getText();
        int counter = 0;
        for (int i = 0; i != Main.taskList.size(); i++) {
            if (title.equals(Main.taskList.getTask(i).getTitle())) {
                selectedIndex = i;
                titleTextBox.setText(title);
                if (Main.taskList.getTask(i).isActive())
                    activeCheck.setSelected(true);
                if (Main.taskList.getTask(i).isRepeated()) {
                    repeatCheck.setSelected(true);
                    timeLabel.setVisible(false);
                    timeTextBox.setVisible(false);
                    startTextBox.setText(Main.formatter.format(Main.taskList.getTask(i).getStartTime()));
                    endTextBox.setText(Main.formatter.format(Main.taskList.getTask(i).getEndTime()));
                    intervalTextBox.setText(Integer.toString(Main.taskList.getTask(i).getRepeatInterval()));
                    Main.logger.info("Повторювана задача успішно виведена.");
                } else if (!Main.taskList.getTask(i).isRepeated()) {
                    startLabel.setVisible(false);
                    startTextBox.setVisible(false);
                    endLabel.setVisible(false);
                    endTextBox.setVisible(false);
                    intervalLabel.setVisible(false);
                    intervalTextBox.setVisible(false);
                    timeTextBox.setText(Main.formatter.format(Main.taskList.getTask(i).getTime()));
                    Main.logger.info("Неповторювана задача успішно виведена.");
                }
                counter++;
                return;
            }
        }
        if (counter == 0) {
            Main.logger.error(Constants.EDIT_TASK_ERROR);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Задача не знайдена");
            alert.setHeaderText("Задачі немає у списку");
            alert.setContentText("Задачі з вказаною назвою <" + searchTitleTextBox.getText() + "> немає у списку.\n" +
                    "Введіть назву потрібної Вам задачі ще раз!");
            alert.showAndWait();
        }
    }
    /**
     * Process of editing the task and saving changes
     * @exception catch DateTimeException (log output)
     * */
    @FXML
    void editTask()
    {
        try {
            if (Constants.EMPTY.equals(titleTextBox.getText())) {
                Main.logger.error(Constants.INPUT_ERROR);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка вводу даних");
                alert.setHeaderText("Неправильні введені дані");
                alert.setContentText(Constants.INPUT_ERROR_CONTENT);
                alert.showAndWait();
                return;
            }
            Main.taskList.getTask(selectedIndex).setTitle(titleTextBox.getText());
            if (activeCheck.isSelected()) {
                Main.taskList.getTask(selectedIndex).setActive(true);
            }
            if (!activeCheck.isSelected()) {
                Main.taskList.getTask(selectedIndex).setActive(false);
            }
            if (!repeatCheck.isSelected()) {
                Main.taskList.getTask(selectedIndex).setTime(LocalDateTime.parse(timeTextBox.getText(), Main.formatter));
                Main.logger.info("Неповторювана задача " + titleTextBox.getText() + " успішно відредагована користувачем.");
            }
            if (repeatCheck.isSelected()) {
                Main.taskList.getTask(selectedIndex).setTime(LocalDateTime.parse(startTextBox.getText(), Main.formatter),
                        LocalDateTime.parse(endTextBox.getText(), Main.formatter), Integer.parseInt(intervalTextBox.getText()));
                Main.logger.info("Повторювана задача " + titleTextBox.getText() + " успішно відредагована користувачем.");
            }
            Main.list.set(selectedIndex, Main.taskList.getTask(selectedIndex));
            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.close();
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
    /**
     * Demonstration of graphic elements according to the type of edited task
     * */
    @FXML
    void repeatTask()
    {
        if (!repeatCheck.isSelected()) {
            repeatCheck.setSelected(false);
            startLabel.setVisible(false);
            endLabel.setVisible(false);
            intervalLabel.setVisible(false);
            startTextBox.setVisible(false);
            endTextBox.setVisible(false);
            intervalTextBox.setVisible(false);
            timeTextBox.setVisible(true);
            timeLabel.setVisible(true);
        }
        if (repeatCheck.isSelected()) {
            timeLabel.setVisible(false);
            timeTextBox.setVisible(false);
            startLabel.setVisible(true);
            endLabel.setVisible(true);
            intervalLabel.setVisible(true);
            startTextBox.setVisible(true);
            endTextBox.setVisible(true);
            intervalTextBox.setVisible(true);
        }
    }
    /**
     * Closing the window when interacting with the "closeEditButton" button ("Close window")
     * */
    @FXML
    void closeEdit()
    {
        Stage stage = (Stage) closeEditButton.getScene().getWindow();
        stage.close();
        Main.logger.info("Закриття вікна для редагування задачі.");
    }
}