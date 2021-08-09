
package com.mycompany.reservations.observer;

import com.mycompany.reservations.bean.ReservationEvent;
import com.mycompany.reservations.qualifier.Restaurant;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.enterprise.inject.Vetoed;
import jakarta.inject.Named;

/**
 *
 * @author Juneau
 */
@Named
@ApplicationScoped
@Vetoed
public class RestaurantEventHandler {

    public void notifyRestaurantTesting(@ObservesAsync @Priority(1) @Restaurant ReservationEvent event) {
        System.out.println("restaurant event (vetoed)...sending information about: " +
                event.getFirstName() + " " + event.getLastName() +
                " - Reservation Date(" + event.getReservationDate() + ")");
    }
    
}
