/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.TicketDAO;
import DataAccess.Entity.Plan;
import DataAccess.Entity.Tickets;
import java.io.IOException;
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
    
    public void createTicket(long userId, long planId, int quantityAdult, int quantityChild) {
        TicketDAO ticketDAO = new TicketDAO();
        Tickets ticket = new Tickets();
        ticket = ticketDAO.createTicket(userId, planId, quantityAdult, quantityChild);
        renderTicket(ticket);
        
    }
    
    public void renderTicket(Tickets ticket){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            
                HttpSession session = Util.getSession();
                session.setAttribute("TicketId", ticket.getIdTicket());  
                session.setAttribute("TicketDateBuy", ticket.getDateBuy());
                
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/viewTicket.xhtml");
            } catch (IOException e) {
            }  
    }
    
    
    
}
