package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.ArticleWithTagDto;
import rs.raf.rafnews.logging.LogEndpoint;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.service.specification.IArticleWithTagService;
import rs.raf.rafnews.service.specification.ITagService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
@Path("article_with_tag")
public class ArticleWithTagResource {
    @Inject
    IArticleWithTagService articleWithTagService;

    @LogEndpoint
    @GET
    @Path("/getAllByArticleId/{articleId}")
    @Produces(APPLICATION_JSON)
    public Response getAllArticlesWithTagByByArticleId(@PathParam("articleId") Integer articleId, @HeaderParam("Authorization") String bearerToken){
        try{
            List<ArticleWithTagDto> articleWithTagDtoList = articleWithTagService.getAllArticlesWithTagByByArticleId(articleId);
            return Response.ok().entity(articleWithTagDtoList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
