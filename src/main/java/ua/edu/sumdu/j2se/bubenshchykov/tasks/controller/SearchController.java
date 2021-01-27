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
 * Class that is responsible for the work of the "Search Task" window
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class SearchController
{
    @FXML
    private Button closeSearchButton;
    @FXML
    private TextField titleTextBox;
    @FXML
    private ListView<Task> searchListView;
    /**
     * Searching for the required task when interacting with the "searchButton" button
     * @param event is an input event that occurs when a mouse is clicked
     * */
    @FXML
    void searchTask(MouseEvent event)
    {
        ObservableList<Task> searchList = FXCollections.observableArrayList();
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
        int counter = 0;
        for (int i = 0; i < Main.taskList.size(); i++) {
            if (title.equals(Main.taskList.getTask(i).getTitle())) {
                searchList.add(Main.taskList.getTask(i));
                searchListView.setItems(searchList);
                counter++;
            }
        }
        Main.logger.info("Задача " + titleTextBox.getText() + " знайдена.");
        if (counter == 0) {
            Main.logger.error("Задача " + titleTextBox.getText() + " не знайдена.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Задача не знайдена");
            alert.setHeaderText("Задачі немає у списку");
            alert.setContentText("Задачі з вказаною назвою <" + titleTextBox.getText() + "> немає у списку.\n" +
                    "Введіть назву потрібної Вам задачі ще раз!");
            alert.showAndWait();
        }
    }
    /**
     * Closing the window when interacting with the "closeSearchButton" button ("Close window")
     * @param event is an input event that occurs when a mouse is clicked
     * */
    @FXML
    void closeSearch(MouseEvent event)
    {
        Stage stage = (Stage) closeSearchButton.getScene().getWindow();
        stage.close();
        Main.logger.info("Закриття вікна для пошуку задачі.");
    }
}