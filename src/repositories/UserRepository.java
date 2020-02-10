package repositories;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import  java.util.*;

import entities.*;
import services.AuthenticationServices;
import tools.*;

public class UserRepository {

    private String fileName;

    public UserRepository(String fileName) throws IOException{
        this.fileName = fileName;

        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();

            User user = new User();
            user.setUsername("admin");
            user.setPassword("adminpass");
            user.setFirstName("Administrator");
            user.setLastName("Administrator");

            user.setcreationDate(LocalDateTime.now());
            user.setcreatorID(0);
            user.setlastChangeDate(LocalDateTime.now());
            user.setuserMadeLastChangeId(0);

            user.setIsAdmin(true);

            Add(user);
        }
    }

    private int GetNextId() throws IOException {

        int nextId = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String value = "";
            while((value = bufferedReader.readLine()) != null) {

                User item = new User();
                item.setId(Integer.parseInt(value));

                item.setUsername(bufferedReader.readLine());
                item.setPassword(bufferedReader.readLine());
                item.setFirstName(bufferedReader.readLine());
                item.setLastName(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorID(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                item.setIsAdmin(Boolean.parseBoolean(bufferedReader.readLine()));

                if (nextId < item.getId())
                    nextId = item.getId();
            }
        }
        return nextId + 1;
    }

    public User GetById(int id) throws NumberFormatException, IOException {

        User result = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String value = "";
            while((value = bufferedReader.readLine()) != null) {

                User item = new User();
                item.setId(Integer.parseInt(value));

                item.setUsername(bufferedReader.readLine());
                item.setPassword(bufferedReader.readLine());
                item.setFirstName(bufferedReader.readLine());
                item.setLastName(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorID(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                item.setIsAdmin(Boolean.parseBoolean(bufferedReader.readLine()));

                if (item.getId() == id) {
                    result = item;
                    break;
                }
            }
        }

        return result;
    }

    public ArrayList<User> GetAll() throws NumberFormatException, IOException {

        ArrayList<User> result = new ArrayList<User>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String value = "";
            while((value = bufferedReader.readLine()) != null) {

                User item = new User();
                item.setId(Integer.parseInt(value));

                item.setUsername(bufferedReader.readLine());
                item.setPassword(bufferedReader.readLine());
                item.setFirstName(bufferedReader.readLine());
                item.setLastName(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorID(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                item.setIsAdmin(Boolean.parseBoolean(bufferedReader.readLine()));

                result.add(item);
            }
        }
        return result;
    }

    public void Add(User item) throws IOException{
        item.setId(GetNextId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true))){

            printWriter.println(item.getId());

            printWriter.println(item.getUsername());
            printWriter.println(item.getPassword());
            printWriter.println(item.getFirstName());
            printWriter.println(item.getLastName());

            printWriter.println(item.getcreationDate().format(formatter));
            printWriter.println(item.getcreatorID());
            printWriter.println(item.getlastChangeDate().format(formatter));
            printWriter.println(item.getuserMadeLastChangeId());

            printWriter.println(item.getIsAdmin());
        }
    }

    public void Edit(User item) throws NumberFormatException,IOException {

        String tempFileName = ConfigurationManager.TempFilePrefix() + fileName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            PrintWriter printWriter = new PrintWriter(new FileWriter(tempFileName, true))){

            String value = "";

            while ((value = bufferedReader.readLine()) != null){
                User tempEntity = new User();

                tempEntity.setId(Integer.parseInt(value));

                tempEntity.setUsername(bufferedReader.readLine());
                tempEntity.setPassword(bufferedReader.readLine());
                tempEntity.setFirstName(bufferedReader.readLine());
                tempEntity.setLastName(bufferedReader.readLine());

                tempEntity.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setcreatorID(Integer.parseInt(bufferedReader.readLine()));
                tempEntity.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                tempEntity.setIsAdmin(Boolean.parseBoolean(bufferedReader.readLine()));

                if (tempEntity.getId() != item.getId()){
                    printWriter.println(tempEntity.getId());

                    printWriter.println(tempEntity.getUsername());
                    printWriter.println(tempEntity.getPassword());
                    printWriter.println(tempEntity.getFirstName());
                    printWriter.println(tempEntity.getLastName());

                    printWriter.println(tempEntity.getcreationDate().format(formatter));
                    printWriter.println(tempEntity.getcreatorID());
                    printWriter.println(tempEntity.getlastChangeDate().format(formatter));
                    printWriter.println(tempEntity.getuserMadeLastChangeId());

                    printWriter.println(tempEntity.getIsAdmin());
                }
                else {
                    printWriter.println(item.getId());

                    printWriter.println(item.getUsername());
                    printWriter.println(item.getPassword());
                    printWriter.println(item.getFirstName());
                    printWriter.println(item.getLastName());

                    printWriter.println(item.getcreationDate().format(formatter));
                    printWriter.println(item.getcreatorID());
                    printWriter.println(item.getlastChangeDate().format(formatter));
                    printWriter.println(item.getuserMadeLastChangeId());

                    printWriter.println(item.getIsAdmin());
                }
            }
        }

        File original = new File(fileName);
        File tempFile = new File(tempFileName);

        original.delete();
        tempFile.renameTo(original);
        tempFile.delete();
    }

    public void Delete(User item) throws FileNotFoundException, IOException {

        String tempFileName = ConfigurationManager.TempFilePrefix() + fileName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
             PrintWriter printWriter = new PrintWriter(new FileWriter(tempFileName, true))) {

            String value = "";
            while((value = bufferedReader.readLine()) != null) {

                User tempEntity = new User();
                tempEntity.setId(Integer.parseInt(value));

                tempEntity.setUsername(bufferedReader.readLine());
                tempEntity.setPassword(bufferedReader.readLine());
                tempEntity.setFirstName(bufferedReader.readLine());
                tempEntity.setLastName(bufferedReader.readLine());

                tempEntity.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setcreatorID(Integer.parseInt(bufferedReader.readLine()));
                tempEntity.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                tempEntity.setIsAdmin(Boolean.parseBoolean(bufferedReader.readLine()));

                if (tempEntity.getId() != item.getId()) {

                    printWriter.println(tempEntity.getId());

                    printWriter.println(tempEntity.getUsername());
                    printWriter.println(tempEntity.getPassword());
                    printWriter.println(tempEntity.getFirstName());
                    printWriter.println(tempEntity.getLastName());

                    printWriter.println(item.getcreationDate().format(formatter));
                    printWriter.println(item.getcreatorID());
                    printWriter.println(item.getlastChangeDate().format(formatter));
                    printWriter.println(item.getuserMadeLastChangeId());

                    printWriter.println(tempEntity.getIsAdmin());
                }
            }
        }

        File original = new File(fileName);
        File tempFile = new File(tempFileName);

        original.delete();
        tempFile.renameTo(original);
        tempFile.delete();

    }

    public User GetByUsernameAndPassword(String username, String password) throws FileNotFoundException, IOException {

        User result = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String value = "";
            while((value = bufferedReader.readLine()) != null) {

                User user = new User();
                user.setId(Integer.parseInt(value));
                user.setUsername(bufferedReader.readLine());
                user.setPassword(bufferedReader.readLine());
                user.setFirstName(bufferedReader.readLine());
                user.setLastName(bufferedReader.readLine());

                user.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                user.setcreatorID(Integer.parseInt(bufferedReader.readLine()));
                user.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                user.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                user.setIsAdmin(Boolean.parseBoolean(bufferedReader.readLine()));

                if (user.getUsername().compareTo(username) == 0 && user.getPassword().compareTo(password) == 0) {
                    result = user;
                    break;
                }
            }
        }
        return result;
    }

}
