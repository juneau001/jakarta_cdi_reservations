
package com.mycompany.reservations.controller;

import com.mycompany.reservations.bean.User;
import com.mycompany.reservations.util.JsfUtil;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Juneau
 */
@Named
@SessionScoped
public class AuthenticationController implements Serializable{
    
    final String TESTING_USER = "testuser";
    final String TESTING_PASSWORD = "cdi";
    
    private String username;
    private String password;
    private User currentUser;
   
    
    public AuthenticationController(){}
    
    /**
     * Authenticates the user, and if valid, returns to the main application view.
     * @return 
     */
    public String authenticate(){
        if(getUsername().equals(TESTING_USER) && password.equals(TESTING_PASSWORD)){
            setCurrentUser(new User());
            getCurrentUser().setUsername(getUsername());
            getCurrentUser().setAuthenticationTime(LocalDateTime.now());
                  
            return "reservations";
        } else {
            JsfUtil.addErrorMessage("Login Not Successful");
            return null;
        }
    }

    /**
     * @return the currentUser
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }


    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
  
    
}
