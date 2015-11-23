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
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Richar
 */
public class ManageTicket implements Serializable{
    
    public Plan getPlan(long planId) {
        TicketDAO ticketDAO = new TicketDAO();
        return ticketDAO.getPlan(planId);  
    }
    
    public void createTicket(long userId, Plan plan, Hotel hotel, int quantityAdult, int quantityChild, int status) throws NamingException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        HttpSession session = Util.getSession(); 
        double price=0;
        long ticketId;
        
        if(session.getAttribute("ticketId")==null){
            ticketId=1;
        }
        else{
            ticketId=(long)session.getAttribute("ticketId") +1;
        }
        session.setAttribute("ticketId", ticketId);         
        TicketDAO ticketDAO = new TicketDAO();
        UserDAO userDAO = new UserDAO();
        if(session.getAttribute("planId_discount") != null && session.getAttribute("planId_discount")== plan.getPlanId()){
            //price = (plan.getBaseCostByAdult() * quantityAdult) + (plan.getBaseCostByChild() * quantityChild) + hotel.getPrice()*(quantityAdult + quantityChild);
            price = 0;
        }
        else{
            price = (plan.getBaseCostByAdult() * quantityAdult) + (plan.getBaseCostByChild() * quantityChild) + hotel.getPrice()*(quantityAdult + quantityChild);
        }
        double newBalance = (double)session.getAttribute("balance") - price;
        session.setAttribute("balance", newBalance);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        Date date = new Date();      
        System.out.println("UPDATE PASSWORD " + userDAO.updateBalance(userId,newBalance));
        session.setAttribute("total", price);
        UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        transaction.begin();
        boolean ticketE = ticketDAO.persist(userId,plan.getPlanId(),plan.getDepartureDate(),(short)status,dateFormat.format(date),(float)price, ticketId);
        transaction.commit(); 
         if(ticketE){
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
         if(session.getAttribute("planId_discount") != null && (long)session.getAttribute("planId_discount")!= 0){
             return true;
         }
         else {
             return balance >= price;
         }
         
    }

    public ArrayList<Tickets> getTickets(long userId, int status) {
        TicketDAO ticketDAO = new TicketDAO();
        return ticketDAO.getTicketsList(userId, status);
    }

    /**
     * @return the ticketsListList
     */


    

}
