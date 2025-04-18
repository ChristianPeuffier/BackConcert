/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.istic.taa.jaxrs;

import java.util.HashSet;
import java.util.Set;

import fr.istic.taa.jaxrs.rest.*;
import fr.istic.taa.jaxrs.utils.JwtFilter;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(title = "Evenement API", version = "1.0", description = "Evenement API"))
public final class TestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> clazzes = new HashSet<>();
        clazzes.add(SwaggerResource.class);
        clazzes.add(OpenApiResource.class);
        clazzes.add(AcceptHeaderOpenApiResource.class);
        clazzes.add(UtilisateurRessource.class);
        clazzes.add(EvenementRessources.class);
        clazzes.add(OrganisateurRessources.class);
        clazzes.add(TicketRessources.class);
        clazzes.add(CorsFilter.class);
        clazzes.add(JwtFilter.class);


        return clazzes;
    }

}
