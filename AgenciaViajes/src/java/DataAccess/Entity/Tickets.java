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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Tickets.findByStatus", query = "SELECT t FROM Tickets t WHERE t.status = :status"),
    @NamedQuery(name = "Tickets.findByDateBuy", query = "SELECT t FROM Tickets t WHERE t.dateBuy = :dateBuy"),
    @NamedQuery(name = "Tickets.findByDateStart", query = "SELECT t FROM Tickets t WHERE t.dateStart = :dateStart"),
    @NamedQuery(name = "Tickets.findByIdPlan", query = "SELECT t FROM Tickets t WHERE t.idPlan = :idPlan"),
    @NamedQuery(name = "Tickets.findByIdUser", query = "SELECT t FROM Tickets t WHERE t.idUser = :idUser"),
    @NamedQuery(name = "Tickets.findByPrice", query = "SELECT t FROM Tickets t WHERE t.price = :price")})
public class Tickets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTicket")
    private Long idTicket;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private int status;
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

    public Tickets(Long idTicket, int status, String dateBuy, String dateStart, long idPlan, long idUser, float price) {
        this.idTicket = idTicket;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
