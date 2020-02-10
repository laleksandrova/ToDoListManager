package entities;

public class Share {

    private int id;
    private int userId;
    private int toDoListId;

    public int getId(){ return this.id; }
    public void setId(int id){
        this.id = id;
    }

    public int getuserId(){ return this.userId; }
    public void setuserId(int userId){
        this.userId = userId;
    }

    public int gettoDoListId(){ return this.toDoListId; }
    public void settoDoListId(int toDoListId){
        this.toDoListId = toDoListId;
    }
}
