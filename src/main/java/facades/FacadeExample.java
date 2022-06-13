package facades;

import dtos.RenameMeDTO;
import dtos.RentalDTO;
import entities.RenameMe;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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


    
    public RenameMeDTO create(RenameMeDTO rm){
        RenameMe rme = new RenameMe(rm.getDummyStr1(), rm.getDummyStr2());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RenameMeDTO(rme);
    }
    public RenameMeDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        RenameMe rm = em.find(RenameMe.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new RenameMeDTO(rm);
    }

    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = getEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
    }

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

//    public List<HouseDTO> getHouseByRental() {
//
//    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        fe.getAllRentals().forEach(dto->System.out.println(dto));
    }

}
