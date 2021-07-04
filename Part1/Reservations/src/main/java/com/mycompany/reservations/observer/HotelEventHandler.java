
package com.mycompany.reservations.observer;

import com.mycompany.reservations.bean.ReservationEvent;
import com.mycompany.reservations.qualifier.Hotel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Named;

/**
 *
 * @author Juneau
 */
@Named
@ApplicationScoped
public class HotelEventHandler  {

    public void notifyBusiness(@ObservesAsync @Hotel ReservationEvent event) {
        System.out.println("hotel event...sending information about: " + event.getFirstName() + " " + event.getLastName() +
                " - Reservation Date(" + event.getReservationDate() + ")");
    }
    
}
