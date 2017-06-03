package com.tutor.resource;

import com.tutor.model.Model;
import com.tutor.service.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Wiranji Dinelka on 6/3/2017.
 */
@Path("model")
public class Resource {
    Service service = new Service();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Model> getMessages() {
        return service.getAllModels();
    }
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String showModelId() {
        return "test";
    }

    @GET
    @Path("/{modelId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String showModelId(@PathParam("modelId") String id) {
        return id;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String showModel() {
        return "Post Method";
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addModel(Model model) {
        return model.getMessage();
    }
}
