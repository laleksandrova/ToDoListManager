package entities;

import views.TaskStatusEnumeration;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private int toDoListId;
    private String title;
    private String description;

    private LocalDateTime creationDate;
    private int creatorId;
    private LocalDateTime lastChangeDate;
    private int userMadeLastChangeId;

    private TaskStatusEnumeration status;

    public int getId() {return id;}
    public  void setId(int id) {this.id = id;}

    public int gettoDoListId() {
        return toDoListId;
    }
    public void settoDoListId(int toDoListId) {
        this.toDoListId = toDoListId;
    }

    public String gettitle(){return title;}
    public void settitle(String title) {this.title = title;}

    public String getdescription(){return description;}
    public void setdescription(String description) {this.description = description;}

    public LocalDateTime getcreationDate(){ return this.creationDate; }
    public void setcreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    public int getcreatorId(){ return this.creatorId; }
    public void setcreatorId(int creatorId){
        this.creatorId = creatorId;
    }

    public LocalDateTime getlastChangeDate(){ return this.lastChangeDate; }
    public void setlastChangeDate(LocalDateTime lastChangeDate){
        this.lastChangeDate = lastChangeDate;
    }

    public int getuserMadeLastChangeId(){ return this.userMadeLastChangeId; }
    public void setuserMadeLastChangeId(int userMadeLastChangeId){
        this.userMadeLastChangeId = userMadeLastChangeId;
    }

    public TaskStatusEnumeration getStatus(){return status;}
    public void setStatus(TaskStatusEnumeration status) {this.status = status;}

}
