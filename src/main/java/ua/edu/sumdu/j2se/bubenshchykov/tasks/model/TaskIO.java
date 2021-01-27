package ua.edu.sumdu.j2se.bubenshchykov.tasks.model;

import com.google.gson.Gson;
import ua.edu.sumdu.j2se.bubenshchykov.tasks.Main;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;

/**
 * Class for implementing recording methods and reading task lists in different formats
 * @author Danyil Bubenshchykov
 * @version 15.0.1
 * */
public class TaskIO
{
    /**
     * Method that writes tasks from a list to a stream in binary format
     * @param tasks is the list of tasks
     * @param out - is object of the class that defines the byte stream of output
     * @throws IOException (log output)
     * */
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException
    {
        try(DataOutputStream outputStream = new DataOutputStream(out))
        {
            outputStream.writeInt(tasks.size());
            Iterator<Task> taskIterator = tasks.iterator();
            while (taskIterator.hasNext()) {
                Task task = taskIterator.next();
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeBoolean(task.isActive());
                outputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    outputStream.writeLong(task.getStartTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                    outputStream.writeLong(task.getEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                } else {
                    outputStream.writeLong(task.getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                }
            }
        } catch (IOException e) {
            Main.logger.error("Запис даних у потік у бінарному форматі неможливий.");
        }
    }
    /**
     * Method that reads tasks from a stream into a given task list
     * @param tasks is the list of tasks
     * @param in - is object of the class that defines the byte stream of input
     * @throws IOException (log output)
     * */
    public static void read(AbstractTaskList tasks, InputStream in) throws IOException
    {
        try(DataInputStream inputStream = new DataInputStream(in)) {
            int size = inputStream.readInt();
            while (size > 0) {
                int titleSize = inputStream.readInt();
                String title = inputStream.readUTF();
                boolean active = inputStream.readBoolean();
                int interval = inputStream.readInt();
                Task task;
                if (interval != 0) {
                    LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(inputStream.readLong()),
                            ZoneId.systemDefault());
                    LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(inputStream.readLong()),
                            ZoneId.systemDefault());
                    task = new Task(title, start, end, interval);
                } else {
                    LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(inputStream.readLong()),
                            ZoneId.systemDefault());
                    task = new Task(title, time);
                }
                task.setActive(active);
                tasks.add(task);
                size--;
            }
        }
        catch (IOException e) {
            Main.logger.error("Читання даних з бінарного потоку неможливе.");
        }
    }
    /**
     * Method that writes tasks from a list to a file
     * @param tasks is the list of tasks
     * @param file that stores a list of existing tasks
     * @exception catch IOException (log output)
     * */
    public static void writeBinary(AbstractTaskList tasks, File file)
    {
        try (FileOutputStream fileOutput = new FileOutputStream(file)) {
            write(tasks, fileOutput);
            fileOutput.flush();
        } catch (IOException e) {
            Main.logger.error("Запис задач із списку у файл неможливий.");
        }
    }
    /**
     * Method that reads tasks from a file into a task list
     * @param tasks is the list of tasks
     * @param file that stores a list of existing tasks
     * @exception catch IOException (log output)
     * */
    public static void readBinary(AbstractTaskList tasks, File file)
    {
        try (FileInputStream fileInput = new FileInputStream(file)) {
            read(tasks, fileInput);
        } catch (IOException e) {
            Main.logger.error("Читання задач із файлу неможливе.");
        }
    }
    /**
     * Method that writes tasks from a list to a stream in JSON format
     * @param tasks is the list of tasks
     * @param out for to record a symbolic data stream
     * @throws IOException (log output)
     * */
    public static void write(AbstractTaskList tasks, Writer out) throws IOException
    {
        Gson gson = new Gson();
        ArrayTaskList list = new ArrayTaskList();
        tasks.getStream().forEach(list::add);
        gson.toJson(list, out);
        out.flush();
    }
    /**
     * Method that reads tasks from a stream into a list
     * @param tasks is the list of tasks
     * @param in for to reading a symbolic data stream
     * */
    public static void read(AbstractTaskList tasks, Reader in)
    {
        Gson gson = new Gson();
        ArrayTaskList list = gson.fromJson(in, ArrayTaskList.class);
        list.getStream().forEach(tasks::add);
    }
    /**
     * Method that writes tasks to a file in JSON format
     * @param tasks is the list of tasks
     * @param file that stores a list of existing tasks
     * @exception catch IOException (log output)
     * */
    public static void writeText(AbstractTaskList tasks, File file)
    {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter(file)) {
            write(tasks, fileWriter);
            fileWriter.flush();
        } catch (IOException e) {
            Main.logger.error("Запис задач у файл неможливий.");
        }
    }
    /**
     * Method that reads tasks from a file
     * @param tasks is the list of tasks
     * @param file that stores a list of existing tasks
     * @exception catch IOException (log output)
     * */
    public static void readText(AbstractTaskList tasks, File file)
    {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(file)) {
            read(tasks, fileReader);
        } catch (IOException e) {
            Main.logger.error("Читання задач з файлу неможливе.");
        }
    }
}