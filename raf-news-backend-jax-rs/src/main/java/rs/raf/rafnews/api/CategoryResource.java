package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.logging.LogEndpoint;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.service.implementation.CategoryService;
import rs.raf.rafnews.service.implementation.ServiceUserService;
import rs.raf.rafnews.service.specification.ICategoryService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/category")
public class CategoryResource {
    @Inject
    ICategoryService categoryService;

    @LogEndpoint
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllCategories(@HeaderParam("Authorization") String bearerToken){
        try{
            List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
            return Response.ok().entity(categoryDtoList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @GET
    @Path("/getById/{id}")
    @Produces(APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            CategoryDto categoryDto = categoryService.getCategoryById(id);
            return Response.ok().entity(categoryDto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @LogEndpoint
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addCategory(Category category, @HeaderParam("Authorization") String bearerToken){
        try{
            CategoryDto categoryDtoWithId = categoryService.addCategory(category);
            return Response.ok().entity(categoryDtoWithId).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @PUT
    @Path("/updateById/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateCategoryById(@PathParam("id") Integer id, Category category, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = categoryService.updateCategoryById(id, category);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @LogEndpoint
    @DELETE
    @Path("/deleteById/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteCategoryById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = categoryService.deleteCategoryById(id);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
