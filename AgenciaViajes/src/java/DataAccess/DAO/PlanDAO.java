/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Plan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
                planList.add(new Plan(rs.getLong("planId"), rs.getString("name"), rs.getString("departureCity"), rs.getString("arrivalCity"), rs.getString("departureDate"),rs.getString("returnDate"), rs.getString("modeTransport"), rs.getDouble("baseCostByAdult"), rs.getDouble("baseCostByChild"), rs.getLong("hotelId")));              
            }          
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        } 
        return planList;
    
    }

    public Plan persist(Plan plan) {
        EntityManager em;        
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("agenciaPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(plan);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();            
        }finally{
            em.close();
            return plan;
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
                    "UPDATE plan SET name = ?, departureCity =?, arrivalCity = ?, departureDate =?, returnDate =?, modeTransport =?, baseCostByAdult =?, baseCostByChild =?, hotelId =?  WHERE planId = ?");            
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
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
    
    public ArrayList<Plan> getUsers(){
        
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Plan> planList = new ArrayList<>();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from user");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                planList.add(new plan(rs.getLong("userId"), rs.getDouble("balance"), rs.getString("document"), rs.getString("documentType"), rs.getString("email"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getString("role"), rs.getString("username"), rs.getString("phone")));            
                
            }          
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        } 
        return planList;
    
    } 
    
    
    
    public boolean searchPlan(String departureCity, String arrivalCity, String departureDate, String returnDate, String modeTransport) {
        System.out.println("Entramos a PlanDAo.searchPlan con "+departureCity +" "+arrivalCity+" "+departureDate+" "+returnDate+" "+modeTransport);
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from plan where departureCity =? , arrivalCity =? , departureDate =? , returnDate =? , modeTransport =?");            
            ps.setString(1,departureCity);
            ps.setString(2,arrivalCity);
            ps.setString(3,departureDate);
            ps.setString(4,returnDate);
            ps.setString(5,modeTransport);
            ResultSet rs = ps.executeQuery();
            System.out.println("Estado de la búsqueda" + rs);
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
            return true;      
                
        } catch (Exception ex) {
            System.out.println("Error in search query() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
    
}
