/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import BusinessLogic.Controller.Util;
import java.util.Date;
import DataAccess.Entity.Plan;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import javax.persistence.*;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class PlanDAO {
    
    public Plan persist(Plan plan){
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("agenciaPU");
        em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try{
            em.persist(plan);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
            return plan;
        }       
    }


    public void query(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select idPlan, name, price, dateStart, dateEnd, destination from plan where name= ? ");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                HttpSession session = Util.getSession();
                session.setAttribute("idPlan", rs.getLong("idPlan"));  
                session.setAttribute("name", rs.getString("name"));
                session.setAttribute("price", rs.getString("price"));
                session.setAttribute("dateStart", rs.getString("dateStart"));
                session.setAttribute("dateEnd", rs.getString("dateEnd"));
                session.setAttribute("destination", rs.getLong("destination"));
            }
            else {
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        }      
    }

    public boolean updatePlan(long l, String name, Long price, Date dateStart, Date dateEnd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
