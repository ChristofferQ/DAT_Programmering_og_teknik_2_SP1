package facades;

import dtos.HouseDTO;
import dtos.RenameMeDTO;
import dtos.RentalDTO;
import dtos.TenantDTO;
import entities.House;
import entities.RenameMe;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

//import errorhandling.RenameMeNotFoundException;
import entities.Rental;
import entities.Tenant;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Rental Methods:
     */

    public RentalDTO getRentalById(long id) {
        EntityManager em = emf.createEntityManager();
        Rental r = em.find(Rental.class, id);
        return new RentalDTO(r);
    }
    
    public List<RentalDTO> getAllRentals() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r", Rental.class);
        List<Rental> rs = query.getResultList();
        return RentalDTO.getDtos(rs);
    }

    public RentalDTO createRental(RentalDTO r){
        Rental re = new Rental(r.getStartDate(), r.getEndDate(), r.getPriceAnnual(), r.getDeposit(), r.getContactPerson());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(re);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDTO(re);
    }

    public RentalDTO connectRentalWithTenant(long rentalId, long tenantId) {
        EntityManager em = emf.createEntityManager();
        try {
            Rental r = em.find(Rental.class, rentalId);
            Tenant t = em.find(Tenant.class, tenantId);

            r.addTenant(t);
            t.addRental(r);

            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            return new RentalDTO(r);

        } finally {
            em.close();
        }
    }

    public RentalDTO editRental(RentalDTO r) {
        EntityManager em = emf.createEntityManager();
        try {
            Rental rental = em.find(Rental.class, r.getId());

            rental.setStartDate(r.getStartDate() );
            rental.setEndDate(r.getEndDate());
            rental.setPriceAnnual(r.getPriceAnnual());
            rental.setDeposit(r.getDeposit());
            rental.setContactPerson(r.getContactPerson());

            em.getTransaction().begin();
            em.merge(rental);
            em.getTransaction().commit();

            return new RentalDTO(rental);

        } finally {
            em.close();
        }
    }

    public Response deleteRental(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Rental r WHERE r.id = :id").setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
            return Response.ok().build();
        } finally {
            em.close();
        }
    }


    /**
     * Tenant Methods:
     */

    public TenantDTO getTenantById(long id) {
        EntityManager em = emf.createEntityManager();
        Tenant t = em.find(Tenant.class, id);
        return new TenantDTO(t);
    }

    public List<TenantDTO> getTenantsInHouseByRental(long id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Tenant> query = em.createQuery("SELECT t FROM Tenant t INNER JOIN t.rentals r WHERE r.house.id =:id",Tenant.class).setParameter("id", id);
        List<Tenant> ts = query.getResultList();
        return TenantDTO.getDtos(ts);
    }

    /**
     * House Methods:
     */

    public HouseDTO getHouseById(long id) {
        EntityManager em = emf.createEntityManager();
        House h = em.find(House.class, id);
        return new HouseDTO(h);
    }

    public List<HouseDTO> getHouseByRental(long id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<House> query = em.createQuery("SELECT r.house FROM Rental r WHERE r.id =:id", House.class).setParameter("id", id);
        List<House> hs = query.getResultList();
        return HouseDTO.getDtos(hs);
    }

    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
//        fe.getAllRentals().forEach(dto->System.out.println(dto));
//        fe.createRental(new RentalDTO(new Rental("startDate3", "endDate3", 3000, 3500, "contactPerson3")));
//        fe.connectRentalWithTenant(3,1);
//        fe.deleteRental(3);
//        fe.getTenantsByRental(2).forEach(dto -> System.out.println(dto));
//        fe.getHouseByRental(5).forEach(dto -> System.out.println(dto));

//        RentalDTO r = fe.getRentalById(1);
//        r.setContactPerson("contactPerson2");
//        fe.editRental(r);
    }

}
