/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageUser;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Richar
 */
@ManagedBean
@ViewScoped
public class loginUserBean {
    private String email;
    private String password;
    private String message;
    
    

    /**
     * Creates a new instance of loginUserBean
     */
    public loginUserBean() {
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void loginUser() throws SQLException {
        ManageUser manageUser = new ManageUser(); 
        if( manageUser.login(getEmail(), getPassword()) ) manageUser.renderIndex();
        else setMessage("Correo y/o contraseña incorrectos, intente nuevamente");
    }
    public void logoutUser() throws SQLException {
        ManageUser manageUser = new ManageUser();        
        manageUser.logout();
    }    

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
