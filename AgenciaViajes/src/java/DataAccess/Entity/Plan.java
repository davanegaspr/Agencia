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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "plan", catalog = "agencia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
    @NamedQuery(name = "Plan.findByIdPlan", query = "SELECT p FROM Plan p WHERE p.idPlan = :idPlan"),
    @NamedQuery(name = "Plan.findByName", query = "SELECT p FROM Plan p WHERE p.name = :name"),
    @NamedQuery(name = "Plan.findByPrice", query = "SELECT p FROM Plan p WHERE p.price = :price"),
    @NamedQuery(name = "Plan.findByDateStart", query = "SELECT p FROM Plan p WHERE p.dateStart = :dateStart"),
    @NamedQuery(name = "Plan.findByDateEnd", query = "SELECT p FROM Plan p WHERE p.dateEnd = :dateEnd"),
    @NamedQuery(name = "Plan.findByDestination", query = "SELECT p FROM Plan p WHERE p.destination = :destination")})
public class Plan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPlan")
    private Long idPlan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private long price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Start")
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_End")
    @Temporal(TemporalType.DATE)
    private Date dateEnd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "destination")
    private String destination;

    public Plan() {
    }

    public Plan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public Plan(Long idPlan, String name, long price, Date dateStart, Date dateEnd, String destination) {
        this.idPlan = idPlan;
        this.name = name;
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.destination = destination;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.idPlan == null && other.idPlan != null) || (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Plan[ idPlan=" + idPlan + " ]";
    }
    
}
