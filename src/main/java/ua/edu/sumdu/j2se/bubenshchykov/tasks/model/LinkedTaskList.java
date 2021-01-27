package ua.edu.sumdu.j2se.bubenshchykov.tasks.model;

import ua.edu.sumdu.j2se.bubenshchykov.tasks.Constants;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;

import java.util.Iterator;

/**
 * Class that represents a list of data that stores individual objects of class "Task"
 * @extends class "AbstractTaskList"
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class LinkedTaskList extends AbstractTaskList
{
    private NodeTask head;
    private NodeTask last;
    private int size;
    /**
     * Constructor - creating a new object
     * */
    public LinkedTaskList()
    {
        head = null;
        last = null;
        size = 0;
        type = ListTypes.types.LINKED;
    }
    /**
     * Method that adds the specified task to the list
     * @param task is the object of class "Task", which characterizes one task from the list
     * @return task which user wants to add to the list
     * */
    public Task add(Task task)
    {
        if (task == null) {
            return task;
        }
        NodeTask item = new NodeTask(task);
        if (head == null) {
            head = item;
            last = item;
        }
        else {
            last.setNext(item);
            last = item;
        }
        size++;
        return task;
    }
    /**
     * Method that removes a task from the list
     * @param task is the object of class "Task", which characterizes one task from the list
     * @return true or false depending on the removal operation (remove or not)
     * */
    public boolean remove(Task task)
    {
        if (task == null || head == null) {
            return false;
        } else {
            NodeTask item = head;
            for(NodeTask i = head; i != null; i = i.getNext())
            {
                if (task.equals(i.getTaskValue())) {
                    if(i.equals(head)) {
                        head = head.getNext();
                        if (size == 1) {
                            last = head;
                        }
                    } else if (i.equals(last)) {
                        item.setNext(null);
                        last = item;
                    } else {
                        item.setNext(i.getNext());
                    }
                    size--;
                    return true;
                }
                item = i;
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
        if (index < 0 || index >= size ) {
            Main.logger.error(Constants.INDEX_ERROR_UA);
            throw new IndexOutOfBoundsException(Constants.INDEX_ERROR_EN);
        }
        else {
            NodeTask item = head;
            for (int i = 0; i != index; i++)
            {
                item = item.getNext();
            }
            return item.getTaskValue();
        }
    }
    /**
     * Class characterizing a separate node of the simply connected list
     * */
    private static class NodeTask
    {
        private Task task;
        private NodeTask next;
        /**
         * Constructor - creating a new node
         * */
        public NodeTask(Task task)
        {
            this.task = task;
            next = null;
        }
        /**
         * method for obtaining the value "task" in the list node
         * @return the need task
         * */
        public Task getTaskValue() { return task; }
        /**
         * Method for obtaining the next node in the list
         * @return the next node
         * */
        public NodeTask getNext() { return next; }
        /**
         * Method for setting the next node in the list
         * */
        public void setNext(NodeTask next) { this.next = next; }
    }
    /**
     * Interface that describes an abstract iteration of a set of objects
     * */
    @Override
    public Iterator<Task> iterator()
    {
        return new Iterator<Task>()
        {
            private NodeTask nextNode = head;
            private NodeTask previousNode = null;
            /**
             * Method for checking for the presence of the next iterator element
             * @return true or false
             * */
            @Override
            public boolean hasNext()
            {
                return nextNode != null;
            }
            /**
             * Method for moving to the next iterator element
             * @return next iterator element
             * */
            @Override
            public Task next()
            {
                previousNode = nextNode;
                nextNode = nextNode.next;
                return previousNode.task;
            }
            /**
             * Method for removing an iterator element
             * */
            @Override
            public void remove()
            {
                if (previousNode == null) {
                    Main.logger.error(Constants.INTERVAL_ERROR_UA);
                    throw new IllegalStateException(Constants.INTERVAL_ERROR_EN);
                }
                else {
                    LinkedTaskList.this.remove(previousNode.task);
                    previousNode = null;
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
        if (object == null || getClass() != object.getClass()) return false;
        LinkedTaskList linkedTaskList = (LinkedTaskList) object;
        int count = 0;
        for (int i = 0; i != size; i++) {
            if (getTask(i).equals(linkedTaskList.getTask(i)))
                count++;
        }
        if (count == size) return true;
        else return false;
    }
    /**
     * Method for checking the equality of 2 objects of increased productivity
     * @return value of type int (hashcode)
     * */
    @Override
    public int hashCode()
    {
        int hash = 0;
        if (head  == null) return hash;
        NodeTask i; int j;
        for (i = head, j = 0; i != null && j != size; i = i.getNext(), j++) {
            hash += (j + 1) * i.getTaskValue().hashCode();
        }
        return hash;
    }
    /**
     * Method that creates a copy of the object (cloning)
     * @return linkedTaskList (task list)
     * */
    @Override
    public LinkedTaskList clone()
    {
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        for (int i = 0; i != size; i++) {
            linkedTaskList.add(getTask(i));
        }
        return linkedTaskList;
    }
    /**
     * Method that returns a character string that describes the corresponding object
     * @return string
     * */
    @Override
    public String toString()
    {
        String str = Constants.EMPTY;
        for(int i = 0; i != size; i++){
            str += getTask(i).toString() + "\n";
        }
        return str;
    }
}