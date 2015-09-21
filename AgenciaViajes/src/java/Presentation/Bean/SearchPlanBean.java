/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManagePlan;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class SearchPlanBean {
    
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private String arrivalDate;
    private String modeTransport;
    private int cantidad_adultos;
    private int cantidad_niños;
    private String message;
        
    
    /**
     * Creates a new instance of SearchPlanBean
     */
    public SearchPlanBean() {
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getCantidad_adultos() {
        return cantidad_adultos;
    }

    public void setCantidad_adultos(int cantidad_adultos) {
        this.cantidad_adultos = cantidad_adultos;
    }

    public int getCantidad_niños() {
        return cantidad_niños;
    }

    public void setCantidad_niños(int cantidad_niños) {
        this.cantidad_niños = cantidad_niños;
    }

    public String getModeTransport() {
        return modeTransport;
    }

    public void setModeTransport(String modeTransport) {
        this.modeTransport = modeTransport;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void searchPlan() throws SQLException {
        ManagePlan managePlan = new ManagePlan(); 
        if(managePlan.searchPlan(departureCity,arrivalCity,departureDate,arrivalDate,modeTransport)) managePlan.renderSearchPlans();
        else setMessage("No se encontraron planes de viaje");
    }
    
}
