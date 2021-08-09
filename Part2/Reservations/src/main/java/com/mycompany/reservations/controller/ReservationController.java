
package com.mycompany.reservations.controller;

import com.mycompany.reservations.ReservationCreate;
import com.mycompany.reservations.bean.ReservationEvent;
import com.mycompany.reservations.entity.Reservation;
import com.mycompany.reservations.qualifier.CustomerAcceptance;
import com.mycompany.reservations.qualifier.CustomerCancellation;
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
import com.mycompany.reservations.qualifier.CustomerConfirmation;

/**
 *
 * @author Juneau
 */
@Named
@ViewScoped
public class ReservationController implements ReservationCreate, java.io.Serializable  {
    
    @EJB
    private ReservationFacadeREST ejbFacade;
    
    @Inject
    private AuthenticationController authenticationController;
    
    @Inject @Hotel
    private Event<ReservationEvent> hotelReservationEvent;
    
    @Inject @Restaurant
    private Event<ReservationEvent> restaurantReservationEvent;
    
    @Inject @CustomerConfirmation
    private Event<ReservationEvent> customerConfirmationEvent;
    
    @Inject @CustomerAcceptance
    private Event<ReservationEvent> customerAcceptanceEvent;
    
    @Inject @CustomerCancellation
    private Event<ReservationEvent> customerCancellationEvent;
    
    @Inject @Hotel
    private ReminderService hotelReminderService;
    
    @Inject @Restaurant
    private ReminderService restaurantReminderService;
    
    private List<Reservation> reservationList;
    private Reservation currentReservation;
    private String selectedReservationType;

    
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
                            System.out.println("there is an issue notifying hotel");
                        } else {
                            hotelReminderService.scheduleReminder();
                            init();
                        }
                    });
                customerConfirmationEvent.fireAsync(payload)
                    .whenCompleteAsync((event, throwable) -> {
                        if (throwable != null) {
                            System.out.println("there is an issue notifying customer of receipt");
                        } 
                    });
            } else if (currentReservation.getReservationType().equals("RESTAURANT")){
                restaurantReservationEvent.fireAsync(payload)
                    .whenCompleteAsync((event, throwable) -> {
                        if (throwable != null) {
                            System.out.println("there is an issue notifying restaurant");
                        } else {
                            restaurantReminderService.scheduleReminder();
                            init();
                        }
                    });
                customerConfirmationEvent.fireAsync(payload)
                    .whenCompleteAsync((event, throwable) -> {
                        if (throwable != null) {
                            System.out.println("there is an issue notifying customer of receipt");
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
    
    public void confirmReservation(){
        ReservationEvent payload = new ReservationEvent();
            payload.setFirstName(currentReservation.getFirstName());
            payload.setLastName(currentReservation.getLastName());
            payload.setReservationDate(currentReservation.getReservationDate());
            payload.setReservationCreator(authenticationController.getCurrentUser().getUsername());
        customerAcceptanceEvent.fireAsync(payload)
                    .whenCompleteAsync((event, throwable) -> {
                        if (throwable != null) {
                            System.out.println("there is an issue");
                        } 
                    });
        JsfUtil.addSuccessMessage("Successfully Confirmed Reservation: " + currentReservation.getId());
        currentReservation = null;
    }
    
    public void removeReservation() {
        ejbFacade.remove(currentReservation);
        ReservationEvent payload = new ReservationEvent();
            payload.setFirstName(currentReservation.getFirstName());
            payload.setLastName(currentReservation.getLastName());
            payload.setReservationDate(currentReservation.getReservationDate());
            payload.setReservationCreator(authenticationController.getCurrentUser().getUsername());
        customerCancellationEvent.fireAsync(payload)
                    .whenCompleteAsync((event, throwable) -> {
                        if (throwable != null) {
                            System.out.println("there is an issue");
                        } 
                    });
        JsfUtil.addSuccessMessage("Successfully Removed Reservation: " + currentReservation.getId());
        currentReservation = null;
        reservationList = null;
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
