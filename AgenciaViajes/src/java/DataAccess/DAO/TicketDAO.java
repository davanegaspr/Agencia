/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Plan;
import DataAccess.Entity.Tickets;
import DataAccess.Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Richar
 */
public class TicketDAO {

    public Tickets persist(Tickets ticket){
        EntityManager em;        
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("agenciaPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(ticket);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();            
        }finally{
            em.close();
            return ticket;
        }  
    }
    
    public Plan getPlan(long planId) {
        Connection con = null;
        PreparedStatement ps = null;
        Plan plan = new Plan();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from plan WHERE planId =? ");
            ps.setString(1, String.valueOf(planId));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                plan.setPlanId(rs.getLong("planId"));
                plan.setName(rs.getString("name"));
                plan.setDepartureCity(rs.getString("departureCity"));
                plan.setArrivalCity(rs.getString("arrivalCity"));
                plan.setDepartureDate(rs.getString("departureDate"));
                plan.setReturnDate(rs.getString("returnDate"));
                plan.setModeTransport(rs.getString("modeTransport"));
                plan.setBaseCostByAdult(rs.getDouble("baseCostByAdult"));
                plan.setBaseCostByChild(rs.getDouble("baseCostByChild"));
            }          
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        } 
        return plan;
    }

    public ArrayList<Tickets> getTicketsList(long userId, int status) {
        PlanDAO p = new PlanDAO();
        UserDAO u = new UserDAO();
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Tickets> ticketsList = new ArrayList<>();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from tickets WHERE user_userId = ? and Status= ?");
            ps.setString(1, String.valueOf(userId));
            ps.setInt(2,status);            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Tickets t = new Tickets(rs.getLong("idTicket"),(short)status, rs.getString("Date_Buy"), rs.getString("Date_Start"), rs.getFloat("price"));              
                t.setPlanplanId(p.getPlan(rs.getLong("plan_planId")));
                t.setUseruserId(u.getUser(userId));
                ticketsList.add(t);
            }          
        } catch (Exception ex) {
            System.out.println("Error in login22222() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        } 
        return ticketsList;
   
    }
    
    public boolean eliminateTicket(Long ticketId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "DELETE FROM tickets WHERE idTicket = ?");          
            ps.setString(1, String.valueOf(ticketId));
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
