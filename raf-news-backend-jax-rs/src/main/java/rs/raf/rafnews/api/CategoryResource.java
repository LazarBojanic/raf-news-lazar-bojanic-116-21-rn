package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.service.implementation.CategoryService;
import rs.raf.rafnews.service.implementation.ServiceUserService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/category")
public class CategoryResource {
    @Inject
    CategoryService categoryService;

    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            CategoryDto category = categoryService.getCategoryById(id);
            return Response.ok().entity(category).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllCategories(@HeaderParam("Authorization") String bearerToken){
        try{
            List<CategoryDto> categoryList = categoryService.getAllCategories();
            return Response.ok().entity(categoryList).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addCategory(Category category, @HeaderParam("Authorization") String bearerToken){
        try{
            CategoryDto categoryWithId = categoryService.addCategory(category);
            return Response.ok().entity(categoryWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/update")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateCategory(Category category, @HeaderParam("Authorization") String bearerToken){
        try{
            CategoryDto categoryWithId = categoryService.updateCategory(category);
            return Response.ok().entity(categoryWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteCategory(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            boolean isDeleted = categoryService.deleteCategoryById(id);
            if(isDeleted){
                return Response.ok().entity(id).build();
            }
            else{
                return Response.status(500).build();
            }
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
}
