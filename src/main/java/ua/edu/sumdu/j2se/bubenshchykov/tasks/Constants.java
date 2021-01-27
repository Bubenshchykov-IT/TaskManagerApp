package ua.edu.sumdu.j2se.bubenshchykov.tasks;

/**
 * Class for storing constant values (Date Format, File Paths, etc.)
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class Constants
{
    /**
     * Basically, constants for the "Main" class
     * */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MAIN_FILEPATH = "/view/main.fxml";
    public static final String LOGO = "/assets/logo.png";
    public static final String DATA_FILEPATH = "data.txt";
    /**
     * Basically, constants for the "ArrayTaskList" and "LinkedTaskList" classes
     * */
    public static final String INDEX_ERROR_UA = "Неправильно введений індекс у списку.";
    public static final String INDEX_ERROR_EN = "Incorrectly entered index in the list.";
    public static final String INTERVAL_ERROR_UA = "Індекс поза інтервалом масиву.";
    public static final String INTERVAL_ERROR_EN = "Out of the array interval.";
    /**
     * Basically, constants for the "Task" and "Tasks" classes
     * */
    public static final String TIME_NULL_UA = "Вказаний час не може бути нульовим.";
    public static final String TIME_NULL_EN = "The specified time cannot be null.";
    public static final String TIME_PERIOD_NULL_UA = "Час не може бути нулем або період не може бути меншим за нуль.";
    public static final String TIME_PERIOD_NULL_EN = "Time cannot be null or period cannot be less then zero.";
    public static final String START_END_UA = "Час <start> не може бути меншим за час <end>.";
    public static final String START_END_EN = "Time <start> cannot be less then time <end>.";
    /**
     * Basically, constants for the "AddController" and "CalendarController" classes
     * */
    public static final String INPUT_ERROR = "Помилка вводу даних.";
    public static final String INPUT_ERROR_CONTENT = "Перевірте, будь ласка, введенний формат данних.\nВін не відповідає поставленим вимогам!";
    public static final String DATE_INPUT_ERROR = "Помилка вводу даних (формат введеної дати невірний).";
    /**
     * Basically, constants for the "Controller" class
     * */
    public static final String ADD_FXML_FILEPATH = "/view/add.fxml";
    public static final String SEARCH_FXML_FILEPATH = "/view/search.fxml";
    public static final String REMOVE_FXML_FILEPATH = "/view/remove.fxml";
    public static final String EDIT_FXML_FILEPATH = "/view/edit.fxml";
    public static final String CALENDAR_FXML_FILEPATH = "/view/calendar.fxml";
    public static final String SEARCH_LIST_ERROR = "Список задач порожній. Пошук неможливий.";
    public static final String SEARCH_LIST_ERROR_CONTENT = "Так як список задач порожній, пошук задач,\nнеможливий. Додайте нові задачі до списку!";
    public static final String REMOVE_LIST_ERROR = "Список задач порожній. Видалення неможливе.";
    public static final String REMOVE_LIST_ERROR_CONTENT = "Так як список задач порожній, видалення задач, неможливе.\nДодайте нові задачі до списку!";
    public static final String EDIT_LIST_ERROR = "Список задач порожній. Редагування неможливе.";
    public static final String EDIT_LIST_ERROR_CONTENT = "Так як список задач порожній, редагування задач,\nнеможливе. Додайте нові задачі до списку!";
    public static final String CALENDAR_LIST_ERROR = "Список задач порожній. Пошук за певний проміжок часу неможливий.";
    public static final String CALENDAR_LIST_ERROR_CONTENT = "Так як список задач порожній, пошук задач,\nякі повинні відбутися у вказаний період часу неможливий. \nДодайте нові задачі до списку!";
    /**
     * Basically, constants for the "EditController" class
     * */
    public static final String EDIT_TASK_ERROR = "Задача для її редагування не знайдена.";
    /**
     * Other constants
     * */
    public static final String EMPTY = "";
}