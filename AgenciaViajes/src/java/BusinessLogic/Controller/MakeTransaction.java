/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import BusinessLogic.Service2.Rob;
import DataAccess.DAO.*;
import DataAccess.Entity.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
public class MakeTransaction {
    
    public ROB make(String email, String password, Long planId){  
        GetResource gr = new GetResource();
        String message = null;
        double price;        
        Rob validate = gr.getResource2(email);        
        UserDAO userDAO= new UserDAO();
        //GetResource gr = new GetResource();
        TicketDAO ticketDAO = new TicketDAO();
        PlanDAO planDAO = new PlanDAO();
        Plan plan = planDAO.getPlan(planId);
        HotelDAO hotelDAO= new HotelDAO();
        Hotel hotel = hotelDAO.getHotel(plan.getHotelhotelId().getHotelId());
        
        ROB rob = new ROB();
        //Verifica si la cuenta existe
        
        if(UserDAO.validateEmail(email)){    
            String username = userDAO.getUsername(email);
            if(UserDAO.validatePassword(username,userDAO.sha256(password))){
                //String var = gr.getResource(email);
                User user = userDAO.getUser2(username);                
                int quantityAdult=1;
                int quantityChild=0;                
                int status = 1;
                long ticketId=1;
                if (validate.isSuccess() && validate.getData().equals(planId.toString())){
                    price =0;
                    message="Transacción Satisfactoria, Su tiquete fue cubierto en su totalidad por el apoyo con el que ud cuenta de bienestar, su numero de tiquete es: ";
                } 
                else{
                    message="Transacción Satisfactoria, su numero de tiquete es: ";
                    price = (plan.getBaseCostByAdult() * quantityAdult) + (plan.getBaseCostByChild() * quantityChild) + hotel.getPrice()*(quantityAdult + quantityChild);
                    System.out.println("preciooooo" + hotel.getName() + " " + price);
                }
                if(user.getBalance()>= price){
                    double newBalance = user.getBalance() - price;
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
                    Date date = new Date();      
                    userDAO.updateBalance(user.getUserId(),newBalance);
                    UserTransaction transaction = null;
                    try {
                        transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                    } catch (NamingException ex) {
                        Logger.getLogger(MakeTransaction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        transaction.begin();
                    } catch (NotSupportedException | SystemException ex) {
                        Logger.getLogger(MakeTransaction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Long ticketE = ticketDAO.persist2(user.getUserId(),plan.getPlanId(),plan.getDepartureDate(),(short)status,dateFormat.format(date),(float)price, ticketId);
                    try { 
                        transaction.commit();
                    } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
                        Logger.getLogger(MakeTransaction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(ticketE!=null){
                        rob.setSuccess(true);
                        rob.setErr_message(message + ticketE);  
                        rob.setData(ticketE.toString());
                    }
                }            
                else{
                    rob.setSuccess(false);
                    rob.setErr_message("Saldo Insuficiente," +" El plan cuesta: " + price + ", su saldo es -> " + user.getBalance());
                    
                }
            }
            else{
                rob.setSuccess(false);
                rob.setErr_message("Contraseña Incorrecta");
            }
        
        }
    
        else{
            rob.setSuccess(false);
            rob.setErr_message("Cuenta Inexistente");
        }
        return rob;        
    }
}