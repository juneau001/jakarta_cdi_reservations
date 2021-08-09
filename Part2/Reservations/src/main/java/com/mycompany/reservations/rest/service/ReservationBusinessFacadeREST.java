
package com.mycompany.reservations.rest.service;

import com.mycompany.reservations.entity.ReservationBusiness;
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
@Path("reservationBusiness")
public class ReservationBusinessFacadeREST extends AbstractFacade<ReservationBusiness> {

    @PersistenceContext(unitName = "reservation_persistence_unit")
    private EntityManager em;

    public ReservationBusinessFacadeREST() {
        super(ReservationBusiness.class);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ReservationBusiness> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Accepts a business id and then returns the corresponding ReservationEntity.
     * @param businessId
     * @return 
     */
    public ReservationBusiness findByBusinessId(Integer businessId){
        System.out.println("checking for business: " + businessId);
        ReservationBusiness res = null;
        try {
            res = (ReservationBusiness) em.createQuery("select object(o) from ReservationBusiness o " +
                    "where o.id = :businessId")
                    .setParameter("businessId", businessId)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return res;
    }
    
    /**
     * Accepts a business type and then returns the corresponding ReservationEntity.
     * @param businessType
     * @return 
     */
    public List<ReservationBusiness> findByBusinessType(String businessType){
        List<ReservationBusiness> res = null;
        try {
            res =  em.createQuery("select object(o) from ReservationBusiness o " +
                    "where o.businessType = :businessType")
                    .setParameter("businessType", businessType)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return res;
    }
    
}
