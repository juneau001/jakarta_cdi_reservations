/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reservations.converter;

import com.mycompany.reservations.entity.ReservationBusiness;
import com.mycompany.reservations.rest.service.ReservationBusinessFacadeREST;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Named;

/**
 *
 * @author Juneau
 */
@Named
@SessionScoped
public class ReservationBusinessConverter implements Converter, java.io.Serializable {

    @EJB
    ReservationBusinessFacadeREST reservationBusinessFacade;

    @Override
    public Object getAsObject(FacesContext facesContext,
            UIComponent component, String value) {
        ReservationBusiness reservationBusiness = null;
        if (value != null && value.length() > 0) {
            System.out.println("value: " + value);
            if (value != null && value.contains("]")) {
                value = value.substring(value.indexOf("=")+1, value.indexOf("]")-1);
            }
            System.out.println("Value now: " + value + "!");
            reservationBusiness = reservationBusinessFacade.findByBusinessId(Integer.valueOf(value));
        }
        return reservationBusiness;
    }

    @Override
    public String getAsString(FacesContext facesContext,
            UIComponent component, Object value) {
        return value.toString();
    }

}
