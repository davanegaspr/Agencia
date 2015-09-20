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
@Table(name = "hotels", catalog = "agencia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotels.findAll", query = "SELECT h FROM Hotels h"),
    @NamedQuery(name = "Hotels.findByIdHotel", query = "SELECT h FROM Hotels h WHERE h.idHotel = :idHotel"),
    @NamedQuery(name = "Hotels.findByName", query = "SELECT h FROM Hotels h WHERE h.name = :name"),
    @NamedQuery(name = "Hotels.findByCategory", query = "SELECT h FROM Hotels h WHERE h.category = :category"),
    @NamedQuery(name = "Hotels.findByPrice", query = "SELECT h FROM Hotels h WHERE h.price = :price"),
    @NamedQuery(name = "Hotels.findByLocation", query = "SELECT h FROM Hotels h WHERE h.location = :location")})
public class Hotels implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idHotel")
    private Long idHotel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "category")
    private int category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "location")
    private String location;

    public Hotels() {
    }

    public Hotels(Long idHotel) {
        this.idHotel = idHotel;
    }

    public Hotels(Long idHotel, String name, int category, float price, String location) {
        this.idHotel = idHotel;
        this.name = name;
        this.category = category;
        this.price = price;
        this.location = location;
    }

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHotel != null ? idHotel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotels)) {
            return false;
        }
        Hotels other = (Hotels) object;
        if ((this.idHotel == null && other.idHotel != null) || (this.idHotel != null && !this.idHotel.equals(other.idHotel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.Hotels[ idHotel=" + idHotel + " ]";
    }
    
}
