/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import BusinessLogic.Controller.Util;
import DataAccess.Entity.User;
import java.sql.*;
import javax.persistence.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
public class UserDAO {
    
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
            if (rs.next()) // found
            {
                return true;
            }
            else {
                return false;
            }
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
            }
            else {
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        }
        
    }
}
