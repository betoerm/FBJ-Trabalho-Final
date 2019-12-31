package com.template.app.rest.author;

import java.util.List;

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
import com.template.app.service.repository.author.AuthorRepository;
import com.template.app.exception.AppException;

@Path("/author")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
	
public class AuthorRest {
	
	@Inject
	private AuthorService authorService;
	
	@Inject
	private AuthorRepository authorRepository;
	
	@GET
	@Path("/all")
	public List<AuthorEntity> getAllAuthors() throws AppException{
		List<AuthorEntity> listAuthors = authorService.retrieveAll();
		return listAuthors;
	}
	
	@POST
	@Path("/create")
	public AuthorEntity create(AuthorEntity authorEntity) throws AppException{
		return authorService.create(authorEntity);
	}
	
	@DELETE
	@Path("/delete/{id}")
	public void delete(@PathParam("id") Long authorId) throws AppException{		
		AuthorEntity author = new AuthorEntity();
		author = authorRepository.get(authorId);
		authorService.delete(author);
	}
	
	@GET
	@Path("/{id}")
	public AuthorEntity get(@PathParam("id") Long authorId) throws AppException{
		AuthorEntity authorEntity = authorService.get(authorId);
		return authorEntity;
	}
}