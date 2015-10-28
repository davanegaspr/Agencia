/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageHotel;
import BusinessLogic.Controller.Util;
import DataAccess.Entity.Hotel;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author Richar
 */
@ManagedBean(name="manageHotel")
@ViewScoped
public class ManageHotelBean implements Serializable{

    /**
     * Creates a new instance of ManageHotelBean
     */
    
    private String name;
    private String category;
    private double price;
    private String location;
    private String message;
    ManageHotel manageHotel = new ManageHotel();
    private ArrayList<Hotel> hotelsList = manageHotel.getHotels();
    
    public ManageHotelBean() {
    }
    
    public void createHotel() throws  IOException, NoSuchAlgorithmException, NamingException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        manageHotel = new ManageHotel();        
        manageHotel.createHotel(getName(),getCategory(),getPrice(),getLocation());
    
    }
    
    public void renderEdit(long hotelId) throws  IOException, NoSuchAlgorithmException {
        HttpSession session = Util.getSession(); 
        manageHotel = new ManageHotel();
        session.setAttribute("hotelIdEdit", hotelId);
        manageHotel.renderHotelEdit();   
    }
    
    public void editHotel() throws  IOException, NoSuchAlgorithmException {
        manageHotel = new ManageHotel();
        if( manageHotel.editHotel(getName(),getCategory(),getPrice(),getLocation()))manageHotel.renderShowHotels();    
    }
    
    public void eliminateHotel(long hotelId) throws  IOException, NoSuchAlgorithmException {
        manageHotel = new ManageHotel();
        if(manageHotel.eliminateHotel(hotelId)){
            setMessage("Hotel Eliminado");
            manageHotel.renderShowHotels();           
        }
        else setMessage("No ha podido eliminarse el hotel");
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the hotelList
     */
    public ArrayList<Hotel> getHotelsList() {
        return hotelsList;
    }

    /**
     * @param hotelsList the hotelList to set
     */
    public void setHotelList(ArrayList<Hotel> hotelsList) {
        this.hotelsList = hotelsList;
    }
    
}
