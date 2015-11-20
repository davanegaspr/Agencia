/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import javax.servlet.http.HttpSession;


/**
 *
 * @author Richar
 */
public class GetResource {
    
  public String getResource(String email) {
      /*
       Rob objectReceived = viajesESBOperation(email);

       if (objectReceived.isSuccess()) {
           HttpSession session = Util.getSession(); 
           String[] values = objectReceived.getData().split(",");
           String planId = values[0];
           String discount = values[1];
           session.setAttribute("planId_discount", planId);
           session.setAttribute("discount", discount);
           return objectReceived.getErrMessage();            
        }else{
            return objectReceived.getErrMessage();            
        }*/
        return null;
    }

}
