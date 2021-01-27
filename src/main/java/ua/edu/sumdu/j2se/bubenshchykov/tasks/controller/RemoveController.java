package ua.edu.sumdu.j2se.bubenshchykov.tasks.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Constants;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.Task;

/**
 * Class that is responsible for the work of the "Remove Task" window
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class RemoveController
{
    @FXML
    private Button closeRemoveButton;
    @FXML
    private Button removeButton;
    @FXML
    private TextField titleTextBox;
    @FXML
    private ListView<Task> removeListView;
    /**
     * Searching for the task required for further removal
     * */
    @FXML
    void searchForRemove()
    {
        ObservableList<Task> searchList = FXCollections.observableArrayList();
        if (Constants.EMPTY.equals(titleTextBox.getText())) {
            Main.logger.error(Constants.INPUT_ERROR);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка вводу даних");
            alert.setHeaderText("Неправильні введені дані");
            alert.setContentText(Constants.INPUT_ERROR_CONTENT);
            alert.showAndWait();
            return;
        }
        String title = titleTextBox.getText();
        int counter = 0;
        for (int i = 0; i < Main.taskList.size(); i++) {
            if (title.equals(Main.taskList.getTask(i).getTitle())) {
                searchList.add(Main.taskList.getTask(i));
                removeListView.setItems(searchList);
                counter++;
            }
        }
        if (counter == 0) {
            Main.logger.error("Задача " + titleTextBox.getText() + " для її видалення не знайдена.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Задача не знайдена");
            alert.setHeaderText("Задачі немає у списку");
            alert.setContentText("Задачі з вказаною назвою <" + titleTextBox.getText() + "> немає у списку.\n" +
                    "Введіть назву потрібної Вам задачі ще раз!");
            alert.showAndWait();
        }
    }
    /**
     * Removal the desired task when interacting with the "removeButton" button
     * @param event is an input event that occurs when a mouse is clicked
     * */
    @FXML
    void removeTask(MouseEvent event) {
        ObservableList<Task> removeList = FXCollections.observableArrayList();
        String title = titleTextBox.getText();
        int counter = 0;
        int index = 0;
        for (int i = 0; i < Main.taskList.size(); i++) {
            if (title.equals(Main.taskList.getTask(i).getTitle())) {
                counter++;
                index = i;
            }
        }
        if (counter == 1) {
            Main.list.remove(Main.taskList.getTask(index));
            removeList.remove(Main.taskList.getTask(index));
            Main.taskList.remove(Main.taskList.getTask(index));
            Main.logger.info("Задача " + titleTextBox.getText() + "видалена зі списку.");
        }
        else if (counter > 1) {
            int selectedIndex = removeListView.getSelectionModel().getSelectedIndex();
            Main.list.remove(Main.taskList.getTask(selectedIndex));
            removeList.remove(Main.taskList.getTask(selectedIndex));
            Main.taskList.remove(Main.taskList.getTask(selectedIndex));
            Main.logger.info("Задача " + titleTextBox.getText() + "видалена зі списку.");
        }
        removeListView.setItems(removeList);
        Stage stage = (Stage) removeButton.getScene().getWindow();
        stage.close();
    }
    /**
     * Closing the window when interacting with the "closeRemoveButton" button ("Close window")
     * @param event is an input event that occurs when a mouse is clicked
     * */
    @FXML
    void closeRemove(MouseEvent event)
    {
        Stage stage = (Stage) closeRemoveButton.getScene().getWindow();
        stage.close();
        Main.logger.info("Закриття вікна для видалення задачі.");
    }
}