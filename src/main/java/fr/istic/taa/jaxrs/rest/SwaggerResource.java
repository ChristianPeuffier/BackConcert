package fr.istic.taa.jaxrs.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;


@Path("/api")
public class SwaggerResource {


    /**
     * Get the index file.
     * @return the index file
     */
    @GET
    public byte[] Get1() {
        try {
            return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/dist/index.html"));
        } catch (IOException e) {
            return new byte[0];
        }
    }

    /**
     * Get the file from the path.
     * @param path the path
     * @return the file
     */
    @GET
    @Path("{path:.*}")
    public byte[] Get(@PathParam("path") final String path) {
        try {
            return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/dist/" + path));
        } catch (IOException e) {
            return new byte[0];
        }
    }

}
