/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reservations.bean;

import java.time.LocalDateTime;

/**
 *
 * @author Juneau
 */
public class ReservationEvent {
    
    private String firstName;
    private String lastName;
    private LocalDateTime reservationDate;
    private String reservationCreator;
    private LocalDateTime createdDateTime;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the reservationDate
     */
    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    /**
     * @param reservationDate the reservationDate to set
     */
    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * @return the reservationCreator
     */
    public String getReservationCreator() {
        return reservationCreator;
    }

    /**
     * @param reservationCreator the reservationCreator to set
     */
    public void setReservationCreator(String reservationCreator) {
        this.reservationCreator = reservationCreator;
    }

    /**
     * @return the createdDateTime
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * @param createdDateTime the createdDateTime to set
     */
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
      
}
