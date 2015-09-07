/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageUser;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Richar
 */
@ManagedBean
@ViewScoped
public class loginUserBean {
    private String email;
    private String password;
    
    

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
        manageUser.login(getEmail(), getPassword());
    }
    public void logoutUser() throws SQLException {
        ManageUser manageUser = new ManageUser();        
        manageUser.logout();
    }    
}
