/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageUser;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Richar
 */
@ManagedBean
@ViewScoped
public class CreateUserBean {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String password2;
    private String username;
    private String phone;
    private String role;
    private double balance;
    private String documentType;
    private String document;
    private String message;

    /**
     * Creates a new instance of CreateUserBean
     */
    
    public void CreateUserBean(){
    } 

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
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(long balance) {
        this.balance = balance;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the document
     */
    public String getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(String document) {
        this.document = document;
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
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2 the password2 to set
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    
    public void createUser() throws  IOException, NoSuchAlgorithmException {
        ManageUser manageUser = new ManageUser();
        String pattern = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
        
        if(manageUser.passwordCheck(getPassword(),getPassword2()) && !manageUser.validateEmail(getEmail()) && !manageUser.validateUsername(getUsername()) && getEmail().matches(pattern) && getPhone().matches("[0-9]{10}")){            
            manageUser.createUser(getUsername(), getFirstname(), getLastname(), manageUser.sha256(getPassword()), getEmail(),getRole(), getPhone(),(long)0, getDocumentType(), getDocument());
            manageUser.renderIndex();            
        }else if(manageUser.passwordCheck(getPassword(),getPassword2()) == false){
            setMessage("Las contraseñas no coinciden");
            //manageUser.renderSignup();
        }else if(manageUser.validateEmail(getEmail())){
            setMessage("El correo " + getEmail() +" ya esta en uso");
        }
        else if(manageUser.validateUsername(getUsername())){
            setMessage("El usuario " + getUsername() +" ya esta en uso");   
        }
        else if(!(getEmail().matches(pattern))) {
            setMessage("El correo electronico no tiene un formato valido");            
        }
        
        else if(!getPhone().matches("[0-9]{10}")) {
            setMessage("El número de celular no es valido");            
        }
    
    }
    
}
