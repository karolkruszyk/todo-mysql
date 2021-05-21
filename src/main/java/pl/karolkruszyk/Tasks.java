package pl.karolkruszyk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Tasks {
    private List<Task> taskList = new ArrayList<>();

    public Tasks() {
        ResultSet sentenceResult = QueryExecutor.executeSelect("SELECT id, sentence, deadline FROM tasks");
        int id = 0;
        String sentence = null;
        String deadline = null;
        while (true){
            try {
                if (sentenceResult.next()){
                    id = sentenceResult.getInt("id");
                    sentence = sentenceResult.getString("sentence");
                    deadline = sentenceResult.getString("deadline");
                }
                else {
                    break;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            Task task = new Task(id, sentence, deadline);
            taskList.add(task);
        }
    }

    public void addTask() {
        String sentence = getSentenceFromUser();
        String deadlineString = getDateFromUser();
        String insertQuery = "INSERT INTO tasks(sentence, deadline) VALUES ('" + sentence +"', '" + deadlineString +"');";
        QueryExecutor.executeQuery(insertQuery);
        int id = getTaskId(sentence);
        Task task = new Task(id, sentence, deadlineString);
        taskList.add(task);
    }

    public void displayTaskList() {
        for (Task task : taskList){
            System.out.println(task);
        }
    }

    public void deleteTask() {
        displayTaskList();
        System.out.print("Wybierz ID: ");
        Scanner keyboard = new Scanner(System.in);
        int toDelete = keyboard.nextInt();

        String deleteQuery = "DELETE FROM tasks WHERE id='" + toDelete + "'";
        QueryExecutor.executeQuery(deleteQuery);

        for (Task task : taskList) {
            if (task.getId() == toDelete){
                taskList.remove(task);
                return;
            }
        }
        System.out.println("Nie ma takiego elementu!");
    }

    private String getSentenceFromUser() {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Treść zadania: ");
        String sentence = keyboard.nextLine();
        return sentence;
    }

    private String getDateFromUser() {
        System.out.print("Data zadania (YYYY-MM-DD HH:MM): ");
        Scanner keyboard = new Scanner(System.in);
        String deadlineInput = keyboard.nextLine();

        String deadlineString = validateDeadlineDate(deadlineInput);
        return deadlineString;
    }

    private int getTaskId(String sentence){
        String selectQuery = "SELECT id FROM tasks WHERE sentence='" + sentence + "';";
        ResultSet selectResult = QueryExecutor.executeSelect(selectQuery);
        int taskId = 0;
        try {
            selectResult.next();
            taskId = selectResult.getInt("id");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return taskId;
    }

    private String validateDeadlineDate(String deadlineInput) {
        String hourMinuteFormat = "yyyy-MM-dd HH:mm";
        String dayMonthYearFormat = "yyyy-MM-dd";
        SimpleDateFormat formatter;
        Date deadline;
        String deadlineString = null;
        if (deadlineInput.length() > dayMonthYearFormat.length()) {
            formatter = new SimpleDateFormat(hourMinuteFormat);
        } else {
            formatter = new SimpleDateFormat(dayMonthYearFormat);
        }
        try {
            deadline = formatter.parse(deadlineInput);
            deadlineString = formatter.format(deadline);
            return deadlineString;
        }
        catch (java.text.ParseException exception) {
            System.out.println("Nieprawidłowy format daty");
            return getDateFromUser();
        }
    }
}
