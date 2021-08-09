/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reservations.decorator;

import com.mycompany.reservations.ReservationCreate;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author Juneau
 */
@Decorator
public class ReservationDecorator implements ReservationCreate, Serializable {
    @Inject @Delegate ReservationCreate reservation;
    
    @Override
    public void createReservation() {
        System.out.println("** Decorator ** Applying additional functionality");
        reservation.createReservation();
    }
}
