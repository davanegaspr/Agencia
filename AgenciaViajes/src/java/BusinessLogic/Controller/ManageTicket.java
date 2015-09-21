/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.TicketDAO;
import DataAccess.Entity.Plan;

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
        ticketDAO.createTicket(userId, planId, quantityAdult, quantityChild);
    }
    
}
