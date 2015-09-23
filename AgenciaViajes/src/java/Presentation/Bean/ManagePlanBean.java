/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.ManageHotel;
import BusinessLogic.Controller.ManagePlan;
import BusinessLogic.Controller.Util;
import DataAccess.DAO.HotelDAO;
import DataAccess.Entity.Hotel;
import DataAccess.Entity.Plan;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Richar
 */
@ManagedBean(name="managePlan")
@ViewScoped
public class ManagePlanBean {
    
    private String name;
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private String returnDate;
    private String modeTransport;
    private double baseCostByAdult;
    private double baseCostByChild;
    private long hotelId;
    private String message;
    ManagePlan managePlan = new ManagePlan();
    ManageHotel manageHotel = new ManageHotel();
    private ArrayList<Plan> plansList = managePlan.getPlans();
    private ArrayList<Hotel> hotelsList = manageHotel.getHotels();    
    
    public void renderShowTicket(long planId) throws  IOException, NoSuchAlgorithmException {
        HttpSession session = Util.getSession(); 
        managePlan = new ManagePlan();
        session.setAttribute("planIdBuy", planId);
        managePlan.renderShowTicket();   
    }
    public void createPlan() throws  IOException, NoSuchAlgorithmException {
        managePlan = new ManagePlan();
        if(!managePlan.isDate(getDepartureDate())){
            setMessage("El formato de la fecha de salida no es valido, ingrese en formato 24H dd/mm/aaaa hh:mm");
        }else if(!managePlan.isDate(getReturnDate())){
            setMessage("El formato de la fecha de regreso no es valido, ingrese en formato 24H dd/mm/aaaa hh:mm");
        }else if(!managePlan.departureDateValidator(getDepartureDate())){
            setMessage("La fecha de salida no puede ser menor que la fecha actual");
        }else if(!managePlan.dateValidator(getDepartureDate(),getReturnDate())){
            setMessage("La fecha de regreso no puede ser menor que la fecha de salida");
        }else{
            managePlan.createPlan(getName(),getDepartureCity(),getArrivalCity(),getDepartureDate(),getReturnDate(), getModeTransport(), getBaseCostByAdult(), getBaseCostByChild(), getHotelId());
            managePlan.renderShowPlans();
        }
        
    }
    
    public String getHotelName(long hotelId){    
        HotelDAO hotelDAO = new HotelDAO();
        return hotelDAO.getHotelName(hotelId);
    }
    public String getHotelCost(long hotelId){
        HotelDAO hotelDAO = new HotelDAO();
        return hotelDAO.getHotelCost(hotelId);
    }
    
    public void eliminatePlan(long planId) throws  IOException, NoSuchAlgorithmException {
        managePlan = new ManagePlan();
        if(managePlan.eliminatePlan(planId)){
            setMessage("Plan Eliminado");
            managePlan.renderShowPlans();         
        }
        else setMessage("No ha podido eliminarse el hotel");
        
    }
    
    public void renderEdit(long planId) throws  IOException, NoSuchAlgorithmException {
        HttpSession session = Util.getSession(); 
        managePlan = new ManagePlan();
        session.setAttribute("planIdEdit", planId);
        managePlan.renderPlanEdit();   
    }
    
    public void editPlan() throws  IOException, NoSuchAlgorithmException {
        managePlan = new ManagePlan();
        if(!managePlan.isDate(getDepartureDate())){
            setMessage("El formato de la fecha de salida no es valido, ingrese en formato 24H dd/mm/aaaa hh:mm");
        }else if(!managePlan.isDate(getReturnDate())){
            setMessage("El formato de la fecha de regreso no es valido, ingrese en formato 24H dd/mm/aaaa hh:mm");
        }else if(!managePlan.departureDateValidator(getDepartureDate())){
            setMessage("La fecha de salida no puede ser menor que la fecha actual");
        }else if(!managePlan.dateValidator(getDepartureDate(),getReturnDate())){
            setMessage("La fecha de regreso no puede ser menor que la fecha de salida");
        }else{
            managePlan.editPlan(getName(),getDepartureCity(),getArrivalCity(),getDepartureDate(), getReturnDate(), getModeTransport(), getBaseCostByAdult(), getBaseCostByChild(), getHotelId());
            managePlan.renderShowPlans();
        }
    }
    /**
     * Creates a new instance of ManagePlanBean
     */
    public ManagePlanBean() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the departureCity
     */
    public String getDepartureCity() {
        return departureCity;
    }

    /**
     * @param departureCity the departureCity to set
     */
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    /**
     * @return the arrivalCity
     */
    public String getArrivalCity() {
        return arrivalCity;
    }

    /**
     * @param arrivalCity the arrivalCity to set
     */
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    /**
     * @return the departureDate
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the modeTransport
     */
    public String getModeTransport() {
        return modeTransport;
    }

    /**
     * @param modeTransport the modeTransport to set
     */
    public void setModeTransport(String modeTransport) {
        this.modeTransport = modeTransport;
    }

    /**
     * @return the baseCostByAdult
     */
    public Double getBaseCostByAdult() {
        return baseCostByAdult;
    }

    /**
     * @param baseCostByAdult the baseCostByAdult to set
     */
    public void setBaseCostByAdult(Double baseCostByAdult) {
        this.baseCostByAdult = baseCostByAdult;
    }

    /**
     * @return the baseCostByChild
     */
    public Double getBaseCostByChild() {
        return baseCostByChild;
    }

    /**
     * @param baseCostByChild the baseCostByChild to set
     */
    public void setBaseCostByChild(Double baseCostByChild) {
        this.baseCostByChild = baseCostByChild;
    }

    /**
     * @return the hotelId
     */
    public long getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId the hotelId to set
     */
    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return the plansList
     */
    public ArrayList<Plan> getPlansList() {
        return plansList;
    }

    /**
     * @param plansList the plansList to set
     */
    public void setPlansList(ArrayList<Plan> plansList) {
        this.plansList = plansList;
    }

    /**
     * @return the hotelsList
     */
    public ArrayList<Hotel> getHotelsList() {
        return hotelsList;
    }

    /**
     * @param hotelsList the hotelsList to set
     */
    public void setHotelsList(ArrayList<Hotel> hotelsList) {
        this.hotelsList = hotelsList;
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
    
}
