
package com.mycompany.reservations.observer;

import com.mycompany.reservations.bean.ReservationEvent;
import com.mycompany.reservations.qualifier.Restaurant;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Named;

/**
 *
 * @author Juneau
 */
@Named
@ApplicationScoped
public class RestaurantEventHandler {

    public void notifyBusiness(@ObservesAsync @Restaurant ReservationEvent event) {
        System.out.println("restaurant event...sending information about: " + event.getFirstName() + " " + event.getLastName() +
                " - Reservation Date(" + event.getReservationDate() + ")");
    }
    
}
