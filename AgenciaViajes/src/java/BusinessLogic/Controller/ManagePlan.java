/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

/**
 *
 * @author USER
 */
import DataAccess.DAO.PlanDAO;
import DataAccess.DAO.UserDAO;
import DataAccess.Entity.Plan;
import DataAccess.Entity.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Richar
 */
public class ManagePlan implements Serializable {
    /**
     *
     * @param idPlan
     * @param name
     * @param price
     * @param dateStart
     * @param dateEnd
     * @param destination
     * @return
     * @throws java.io.IOException
     */
    
    private static final long serialVersionUID = 1L;
    public void createPlan(Long idPlan, String name, Long price, Date dateStart, Date dateEnd, String destination) throws NoSuchAlgorithmException, IOException{
        
        Plan plan = new Plan();
        
        plan.setIdPlan(idPlan);
        plan.setName(name);
        plan.setPrice(price);
        plan.setDateStart(dateStart);
        plan.setDateEnd(dateEnd);
        plan.setDestination(destination);
        
        PlanDAO planDAO = new PlanDAO();
        Plan planE = planDAO.persist(plan);
        if(planE != null){
                planDAO.query(name);
                renderIndex(); 
        }
        else{
            
        }   
    }
    
    public void renderIndex(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/index.xhtml");
            } catch (IOException e) {
            }
    
    }
    public void renderSignup(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/signup.xhtml");
            } catch (IOException e) {
            }  
    }
    public void renderProfile(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
            .redirect(contextPath  + "/faces/viewProfile.xhtml");
            } catch (IOException e) {
            }  
    }
    
    public void updatePlan(String name, Long price, Date dateStart, Date dateEnd) {
        HttpSession session = Util.getSession();
        PlanDAO planDAO = new PlanDAO();
        if(planDAO.updatePlan((long)session.getAttribute("idPlan"), name, price, dateStart, dateEnd)){
                planDAO.query((String)session.getAttribute("name"));
        } 
    }


}