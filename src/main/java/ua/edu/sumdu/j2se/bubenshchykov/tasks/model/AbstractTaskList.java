package ua.edu.sumdu.j2se.bubenshchykov.tasks.model;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * An abstract class that acts as a parent for classes "ArrayTaskList" and "LinkedTaskList"
 * @implements Iterable<Task>, Cloneable, Serializable
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable
{
    /**
     * The class field required to create an instance of the class by its type
     * */
    protected ListTypes.types type;
    /**
     * An abstract method that adds the specified task to the list
     * @param task is the object of class "Task", which characterizes one task from the list
     * */
    public abstract Task add(Task task);
    /**
     * An abstract method that removes a task from the list
     * @param task is the object of class "Task", which characterizes one task from the list
     * */
    public abstract boolean remove(Task task);
    /**
     * An abstract method that returns the number of tasks in a list
     * */
    public abstract int size();
    /**
     * An abstract method that returns the task that is at the specified location in the list
     * @param index is the index of the task to be returned
     * */
    public abstract Task getTask(int index);
    /**
     * Method that allows to work with collections as threads
     * */
    public Stream<Task> getStream()
    {
        Stream.Builder<Task> streamBuilder = Stream.builder();
        for (Task task : this) {
            streamBuilder.add(task);
        }
        return streamBuilder.build();
    }
}