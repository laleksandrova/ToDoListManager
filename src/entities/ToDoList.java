package entities;

import java.time.LocalDateTime;

public class ToDoList {

    private int id;
    private String title;

    private LocalDateTime creationDate;
    private int creatorId;
    private LocalDateTime lastChangeDate;
    private int userMadeLastChangeId;

    public int getId() {return id;}
    public  void setId(int id) {this.id = id;}

    public String getTitle(){return title;}
    public void setTitle(String title) {this.title = title;}

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

}
