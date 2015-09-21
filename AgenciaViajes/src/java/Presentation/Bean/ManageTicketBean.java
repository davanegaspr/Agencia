/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageTicket;
import BusinessLogic.Controller.Util;
import DataAccess.Entity.Plan;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
@ManagedBean(name="manageTicket")
@ViewScoped
public class ManageTicketBean {
    private int quantityAdult;
    private int quantityChild;
    ManageTicket manageTicket = new ManageTicket();    
    HttpSession session = Util.getSession(); 
    private Plan plan = manageTicket.getPlan((long)session.getAttribute("planIdBuy"));

    /**
     * Creates a new instance of ManageTicketBean
     */
    public ManageTicketBean() {
    }
    
    public void createTicket() throws  IOException, NoSuchAlgorithmException {
        manageTicket.createTicket((long)session.getAttribute("userId"),(long)session.getAttribute("planIdBuy"), getQuantityAdult(), getQuantityChild());  
        
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
    
}
