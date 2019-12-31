package com.template.app.rest.comment;

import java.util.Date;
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

import com.template.app.entity.AuthorEntity;
import com.template.app.entity.CommentEntity;
import com.template.app.entity.PostEntity;
import com.template.app.exception.AppException;
import com.template.app.service.comment.CommentService;
import com.template.app.service.repository.comment.CommentRepository;
import com.template.app.service.repository.post.PostRepository;

@Path("/comment")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class CommentRest{
	
	@Inject
	private CommentService commentService;
	
	@Inject 
	private CommentRepository commentRepository;
	
	@Inject
	private PostRepository postRepository;
	
	@GET
	@Path("/all")
	public List<CommentEntity> getAllComments() throws AppException{
		List<CommentEntity> listComments = commentService.retrieveAll();
		return listComments;
	}
	
	@GET
	@Path("/{id}")
	public CommentEntity get(@PathParam("id") Long commentId) throws AppException{
		CommentEntity comment = commentService.get(commentId);
		return comment;
	}
	
	@GET
	@Path("/post/{id}")
	public List<CommentEntity> getByPosts(@PathParam("id") Long entityId) throws AppException {
		List<CommentEntity> listComments =  commentService.getByPosts(entityId);
		return listComments;
	}
	
	@POST
	@Path("/create")
	public CommentEntity create(CommentModel commentEntity) throws AppException{
		
		CommentEntity comment = new CommentEntity();
		PostEntity post = postRepository.get(commentEntity.getPost_id());
		
		comment.setPost(post);
		comment.setContent(commentEntity.getContent());
		comment.setDate(new Date(System.currentTimeMillis()));
		
		return commentService.create(comment);
	}
	
	@DELETE
	@Path("/delete/{id}")
	public void delete(@PathParam("id") Long commentId) throws AppException{
		CommentEntity comment = new CommentEntity();
		comment = commentRepository.get(commentId);
		commentService.delete(comment);
	}
	
}
