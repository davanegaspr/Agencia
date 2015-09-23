/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.HotelDAO;
import DataAccess.DAO.TicketDAO;
import DataAccess.Entity.Plan;
import DataAccess.Entity.Tickets;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
public class ManageTicket {
    
    private String price;
    public Plan getPlan(long planId) {
        TicketDAO ticketDAO = new TicketDAO();
        return ticketDAO.getPlan(planId);  
    }
    
    public void createTicket(long userId, long planId, int quantityAdult, int quantityChild, int status) {
        TicketDAO ticketDAO = new TicketDAO();
        Tickets ticket = new Tickets();
        Plan plana = ticketDAO.getPlan(planId);
        long hotelid = plana.getHotelId();
        HotelDAO hotel =  new HotelDAO();
        ManageUser mUser = new ManageUser();
        
        float price = Float.valueOf(hotel.getHotelCost(hotelid));
        price += + (float)(plana.getBaseCostByAdult()*quantityAdult + (float)plana.getBaseCostByChild()*quantityChild);
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
	   //get current date time with Date()
	   Date date = new Date();        
        
        ticket.setIdPlan(planId);
        ticket.setIdUser(userId);
        ticket.setDateStart(plana.getDepartureDate());
        ticket.setDateBuy(dateFormat.format(date));
        ticket.setPrice(price);
        ticket.setStatus(status);
        Tickets ticketE = ticketDAO.persist(ticket);
        mUser.updateBalance(-price);
        setPrice(String.valueOf(price));
         if(ticketE != null){
                renderTicket();
        }
        else{
            
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    } 
}
