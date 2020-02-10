package repositories;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import entities.*;
import tools.*;

public class ToDoListRepository {

    private String fileName;

    public ToDoListRepository(String fileName) throws IOException{
        this.fileName = fileName;

        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    private int GetNextId() throws IOException{
        int nextId = 0;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){

            String value = "";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while((value = bufferedReader.readLine()) != null){

                ToDoList item = new ToDoList();
                item.setId(Integer.parseInt(value));

                item.setTitle(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorId(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                if (nextId < item.getId())
                    nextId = item.getId();

            }
        }
        return nextId + 1;
    }

    public ToDoList GetById(int id) throws NumberFormatException, IOException{

        ToDoList result = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String value = "";

            while ((value = bufferedReader.readLine()) != null){

                ToDoList item = new ToDoList();

                item.setId(Integer.parseInt(value));
                item.setTitle(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorId(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                if (item.getId() == id) {
                    result = item;
                    break;
                }
            }
        }
        return result;
    }

    public ArrayList<ToDoList> GetAll() throws NumberFormatException, IOException{

        ArrayList<ToDoList> result = new ArrayList<ToDoList>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){

            String value = "";

            while ((value = bufferedReader.readLine()) != null){

                ToDoList item = new ToDoList();

                item.setId(Integer.parseInt(value));
                item.setTitle(bufferedReader.readLine());

                LocalDateTime creationDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setcreationDate(creationDate);

                item.setcreatorId(Integer.parseInt(bufferedReader.readLine()));

                LocalDateTime lastChangeDate = LocalDateTime.parse(bufferedReader.readLine(), formatter);
                item.setlastChangeDate(lastChangeDate);

                item.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                result.add(item);
            }
        }
        return result;
    }

    public void Add(ToDoList item) throws IOException{

        item.setId(GetNextId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try(PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true))){

            printWriter.println(item.getId());
            printWriter.println(item.getTitle());

            printWriter.println(item.getcreationDate().format(formatter));
            printWriter.println(item.getcreatorId());
            printWriter.println(item.getlastChangeDate().format(formatter));
            printWriter.println(item.getuserMadeLastChangeId());
        }
    }

    public void Edit(ToDoList item) throws NumberFormatException, IOException{

        String tempFileName = ConfigurationManager.TempFilePrefix() + fileName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        PrintWriter printWriter = new PrintWriter(new FileWriter(tempFileName, true))){

            String value = "";

            while ((value = bufferedReader.readLine()) != null){

                ToDoList tempEntity = new ToDoList();

                tempEntity.setId(Integer.parseInt(value));

                tempEntity.setTitle(bufferedReader.readLine());

                tempEntity.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setcreatorId(Integer.parseInt(bufferedReader.readLine()));
                tempEntity.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                if(tempEntity.getId() != item.getId()){

                    printWriter.println(tempEntity.getId());

                    printWriter.println(tempEntity.getTitle());

                    printWriter.println(tempEntity.getcreationDate().format(formatter));
                    printWriter.println(tempEntity.getcreatorId());
                    printWriter.println(tempEntity.getlastChangeDate().format(formatter));
                    printWriter.println(tempEntity.getuserMadeLastChangeId());
                }
                else {
                    printWriter.println(item.getId());

                    printWriter.println(item.getTitle());

                    printWriter.println(item.getcreationDate().format(formatter));
                    printWriter.println(item.getcreatorId());
                    printWriter.println(item.getlastChangeDate().format(formatter));
                    printWriter.println(item.getuserMadeLastChangeId());
                }
            }
        }
        File original = new File(fileName);
        File tempFile = new File(tempFileName);

        original.delete();
        tempFile.renameTo(original);
        tempFile.delete();
    }

    public void Delete(ToDoList item) throws FileNotFoundException, IOException{

        String tempFileName = ConfigurationManager.TempFilePrefix() + fileName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
             PrintWriter printWriter = new PrintWriter(new FileWriter(tempFileName, true))) {

            String value = "";
            while((value = bufferedReader.readLine()) != null) {

                ToDoList tempEntity = new ToDoList();
                tempEntity.setId(Integer.parseInt(value));

                tempEntity.setTitle(bufferedReader.readLine());

                tempEntity.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setcreatorId(Integer.parseInt(bufferedReader.readLine()));
                tempEntity.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                tempEntity.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                if (tempEntity.getId() != item.getId()) {
                    printWriter.println(tempEntity.getId());

                    printWriter.println(item.getcreationDate().format(formatter));
                    printWriter.println(item.getcreatorId());
                    printWriter.println(item.getlastChangeDate().format(formatter));
                    printWriter.println(item.getuserMadeLastChangeId());
                }
            }
        }
        File original = new File(fileName);
        File tempFile = new File(tempFileName);

        original.delete();
        tempFile.renameTo(original);
        tempFile.delete();
    }

    public ArrayList<ToDoList> GetAll(int parentId) throws NumberFormatException, IOException{

        ArrayList<ToDoList> result = new ArrayList<ToDoList>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String value = "";

            while ((value = bufferedReader.readLine()) != null){

                ToDoList toDoList = new ToDoList();

                toDoList.setId(Integer.parseInt(value));
                toDoList.setTitle(bufferedReader.readLine());

                toDoList.setcreationDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                toDoList.setcreatorId(Integer.parseInt(bufferedReader.readLine()));
                toDoList.setlastChangeDate(LocalDateTime.parse(bufferedReader.readLine(), formatter));
                toDoList.setuserMadeLastChangeId(Integer.parseInt(bufferedReader.readLine()));

                if (toDoList.getId() == parentId) {
                    result.add(toDoList);
                    break;
                }
            }
        }
        return result;
    }
}
