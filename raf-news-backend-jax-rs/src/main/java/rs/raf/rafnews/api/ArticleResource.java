package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.service.specification.IArticleService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/article")
public class ArticleResource {
    @Inject
    IArticleService articleService;

    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Response getArticleById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            ArticleDto article = articleService.getArticleById(id);
            return Response.ok().entity(article).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllArticles(@HeaderParam("Authorization") String bearerToken){
        try{
            List<ArticleDto> articleList = articleService.getAllArticles();
            return Response.ok().entity(articleList).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addArticle(Article article, @HeaderParam("Authorization") String bearerToken){
        try{
            ArticleDto articleWithId = articleService.addArticle(article);
            return Response.ok().entity(articleWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/update")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateArticle(Article article, @HeaderParam("Authorization") String bearerToken){
        try{
            ArticleDto articleWithId = articleService.updateArticle(article);
            return Response.ok().entity(articleWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteArticle(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            boolean isDeleted = articleService.deleteArticleById(id);
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
