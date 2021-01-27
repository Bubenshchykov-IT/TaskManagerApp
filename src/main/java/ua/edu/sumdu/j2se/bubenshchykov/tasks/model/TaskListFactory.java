package ua.edu.sumdu.j2se.bubenshchykov.tasks.model;

/**
 * Class for realization of a pattern "Abstract factory"
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class TaskListFactory
{
    /**
     * Static method required to instantiate the "ArrayTaskList" or "LinkedTaskList" class
     * @param type of the task list
     * @return list of tasks
     * */
    public static AbstractTaskList createTaskList(ListTypes.types type)
    {
        AbstractTaskList list = null;
        switch (type) {
            case ARRAY:
                list = new ArrayTaskList();
                break;
            case LINKED:
                list = new LinkedTaskList();
                break;
        }
        return list;
    }
}