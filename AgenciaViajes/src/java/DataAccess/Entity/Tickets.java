/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "tickets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @NamedQuery(name = "Tickets.findByIdTicket", query = "SELECT t FROM Tickets t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Tickets.findByDateBuy", query = "SELECT t FROM Tickets t WHERE t.dateBuy = :dateBuy"),
    @NamedQuery(name = "Tickets.findByDateStart", query = "SELECT t FROM Tickets t WHERE t.dateStart = :dateStart"),
    @NamedQuery(name = "Tickets.findByIdPlan", query = "SELECT t FROM Tickets t WHERE t.idPlan = :idPlan"),
    @NamedQuery(name = "Tickets.findByIdUser", query = "SELECT t FROM Tickets t WHERE t.idUser = :idUser"),
    @NamedQuery(name = "Tickets.findByPrice", query = "SELECT t FROM Tickets t WHERE t.price = :price")})
public class Tickets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTicket")
    private Long idTicket;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Buy")
    @Temporal(TemporalType.DATE)
    private Date dateBuy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Start")
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPlan")
    private long idPlan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private long idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;

    public Tickets() {
    }

    public Tickets(Long idTicket) {
        this.idTicket = idTicket;
    }

    public Tickets(Long idTicket, Date dateBuy, Date dateStart, long idPlan, long idUser, float price) {
        this.idTicket = idTicket;
        this.dateBuy = dateBuy;
        this.dateStart = dateStart;
        this.idPlan = idPlan;
        this.idUser = idUser;
        this.price = price;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(long idPlan) {
        this.idPlan = idPlan;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Tickets[ idTicket=" + idTicket + " ]";
    }
    
}
