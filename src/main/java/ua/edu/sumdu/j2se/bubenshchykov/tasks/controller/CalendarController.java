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
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.Tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * Class that is responsible for the work of the "Task Calendar" window
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class CalendarController
{
    @FXML
    private Button closeCalendarButton;
    @FXML
    private TextField startTextBox;
    @FXML
    private TextField endTextBox;
    @FXML
    private ListView<Map.Entry<LocalDateTime, Set<Task>>> calendarListView;
    /**
     * Display a list of tasks to be performed in the specified period of time
     * @param event is an input event that occurs when a mouse is clicked
     * @exception catch DateTimeException (log output)
     * */
    @FXML
    void showCalendar(MouseEvent event)
    {
        try {
            String start = startTextBox.getText();
            String end = endTextBox.getText();
            ObservableList<Map.Entry<LocalDateTime, Set<Task>>> calendarMap =
                    FXCollections.observableArrayList(Tasks.calendar(Main.taskList,
                            LocalDateTime.parse(start, Main.formatter), LocalDateTime.parse(end, Main.formatter)).entrySet());
            calendarListView.setItems(calendarMap);
            Main.logger.info("Список задач за введений проміжок часу [" + LocalDateTime.parse(start, Main.formatter) + ";" +
                    LocalDateTime.parse(end, Main.formatter) + "] успішно виведено.");
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
     * Closing the window when interacting with the "closeCalendarButton" button ("Close window")
     * @param event is an input event that occurs when a mouse is clicked
     * */
    @FXML
    void closeCalendar(MouseEvent event)
    {
        Stage stage = (Stage) closeCalendarButton.getScene().getWindow();
        stage.close();
        Main.logger.info("Закриття вікна для пошуку списку задач (календар задач).");
    }
}