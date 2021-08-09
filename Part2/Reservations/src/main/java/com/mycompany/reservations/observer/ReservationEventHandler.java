package com.mycompany.reservations.observer;

import com.mycompany.reservations.bean.ReservationEvent;

/**
 *
 * @author Juneau
 */
public interface ReservationEventHandler {
    public void notifyBusiness ( ReservationEvent event);
}
