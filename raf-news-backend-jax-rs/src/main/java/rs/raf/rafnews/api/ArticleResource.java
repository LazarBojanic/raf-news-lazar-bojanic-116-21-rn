package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.logging.LogEndpoint;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.request.ArticleRequest;
import rs.raf.rafnews.request.ArticleSearchRequest;
import rs.raf.rafnews.service.specification.IArticleService;
import rs.raf.rafnews.service.specification.IArticleWithTagService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/article")
public class ArticleResource {
    @Inject
    IArticleService articleService;
    @Inject
    IArticleWithTagService articleWithTagService;

    @LogEndpoint
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllArticles(@HeaderParam("Authorization") String bearerToken){
        try{
            List<ArticleDto> articleDtoList = articleService.getAllArticles();
            return Response.ok().entity(articleDtoList).build();
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
    public Response getArticleById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            ArticleDto articleDto = articleService.getArticleById(id);
            return Response.ok().entity(articleDto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @POST
    @Path("/getAllFiltered")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getAllArticlesFiltered(ArticleSearchRequest articleSearchRequest, @HeaderParam("Authorization") String bearerToken){
        try{
            List<ArticleDto> articleDtoListFiltered = articleService.getAllArticlesFiltered(articleSearchRequest);
            return Response.ok().entity(articleDtoListFiltered).build();
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
    public Response addArticle(ArticleRequest articleRequest, @HeaderParam("Authorization") String bearerToken){
        try{
            ArticleDto addedArticleDto = articleService.addArticle(articleRequest);
            return Response.ok().entity(addedArticleDto).build();
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
    public Response updateArticle(@PathParam("id") Integer id, ArticleRequest articleRequest, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = articleService.updateArticleById(id, articleRequest);
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
    public Response deleteArticle(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = articleService.deleteArticleById(id);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
