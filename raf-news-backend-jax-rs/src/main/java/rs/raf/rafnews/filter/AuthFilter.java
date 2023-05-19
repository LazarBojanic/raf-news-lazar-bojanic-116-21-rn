package rs.raf.rafnews.filter;

import io.jsonwebtoken.Claims;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import rs.raf.rafnews.api.ServiceUserResource;
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
                if(userRole.equals(Util.ROLE_ADMIN)){
                    return true;
                }
                else if(userRole.equals(Util.ROLE_CONTENT_CREATOR)){
                    for (Object matchedResource : matchedResources) {
                        if (matchedResource instanceof ServiceUserResource) {
                            return req.getUriInfo().getPath().contains("/get");
                        }
                    }
                    return true;
                }
                else if(userRole.equals(Util.ROLE_GUEST)){
                    for (Object matchedResource : matchedResources) {
                        if (matchedResource instanceof ServiceUserResource) {
                            return req.getUriInfo().getPath().contains("/get");
                        }
                    }
                    return true;
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
}
