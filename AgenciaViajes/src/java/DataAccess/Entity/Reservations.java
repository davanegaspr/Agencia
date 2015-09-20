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
@Table(name = "reservations", catalog = "agencia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r"),
    @NamedQuery(name = "Reservations.findByIdReservation", query = "SELECT r FROM Reservations r WHERE r.idReservation = :idReservation"),
    @NamedQuery(name = "Reservations.findByIdPlan", query = "SELECT r FROM Reservations r WHERE r.idPlan = :idPlan"),
    @NamedQuery(name = "Reservations.findByDateReservation", query = "SELECT r FROM Reservations r WHERE r.dateReservation = :dateReservation")})
public class Reservations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idReservation")
    private Long idReservation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPlan")
    private long idPlan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Reservation")
    @Temporal(TemporalType.DATE)
    private Date dateReservation;

    public Reservations() {
    }

    public Reservations(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Reservations(Long idReservation, long idPlan, Date dateReservation) {
        this.idReservation = idReservation;
        this.idPlan = idPlan;
        this.dateReservation = dateReservation;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(long idPlan) {
        this.idPlan = idPlan;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservation != null ? idReservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.idReservation == null && other.idReservation != null) || (this.idReservation != null && !this.idReservation.equals(other.idReservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Reservations[ idReservation=" + idReservation + " ]";
    }
    
}
