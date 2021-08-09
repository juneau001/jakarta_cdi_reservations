
package com.mycompany.reservations.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * Entity class for the RESERVATION_TYPE table.
 * @author Juneau
 */
@Table(name = "RESERVATION_TYPE")
@Entity
public class ReservationType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;
    
    @Column(name="RESERVATION_TYPE")
    private String reservationType;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

        /**
     * @return the type
     */
    public String getReservationType() {
        return reservationType;
    }

    /**
     * @param type the type to set
     */
    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationType)) {
            return false;
        }
        ReservationType other = (ReservationType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.reservations.entity.ReservationType[ id=" + id + " ]";
    }


}
