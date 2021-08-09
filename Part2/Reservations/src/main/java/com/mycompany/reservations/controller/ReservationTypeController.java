
package com.mycompany.reservations.controller;

import com.mycompany.reservations.entity.ReservationType;
import com.mycompany.reservations.rest.service.ReservationTypeFacadeREST;
import com.mycompany.reservations.stereotype.HotelAction;
import jakarta.ejb.EJB;
import java.util.List;

/**
 *
 * @author Juneau
 */
@HotelAction
public class ReservationTypeController implements java.io.Serializable {
    
    @EJB
    private ReservationTypeFacadeREST ejbFacade;
    
    private List<ReservationType> reservationTypeList;
    
    public ReservationTypeController(){
        
    }
    
    /**
     * @return the reservationTypeList
     */
    public List<ReservationType> getReservationTypeList() {
        if(reservationTypeList == null){
            reservationTypeList = ejbFacade.findAll();
        }
        return reservationTypeList;
    }

    /**
     * @param reservationTypeList the reservationTypeList to set
     */
    public void setReservationTypeList(List<ReservationType> reservationTypeList) {
        this.reservationTypeList = reservationTypeList;
    }
    
}
