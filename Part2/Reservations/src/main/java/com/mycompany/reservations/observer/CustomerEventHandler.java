/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reservations.observer;

import com.mycompany.reservations.bean.ReservationEvent;
import com.mycompany.reservations.qualifier.CustomerAcceptance;
import com.mycompany.reservations.qualifier.CustomerCancellation;
import com.mycompany.reservations.qualifier.CustomerConfirmation;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Named;

/**
 *
 * @author Juneau
 */
@Named
@ApplicationScoped
public class CustomerEventHandler {
    
    public void notifyCustomerOfReceipt(@ObservesAsync @Priority(2) @CustomerConfirmation ReservationEvent event) {
        System.out.println("send customer confirmation of receipt: " + event.getFirstName() + " " + event.getLastName() +
                " - Reservation Date(" + event.getReservationDate() + ")");
    }
    
    public void notifyCustomerAcceptance(@ObservesAsync @Priority(2) @CustomerAcceptance ReservationEvent event) {
        System.out.println("send customer confirmation of accepatance: " + event.getFirstName() + " " + event.getLastName() +
                " - Reservation Date(" + event.getReservationDate() + ")");
    }
    
    public void notifyCustomerCancellation(@ObservesAsync @Priority(2) @CustomerCancellation ReservationEvent event) {
        System.out.println("send customer confirmation of cancellation: " + event.getFirstName() + " " + event.getLastName() +
                " - Reservation Date(" + event.getReservationDate() + ")");
    }
}
