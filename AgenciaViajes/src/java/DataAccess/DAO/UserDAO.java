/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import BusinessLogic.Controller.Util;
import DataAccess.Entity.User;
import java.sql.*;
import java.util.ArrayList;
import javax.persistence.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
public class UserDAO {

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
    
    public User persist(User user){
        EntityManager em;        
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("agenciaPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(user);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();            
        }finally{
            em.close();
            return user;
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
        System.out.println("Usuario a borrar" + userId);
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
    
    

}
