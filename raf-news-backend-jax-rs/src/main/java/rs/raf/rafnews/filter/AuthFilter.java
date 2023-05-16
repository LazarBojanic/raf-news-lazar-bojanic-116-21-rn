package rs.raf.rafnews.filter;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import rs.raf.rafnews.service.implementation.ServiceUserService;

import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {
    @Inject
    ServiceUserService serviceUserService;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try{
            if(isAuthRequired(requestContext)){
                String bearerToken = requestContext.getHeaderString("Authorization");
                if(bearerToken.startsWith("Bearer")){
                    String token = bearerToken.split(" ")[1];
                    if (this.serviceUserService.parseToken(token) == null) {
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
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login") || req.getUriInfo().getPath().contains("register")) {
            return false;
        }
        List<Object> matchedResources = req.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {
            /*if (matchedResource instanceof BlogPostResource || matchedResource instanceof BlogPostCommentResource) {
                return true;
            }*/
            return false;
        }
        return false;
    }
}
