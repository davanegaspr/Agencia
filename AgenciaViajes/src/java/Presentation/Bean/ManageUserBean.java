/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageUser;
import BusinessLogic.Controller.Util;
import DataAccess.Entity.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
@ManagedBean(name="manageUser")
@RequestScoped
public class ManageUserBean {
    private String firstname;
    private String lastname;
    private String role;
    private String phone;
    private String message;
    private double balance;
    private String oldPassword;
    private String newPassword;
    private String newPassword2; 
    private String email;
    private String password;
    private String password2;
    private String username;
    private String documentType;
    private String document;
    ManageUser manageUser = new ManageUser();
    private ArrayList<User> usersList = manageUser.getUsers();
    
   /**
     * Creates a new instance of ManageUserBean
     */
    public ManageUserBean() {
    }
    
    public void updateUser() throws  IOException, NoSuchAlgorithmException {
        ManageUser manageUser = new ManageUser(); 
        if(getFirstname() == null){
            setMessage("Se requiere un Nombre");
        }else if(getLastname() == null){
            setMessage("Se requiere un Apellido");
        }else if(!getPhone().matches("[0-9]{10}")) {
            setMessage("El número de celular no es valido");            
        }else if(getPhone().matches("[0-9]{10}") && !getFirstname().equals("null") && !getLastname().equals("null")){            
            manageUser.updateUser(getFirstname(), getLastname(),getRole(), getPhone());
            manageUser.renderProfile();            
        }  
    }
    
    public void eliminateUser(Long userId) throws  IOException, NoSuchAlgorithmException {
        manageUser = new ManageUser();
        if(manageUser.eliminateUser(userId)){
            setMessage("Usuario eliminado");
            manageUser.renderShowUsers();
            
        }
        else setMessage("No ha podido eliminarse el usuario");
        
    }
    public void updateBalance() throws  IOException, NoSuchAlgorithmException {
        manageUser = new ManageUser();         
        if(getBalance()>= 0){            
            manageUser.updateBalance(getBalance());
            manageUser.renderIndex();            
        }else{
            setMessage("El valor ingresado no es valido");            
        }  
    }
    
    public void updatePassword() throws  IOException, NoSuchAlgorithmException, SQLException {
        ManageUser manageUser = new ManageUser();         
        HttpSession session = Util.getSession();
        String email = (String)session.getAttribute("email");
        if(manageUser.passwordCheck(getNewPassword(),getNewPassword2()) && manageUser.login(email, getOldPassword())){            
            manageUser.updatePassword(manageUser.sha256(getNewPassword()));
            manageUser.renderIndex();            
        }else if(!manageUser.login(email, getOldPassword())){
            setMessage("La contraseña antigua no es correcta");
        }else if(manageUser.passwordCheck(getNewPassword(),getNewPassword2()) == false){
            setMessage("Las contraseñas no coinciden");
            //manageUser.renderSignup();
        } 
    }
    
    public void renderEdit(Long userId) throws  IOException, NoSuchAlgorithmException {
        HttpSession session = Util.getSession(); 
        manageUser = new ManageUser();
        session.setAttribute("userIdEdit", userId);
        manageUser.renderUserEdit();   
    }
    
    public void editUser() throws  IOException, NoSuchAlgorithmException {
        manageUser = new ManageUser();
        String pattern = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";        
        if(!manageUser.validateEmail(getEmail()) && !manageUser.validateUsername(getUsername()) && getEmail().matches(pattern) && getPhone().matches("[0-9]{10}")){            
            manageUser.editUser(getUsername(), getFirstname(), getLastname(), manageUser.sha256(getPassword()), getEmail(),getRole(), getPhone(),(long)getBalance(), getDocumentType(), getDocument());
            setMessage("Usuario editado");
            manageUser.renderShowUsers();
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
    
    public void createUser() throws  IOException, NoSuchAlgorithmException {
        manageUser = new ManageUser();
        String pattern = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";        
        if(!manageUser.validateEmail(getEmail()) && !manageUser.validateUsername(getUsername()) && getEmail().matches(pattern) && getPhone().matches("[0-9]{10}")){            
            manageUser.createUser2(getUsername(), getFirstname(), getLastname(), manageUser.sha256(getPassword()), getEmail(),getRole(), getPhone(),(long)0, getDocumentType(), getDocument());
            manageUser.renderIndex();            
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

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the newPassword2
     */
    public String getNewPassword2() {
        return newPassword2;
    }

    /**
     * @param newPassword2 the newPassword2 to set
     */
    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    /**
     * @return the usersList
     */
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    /**
     * @param usersList the usersList to set
     */
    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
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
   
}
