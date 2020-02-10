import java.io.*;

import services.AuthenticationServices;
import tools.ConsoleManager;
import views.*;

public class Program {

    public static void main(String[] args) throws IOException{

        AuthenticationView authView = new AuthenticationView();
        authView.Run();

        if(AuthenticationServices.getInstance().getLoggedUser() != null){
            AdministrationView view = new AdministrationView();
            view.Run();
        }
        else{
            UserManagementView view = new UserManagementView();
            view.Run();
        }

        ConsoleManager.Clear();
        ConsoleManager.WriteLine("End");
    }
}
