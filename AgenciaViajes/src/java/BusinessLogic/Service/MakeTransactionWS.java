/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Service;

import BusinessLogic.Controller.MakeTransaction;
import BusinessLogic.Controller.ROB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author Richar
 */
@WebService(serviceName = "MakeTransactionWS")
public class MakeTransactionWS {

    /**
     * This is a sample web service operation
     * @param username    
     * @param password    
     * @param planId    
     * @return       
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    * */
    @WebMethod(operationName = "makeTransaction")
    public ROB makeTransaction(String username, String password, Long planId){
        //Instancia un objeto de la clase encargada de realizar las transacciones 
        MakeTransaction makeTransaction = new MakeTransaction();
    	//Se llama a la función que realiza la transacción y se le enían los parámetros que necesita 
        //para realizarla, los cuales vienen de la aplicacion que consume el servicio 
        return makeTransaction.make(username, password, planId);
    }
}
