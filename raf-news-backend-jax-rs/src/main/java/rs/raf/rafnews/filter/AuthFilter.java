package rs.raf.rafnews.filter;

import io.jsonwebtoken.Claims;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import rs.raf.rafnews.api.*;
import rs.raf.rafnews.service.implementation.ServiceUserService;
import rs.raf.rafnews.util.Util;

import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {
    @Inject
    private ServiceUserService serviceUserService;
    @Override
    public void filter(ContainerRequestContext requestContext)  {
        try{
            if (!requestContext.getUriInfo().getPath().contains("login") && !requestContext.getUriInfo().getPath().contains("loginWithToken") && !requestContext.getUriInfo().getPath().contains("register") && !requestContext.getUriInfo().getPath().contains("logout")) {
                String bearerToken = requestContext.getHeaderString("Authorization");
                if(bearerToken.startsWith("Bearer ")){
                    String token = bearerToken.split(" ")[1];
                    Claims claims = this.serviceUserService.parseToken(token);
                    if(!checkAuthorization(requestContext, claims)){
                        System.out.println("unauthorized");
                        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    }
                }
                else{
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            }
        }
        catch (Exception exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
        //requestContext.abortWith(Response.status(Response.Status.ACCEPTED).build());
    }

    private boolean checkAuthorization(ContainerRequestContext req, Claims claims) {
        try{
            if(claims.get("is_enabled").toString().equals("true")){
                String userRole = claims.get("user_role").toString();
                List<Object> matchedResources = req.getUriInfo().getMatchedResources();
                String path = req.getUriInfo().getPath();
                if(userRole.equals(Util.ROLE_ADMIN)){
                    return true;
                }
                else if(userRole.equals(Util.ROLE_CONTENT_CREATOR)){
                    for (Object matchedResource : matchedResources) {
                        if (matchedResource instanceof ArticleResource) {
                            if(path.contains("update") || path.contains("delete")){
                                return Integer.parseInt(claims.get("id").toString()) == getIdFromPath(path);
                            }
                            return true;
                        }
                        else if (matchedResource instanceof ArticleWithTagResource) {
                            return true;
                        }
                        else if (matchedResource instanceof CategoryResource) {
                            return path.contains("/get");
                        }
                        else if (matchedResource instanceof CommentResource) {
                            return true;
                        }
                        else if (matchedResource instanceof ServiceUserResource) {
                            return !path.contains("/registerFromAdmin") && !path.contains("/add") && !path.contains("/update") && !path.contains("/switchEnabled") && !path.contains("/delete");
                        }
                        else if (matchedResource instanceof TagResource) {
                            return !path.contains("/add") && !path.contains("/update") && !path.contains("/delete");
                        }
                    }
                    return false;
                }
                else if(userRole.equals(Util.ROLE_GUEST)){
                    for (Object matchedResource : matchedResources) {
                        if (matchedResource instanceof ArticleResource) {
                            return path.contains("/get") || path.contains("/increment");
                        }
                        else if (matchedResource instanceof ArticleWithTagResource) {
                            return true;
                        }
                        else if (matchedResource instanceof CategoryResource) {
                            return path.contains("/get");
                        }
                        else if (matchedResource instanceof CommentResource) {
                            return path.contains("/get") || path.contains("/addToArticle");
                        }
                        else if (matchedResource instanceof ServiceUserResource) {
                            return !path.contains("/registerFromAdmin") && !path.contains("/add") && !path.contains("/update") && !path.contains("/switchEnabled") && !path.contains("/delete") && !path.contains("/get");
                        }
                        else if (matchedResource instanceof TagResource) {
                            return path.contains("/get");
                        }
                    }
                    return false;
                }
                else {
                    return false;
                }
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }
    private Integer getIdFromPath(String path){
        return Integer.parseInt(path.substring(path.lastIndexOf("/")));
    }
}
