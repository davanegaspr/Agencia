/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import BusinessLogic.Controller.Util;
import DataAccess.Entity.Hotel;
import DataAccess.Entity.Plan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
public class PlanDAO {

    public ArrayList<Plan> getPlans() {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Plan> planList = new ArrayList<>();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from plan");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                planList.add(new Plan(rs.getLong("planId"), rs.getString("name"), rs.getString("departureCity"), rs.getString("arrivalCity"), rs.getString("departureDate"),rs.getString("returnDate"), rs.getString("modeTransport"), rs.getDouble("baseCostByAdult"), rs.getDouble("baseCostByChild")));              
            }          
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        } 
        return planList;
    
    }

    public boolean persist(String name, String departureCity, String arrivalCity, String departureDate, String returnDate, String modeTransport, double baseCostByAdult, double baseCostByChild, long hotelId) {
           
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "INSERT INTO agencia.plan (`arrivalCity`, `baseCostByAdult`, `baseCostByChild`, `departureCity`, `departureDate`, `ModeTransport`, `name`, `returnDate`, `hotel_hotelId`) \n" +
"	VALUES (?,?,?,?,?,?,?,?,?)"); 
            ps.setString(1,arrivalCity);
            ps.setDouble(2, baseCostByAdult);
            ps.setDouble(3, baseCostByChild);
            ps.setString(4,departureCity);
            ps.setString(5, departureDate);
            ps.setString(6, modeTransport);
            ps.setString(7,name);
            ps.setString(8, returnDate);           
            ps.setString(9, String.valueOf(hotelId));
            int rs = ps.executeUpdate();
                return rs==1;
        } catch (Exception ex) {
            System.out.println("Error in edit plan() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }

    public boolean eliminatePlan(long planId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "DELETE FROM plan WHERE planId = ?");          
            ps.setString(1, String.valueOf(planId));
            int rs = ps.executeUpdate();
                return rs==1;  
                
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }

    public boolean editPlan(long l, String name, String departureCity, String arrivalCity, String date, String date0, String modeTransport, Double baseCostByAdult, Double baseCostByChild, long hotelId) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "UPDATE plan SET name = ?, departureCity =?, arrivalCity = ?, departureDate =?, returnDate =?, modeTransport =?, baseCostByAdult =?, baseCostByChild =?, hotel_hotelId =?  WHERE planId = ?");            
            ps.setString(1,name);
            ps.setString(2,departureCity);
            ps.setString(3,arrivalCity);
            ps.setString(4, date);
            ps.setString(5, date0);
            ps.setString(6, modeTransport);
            ps.setDouble(7, baseCostByAdult);
            ps.setDouble(8, baseCostByChild);
            ps.setString(9, String.valueOf(hotelId));
            ps.setString(10, String.valueOf(l));
            int rs = ps.executeUpdate();
                return rs==1;
        } catch (Exception ex) {
            System.out.println("Error in edit plan() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
    
    public Plan getPlan(long planId) {
    Connection con = null;
        PreparedStatement ps = null;
        Plan plan = new Plan();
        HotelDAO h = new HotelDAO();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from plan where planId= ?");
            ps.setString(1,String.valueOf(planId));
            ResultSet rs = ps.executeQuery();
            
             if (rs.next()){
                 plan.setPlanId(planId);
                 plan.setName(rs.getString("name"));
                 plan.setDepartureCity(rs.getString("departureCity"));
                 plan.setArrivalCity(rs.getString("arrivalCity"));
                 plan.setDepartureDate(rs.getString("departureDate"));
                 plan.setReturnDate(rs.getString("returnDate"));
                 plan.setModeTransport(rs.getString("modeTransport"));
                 plan.setBaseCostByAdult(rs.getDouble("baseCostByAdult"));
                 plan.setBaseCostByChild(rs.getDouble("baseCostByChild"));
                 plan.setHotelhotelId(h.getHotel(rs.getLong("hotel_hotelId")));
                 
             } // found
            
        } catch (Exception ex) {
            System.out.println("Error in hotel2() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }   
    return plan;
    
    }
}
