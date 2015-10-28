/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;
//import BusinessLogic.Service.Rob;

import BusinessLogic.Service2.Rob;


/**
 *
 * @author Richar
 */
public class GetResource {
    
  public String getResource(String username, String password, Long planId) {
       
       Rob objectReceived = makeTransaction(username, password, planId);

       if (objectReceived.isSuccess()) {
           return objectReceived.getErrMessage();
            
        }else{

            return objectReceived.getErrMessage();
            
        }
        //return null;
    }

    private static Rob makeTransaction(java.lang.String arg0, java.lang.String arg1, java.lang.Long arg2) {
        BusinessLogic.Service2.MakeTransactionWS_Service service = new BusinessLogic.Service2.MakeTransactionWS_Service();
        BusinessLogic.Service2.MakeTransactionWS port = service.getMakeTransactionWSPort();
        return port.makeTransaction(arg0, arg1, arg2);
    }
  
  
}
