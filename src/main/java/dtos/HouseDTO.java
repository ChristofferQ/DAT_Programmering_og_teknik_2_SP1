package dtos;

import entities.House;
import entities.Rental;

import java.util.ArrayList;
import java.util.List;

public class HouseDTO {
    private long id;
    private String address;
    private String city;
    private int numberOfRooms;

    public HouseDTO(long id, String address, String city, int numberOfRooms) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.numberOfRooms = numberOfRooms;
    }

    public HouseDTO(House h) {
        this.id = h.getId();
        this.address = h.getAddress();
        this.city = h.getCity();
        this.numberOfRooms = h.getNumberOfRooms();
    }

    public static List<HouseDTO> getDtos(List<House> hs) {
        List<HouseDTO> houseDTOS = new ArrayList<>();
        hs.forEach(h -> houseDTOS.add(new HouseDTO(h)));
        return houseDTOS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public String toString() {
        return "HouseDTO{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                '}';
    }
}
