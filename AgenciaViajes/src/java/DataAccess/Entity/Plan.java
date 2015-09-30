/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Richar
 */
@Entity
@Table(name = "plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
    @NamedQuery(name = "Plan.findByPlanId", query = "SELECT p FROM Plan p WHERE p.planId = :planId"),
    @NamedQuery(name = "Plan.findByName", query = "SELECT p FROM Plan p WHERE p.name = :name"),
    @NamedQuery(name = "Plan.findByDepartureCity", query = "SELECT p FROM Plan p WHERE p.departureCity = :departureCity"),
    @NamedQuery(name = "Plan.findByArrivalCity", query = "SELECT p FROM Plan p WHERE p.arrivalCity = :arrivalCity"),
    @NamedQuery(name = "Plan.findByDepartureDate", query = "SELECT p FROM Plan p WHERE p.departureDate = :departureDate"),
    @NamedQuery(name = "Plan.findByReturnDate", query = "SELECT p FROM Plan p WHERE p.returnDate = :returnDate"),
    @NamedQuery(name = "Plan.findByModeTransport", query = "SELECT p FROM Plan p WHERE p.modeTransport = :modeTransport"),
    @NamedQuery(name = "Plan.findByBaseCostByAdult", query = "SELECT p FROM Plan p WHERE p.baseCostByAdult = :baseCostByAdult"),
    @NamedQuery(name = "Plan.findByBaseCostByChild", query = "SELECT p FROM Plan p WHERE p.baseCostByChild = :baseCostByChild")})
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planId")
    private Long planId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "departureCity")
    private String departureCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "arrivalCity")
    private String arrivalCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "departureDate")
    private String departureDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "returnDate")
    private String returnDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ModeTransport")
    private String modeTransport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "baseCostByAdult")
    private double baseCostByAdult;
    @Basic(optional = false)
    @NotNull
    @Column(name = "baseCostByChild")
    private double baseCostByChild;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planplanId")
    private Collection<Tickets> ticketsCollection;
    @JoinColumn(name = "hotel_hotelId", referencedColumnName = "hotelId")
    @ManyToOne(optional = false)
    private Hotel hotelhotelId;

    public Plan() {
    }

    public Plan(Long planId) {
        this.planId = planId;
    }

    public Plan(Long planId, String name, String departureCity, String arrivalCity, String departureDate, String returnDate, String modeTransport, double baseCostByAdult, double baseCostByChild) {
        this.planId = planId;
        this.name = name;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.modeTransport = modeTransport;
        this.baseCostByAdult = baseCostByAdult;
        this.baseCostByChild = baseCostByChild;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getModeTransport() {
        return modeTransport;
    }

    public void setModeTransport(String modeTransport) {
        this.modeTransport = modeTransport;
    }

    public double getBaseCostByAdult() {
        return baseCostByAdult;
    }

    public void setBaseCostByAdult(double baseCostByAdult) {
        this.baseCostByAdult = baseCostByAdult;
    }

    public double getBaseCostByChild() {
        return baseCostByChild;
    }

    public void setBaseCostByChild(double baseCostByChild) {
        this.baseCostByChild = baseCostByChild;
    }

    @XmlTransient
    public Collection<Tickets> getTicketsCollection() {
        return ticketsCollection;
    }

    public void setTicketsCollection(Collection<Tickets> ticketsCollection) {
        this.ticketsCollection = ticketsCollection;
    }

    public Hotel getHotelhotelId() {
        return hotelhotelId;
    }

    public void setHotelhotelId(Hotel hotelhotelId) {
        this.hotelhotelId = hotelhotelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planId != null ? planId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.planId == null && other.planId != null) || (this.planId != null && !this.planId.equals(other.planId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Plan[ planId=" + planId + " ]";
    }
    
}
