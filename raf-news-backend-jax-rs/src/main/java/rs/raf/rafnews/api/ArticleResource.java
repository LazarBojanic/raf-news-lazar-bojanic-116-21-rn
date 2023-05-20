package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.ArticleWithTagRequest;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.service.specification.IArticleService;
import rs.raf.rafnews.service.specification.IArticleWithTagService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/article")
public class ArticleResource {
    @Inject
    IArticleService articleService;
    @Inject
    IArticleWithTagService articleWithTagService;
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllArticles(@HeaderParam("Authorization") String bearerToken){
        try{
            System.out.println("Getting All Articles - " + Timestamp.from(Instant.now()));
            List<ArticleDto> articleDtoList = articleService.getAllArticles();
            return Response.ok().entity(articleDtoList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

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

    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addArticle(Article article, @HeaderParam("Authorization") String bearerToken){
        try{
            ArticleDto addedArticleDto = articleService.addArticle(article);
            return Response.ok().entity(addedArticleDto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @POST
    @Path("/addTagListToArticle")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addTagListToArticle(List<ArticleWithTagRequest> articleWithTagRequestList, @HeaderParam("Authorization") String bearerToken){
        try{
            List<ArticleWithTag> articleWithTagList = articleWithTagService.addTagListToArticle(articleWithTagRequestList);
            return Response.ok().entity(articleWithTagList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @POST
    @Path("/addTagToArticle")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addTagToArticle(ArticleWithTagRequest articleWithTagRequest, @HeaderParam("Authorization") String bearerToken){
        try{
            ArticleWithTag articleWithTag = articleWithTagService.addTagToArticle(articleWithTagRequest);
            return Response.ok().entity(articleWithTag).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @PUT
    @Path("/updateById/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateArticle(@PathParam("id") Integer id, Article article, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = articleService.updateArticleById(id, article);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

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
