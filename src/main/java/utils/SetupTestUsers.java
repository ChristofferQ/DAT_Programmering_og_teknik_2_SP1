package utils;


import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Set;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test123");
    User admin = new User("admin", "test123");
    User both = new User("user_admin", "test123");

    Tenant tenant1 = new Tenant("Name1",123456,"Job1");
    Tenant tenant2 = new Tenant("Name2",123456,"Job2");

    Rental rental1 = new Rental("startDate1","endDate1",1000,1500,"contactPerson1");
    Rental rental2 = new Rental("startDate2","endDate2",2000,2500,"contactPerson2");

    House house1 = new House("address1","city1",1);
    House house2 = new House("address2","city2",2);

    rental1.addTenant(tenant1);
    rental2.addTenant(tenant2);

    rental1.addHouse(house1);
    rental2.addHouse(house2);


    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.persist(tenant1);
    em.persist(tenant2);
    em.persist(rental1);
    em.persist(rental2);
    em.persist(house1);
    em.persist(house2);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
