/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

//import BusinessLogic.Service2.Rob;
import BusinessLogic.Service2.Rob;
import DataAccess.DAO.PlanDAO;
import DataAccess.Entity.Plan;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Richar
 */
public class GetResource {
    
  public String getResource(String email) {
      
      Rob objectReceived = agenciaESBOperation(email);      
      HttpSession session = Util.getSession(); 
       PlanDAO planDAO = new PlanDAO();
       if (objectReceived.isSuccess()) {
           
           String[] values = objectReceived.getData().split(",");
           String departureCity = values[0];
           String arrivalCity = values[1];
           Plan plan= planDAO.getPlan2(departureCity,arrivalCity);
           long planId = plan.getPlanId();
           session.setAttribute("planId_discount", planId);
           return objectReceived.getErrMessage();            
        }else{
            session.setAttribute("planId_discount", 0);
            return objectReceived.getErrMessage();            
        }
        //return null;
    }
  
  public Rob getResource2(String email) {
      
      Rob objectReceived = agenciaESBOperation(email);  
       PlanDAO planDAO = new PlanDAO();
       if (objectReceived.isSuccess()) {           
           String[] values = objectReceived.getData().split(",");
           String departureCity = values[0];
           String arrivalCity = values[1];
           Plan plan= planDAO.getPlan2(departureCity,arrivalCity);
           long planId = plan.getPlanId();           
           objectReceived.setData(String.valueOf(planId));
           return objectReceived;            
        }else{
            return objectReceived;            
        }
       /// return null;
    }

    private static Rob agenciaESBOperation(java.lang.String correo) {
        BusinessLogic.Service2.AgenciaESBService service = new BusinessLogic.Service2.AgenciaESBService();
        BusinessLogic.Service2.AgenciaESBPortType port = service.getAgenciaESBPort();
        return port.agenciaESBOperation(correo);
    }
 
}
