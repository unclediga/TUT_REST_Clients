package ru.unclediga.jersey;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("libs")
public class LibraryResource {

        @GET
        @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
        public List<Library> getLibraries() {
            return Repository.getLibraries();
        }
    
        @GET
        @Path("/{id}")
        @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
        public Library getBook(@PathParam("id") int id) {
            return Repository.getLibrary(id);
        }
    }
