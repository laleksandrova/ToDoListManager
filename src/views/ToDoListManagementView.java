package views;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import entities.*;
import repositories.*;
import services.*;
import tools.*;

public class ToDoListManagementView {

    public void Run() throws IOException{
        while(true){
            MenuEnumeration choice = RenderMenu();

            switch (choice){

                case List:{
                    List();
                    break;
                }
                case Add: {
                    Add();
                    break;
                }
                case Edit: {
                    Edit();
                    break;
                }
                case Delete: {
                    Delete();
                    break;
                }
                case View: {
                    View();
                    break;
                }
                case Share:{
                    Share();
                }
                case Exit:{
                    return;
                }
            }
        }
    }



    private MenuEnumeration RenderMenu() throws IOException{

        while (true){
            ConsoleManager.Clear();

            ConsoleManager.WriteLine("[L]ist of ToDoLists");
            ConsoleManager.WriteLine("[A]dd a ToDoList");
            ConsoleManager.WriteLine("[E]dit a ToDoList");
            ConsoleManager.WriteLine("[D]elete a ToDoList");
            ConsoleManager.WriteLine("[V]iew a ToDList");
            ConsoleManager.WriteLine("[S]hare a ToDList");
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
                case "E":{
                    return MenuEnumeration.Edit;
                }
                case "D":{
                    return MenuEnumeration.Delete;
                }
                case "V":{
                    return MenuEnumeration.View;
                }
                case "S":{
                    return MenuEnumeration.Share;
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
        ConsoleManager.WriteLine("Add a ToDoList");

        ToDoList item = new ToDoList();

        ConsoleManager.Write("Title: ");
        item.setTitle(ConsoleManager.ReadLine());

        item.setcreationDate(LocalDateTime.now());
        item.setcreatorId(AuthenticationServices.getInstance().getLoggedUser().getId());
        item.setlastChangeDate(LocalDateTime.now());
        item.setuserMadeLastChangeId(AuthenticationServices.getInstance().getLoggedUser().getId());

        ToDoListRepository toDoListRepo = new ToDoListRepository("toDoLists.txt");
        toDoListRepo.Add(item);

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("Item added successfully");
        ConsoleManager.WriteLine("Press [Enter] to continue");
        ConsoleManager.ReadLine();
    }

    private void List() throws NumberFormatException, IOException{
        ConsoleManager.Clear();
        ConsoleManager.WriteLine("List of ToDoLists");

        ToDoListRepository toDoListRepo = new ToDoListRepository("toDoLists.txt");
        ArrayList<ToDoList> toDoLists = toDoListRepo.GetAll();

        for(int i=0;i<toDoLists.size();i++){

            ToDoList toDoList = toDoLists.get(i);

            ConsoleManager.Write("ID: ");
            ConsoleManager.WriteLine(toDoList.getId());
            ConsoleManager.Write("Title: ");
            ConsoleManager.WriteLine(toDoList.getTitle());

            toDoList.setcreationDate(LocalDateTime.now());
            toDoList.setcreatorId(AuthenticationServices.getInstance().getLoggedUser().getId());
            toDoList.setlastChangeDate(LocalDateTime.now());
            toDoList.setuserMadeLastChangeId(AuthenticationServices.getInstance().getLoggedUser().getId());

            ConsoleManager.WriteLine("---------------------------------");
        }
        ConsoleManager.WriteLine("Press [Enter] to continue");
        ConsoleManager.ReadLine();
    }

    private void Edit() throws NumberFormatException, IOException{

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("Edit a ToDoList");

        ToDoListRepository toDoListRepo = new ToDoListRepository("toDoLists.txt");
        ArrayList<ToDoList> toDoLists = toDoListRepo.GetAll();

        for(int i=0;i<toDoLists.size();i++){

            ToDoList toDoList = toDoLists.get(i);

            ConsoleManager.Write(toDoList.getTitle() + " ( " + toDoList.getId() + " )\t");

            if(i > 0 && i % 5 == 0)
                ConsoleManager.WriteLine();
        }

        ConsoleManager.WriteLine();
        ConsoleManager.Write("Enter ID of a ToDoList: ");
        int id = Integer.parseInt(ConsoleManager.ReadLine());

        ToDoList toDoList = toDoListRepo.GetById(id);

        ConsoleManager.Write("Title ( " + toDoList.getTitle() + " ): ");
        toDoList.setTitle(ConsoleManager.ReadLine());

        toDoList.setlastChangeDate(LocalDateTime.now());
        toDoList.setuserMadeLastChangeId(AuthenticationServices.getInstance().getLoggedUser().getId());

        toDoListRepo.Edit(toDoList);

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("Item updated successfully");
        ConsoleManager.WriteLine("Press [Enter] to continue");
        ConsoleManager.ReadLine();
    }

    private void Delete() throws NumberFormatException, IOException{

        ConsoleManager.Clear();

        ConsoleManager.WriteLine("Delete a ToDoList");

        ToDoListRepository toDoListRepo = new ToDoListRepository("toDoLists.txt");
        ArrayList<ToDoList> toDoLists = toDoListRepo.GetAll();

        for(int i=0;i<toDoLists.size();i++){

            ToDoList toDoList = toDoLists.get(i);

            ConsoleManager.Write(toDoList.getTitle() + " ( " + toDoList.getId() + " )\t");

            if (i > 0 && i % 5 == 0){
                ConsoleManager.WriteLine();
            }
        }

        ConsoleManager.WriteLine();
        ConsoleManager.Write("Enter ID of a ToDoList: ");
        int id = Integer.parseInt(ConsoleManager.ReadLine());

        ToDoList toDoList = toDoListRepo.GetById(id);
        toDoListRepo.Delete(toDoList);

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("Item deleted successfully");
        ConsoleManager.WriteLine("Press [Enter] to continue");
        ConsoleManager.ReadLine();
    }

    private void View() throws NumberFormatException, IOException{

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("View a ToDoList");

        ToDoListRepository toDoListRepo = new ToDoListRepository("toDoLists.txt");
        ArrayList<ToDoList> toDoLists = toDoListRepo.GetAll();

        for(int i=0;i<toDoLists.size();i++) {

            ToDoList toDoList = toDoLists.get(i);

            ConsoleManager.Write(toDoList.getTitle() + " ( " + toDoList.getId() + " )\t");
            if (i > 0 && i % 5 == 0)
                ConsoleManager.WriteLine();
        }

        ConsoleManager.WriteLine();
        ConsoleManager.Write("Enter ID of a ToDoList: ");
        int id = Integer.parseInt(ConsoleManager.ReadLine());

        ToDoList toDoList = toDoListRepo.GetById(id);

        ConsoleManager.Clear();

        TaskManagementView taskManagementView = new TaskManagementView(toDoList);
        taskManagementView.Run();
    }

    private void Share() throws NumberFormatException, IOException {
        ConsoleManager.Clear();
        ConsoleManager.WriteLine("View a ToDoList");

        ToDoListRepository toDoListRepo = new ToDoListRepository("toDoLists.txt");
        ArrayList<ToDoList> toDoLists = toDoListRepo.GetAll();

        for(int i=0;i<toDoLists.size();i++) {

            ToDoList toDoList = toDoLists.get(i);

            ConsoleManager.Write(toDoList.getTitle() + " ( " + toDoList.getId() + " )\t");
            if (i > 0 && i % 5 == 0)
                ConsoleManager.WriteLine();
        }

        ConsoleManager.WriteLine();
        ConsoleManager.Write("Enter ID of a ToDoList: ");
        int id = Integer.parseInt(ConsoleManager.ReadLine());

        ToDoList toDoList = toDoListRepo.GetById(id);

        ConsoleManager.Clear();

        ShareManagementView userToToDoListView = new ShareManagementView(toDoList);
        userToToDoListView.Run();
    }

}
