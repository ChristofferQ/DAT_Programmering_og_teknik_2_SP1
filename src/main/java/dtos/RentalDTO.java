package dtos;

import entities.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalDTO {
    private long id;
    private String startDate;
    private String endDate;
    private int priceAnnual;
    private int deposit;
    private String contactPerson;

    public RentalDTO(long id, String startDate, String endDate, int priceAnnual, int deposit, String contactPerson) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.contactPerson = contactPerson;
    }

    public RentalDTO(Rental r) {
        this.id = r.getId();
        this.startDate = r.getStartDate();
        this.endDate = r.getEndDate();
        this.priceAnnual = r.getPriceAnnual();
        this.deposit = r.getDeposit();
        this.contactPerson = r.getContactPerson();
    }

    public static List<RentalDTO> getDtos(List<Rental> rs) {
        List<RentalDTO> rentalDTOS = new ArrayList<>();
        rs.forEach(r -> rentalDTOS.add(new RentalDTO(r)));
        return rentalDTOS;
    }

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

    @Override
    public String toString() {
        return "RentalDTO{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", priceAnnual=" + priceAnnual +
                ", deposit=" + deposit +
                ", contactPerson='" + contactPerson + '\'' +
                '}';
    }
}
