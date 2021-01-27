package ua.edu.sumdu.j2se.bubenshchykov.tasks.model;

import ua.edu.sumdu.j2se.bubenshchykov.tasks.Constants;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class that characterizes a particular task and its behavior
 * @implements Cloneable, Serializable
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class Task implements Cloneable, Serializable
{
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    /**
     * active/not active task
     * */
    private boolean active;
    /**
     * Constructor - creating not active task without repetition
     * */
    public Task(String title, LocalDateTime time)
    {
        if (time == null) {
            Main.logger.error(Constants.TIME_NULL_UA);
            throw new IllegalArgumentException(Constants.TIME_NULL_EN);
        }
        this.title = title;
        this.time = time;
        start = time;
        end = time;
        active = false;
    }
    /**
     * Constructor - creating not active task with repetition
     * */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval)
    {
        if (start == null || end == null || interval < 0) {
            Main.logger.error(Constants.TIME_PERIOD_NULL_UA);
            throw new IllegalArgumentException(Constants.TIME_PERIOD_NULL_EN);
        }
        if (end.compareTo(start) < 0) {
            Main.logger.error(Constants.START_END_UA);
            throw new IllegalArgumentException(Constants.START_END_EN);
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        active = false;
    }
    /**
     * Method for reading the task name
     * @return title of the task
     * */
    public String getTitle()
    {
        return title;
    }
    /**
     * Method for setting the name of the task
     * @param title - new title of the need task
     * */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * Method for reading the state of the task
     * @return active state of the task
     * */
    public boolean isActive()
    {
        return active;
    }
    /**
     * method for setting the state of the task
     * @param active - new state of the need task
     * */
    public void setActive(boolean active)
    {
        this.active = active;
    }
    /**
     * Method for reading execution time for non-repetitive tasks
     * @return time of the task
     * */
    public LocalDateTime getTime()
    {
        return start;
    }
    /**
     * Method for setting the execution time for non-recurring tasks
     * @param time - new time of the need task
     * */
    public void setTime(LocalDateTime time)
    {
        if (time == null) {
            Main.logger.error(Constants.TIME_NULL_UA);
            throw new IllegalArgumentException(Constants.TIME_NULL_EN);
        }
        this.time = time;
        start = time;
        end = time;
        interval = 0;
    }
    /**
     * Methods for reading execution time for repetitive tasks
     * */
    public LocalDateTime getStartTime() {
        return start;
    }
    public LocalDateTime getEndTime() {
        return end;
    }
    public int getRepeatInterval() {
        return interval;
    }
    /**
     * Method for setting execution time for repetitive tasks
     * @param start - new start time of the need task
     * @param end - new end time of the need task
     * @param interval - new interval of the need task
     * */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval)
    {
        if (start == null || end == null || interval < 0) {
            Main.logger.error(Constants.TIME_PERIOD_NULL_UA);
            throw new IllegalArgumentException(Constants.TIME_PERIOD_NULL_EN);
        }
        if (end.compareTo(start) < 0) {
            Main.logger.error(Constants.START_END_UA);
            throw new IllegalArgumentException(Constants.START_END_EN);
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }
    /**
     * method for checking the repeatability of the task
     * @return true or false
     * */
    public boolean isRepeated() {
        return interval != 0;
    }
    /**
     * Method that returns the time of the next task after the specified time
     * @param current is a time specified by the user
     * @return time after current time that specified by the user
     * */
    public LocalDateTime nextTimeAfter(LocalDateTime current)
    {
        if (current == null) {
            Main.logger.error(Constants.TIME_NULL_UA);
            throw new IllegalArgumentException(Constants.TIME_NULL_EN);
        }
        if (!active)
            return null;
        else if (interval == 0) {
            if (end.compareTo(current) > 0)
                return end;
            else return null;
        }
        else {
            for (LocalDateTime i = start; i.compareTo(end) <= 0; i = i.plusSeconds(interval)) {
                if (i.compareTo(current) > 0)
                    return i;
            }
        }
        return null;
    }
    /**
     * Method for checking the equality of 2 objects
     * @param object for comparison
     * @return true or false
     * */
    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null) return false;
        if (!(object instanceof Task)) return false;
        Task task = (Task) object;
        if (start == task.start && end == task.end && interval == task.interval
                && active == task.active && Objects.equals(title, task.title)) {
            return true;
        }
        else return false;
    }
    /**
     * Method for checking the equality of 2 objects of increased productivity
     * @return value of type int (hashcode)
     * */
    @Override
    public int hashCode()
    {
        return Objects.hash(title, start, end, interval, active);
    }
    /**
     * Method that creates a copy of the object (cloning)
     * @return task
     * */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Task task = new Task(title, time);
        task.active = active;
        task.start = start;
        task.end = end;
        task.interval = interval;
        return task;
    }
    /**
     * Method that returns a character string that describes the corresponding object
     * @return string
     * */
    @Override
    public String toString()
    {
        if (interval != 0) {
            return "Task {" + "title = " + title + ", active = " + active + ", start = " + Main.formatter.format(start) + ", end = " +
                    Main.formatter.format(end) + ", interval = " + interval + '}';
        } else {
            return "Task {" + "title = " + title + ", active = " + active + ", time = " + Main.formatter.format(time) + '}';
        }
    }
}