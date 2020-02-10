package views;

import java.io.*;
import java.util.*;

import entities.*;
import repositories.*;
import tools.*;

public class TaskToUserManagementView {

    private Task parent;

    public TaskToUserManagementView(Task parent) {this.parent = parent;}

    public void Run() throws IOException{

        while (true){

            MenuEnumeration choice = RenderMenu();

            switch(choice){

                case List: {
                    List();
                    break;
                }
                case Add: {
                    Add();
                    break;
                }
                case Delete: {
                    Delete();
                    break;
                }
                case Exit: {
                    return;
                }
            }
        }
    }

    private MenuEnumeration RenderMenu() throws NumberFormatException, IOException{

        TaskToUserRepository taskAssignRepository = new TaskToUserRepository("taskToUser.txt");

        while (true){

            ConsoleManager.Clear();

            ConsoleManager.WriteLine("Task Details:");
            ConsoleManager.Write("Title: ");
            ConsoleManager.WriteLine(parent.gettitle());
            ConsoleManager.WriteLine();

            ArrayList<TaskToUser> taskAssigns = taskAssignRepository.GetAll(parent.getId());

            for(int i = 0; i< taskAssigns.size(); i++){

                TaskToUser taskAssign = taskAssigns.get(i);

                ConsoleManager.WriteLine(taskAssign.getId());
                ConsoleManager.WriteLine(taskAssign.getTaskId());
                ConsoleManager.WriteLine(taskAssign.getUserId());

            }

            ConsoleManager.WriteLine("\n###############################\n");

            ConsoleManager.WriteLine("[L]ist Task Assign");
            ConsoleManager.WriteLine("[A]dd Task Assign");
            ConsoleManager.WriteLine("[D]elete Task Assign");
            ConsoleManager.WriteLine("E[x]it");

            ConsoleManager.Write(">");
            String choice = ConsoleManager.ReadLine();

            switch (choice.toUpperCase()){
                case "L":{
                    return MenuEnumeration.List;
                }
                case "A":{
                    return MenuEnumeration.Add;
                }
                case "D":{
                    return MenuEnumeration.Delete;
                }
                case "X":{
                    return MenuEnumeration.Exit;
                }
                default: {
                    ConsoleManager.Clear();
                    ConsoleManager.WriteLine("Invalid choice!");
                    ConsoleManager.WriteLine("Press [Enter] to continue");
                    ConsoleManager.ReadLine();
                    break;
                }
            }
        }
    }

    private void Add() throws IOException{

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("Add a task assign");

        TaskToUser item = new TaskToUser();

        TaskRepository taskRepo = new TaskRepository("tasks.txt");
        ArrayList<Task> tasks = taskRepo.GetAll();

        for(int i=0;i<tasks.size();i++){

            Task task = tasks.get(i);

            ConsoleManager.Write(task.gettitle() + " ( " + task.getId() + " )\t");

            if(i > 0 && i % 5 == 0)
                ConsoleManager.WriteLine();
        }
        ConsoleManager.WriteLine();
        ConsoleManager.Write("ID of a Task: ");
        item.setTaskId(Integer.parseInt(ConsoleManager.ReadLine()));

        UserRepository userRepo = new UserRepository("users.txt");
        ArrayList<User> users = userRepo.GetAll();

        for(int i=0;i<users.size();i++){

            User user = users.get(i);

            ConsoleManager.Write(user.getUsername() + " ( " + user.getId() + " )\t");

            if(i > 0 && i % 5 == 0)
                ConsoleManager.WriteLine();
        }

        ConsoleManager.WriteLine();
        ConsoleManager.Write("ID of a User: ");
        item.setUserId(Integer.parseInt(ConsoleManager.ReadLine()));

        TaskToUserRepository taskAssignRepository = new TaskToUserRepository("taskToUser.txt");
        taskAssignRepository.Add(item);

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("Item added successfully");
        ConsoleManager.WriteLine("Press [Enter] to continue");
        ConsoleManager.ReadLine();
    }

    private void List() throws NumberFormatException, IOException{

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("List of tasks assignments");

        TaskToUserRepository taskAssignRepository = new TaskToUserRepository("taskToUser.txt");
        ArrayList<TaskToUser> taskAssigns = taskAssignRepository.GetAll();

        for(int i = 0; i< taskAssigns.size(); i++){

            TaskToUser taskAssign = taskAssigns.get(i);

            ConsoleManager.Write("ID: ");
            ConsoleManager.WriteLine(taskAssign.getId());
            ConsoleManager.Write("ID of a task: ");
            ConsoleManager.WriteLine(taskAssign.getTaskId());
            ConsoleManager.Write("ID of a user: ");
            ConsoleManager.WriteLine(taskAssign.getUserId());

            ConsoleManager.WriteLine("---------------------------------");
        }

        ConsoleManager.WriteLine("Press [Enter] to continue");
        ConsoleManager.ReadLine();
    }

    private void Delete() throws NumberFormatException, IOException{

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("Delete Task Assignment");

        TaskToUserRepository taskAssignRepository = new TaskToUserRepository("taskToUser.txt");
        ArrayList<TaskToUser> taskAssigns = taskAssignRepository.GetAll();

        for(int i = 0; i< taskAssigns.size(); i++){

            TaskToUser taskAssign = taskAssigns.get(i);

            ConsoleManager.Write("ID of a task: " + taskAssign.getTaskId() + " ( " + taskAssign.getId() + " )\t");

            if (i > 0 && i % 5 == 0)
                ConsoleManager.WriteLine();
        }

        ConsoleManager.WriteLine();
        ConsoleManager.Write("Enter ID of task assignment: ");
        int id = Integer.parseInt(ConsoleManager.ReadLine());

        TaskToUser taskAssign = taskAssignRepository.GetById(id);
        taskAssignRepository.Delete(taskAssign);

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("Item deleted successfully");
        ConsoleManager.WriteLine("Press [Enter] to continue");
        ConsoleManager.ReadLine();
    }
}