/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import BusinessLogic.Controller.Util;
import DataAccess.Entity.User;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Richar
 */
public class UserDAO implements Serializable{

    public static boolean validateEmail(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select email from user where email= ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            return rs.next(); // found
            
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }

    public static boolean validateUsername(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select username from user where username= ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            return rs.next(); // found
            
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }   
    }
    
    public boolean persist(String username, String firstname, String lastname, String password, String email, String role, String phone, double balance, String documentType, String document, long userId){
        
        while(exist(userId)){
            userId++;
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "INSERT INTO user (userId,balance, document, `documentType`, email, firstname, lastname, password, phone, `role`, username) \n" +
                    "	VALUES (?,?, ?, ?, ?, ?, ?,? ,?, ?, ?)");        
            ps.setLong(1, userId);
            ps.setDouble(2, balance);
            ps.setString(3, document);
            ps.setString(4,documentType);
            ps.setString(5,email);
            ps.setString(6,firstname);
            ps.setString(7,lastname);
            ps.setString(8,password);
            ps.setString(9,phone);
            ps.setString(10,role);            
            ps.setString(11,username);           
            int rs = ps.executeUpdate();
                return rs==1;
                
        } catch (Exception ex) {
            System.out.println("Error agregar usuario -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }  
    }  
    
    public static boolean login(String email, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select email, password from user where email= ? and password= ? ");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // found
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
    
   public static void query(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select userId, username, firstname, lastname, email, password, phone, balance, documentType, document, role from user where email= ? ");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                HttpSession session = Util.getSession();
                session.setAttribute("userId", rs.getLong("userId"));  
                session.setAttribute("username", rs.getString("username"));
                session.setAttribute("firstname", rs.getString("firstname"));
                session.setAttribute("lastname", rs.getString("lastname"));
                session.setAttribute("email", rs.getString("email"));
                session.setAttribute("phone", rs.getLong("phone"));
                session.setAttribute("balance", rs.getDouble("balance"));
                session.setAttribute("documentType", rs.getString("documentType"));
                session.setAttribute("document", rs.getString("document"));
                session.setAttribute("role", rs.getString("role"));
                session.setAttribute("password", rs.getString("password"));
                
            }
            else {
                System.out.println("No se pudo hacer la asignacion");
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        }      
    }

    public boolean updateUser(long userId, String firstname, String lastname, String role, String phone) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "UPDATE user SET firstname = ?, lastname =?, role = ?, phone =? WHERE userId = ?");            
            ps.setString(1,firstname);
            ps.setString(2,lastname);
            ps.setString(3,role);
            ps.setString(4,phone);
            ps.setString(5, String.valueOf(userId));
            int rs = ps.executeUpdate();
                return rs==1;
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }   
    }
    public boolean updateBalance(long userId, double balance) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "UPDATE user SET balance = ? WHERE userId = ?");            
            ps.setDouble(1,balance);
            ps.setString(2, String.valueOf(userId));
            int rs = ps.executeUpdate();
                return rs==1;
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }   
    }

    public boolean updatePassword(long userId, String newPassword) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "UPDATE user SET password = ? WHERE userId = ?");            
            ps.setString(1,newPassword);
            ps.setString(2, String.valueOf(userId));
            int rs = ps.executeUpdate();
                return rs==1;
                
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }  
    }
    
    public ArrayList<User> getUsers(){
        
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<User> userList = new ArrayList<>();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from user");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                userList.add(new User(rs.getLong("userId"), rs.getDouble("balance"), rs.getString("document"), rs.getString("documentType"), rs.getString("email"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getString("role"), rs.getString("username"), rs.getString("phone")));            
                
            }          
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        } 
        return userList;
    
    } 

    public boolean eliminateUser(Long userId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "DELETE FROM user WHERE userId = ?");          
            ps.setString(1, String.valueOf(userId));
            int rs = ps.executeUpdate();
                return rs==1;  
                
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        } 
    }

    public boolean editUser(Long userId, String username, String firstname, String lastname, String password, String email, String role, String phone, long l, String documentType, String document) {
    
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "UPDATE user SET firstname = ?, lastname =?, role = ?, phone =?, username =?, email =?, balance=?, documentType =?, document=?, password =? WHERE userId = ?");            
            ps.setString(1,firstname);
            ps.setString(2,lastname);
            ps.setString(3,role);
            ps.setString(4,phone);
            ps.setString(5,username);
            ps.setString(6,email);
            ps.setLong(7, l);
            ps.setString(8,documentType);
            ps.setString(9, document);
            ps.setString(10,password);
            ps.setString(11, String.valueOf(userId));
            int rs = ps.executeUpdate();
                return rs==1;
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        } 
        
    }

    public User getUser(long userId) {
        Connection con = null;
        PreparedStatement ps = null;
        User user = new User();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from user where userId = ?");  
            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                user.setUserId(userId);
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDocumentType(rs.getString("documentType"));
                user.setDocument(rs.getString("document"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setBalance(rs.getDouble("balance"));      
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }   
        return user;    
    }

    public void insertUser(User user) {       
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "INSERT INTO agencia.`user` (balance, document, `documentType`, email, firstname, lastname, password, phone, `role`, username)"
                    + "VALUES (0.0, '1234567890', 'CC', 'u@mail.com', 'user', 'user', '9b8769a4a742959a2d0298c36fb70623f2dfacda8436237df08d8dfd5b37374c', '0123456789', 'Estandar', 'user')");              
          /*  ps.setString(5,user.getFirstname());
            ps.setString(6,user.getLastname());
            ps.setString(9,user.getRole());
            ps.setString(8,user.getPhone());
            ps.setString(10,user.getUsername());
            ps.setString(4,user.getEmail());
            ps.setLong(1, (long)0.0);
            ps.setString(3,user.getDocumentType());
            ps.setString(2, user.getDocument());
            ps.setString(7,user.getPassword());*/
            int i = ps.executeUpdate();
            if(i==1) System.out.println("Agregadooooo");
            else System.out.println("No agregadooooooo");
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        }
        
    }

    private boolean exist(long userId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from user where userId = ?");  
            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();
            return rs.next();
            
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return true;
        } finally {
            Database.close(con);
        }   
        
    }
    
    
    

}
