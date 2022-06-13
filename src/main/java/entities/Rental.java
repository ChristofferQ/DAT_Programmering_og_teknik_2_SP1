package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rental implements Serializable {
    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    private String startDate;
    private String endDate;
    private int priceAnnual;
    private int deposit;
    private String contactPerson;

    public Rental() {
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    private House house;

    @ManyToMany(mappedBy = "Tenant")
    @JoinTable(
            name = "RENTAL_TENANT",
            joinColumns = @JoinColumn(name = "RENTAL_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TENANT_ID", referencedColumnName = "ID"))
    private List<Rental> rentals = new ArrayList<Rental>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPriceAnnual() {
        return priceAnnual;
    }

    public void setPriceAnnual(int priceAnnual) {
        this.priceAnnual = priceAnnual;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
