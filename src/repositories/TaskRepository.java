package repositories;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import entities.Task;
import entities.ToDoList;
import tools.*;
import views.TaskStatusEnumeration;

public class TaskRepository {

    private String fileName;

    public TaskRepository(String fileName) throws IOException{
        this.fileName = fileName;

        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    private int GetNextId() throws IOException{

        int nextId = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String value = "";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while ((value = bufferedReader.readLine()) != null){

                Task item = new Task();
                item.setId(Integer.parseInt(value));

                item.settoDoListId(Integer.parseInt(bufferedReader.readLine()));
                item.settitle(bufferedReader.readLine());
                item.setdescription(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorId(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                String valueStatus = bufferedReader.readLine();
                TaskStatusEnumeration status = Enum.valueOf(TaskStatusEnumeration.class, valueStatus);
                item.setStatus(status);

                if (nextId < item.getId())
                    nextId = item.getId();
            }
        }
        return nextId + 1;
    }

    public Task GetById(int id) throws NumberFormatException, IOException {

        Task result = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String value = "";

            while((value = bufferedReader.readLine()) != null) {

                Task item = new Task();

                item.setId(Integer.parseInt(value));

                item.settoDoListId(Integer.parseInt(bufferedReader.readLine()));
                item.settitle(bufferedReader.readLine());
                item.setdescription(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorId(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                String valueStatus = bufferedReader.readLine();
                TaskStatusEnumeration status = Enum.valueOf(TaskStatusEnumeration.class, valueStatus);
                item.setStatus(status);

                if (item.getId() == id) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }

    public ArrayList<Task> GetAll() throws NumberFormatException, IOException {

        ArrayList<Task> result = new ArrayList<Task>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String value = "";
            while((value = bufferedReader.readLine()) != null) {

                Task item = new Task();

                item.setId(Integer.parseInt(value));

                item.settoDoListId(Integer.parseInt(bufferedReader.readLine()));
                item.settitle(bufferedReader.readLine());
                item.setdescription(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorId(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                String valueStatus = bufferedReader.readLine();
                TaskStatusEnumeration status = Enum.valueOf(TaskStatusEnumeration.class, valueStatus);
                item.setStatus(status);

                result.add(item);
            }
        }
        return result;
    }

    public void Add(Task item) throws IOException{

        item.setId(GetNextId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true))){
            printWriter.println(item.getId());

            printWriter.println(item.gettoDoListId());
            printWriter.println(item.gettitle());
            printWriter.println(item.getdescription());

            printWriter.println(item.getcreationDate().format(formatter));
            printWriter.println(item.getcreatorId());
            printWriter.println(item.getlastChangeDate().format(formatter));
            printWriter.println(item.getuserMadeLastChangeId());

            printWriter.println(item.getStatus());
        }
    }

    public void Edit(Task item) throws NumberFormatException, IOException{

        String tempFileName = ConfigurationManager.TempFilePrefix() + fileName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        PrintWriter printWriter = new PrintWriter(new FileWriter(tempFileName, true))){

            String value = "";

            while((value = bufferedReader.readLine()) != null){

                Task tempEntity = new Task();

                tempEntity.setId(Integer.parseInt(value));

                tempEntity.settoDoListId(Integer.parseInt(bufferedReader.readLine()));
                tempEntity.settitle(bufferedReader.readLine());
                tempEntity.setdescription(bufferedReader.readLine());

                tempEntity.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setcreatorId(Integer.parseInt(bufferedReader.readLine()));
                tempEntity.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                String valueStatus = bufferedReader.readLine();
                TaskStatusEnumeration status = Enum.valueOf(TaskStatusEnumeration.class, valueStatus);
                tempEntity.setStatus(status);

                if(tempEntity.getId() != item.getId()){

                    printWriter.println(tempEntity.getId());

                    printWriter.println(tempEntity.gettoDoListId());
                    printWriter.println(tempEntity.gettitle());
                    printWriter.println(tempEntity.getdescription());

                    printWriter.println(item.getcreationDate().format(formatter));
                    printWriter.println(item.getcreatorId());
                    printWriter.println(item.getlastChangeDate().format(formatter));
                    printWriter.println(item.getuserMadeLastChangeId());

                    printWriter.println(item.getStatus());
                }

                else{

                    printWriter.println(item.getId());

                    printWriter.println(item.gettoDoListId());
                    printWriter.println(item.gettitle());
                    printWriter.println(item.getdescription());

                    printWriter.println(item.getcreationDate().format(formatter));
                    printWriter.println(item.getcreatorId());
                    printWriter.println(item.getlastChangeDate().format(formatter));
                    printWriter.println(item.getuserMadeLastChangeId());

                    printWriter.println(item.getStatus());
                }
            }
        }

        File original = new File(fileName);
        File tempFile = new File(tempFileName);

        original.delete();
        tempFile.renameTo(original);
        tempFile.delete();
    }

    public void Delete(Task item) throws FileNotFoundException, IOException{

        String tempFileName = ConfigurationManager.TempFilePrefix() + fileName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            PrintWriter printWriter = new PrintWriter(new FileWriter(tempFileName, true))){

            String value = "";

            while((value = bufferedReader.readLine()) != null){

                Task tempEntity = new Task();

                tempEntity.setId(Integer.parseInt(value));
                tempEntity.settoDoListId(Integer.parseInt(bufferedReader.readLine()));
                tempEntity.settitle(bufferedReader.readLine());
                tempEntity.setdescription(bufferedReader.readLine());

                tempEntity.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setcreatorId(Integer.parseInt(bufferedReader.readLine()));
                tempEntity.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                String valueStatus = bufferedReader.readLine();
                TaskStatusEnumeration status = Enum.valueOf(TaskStatusEnumeration.class, valueStatus);
                tempEntity.setStatus(status);

                if(tempEntity.getId() != item.getId()){

                    printWriter.println(tempEntity.getId());
                    printWriter.println(tempEntity.gettoDoListId());
                    printWriter.println(tempEntity.gettitle());
                    printWriter.println(tempEntity.getdescription());

                    printWriter.println(item.getcreationDate().format(formatter));
                    printWriter.println(item.getcreatorId());
                    printWriter.println(item.getlastChangeDate().format(formatter));
                    printWriter.println(item.getuserMadeLastChangeId());

                    printWriter.println(item.getStatus());
                }
            }
        }

        File original = new File(fileName);
        File tempFile = new File(tempFileName);

        original.delete();
        tempFile.renameTo(original);
        tempFile.delete();
    }

    public ArrayList<Task> GetAll(int parentId) throws NumberFormatException, IOException {

        ArrayList<Task> result = new ArrayList<Task>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            String value = "";
            while((value = bufferedReader.readLine()) != null) {

                Task task = new Task();

                task.setId(Integer.parseInt(value));
                task.settoDoListId(Integer.parseInt(bufferedReader.readLine()));
                task.settitle(bufferedReader.readLine());
                task.setdescription(bufferedReader.readLine());

                task.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                task.setcreatorId(Integer.parseInt(bufferedReader.readLine()));
                task.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                task.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                String valueStatus = bufferedReader.readLine();
                TaskStatusEnumeration status = Enum.valueOf(TaskStatusEnumeration.class, valueStatus);
                task.setStatus(status);

                if (task.gettoDoListId() == parentId)
                    result.add(task);
            }
        }
        return result;
    }
}
