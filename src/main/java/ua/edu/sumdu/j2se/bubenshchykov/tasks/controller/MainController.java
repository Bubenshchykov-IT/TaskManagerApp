package ua.edu.sumdu.j2se.bubenshchykov.tasks.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Constants;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.Task;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;

/**
 * The class that is responsible for the operation of the main application window and implements the menu functions
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class MainController
{
    @FXML
    private Button calendarButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<Task> listView = new ListView<Task>(Main.list);
    /**
     * Change-over to the window for adding a task when interacting with the "addButton" button
     * ("Add a new task")
     * @throws IOException (log output)
     * */
    @FXML
    public void addTask() throws IOException {
        addButton.toBack();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Constants.ADD_FXML_FILEPATH));
        loader.load(); // with try-catch
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(Constants.LOGO));
        stage.setTitle("Додавання задачі");
        stage.showAndWait();
        Main.logger.info("Відкрито вікно для додавання нової задачі.");
    }
    /**
     * Change-over to the task search window when interacting with the "searchButton" button
     * ("Search for the selected task")
     * @throws IOException (log output)
     * */
    @FXML
    public void searchTask() throws IOException
    {
        if (Main.taskList.size() == 0) {
            Main.logger.error(Constants.SEARCH_LIST_ERROR);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Список задач порожній");
            alert.setHeaderText("Пошук задач неможливий");
            alert.setContentText(Constants.SEARCH_LIST_ERROR_CONTENT);
            alert.showAndWait();
            return;
        }
        searchButton.toBack();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Constants.SEARCH_FXML_FILEPATH));
        loader.load(); // with try-catch
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(Constants.LOGO));
        stage.setTitle("Пошук задачі");
        stage.showAndWait();
        Main.logger.info("Відкрито вікно для пошуку задачі.");
    }
    /**
     * Change-over to the task remove window when interacting with the "removeButton" button
     * ("Remove the need task")
     * @throws IOException (log output)
     * */
    @FXML
    public void removeTask() throws IOException
    {
        if (Main.taskList.size() == 0) {
            Main.logger.error(Constants.REMOVE_LIST_ERROR);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Список задач порожній");
            alert.setHeaderText("Видалення задач неможливе");
            alert.setContentText(Constants.REMOVE_LIST_ERROR_CONTENT);
            alert.showAndWait();
            return;
        }
        removeButton.toBack();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Constants.REMOVE_FXML_FILEPATH));
        loader.load(); // with try-catch
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(Constants.LOGO));
        stage.setTitle("Видалення задачі");
        stage.showAndWait();
        Main.logger.info("Відкрито вікно для видалення задачі.");
    }
    /**
     * Change-over to the task edit window when interacting with the "editButton" button
     * ("Edit the need task")
     * @throws IOException (log output)
     * */
    @FXML
    public void editTask() throws IOException
    {
        if (Main.taskList.size() == 0) {
            Main.logger.error(Constants.EDIT_LIST_ERROR);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Список задач порожній");
            alert.setHeaderText("Редагування задач неможливе");
            alert.setContentText(Constants.EDIT_LIST_ERROR_CONTENT);
            alert.showAndWait();
            return;
        }
        editButton.toBack();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Constants.EDIT_FXML_FILEPATH));
        loader.load(); // with try-catch
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(Constants.LOGO));
        stage.setTitle("Редагування задачі");
        stage.showAndWait();
        Main.logger.info("Відкрито вікно для редагування задачі.");
    }
    /**
     * Change-over to the task calendar window when interacting with the "calendarButton" button
     * ("View the task calendar")
     * @throws IOException (log output)
     * */
    @FXML
    public void calendar() throws IOException
    {
        if (Main.taskList.size() == 0) {
            Main.logger.error(Constants.CALENDAR_LIST_ERROR);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Список задач порожній");
            alert.setHeaderText("Пошук задач неможливий");
            alert.setContentText(Constants.CALENDAR_LIST_ERROR_CONTENT);
            alert.showAndWait();
            return;
        }
        calendarButton.toBack();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Constants.CALENDAR_FXML_FILEPATH));
        loader.load(); // with try-catch
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(Constants.LOGO));
        stage.setTitle("Календар задач");
        stage.showAndWait();
        Main.logger.info("Відкрито вікно для пошуку задачі (календар задач).");
    }
    /**
     * Closing the program when interacting with the "exitButton" button
     * ("Finish work")
     * */
    @FXML
    public void exit()
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        Main.logger.info("Закриття головного вікна додатку.");
    }
    /**
     * Initialization the elements of the main application window
     * */
    @FXML
    void initialize()
    {
        Main.logger.info("Запуск роботи програми!");
        TaskIO input = new TaskIO();
        try {
            input.readText(Main.taskList, new File(Constants.DATA_FILEPATH));
            Main.logger.info("Файл з задачами успішно зчитано.");
        } catch (NullPointerException e) {
            Main.logger.error("Задач немає у списку.");
        }
        for (int i = 0; i != Main.taskList.size(); i++) {
            Main.list.add(Main.taskList.getTask(i));
        }
        listView.setItems(Main.list);
    }
}