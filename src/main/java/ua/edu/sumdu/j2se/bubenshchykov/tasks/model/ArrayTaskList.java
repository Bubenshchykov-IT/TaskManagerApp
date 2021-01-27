package ua.edu.sumdu.j2se.bubenshchykov.tasks.model;

import ua.edu.sumdu.j2se.bubenshchykov.tasks.Constants;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class that represents an array of data that stores individual objects of class "Task"
 * @extends class "AbstractTaskList"
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class ArrayTaskList extends AbstractTaskList
{
    private Task[] array;
    private int size;
    private static final int INITIAL_SIZE = 10;
    /**
     * Constructor - creating a new object
     * */
    public ArrayTaskList()
    {
        array = new Task[INITIAL_SIZE];
        size = 0;
        type = ListTypes.types.ARRAY;
    }
    /**
     * Method that adds the specified task to the list
     * @param task is the object of class "Task", which characterizes one task from the list
     * @return task which user wants to add to the list
     * */
    public Task add(Task task)
    {
        if (size < array.length) {
            array[size++] = task;
        }
        else {
            Task[] newArray = new Task[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
            array[size++] = task;
        }
        return task;
    }
    /**
     * Method that removes a task from the list
     * @param task is the object of class "Task", which characterizes one task from the list
     * @return true or false depending on the removal operation (remove or not)
     * */
    public boolean remove(Task task)
    {
        if (task == null) {
            return false;
        }
        else {
            for (int i = 0; i != size; i++) {
                if (task.equals(array[i])) {
                    System.arraycopy(array, i + 1, array, i, size - (i + 1));
                    array[size--] = null;
                    return true;
                }
            }
            return false;
        }
    }
    /**
     * Method that returns the number of tasks in a list
     * @return size of the task list
     * */
    public int size() { return size; }
    /**
     * Method that returns the task that is at the specified location in the list
     * @param index is the index of the task to be returned
     * @return the need task for user (by index)
     * */
    public Task getTask(int index)
    {
        if (index < 0 || index >= size) {
            Main.logger.error(Constants.INDEX_ERROR_UA);
            throw new IndexOutOfBoundsException(Constants.INDEX_ERROR_EN);
        }
        else {
            return array[index];
        }
    }
    /**
     * Interface that describes an abstract iteration of a set of objects
     * */
    @Override
    public Iterator<Task> iterator()
    {
        return new Iterator<Task>()
        {
            private int index = 0;
            /**
             * Method for checking for the presence of the next iterator element
             * @return true or false
             * */
            @Override
            public boolean hasNext()
            {
                return index != size;
            }
            /**
             * Method for moving to the next iterator element
             * @return next iterator element (by index)
             * */
            @Override
            public Task next()
            {
                return array[index++];
            }
            /**
             * Method for removing an iterator element
             * */
            @Override
            public void remove()
            {
                if (index == 0) {
                    Main.logger.error(Constants.INTERVAL_ERROR_UA);
                    throw new IllegalStateException(Constants.INTERVAL_ERROR_EN);
                }
                else {
                    ArrayTaskList.this.remove(array[--index]);
                }
            }
        };
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
        if (!(object instanceof ArrayTaskList)) return false;
        ArrayTaskList taskList = (ArrayTaskList) object;
        if (size == taskList.size && Arrays.equals(array, taskList.array)) {
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
        return Arrays.hashCode(array);
    }
    /**
     * Method that creates a copy of the object (cloning)
     * @return arrayTaskList (task list)
     * */
    @Override
    public ArrayTaskList clone()
    {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        for (int i = 0; i != size; i++) {
            arrayTaskList.add(array[i]);
        }
        return arrayTaskList;
    }
    /**
     * Method that returns a character string that describes the corresponding object
     * @return string
     * */
    @Override
    public String toString()
    {
        String str = Constants.EMPTY;
        for(int i = 0; i != size; i++) {
            str += array[i].toString() + "\n";
        }
        return str;
    }
}