package entities;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime creationDate;
    private int creatorID;
    private LocalDateTime lastChangeDate;
    private int userMadeLastChangeId;
    private boolean isAdmin;

    public int getId(){ return this.id; }
    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public LocalDateTime getcreationDate(){ return this.creationDate; }
    public void setcreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    public int getcreatorID(){ return this.creatorID; }
    public void setcreatorID(int creatorID){
        this.creatorID = creatorID;
    }

    public LocalDateTime getlastChangeDate(){ return this.lastChangeDate; }
    public void setlastChangeDate(LocalDateTime lastChangeDate){
        this.lastChangeDate = lastChangeDate;
    }

    public int getuserMadeLastChangeId(){ return this.userMadeLastChangeId; }
    public void setuserMadeLastChangeId(int userMadeLastChangeId){
        this.userMadeLastChangeId = userMadeLastChangeId;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

