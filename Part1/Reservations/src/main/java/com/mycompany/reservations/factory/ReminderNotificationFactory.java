
package com.mycompany.reservations.factory;

import static com.mycompany.reservations.ReservationTypeEnum.HOTEL;
import static com.mycompany.reservations.ReservationTypeEnum.RESTAURANT;
import com.mycompany.reservations.controller.ReminderController;
import com.mycompany.reservations.qualifier.Hotel;
import com.mycompany.reservations.qualifier.Restaurant;
import com.mycompany.reservations.rest.service.ReminderService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

/**
 * Create instances of other classes, on demand.
 * @author Juneau
 */
@ApplicationScoped
public class ReminderNotificationFactory {
    
    private static final long serialVersionUID = 5269302440619391616L;
    
    public ReminderNotificationFactory(){
        System.out.println("Instantiating Notification Factory");
    }
    
    /**
     * Produce a hotel reminder service.
     * @return 
     */
    @Produces
    @ApplicationScoped
    @Hotel
    public ReminderService getHotelReminderService() {
        System.out.println("Creating Hotel Reminder Service");
        return new ReminderController(HOTEL);
    } 
    
    /**
     * Produce a restaurant reminder service.
     * @return 
     */
    @Produces
    @ApplicationScoped
    @Restaurant
    public ReminderService getRestaurantReminderService() {
        System.out.println("Creating Restaurant Reminder Service");
        return new ReminderController(RESTAURANT);
    } 
}
