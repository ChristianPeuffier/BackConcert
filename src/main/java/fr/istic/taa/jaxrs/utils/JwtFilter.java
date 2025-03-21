package fr.istic.taa.jaxrs.utils;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import java.security.Principal;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String path = requestContext.getUriInfo().getPath();
        System.out.println(path);
        if (path.equals("/utilisateur/login")||path.equals("/utilisateur/add")) {
            return; // Ne pas filtrer la requête de connexion
        }

        System.out.println("🔍 JWT Filter exécuté !");

        String authHeader = requestContext.getHeaderString(AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            System.out.println(authHeader);
            System.out.println(BEARER_PREFIX);
            abortRequest(requestContext, "Token manquant ou incorrect");
            return;
        }

        String token = authHeader.substring(BEARER_PREFIX.length());
        Claims claims = TokenUtil.validateToken(token);

        if (claims == null) {
            abortRequest(requestContext, "Token invalide ou expiré");
            return;
        }

        String email = claims.getSubject();
        String role = claims.get("role", String.class); // Le rôle est récupéré depuis le token


        final Principal principal = new Principal() {
            @Override
            public String getName() {
                return email;
            }
        };

        final SecurityContext securityContext = requestContext.getSecurityContext();
        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return principal;
            }

            @Override
            public boolean isUserInRole(String requestedRole) {
                return role != null && role.equals(requestedRole);
            }

            @Override
            public boolean isSecure() {
                return securityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return "Bearer";
            }
        });
    }



    private void abortRequest(ContainerRequestContext requestContext, String message) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("{\"error\":\"" + message + "\"}")
                .build());
    }
}

