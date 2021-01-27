package ua.edu.sumdu.j2se.bubenshchykov.tasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.Task;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.model.TaskIO;
import java.io.File;
import java.time.format.DateTimeFormatter;

/**
 * This is the Main class of the program where the application runs
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class Main extends Application
{
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT);
    public static AbstractTaskList taskList = new ArrayTaskList();
    public static ObservableList<Task> list = FXCollections.observableArrayList();
    public static Logger logger = Logger.getLogger(Main.class);
    /**
     * Open the main program window (Start Window)
     * @param primaryStage is the basis of the main window of the application
     * @throws Exception to handle the drop-down exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.MAIN_FILEPATH));
        primaryStage.setTitle("Task Manager");
        primaryStage.getIcons().add(new Image(Constants.LOGO));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    /**
     * Program entry point (program start)
     * @param args are command line values
     * */
    public static void main(String[] args)
    {
        launch(args);
        TaskIO output = new TaskIO();
        output.writeText(taskList, new File(Constants.DATA_FILEPATH));
        logger.info("Усі зміни при роботі зі списком задач додано до файлу.");
        logger.info("Завершення роботи програми!\n");
    }
}