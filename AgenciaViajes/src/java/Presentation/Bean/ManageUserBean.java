/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageUser;
import BusinessLogic.Controller.Util;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
@ManagedBean
@RequestScoped
public class ManageUserBean {
    private String firstname;
    private String lastname;
    private String role;
    private String phone;
    private String message;
    private double balance;
   /**
     * Creates a new instance of ManageUserBean
     */
    public ManageUserBean() {
    }
    
    public void updateUser() throws  IOException, NoSuchAlgorithmException {
        ManageUser manageUser = new ManageUser();         
        if(getPhone().matches("[0-9]{10}")){            
            manageUser.updateUser(getFirstname(), getLastname(),getRole(), getPhone());
            manageUser.renderProfile();            
        }else if(!getPhone().matches("[0-9]{10}")) {
            setMessage("El número de celular no es valido");            
        }
    
    }
    
    public void seeTrips(){}
    public void setBalance(){}

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    
}
