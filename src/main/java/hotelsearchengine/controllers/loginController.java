package hotelsearchengine.controllers;

import hotelsearchengine.models.Person;
import hotelsearchengine.storage.DatabaseInterface;
import hotelsearchengine.storage.databaseHelper;

public class loginController {

    private Person loggedIn = null;
    private DatabaseInterface db;

    public loginController(DatabaseInterface DB){
        this.db = DB;
    }

    public Person login(String name, String password){
        Person loginTry = db.login(name, password);
        this.loggedIn = loginTry;
        return loginTry;
    }

    public void logout(){
        this.loggedIn = null;
    }

    public boolean isLoggedin(int personID){
        return(loggedIn.getId()==personID);
    }

    public int getLogged(){
        if (loggedIn==null) {
            return -1;
        }
        return loggedIn.getId();
    }
}
