
package com.mycompany.reservations.controller;

import com.mycompany.reservations.ReservationTypeEnum;
import com.mycompany.reservations.rest.service.ReminderService;

/**
 *
 * @author Juneau
 */
public class ReminderController implements ReminderService {
    
    private final ReservationTypeEnum notificationType;
    
    public ReminderController(ReservationTypeEnum type){
        this.notificationType = type;
    }
    
    /**
     * Schedule the reminder for the appropriate entity.
     */
    @Override
    public void scheduleReminder() {
        switch(notificationType){
            case HOTEL:
                System.out.println("Set up the reminder for the Hotel");
                break;
            case RESTAURANT:
                System.out.println("Set up the reminder for the Restaruant");
                break;
            default:
                System.out.println("No reminder");
        }
       
    }
    
}
