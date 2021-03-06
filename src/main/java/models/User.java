package models;

import org.json.simple.JSONObject;

public class User {
    public String name;
    public String email;
    public String password;
    public String isAdmin;
    public String authToken;
    public String userID;

    public User(String name, String email, String password, String isAdmin){
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserAuthToken(String authToken){
        this.authToken= authToken;
    }

    public void setUserId(String userID){
        this.userID = userID;
    }

    public String getUserCredentialsAsJson(){
        JSONObject userJsonRepresentation = new JSONObject();
        userJsonRepresentation.put("email",this.email);
        userJsonRepresentation.put("password",this.password);
        return userJsonRepresentation.toJSONString();
    }

    public String getUserJsonRepresentationAsString(){
        JSONObject userJsonRepresentation = new JSONObject();
        userJsonRepresentation.put("nome", this.name);
        userJsonRepresentation.put("email", this.email);
        userJsonRepresentation.put("password", this.password);
        userJsonRepresentation.put("administrador", this.isAdmin);
        return userJsonRepresentation.toJSONString();
    }
}
