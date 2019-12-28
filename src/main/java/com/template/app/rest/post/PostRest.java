package com.template.app.rest.post;

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
import com.template.app.entity.PostEntity;
import com.template.app.exception.AppException;
import com.template.app.service.post.PostService;

@Path("/post")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PostRest{
	
	@Inject
	private PostService postService;
	
	@GET
	@Path("/all")
	public List<PostEntity> getAllPosts() throws AppException{
		List<PostEntity> listPosts = postService.retrieveAll();
		return listPosts;
	}
		
	@GET
	@Path("/{id}")
	public PostEntity get(@PathParam("id") Long postId) throws AppException{
		PostEntity postEntity = postService.get(postId);
		return postEntity;
	}
	
	@POST
	@Path("/create")
	public PostEntity create(PostEntity postEntity) throws AppException{
		return postService.create(postEntity);
	}
	
	@DELETE
	@Path("/delete")
	public void delete(PostEntity postEntity) throws AppException{
		postService.delete(postEntity);
	}

}
