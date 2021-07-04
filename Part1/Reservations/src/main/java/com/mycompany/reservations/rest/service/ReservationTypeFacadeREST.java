
package com.mycompany.reservations.rest.service;

import com.mycompany.reservations.entity.ReservationType;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author Juneau
 */
@jakarta.ejb.Stateless
@Path("reservationType")
public class ReservationTypeFacadeREST extends AbstractFacade<ReservationType> {

    @PersistenceContext(unitName = "reservation_persistence_unit")
    private EntityManager em;

    public ReservationTypeFacadeREST() {
        super(ReservationType.class);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ReservationType> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
