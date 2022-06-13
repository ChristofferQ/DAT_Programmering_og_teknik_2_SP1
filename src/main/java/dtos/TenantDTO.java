package dtos;

import entities.Rental;
import entities.Tenant;

import java.util.ArrayList;
import java.util.List;

public class TenantDTO {
    private long id;
    private String name;
    private int phone;
    private String job;

    public TenantDTO(long id, String name, int phone, String job) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.job = job;
    }

    public TenantDTO(Tenant t) {
        this.id = t.getId();
        this.name = t.getName();
        this.phone = t.getPhone();
        this.job = t.getJob();
    }

    public static List<TenantDTO> getDtos(List<Tenant> ts) {
        List<TenantDTO> tenantDTOS = new ArrayList<>();
        ts.forEach(t -> tenantDTOS.add(new TenantDTO(t)));
        return tenantDTOS;
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

    @Override
    public String toString() {
        return "TenantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", job='" + job + '\'' +
                '}';
    }
}
