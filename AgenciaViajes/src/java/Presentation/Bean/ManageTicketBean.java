/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageTicket;
import BusinessLogic.Controller.Util;
import DataAccess.DAO.HotelDAO;
import DataAccess.Entity.Hotel;
import DataAccess.Entity.Plan;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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
@ManagedBean(name="manageTicket")
@ViewScoped
public class ManageTicketBean implements Serializable{    
    private int quantityAdult;
    private String enoughBalance;
    private int quantityChild;
    ManageTicket manageTicket = new ManageTicket();    
    HttpSession session = Util.getSession(); 
    HotelDAO hotelDAO = new HotelDAO();
    private Plan plan = manageTicket.getPlan((long)session.getAttribute("planIdBuy"));
    private Hotel hotel = hotelDAO.getHotel2(plan.getPlanId());
    ManageTicket mt = new ManageTicket();
    
            //(plan.getBaseCostByAdult() * (int)session.getAttribute("quantityAdult")) + (plan.getBaseCostByChild() * (int)session.getAttribute("quantityChild")) + (hotel.getPrice() * ((int)session.getAttribute("quantityChild") + (int)session.getAttribute("quantityAdult")));

    /**
     * Creates a new instance of ManageTicketBean
     */
    public ManageTicketBean() {
    }    
    
    public void createTicket() throws  IOException, NoSuchAlgorithmException, NamingException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        if(manageTicket.balance(plan, hotel) == true) {
            setEnoughBalance("true");
            manageTicket.createTicket((long)session.getAttribute("userId"),plan, getHotel(), (int)session.getAttribute("quantityAdult"), (int)session.getAttribute("quantityChild"), 1);
        }
        else {
            setEnoughBalance("false");
        }
    }    
    public void createReservation() throws  IOException, NoSuchAlgorithmException, NamingException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException  {
        manageTicket.createTicket((long)session.getAttribute("userId"),plan, getHotel(), (int)session.getAttribute("quantityAdult"), (int)session.getAttribute("quantityChild"), 0);
    }
    public void enoughBalance(){    
    }
    public void renderShowTicket(){
        setQuantityChild(getQuantityChild());        
        setQuantityAdult(getQuantityAdult());
        session.setAttribute("quantityChild", getQuantityChild());
        session.setAttribute("quantityAdult", getQuantityAdult());
        ManageTicket mt = new ManageTicket();        
        mt.renderShowTicket();
    }
    
    /**
     * @return the plan
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * @param plan the plan to set
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * @return the quantityAdult
     */
    public int getQuantityAdult() {
        return quantityAdult;
    }

    /**
     * @param quantityAdult the quantityAdult to set
     */
    public void setQuantityAdult(int quantityAdult) {
        this.quantityAdult = quantityAdult;
    }

    /**
     * @return the quantityChild
     */
    public int getQuantityChild() {
        return quantityChild;
    }

    /**
     * @param quantityChild the quantityChild to set
     */
    public void setQuantityChild(int quantityChild) {
        this.quantityChild = quantityChild;
    }

    /**
     * @return the hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * @param hotel the hotel to set
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * @return the enoughBalance
     */
    public String getEnoughBalance() {
        return enoughBalance;
    }

    /**
     * @param enoughBalance the enoughBalance to set
     */
    public void setEnoughBalance(String enoughBalance) {
        this.enoughBalance = enoughBalance;
    }

   
}
