/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UserDAO;
import DataAccess.Entity.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Richar
 */
public class ManageUser implements Serializable {
    /**
     *
     * @param username
     * @param firstname
     * @param lastname
     * @param password
     * @param email
     * @param role
     * @param phone
     * @param balance
     * @param documentType
     * @param document
     * @return
     * @throws java.io.IOException
     */
    
    private static final long serialVersionUID = 1L;
    public void createUser(String username, String firstname, String lastname, String password, String email, String role, long phone, double balance, String documentType, String document ) throws NoSuchAlgorithmException, IOException{
        
        User user = new User();
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRole(role);
        user.setBalance(balance);
        user.setDocumentType(documentType);
        user.setDocument(document);
        
        UserDAO userDAO = new UserDAO();
        User userE = userDAO.persist(user);
        if(userE != null){
                UserDAO.query(email);
                renderIndex();
            
           
                           
        }
        else{
            
        }   
    }
    
    public void renderIndex(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/index.xhtml");
            } catch (IOException e) {
            }
    
    }
    
    public void login(String email, String password) throws SQLException {
        boolean result = UserDAO.login(email, password);   
        UserDAO.query(email);       
        if (result) {
            // get Http Session and store username           
            renderIndex();
            
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
            String contextPath = origRequest.getContextPath();
            try {
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect(contextPath  + "/faces/signup.xhtml");
                } catch (IOException e) {
                }
        }
    } 
    public void logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        renderIndex();
    }   
    public String sha256(String base) {
         try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = base;
            md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);           
            return output;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return null;
    }
}
