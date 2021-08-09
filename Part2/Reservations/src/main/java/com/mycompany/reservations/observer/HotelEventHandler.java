
package com.mycompany.reservations.observer;

import com.mycompany.reservations.bean.ReservationEvent;
import com.mycompany.reservations.qualifier.Hotel;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import static jakarta.enterprise.context.BeforeDestroyed.Literal.APPLICATION;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Named;

/**
 *
 * @author Juneau
 */
@Named
@ApplicationScoped
public class HotelEventHandler  {
     
    
    public void notifyBusiness(@ObservesAsync @Priority(1) @Hotel ReservationEvent event) {
        System.out.println("hotel event...sending information about: " + event.getFirstName() + " " + event.getLastName() +
                " - Reservation Date(" + event.getReservationDate() + ")");
    }
    
}
