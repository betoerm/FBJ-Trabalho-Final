package com.template.app.rest.comment;

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

import com.template.app.entity.CommentEntity;
import com.template.app.entity.PostEntity;
import com.template.app.exception.AppException;
import com.template.app.service.comment.CommentService;

@Path("/comment")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class CommentRest{
	
	@Inject
	private CommentService commentService;
	
	@GET
	@Path("/all")
	public List<CommentEntity> getAllComments() throws AppException{
		List<CommentEntity> listComments = commentService.retrieveAll();
		return listComments;
	}
	
	@GET
	@Path("/{id}")
	public CommentEntity get(@PathParam("id") Long entityId) throws AppException {
		CommentEntity commentEntity =  commentService.get(entityId);
		return commentEntity;
	}
	
	@POST
	@Path("/create")
	public CommentEntity create(CommentEntity commentEntity) throws AppException{
		return commentService.create(commentEntity);
	}
	
	@DELETE
	@Path("/delete")
	public void delete(CommentEntity commentEntity) throws AppException{
		commentService.delete(commentEntity);
	}
	
}
