/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Richar
 */
@Entity
@Table(name = "tickets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @NamedQuery(name = "Tickets.findByIdticket", query = "SELECT t FROM Tickets t WHERE t.idticket = :idticket"),
    @NamedQuery(name = "Tickets.findByStatus", query = "SELECT t FROM Tickets t WHERE t.status = :status"),
    @NamedQuery(name = "Tickets.findByDateBuy", query = "SELECT t FROM Tickets t WHERE t.dateBuy = :dateBuy"),
    @NamedQuery(name = "Tickets.findByDateStart", query = "SELECT t FROM Tickets t WHERE t.dateStart = :dateStart"),
    @NamedQuery(name = "Tickets.findByPrice", query = "SELECT t FROM Tickets t WHERE t.price = :price")})
public class Tickets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idticket")
    private Long idticket;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private short status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Date_Buy")
    private String dateBuy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Date_Start")
    private String dateStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @JoinColumn(name = "plan_planId", referencedColumnName = "planId")
    @ManyToOne(optional = false)
    private Plan planplanId;
    @JoinColumn(name = "user_userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private User useruserId;

    public Tickets() {
    }

    public Tickets(Long idticket) {
        this.idticket = idticket;
    }

    public Tickets(Long idticket, short status, String dateBuy, String dateStart, double price) {
        this.idticket = idticket;
        this.status = status;
        this.dateBuy = dateBuy;
        this.dateStart = dateStart;
        this.price = price;
    }

    public Long getIdticket() {
        return idticket;
    }

    public void setIdticket(Long idticket) {
        this.idticket = idticket;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(String dateBuy) {
        this.dateBuy = dateBuy;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Plan getPlanplanId() {
        return planplanId;
    }

    public void setPlanplanId(Plan planplanId) {
        this.planplanId = planplanId;
    }

    public User getUseruserId() {
        return useruserId;
    }

    public void setUseruserId(User useruserId) {
        this.useruserId = useruserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idticket != null ? idticket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.idticket == null && other.idticket != null) || (this.idticket != null && !this.idticket.equals(other.idticket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Tickets[ idticket=" + idticket + " ]";
    }
    
}
