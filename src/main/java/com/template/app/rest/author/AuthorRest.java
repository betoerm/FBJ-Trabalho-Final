package com.template.app.rest.author;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
		List<AuthorEntity> listAuthors = authorservice.retrieveAll(null);
		//LOGGER.info("AuthorRest.getAllAuthors: " + listAuthors);
		return listAuthors;
	}
	
	@GET
	@Path("/all/{relationships}")
	public List<AuthorEntity> getAllProducts(@PathParam("relationships") String relationships) throws AppException{
		//LOGGER.info("ProductRest.getAllProducts{relationships}");
		List<AuthorEntity> listAuthors = authorservice.retrieveAll(relationships);
		//handleNoContent(listAuthors);
		//LOGGER.info("ProductRest.getAllProducts{relationships}: "+listProducts);
		return listAuthors;
	}
	
	@GET
	@Path("/{id}/{relationships}")
	public AuthorEntity get( @PathParam("id") Long entityId, @PathParam("relationships") String relationships) throws AppException {
		//LOGGER.info("AuthorRest.get{relationships}");
		AuthorEntity p =  authorservice.get(entityId, "");
		//LOGGER.info("AuthorRest.get{relationships}: "+p);
		return p;
	}
	
	@GET
	@Path("/posts/{id}")
	public List<PostEntity> getImages(@PathParam("id") Long entityId) throws AppException {
		//LOGGER.info("AuthorRest.getImages");
		List<PostEntity> posts=  authorservice.getPosts(entityId);
		if (posts==null) {
			//LOGGER.warning("ProductRest.getImages: SC_NO_CONTENT");
			new WebApplicationException(HttpServletResponse.SC_NO_CONTENT);		
		}		
		//LOGGER.info("ProductRest.getImages: "+images);
		return posts;
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