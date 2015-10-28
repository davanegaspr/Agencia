/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Plan;
import DataAccess.Entity.Tickets;
import DataAccess.Entity.User;
import java.io.Serializable;
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
public class TicketDAO implements Serializable{

    public boolean persist(long userId, long planId, String departureDate, short status, String dateBuy, float price, long ticketId){
        while(exist(ticketId)){
            ticketId++;
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "INSERT INTO agencia.tickets (idticket,`Date_Buy`, `Date_Start`, price, `Status`, `plan_planId`, `user_userId`) \n" +
"	VALUES (?,?,?,?,?,?,?)");     
            ps.setLong(1, ticketId);   
            ps.setString(2, dateBuy);
            ps.setString(3, departureDate);
            ps.setFloat(4, price);
            ps.setShort(5, status);
            ps.setLong(6, planId);   
            ps.setLong(7, userId);            
            int rs = ps.executeUpdate();
                return rs==1;  
                
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
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
    
    private boolean exist(long ticketId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from tickets where idTicket= ?");  
            ps.setString(1, String.valueOf(ticketId));
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
