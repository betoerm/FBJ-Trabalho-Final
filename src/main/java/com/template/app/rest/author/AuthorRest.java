package com.template.app.rest.author;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.template.app.entity.*;
import com.template.app.service.author.AuthorService;
import com.template.app.exception.AppException;

@Path("/author")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
	
public class AuthorRest {
	
	//private final static Logger LOGGER = Logger.getLogger(AuthorRest.class.getName());
	
	@Inject
	private AuthorService authorservice;
	
	@GET
	@Path("/all")
	public List<AuthorEntity> getAllAuthors() throws AppException{
		//LOGGER.info("AuthorRest.getAllAuthors");
		//List<AuthorEntity> listAuthors = authorservice.getAllAuthors();		
		List<AuthorEntity> listAuthors = authorservice.retrieveAll();
		//LOGGER.info("AuthorRest.getAllAuthors: " + listAuthors);
		return listAuthors;
	}
	
	@POST
	@Path("/create")
	public AuthorEntity create(AuthorEntity authorEntity) throws AppException{
		return authorservice.create(authorEntity);
	}
	
	@DELETE
	@Path("/delete")
	public void delete(AuthorEntity authorEntity) throws AppException{
		authorservice.delete(authorEntity);
	}
	
	@GET
	@Path("/{id}")
	public AuthorEntity get( @PathParam("id") Long entityId) throws AppException {
		AuthorEntity authorEntity =  authorservice.get(entityId);
		return authorEntity;
	}	
	
}