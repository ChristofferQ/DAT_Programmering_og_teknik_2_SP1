package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RentalDTO;
import dtos.TenantDTO;
import utils.EMF_Creator;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
       
        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("rental/{id}")
    public Response getRentalById(@PathParam("id")long id) {
        return Response.ok(GSON.toJson(FACADE.getRentalById(id))).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("rentals")
    public Response getAllRentals() {
        return Response.ok(GSON.toJson(FACADE.getAllRentals())).build();
    }


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("createrental")
    public Response createRental(String Rental) {
        RentalDTO r = GSON.fromJson(Rental, RentalDTO.class);
        RentalDTO re = FACADE.createRental(r);
        return Response.ok(re).build();
    }

    // TODO Fix 500 error in login.http PUT request
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("connectrental/{id}")
    public Response connectRentalWithTenant(@PathParam("id") long id, String tenant) {
        TenantDTO t = GSON.fromJson(tenant, TenantDTO.class);
        RentalDTO rEdited = FACADE.connectRentalWithTenant(id, t.getId());
        return Response.ok(GSON.toJson(rEdited)).build();
    }


    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("deleterental/{id}")
    public Response deleteBooking(@PathParam("id") long id) {
        return Response.ok(GSON.toJson(FACADE.deleteRental(id))).build();
    }

}
