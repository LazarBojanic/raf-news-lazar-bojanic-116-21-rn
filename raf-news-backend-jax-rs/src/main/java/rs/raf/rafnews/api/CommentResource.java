package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.CommentDto;
import rs.raf.rafnews.logging.LogEndpoint;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.request.CommentRequest;
import rs.raf.rafnews.request.CommentSearchRequest;
import rs.raf.rafnews.service.implementation.CommentService;
import rs.raf.rafnews.service.implementation.ServiceUserService;
import rs.raf.rafnews.service.specification.ICommentService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/comment")
public class CommentResource {
    @Inject
    ICommentService commentService;

    @LogEndpoint
    @GET
    @Path("/getById/{id}")
    @Produces(APPLICATION_JSON)
    public Response getCommentById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            CommentDto commentDto = commentService.getCommentById(id);
            return Response.ok().entity(commentDto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllComments(@HeaderParam("Authorization") String bearerToken){
        try{
            List<CommentDto> commentDtoList = commentService.getAllComments();
            return Response.ok().entity(commentDtoList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @POST
    @Path("/getAllFiltered")
    @Produces(APPLICATION_JSON)
    public Response getAllCommentsFiltered(CommentSearchRequest commentSearchRequest, @HeaderParam("Authorization") String bearerToken){
        try{
            List<CommentDto> commentDtoList = commentService.getAllCommentsFiltered(commentSearchRequest);
            return Response.ok().entity(commentDtoList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @GET
    @Path("/getAllByArticleId/{articleId}")
    @Produces(APPLICATION_JSON)
    public Response getAllCommentsByArticleId(@PathParam("articleId") Integer articleId, @HeaderParam("Authorization") String bearerToken){
        try{
            List<CommentDto> commentDtoList = commentService.getAllCommentsByArticleId(articleId);
            return Response.ok().entity(commentDtoList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @POST
    @Path("/getAllByArticleIdFiltered/{articleId}")
    @Produces(APPLICATION_JSON)
    public Response getAllCommentsByArticleIdFiltered(@PathParam("articleId") Integer articleId, CommentSearchRequest commentSearchRequest, @HeaderParam("Authorization") String bearerToken){
        try{
            List<CommentDto> commentDtoList = commentService.getAllCommentsByArticleIdFiltered(articleId, commentSearchRequest);
            return Response.ok().entity(commentDtoList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @POST
    @Path("/addToArticle")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addCommentToArticle(CommentRequest commentRequest, @HeaderParam("Authorization") String bearerToken){
        try{
            CommentDto commentDtoWithId = commentService.addCommentToArticle(commentRequest);
            return Response.ok().entity(commentDtoWithId).build();
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
    public Response updateCommentById(@PathParam("id") Integer id, Comment comment, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = commentService.updateCommentById(id, comment);
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
    public Response deleteCommentById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = commentService.deleteCommentById(id);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
