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

public boolean persist(String name, String category, double price, String location, long hotelId){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "INSERT INTO hotel (`hotelId`,`name`, category, price, location) \n" +
                    "	VALUES (?,?, ?, ?, ?)");        
            ps.setLong(1, hotelId);
            ps.setString(2, name);
            ps.setString(3, category);
            ps.setDouble(4,price);
            ps.setString(5,location);          
            int rs = ps.executeUpdate();
                return rs==1;
                
        } catch (Exception ex) {
            System.out.println("Error agregar usuario -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
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
    
    public String getHotelName(long planId) {
        Connection con = null;
        PreparedStatement ps = null;
        String name = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from plan where planId= ?");
            ps.setString(1,String.valueOf(planId));
            ResultSet rs = ps.executeQuery();
            
             if (rs.next()){ 
                 
                 ps = con.prepareStatement(
                    "select * from hotel where hotelId= ?");
                ps.setString(1,String.valueOf(rs.getString("hotel_hotelId")));
                ResultSet rs2 = ps.executeQuery();       
                if (rs2.next()) name = rs2.getString("name");      
             
             } // found
            
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }   
    return name;
    }
    
    public String getHotelCost(long planId) {
        
        Connection con = null;
        PreparedStatement ps = null;
        String price = null;
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from plan where planId= ?");
            ps.setString(1,String.valueOf(planId));
            ResultSet rs = ps.executeQuery();
            
             if (rs.next()){ 
                 
                 ps = con.prepareStatement(
                    "select * from hotel where hotelId= ?");
                ps.setString(1,String.valueOf(rs.getString("hotel_hotelId")));
                ResultSet rs2 = ps.executeQuery();      
                
                if (rs2.next()){ 
                    System.out.println("preciooo" + rs2.getString("price"));
                    price = rs2.getString("price");    }  
             
             } // found
            
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }   
    return price;
    }

    public Hotel getHotel(long hotelId) {
    Connection con = null;
        PreparedStatement ps = null;
        Hotel hotel = new Hotel();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from hotel where hotelId= ?");
            ps.setString(1,String.valueOf(hotelId));
            ResultSet rs = ps.executeQuery();
            
             if (rs.next()){
                 hotel.setName(rs.getString("name"));
                 hotel.setHotelId(hotelId);
                 hotel.setCategory(rs.getString("category"));
                 hotel.setLocation(rs.getString("location"));
                 hotel.setPrice(rs.getFloat("price"));            
             } // found
            
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }   
    return hotel;
    
    }
    
    public Hotel getHotel2(long planId) {
    Connection con = null;
        PreparedStatement ps = null;
        Hotel hotel = new Hotel();
        try {
            con = Database.getConnection();
            ps = con.prepareStatement(
                    "select * from plan where planId= ?");
            ps.setString(1,String.valueOf(planId));
            ResultSet rs = ps.executeQuery();
            
             if (rs.next()){
                 ps = con.prepareStatement(
                    "select * from hotel where hotelId= ?");
                 ps.setString(1,String.valueOf(rs.getString("hotel_hotelId")));
                 ResultSet rs2 = ps.executeQuery();
                 if(rs2.next()){
                 hotel.setName(rs2.getString("name"));
                 hotel.setHotelId(rs2.getLong("hotelId"));
                 hotel.setCategory(rs2.getString("category"));
                 hotel.setLocation(rs2.getString("location"));
                 hotel.setPrice(rs2.getFloat("price"));            
             }
             }// found
            
        } catch (Exception ex) {
            System.out.println("Error in hotel() -->" + ex.getMessage());
            return null;
        } finally {
            Database.close(con);
        }   
    return hotel;
    
    }
    
}
