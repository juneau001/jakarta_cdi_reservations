
package com.mycompany.reservations.controller;

import com.mycompany.reservations.bean.ReservationEvent;
import com.mycompany.reservations.entity.Reservation;
import com.mycompany.reservations.qualifier.Hotel;
import com.mycompany.reservations.qualifier.Log;
import com.mycompany.reservations.qualifier.Restaurant;
import com.mycompany.reservations.rest.service.ReminderService;
import com.mycompany.reservations.rest.service.ReservationFacadeREST;
import com.mycompany.reservations.util.JsfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import jakarta.faces.view.ViewScoped;

/**
 *
 * @author Juneau
 */
@Named
@ViewScoped
public class ReservationController implements java.io.Serializable  {
    
    @EJB
    private ReservationFacadeREST ejbFacade;
    
    @Inject
    private AuthenticationController authenticationController;
    
    @Inject @Hotel
    private Event<ReservationEvent> hotelReservationEvent;
    
    @Inject @Restaurant
    private Event<ReservationEvent> restaurantReservationEvent;
    
    @Inject @Hotel
    private ReminderService hotelReminderService;
    
    @Inject @Restaurant
    private ReminderService restaurantReminderService;
    
    private List<Reservation> reservationList;
    private Reservation currentReservation;
    
    public ReservationController(){}
    
    @PostConstruct
    public void init(){
        populateReservationList();
    }
    
    /**
     * Initialize and populate reservationList
     */
    public void populateReservationList(){
        reservationList = ejbFacade.findAll();
    }
    
    /**
     * Create new reservation by saving the contents of currentReservation to
     * the database.
     */
    @Log
    public void createReservation(){
        if(currentReservation != null && 
                currentReservation.getFirstName() != null &&
                currentReservation.getLastName() != null &&
                currentReservation.getReservationDate()!= null){
            // Find max CUSTOMER_ID and add one
            System.out.println("creating reservation:");
            System.out.println("first: " + currentReservation.getFirstName());
            System.out.println("last: " + currentReservation.getLastName());
            System.out.println("date: " + currentReservation.getReservationDate());
            int id = 0;
            if(reservationList != null && reservationList.size() > 0){
                Reservation maxId =  Collections.max(reservationList, Comparator.comparing(c -> c.getId()));
                id = maxId.getId() + 1;
            }
            currentReservation.setId(id);
            ejbFacade.create(currentReservation);
            // Fire event to send notification to hotel or restaurant
            ReservationEvent payload = new ReservationEvent();
            payload.setFirstName(currentReservation.getFirstName());
            payload.setLastName(currentReservation.getLastName());
            payload.setReservationDate(currentReservation.getReservationDate());
            payload.setReservationCreator(authenticationController.getCurrentUser().getUsername());
            if(currentReservation.getReservationType().equals("HOTEL")){
                hotelReservationEvent.fireAsync(payload)
                    .whenCompleteAsync((event, throwable) -> {
                        if (throwable != null) {
                            System.out.println("there is an issue");
                        } else {
                            System.out.println("hotel");
                            hotelReminderService.scheduleReminder();
                            init();
                        }
                    });
            } else if (currentReservation.getReservationType().equals("RESTAURANT")){
                restaurantReservationEvent.fireAsync(payload)
                    .whenCompleteAsync((event, throwable) -> {
                        if (throwable != null) {
                            System.out.println("there is an issue");
                        } else {
                            restaurantReminderService.scheduleReminder();
                            init();
                        }
                    });
            }
            reservationList = null;
            currentReservation = null;
            JsfUtil.addSuccessMessage("Reservation Created Successfully");
        } else {
            JsfUtil.addErrorMessage("Reservation Not Created:  Ensure all fields are popoulated");
        }
    }

    /**
     * Initialize the currentReservation, if null.  Return currentReservation.
     * @return the currentReservation
     */
    public Reservation getCurrentReservation() {
        if(currentReservation == null){
            currentReservation = new Reservation();
        }
        return currentReservation;
    }

    /**
     * Set the currentReservation.
     * @param currentReservation the currentReservation to set
     */
    public void setCurrentReservation(Reservation currentReservation) {
        this.currentReservation = currentReservation;
    }

    /**
     * Populate the reservationList if null, and return.
     * @return the reservationList
     */
    public List<Reservation> getReservationList() {
        if(reservationList == null){
            populateReservationList();
        }
        return reservationList;
    }

    /**
     * Set the reservationList.
     * @param reservationList the reservationList to set
     */
    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
    
    
    
}
