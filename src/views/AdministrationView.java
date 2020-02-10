package views;

import  java.io.*;
import tools.*;

public class AdministrationView {

    public void Run() throws IOException{

        while (true){
            ConsoleManager.Clear();

            ConsoleManager.WriteLine("[U]ser Management");
            ConsoleManager.WriteLine("To[D]oList Management");
            ConsoleManager.WriteLine("E[x]it");

            ConsoleManager.Write(">");
            String choice = ConsoleManager.ReadLine();

            switch (choice.toUpperCase()){
                case "U":{
                    UsersManagement();
                    break;
                }
                case "D":{
                    ToDoListManagement();
                    break;
                }
                case "X":{
                    return;
                }
                default:{
                    ConsoleManager.Clear();
                    ConsoleManager.WriteLine("Invalid choice!");
                    ConsoleManager.WriteLine("Press [Enter] to continue");
                    ConsoleManager.ReadLine();
                    break;
                }
            }
        }
    }

    private void UsersManagement() throws IOException{
        UserManagementView view = new UserManagementView();
        view.Run();
    }

    private void ToDoListManagement() throws IOException{
        ToDoListManagementView view = new ToDoListManagementView();
        view.Run();
    }

}
