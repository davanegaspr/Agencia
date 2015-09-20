/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Hotel;
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
public class HotelDAO {

public Hotel persist(Hotel hotel){
        EntityManager em;        
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("agenciaPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(hotel);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();            
        }finally{
            em.close();
            return hotel;
        }  
    } 

    public ArrayList<Hotel> getHotels(){
        
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from hotel");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                hotelList.add(new Hotel(rs.getLong("hotelId"), rs.getString("name"), rs.getString("category"), rs.getFloat("price"), rs.getString("location")));              
            }          
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
        } finally {
            Database.close(con);
        } 
        return hotelList;
    
    } 

    public boolean editHotel(long l, String name, String category, double price, String location) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "UPDATE hotel SET name = ?, category =?, price = ?, location =? WHERE hotelId = ?");            
            ps.setString(1,name);
            ps.setString(2,category);
            ps.setString(3,String.valueOf(price));
            ps.setString(4,location);
            ps.setString(5, String.valueOf(l));
            int rs = ps.executeUpdate();
                return rs==1;
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        } 
    }

    public boolean eliminateHotel(long hotelId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "DELETE FROM hotel WHERE hotelId = ?");          
            ps.setString(1, String.valueOf(hotelId));
            int rs = ps.executeUpdate();
                return rs==1;  
                
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
    
    public String getHotelName(long hotelId) {
        Connection con = null;
        PreparedStatement ps = null;
        String name = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select name from hotel where hotelId= ?");
            ps.setString(1,String.valueOf(hotelId));
            ResultSet rs = ps.executeQuery();
            
             if (rs.next()) name = rs.getString("name"); // found
            
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }   
    return name;
    }
    
}
