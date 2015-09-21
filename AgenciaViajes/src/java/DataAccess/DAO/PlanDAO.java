/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import BusinessLogic.Controller.Util;
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
}
