package com.dana.controller;

import com.dana.model.HRHireFireWorkerDTO;
import com.dana.model.Position;
import com.dana.model.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/hr")
public class HrServer {

    @GET
    @Path("/{name}")
    public Response getMsg(@PathParam("name") String name) {
        String output = "Welcome   : " + name;
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/hire/{person-id}/{org-id}/{position}/{status}/{start-date}")
    public Response hrHire(@PathParam("person-id") long personId, @PathParam("org-id") long orgId,
                           @PathParam("position") String position, @PathParam("status") String status,
                           @PathParam("start-date") String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(date);
            Response response = HrClient.callXmlHrHireFireWorkerDTO(personId,
                    new HRHireFireWorkerDTO(orgId, Position.getByTitle(position),
                            Status.getByTitle(status), startDate));
            System.out.println(response.getStatus());
            System.out.println(response.getEntity().toString());
            System.out.println(response.getStatusInfo().toString());
            return response;
        } catch (IllegalArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        } catch (ParseException e) {
            return Response.status(422).entity("Could not parse date format: yyyy-MM-dd").build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            for (int i = 0; i < e.getStackTrace().length; i++) {
                System.out.println(e.getStackTrace()[i].toString());
            }
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/fire/{id}")
    public Response hrHire(@PathParam("id") long id) {
        return HrClient.callXmlHrHireFireWorkerDTO(id, new HRHireFireWorkerDTO());
    }
}
