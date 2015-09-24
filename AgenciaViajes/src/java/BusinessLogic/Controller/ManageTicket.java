/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.HotelDAO;
import DataAccess.DAO.TicketDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Hotel;
import DataAccess.Entity.Plan;
import DataAccess.Entity.Tickets;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
public class ManageTicket {
    
    public Plan getPlan(long planId) {
        TicketDAO ticketDAO = new TicketDAO();
        return ticketDAO.getPlan(planId);  
    }
    
    public void createTicket(long userId, Plan plan, Hotel hotel, int quantityAdult, int quantityChild, int status) {
        
        HttpSession session = Util.getSession(); 
        TicketDAO ticketDAO = new TicketDAO();
        Tickets ticket = new Tickets();
        UserDAO userDAO = new UserDAO();        
        double price = (plan.getBaseCostByAdult() * quantityAdult) + (plan.getBaseCostByChild() * quantityChild) + hotel.getPrice()*(quantityAdult + quantityChild);
        double newBalance = (double)session.getAttribute("balance") - price;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        Date date = new Date();                
        ticket.setIdPlan(plan.getPlanId());
        ticket.setIdUser(userId);
        ticket.setDateStart(plan.getDepartureDate());
        ticket.setDateBuy(dateFormat.format(date));
        ticket.setPrice((float)price);
        ticket.setStatus(status);
        userDAO.updateBalance(userId,newBalance);
        session.setAttribute("total", price);
        Tickets ticketE = ticketDAO.persist(ticket);
         if(ticketE != null){
             renderTicket();
        }
        else{
            
        }
        }
    public boolean eliminateTicket(Long ticketId) {
        TicketDAO ticketDAO = new TicketDAO();
        return ticketDAO.eliminateTicket(ticketId);
    }
    
    public void renderShowTicket(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/ShowTicket_1.xhtml");
            } catch (IOException e) {
            }
    
    }
    public void renderTicket(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/viewTicket.xhtml");
            } catch (IOException e) {
            } 
    }
    public void renderTicketsList(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/showTicketUser.xhtml");
            } catch (IOException e) {
            } 
    }

    public boolean balance(Plan plan, Hotel hotel) {
        HttpSession session = Util.getSession(); 
        int quantityAdult = (int)session.getAttribute("quantityAdult");
        int quantityChild = (int)session.getAttribute("quantityChild");
         double price = (plan.getBaseCostByAdult() * quantityAdult) + (plan.getBaseCostByChild() * quantityChild) + hotel.getPrice()*(quantityAdult + quantityChild);
         
         double balance = (double)session.getAttribute("balance");
         return balance >= price;
    }

    public ArrayList<Tickets> getTickets(long userId, int status) {
        TicketDAO ticketDAO = new TicketDAO();
        return ticketDAO.getTicketsList(userId, status);
    }

    /**
     * @return the ticketsListList
     */


    

}
