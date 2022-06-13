package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TENANT")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int phone;
    private String job;

    @ManyToMany(mappedBy = "tenants", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) // Non owning side
    private Set<Rental> rentals = new HashSet<>();

    public Tenant() {
    }

    public Tenant(String name, int phone, String job) {
        this.name = name;
        this.phone = phone;
        this.job = job;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void addRental (String startDate, String endDate, int priceAnnual, int deposit, String contactPerson) {
        Rental newRental = new Rental(startDate, endDate, priceAnnual, deposit, contactPerson);
        newRental.addTenant(this);
        this.rentals.add(newRental);
    }
}
