package ua.edu.sumdu.j2se.bubenshchykov.tasks.model;

import ua.edu.sumdu.j2se.bubenshchykov.tasks.Constants;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class for working with collections of tasks
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class Tasks
{
    /**
     * Method that returns tasks that are scheduled at least once in a period of time from "start" to "end"
     * @param tasks is collection of a certain type
     * @param start is a start time of a specified period
     * @param end is a end time of a specified period
     * @return list of tasks
     * */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
    {
        if (start == null || end == null ) {
            Main.logger.error(Constants.TIME_NULL_UA);
            throw new IllegalArgumentException(Constants.TIME_NULL_EN);
        }
        if (end.compareTo(start) < 0) {
            Main.logger.error(Constants.START_END_UA);
            throw new IllegalArgumentException(Constants.START_END_EN);
        }
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks) {
            if (task.nextTimeAfter(start) != null && task.nextTimeAfter(start).compareTo(end) <= 0) {
                list.add(task);
            }
        }
        return list;
    }
    /**
     * Method that builds a task calendar for a given period of time
     * @param tasks is collection of a certain type
     * @param start is a start time of a specified period
     * @param end is a end time of a specified period
     * @return collection of a certain type
     * */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
    {
        if (start == null || end == null ) {
            Main.logger.error(Constants.TIME_NULL_UA);
            throw new IllegalArgumentException(Constants.TIME_NULL_EN);
        }
        if (end.compareTo(start) < 0) {
            Main.logger.error(Constants.START_END_UA);
            throw new IllegalArgumentException(Constants.START_END_EN);
        }
        SortedMap<LocalDateTime, Set<Task>> map = new TreeMap<>();
        for (Task task : tasks) {
            for (LocalDateTime i = task.nextTimeAfter(start); i != null && i.compareTo(end) <= 0; i = task.nextTimeAfter(i)) {
                if (map.get(i) == null) {
                    HashSet<Task> set = new HashSet<Task>();
                    set.add(task);
                    map.put(i, set);
                }
                else {
                    map.get(i).add(task);
                }
            }
        }
        return map;
    }
}