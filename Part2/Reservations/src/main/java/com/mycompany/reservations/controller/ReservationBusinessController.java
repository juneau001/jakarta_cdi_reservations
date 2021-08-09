/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reservations.controller;

import com.mycompany.reservations.entity.ReservationBusiness;
import com.mycompany.reservations.rest.service.ReservationBusinessFacadeREST;
import com.mycompany.reservations.util.JsfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Juneau
 */
@Named
@ViewScoped
public class ReservationBusinessController implements java.io.Serializable {

    @EJB
    private ReservationBusinessFacadeREST ejbFacade;

    @Inject
    AuthenticationController authenticationController;

    private List<ReservationBusiness> reservationBusinessList;

    private ReservationBusiness current;
    
    private String selectedReservationType;

    public ReservationBusinessController() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        // If the authenticated user is a business owner, then load their business
        // into the current variable.
        int businessId = -1;
        if (authenticationController.getCurrentUser() != null
                && authenticationController.getCurrentUser().getBusinessId() > -1) {
            businessId = authenticationController.getCurrentUser().getBusinessId();
            setCurrent(ejbFacade.findByBusinessId(businessId));
        }
    }

    /**
     * Action event that is invoked when user selects a reservation type.  Populates
     * the reservationBusinessList with the appropriate list of businesses for the 
     * transaction.
     * @param event 
     */
    public void changeReservationTypeAction(SelectEvent event) {
        reservationBusinessList = null;
        this.selectedReservationType = (String) event.getObject();
    }

    /**
     * Persists the ReservationBusiness
     */
    public void createEntity() {
        if (current != null) {
            int id = 0;
            if(reservationBusinessList != null && reservationBusinessList.size() > 0){
                ReservationBusiness maxId =  Collections.max(reservationBusinessList, Comparator.comparing(c -> c.getId()));
                id = maxId.getId() + 1;
            }
            current.setId(id);
            ejbFacade.create(current);
            reservationBusinessList = null;
            JsfUtil.addSuccessMessage("Successfully Created Entity");
        }
    }

    /**
     * @return the current
     */
    public ReservationBusiness getCurrent() {
        if(current == null){
            current = new ReservationBusiness();
        }
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(ReservationBusiness current) {
        this.current = current;
    }

    /**
     * @return the reservationBusinessList
     */
    public List<ReservationBusiness> getReservationBusinessList() {
        if (reservationBusinessList == null) {
            if(selectedReservationType != null){
                reservationBusinessList = ejbFacade.findByBusinessType(selectedReservationType);
            } else {
                reservationBusinessList = ejbFacade.findAll();
            }
        }
        return reservationBusinessList;
    }

    /**
     * @param reservationBusinessList the reservationBusinessList to set
     */
    public void setReservationBusinessList(List<ReservationBusiness> reservationBusinessList) {
        this.reservationBusinessList = reservationBusinessList;
    }

}
