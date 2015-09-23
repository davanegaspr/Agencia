/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.HotelDAO;
import DataAccess.Entity.Hotel;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
public class ManageHotel {

    public static Hotel getHotel(long hotelId) {
        HotelDAO hotelDAO = new HotelDAO();
        return hotelDAO.getHotel(hotelId);
    }

    public ArrayList<Hotel> getHotels() {        
        HotelDAO hotelDAO = new HotelDAO();
        return hotelDAO.getHotels();
    }

    public void createHotel(String name, String category, double price, String location) {
        
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setCategory(category);
        hotel.setPrice((long)price);
        hotel.setLocation(location); 
        HotelDAO hotelDAO = new HotelDAO();
        Hotel hotelE = hotelDAO.persist(hotel);
        if(hotelE != null){
                renderShowHotels(); 
        }
        else{
            
        }   
    }
    
    public void renderShowHotels(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/showHotels.xhtml");
            } catch (IOException e) {
            }  
    }
    
    public void renderHotelEdit(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/editHotel.xhtml");
            } catch (IOException e) {
            }  
    }

    public boolean editHotel(String name, String category, double price, String location) {
        HotelDAO hotelDAO = new HotelDAO();
        HttpSession session = Util.getSession();  
        return hotelDAO.editHotel((long)session.getAttribute("hotelIdEdit"), name, category, price, location);
    }

    public boolean eliminateHotel(long hotelId) {
        HotelDAO hotelDAO = new HotelDAO();
        return hotelDAO.eliminateHotel(hotelId);   
    }
    
    
    
}
