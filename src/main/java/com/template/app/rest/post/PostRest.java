package com.template.app.rest.post;

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
import com.template.app.service.post.PostService;
import com.template.app.service.repository.author.AuthorRepository;
import com.template.app.service.repository.post.PostRepository;

@Path("/post")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })

public class PostRest{
	
	@Inject
	private PostService postService;
	
	@Inject
	private PostRepository postRepository;
	
	@Inject
	private AuthorRepository authorRepository;	
	
	@GET
	@Path("/all")
	public List<PostEntity> getAllPosts() throws AppException{
		List<PostEntity> listPosts = postService.retrieveAll();
		return listPosts;
	}
	
	@GET
	@Path("/{id}")
	public PostEntity get(@PathParam("id") Long postId) throws AppException{
		PostEntity post = postService.get(postId);
		return post;
	}
	
	@GET
	@Path("/author/{id}")
	public List<PostEntity> getByAuthor(@PathParam("id") Long entityId) throws AppException {
		List<PostEntity> listPosts =  postService.getByAuthor(entityId);
		return listPosts;
	}
	
	@POST
	@Path("/create")
	public PostEntity create(PostModel postEntity) throws AppException{		
		PostEntity post = new PostEntity();
		AuthorEntity author = authorRepository.get(postEntity.getAuthor_id());
		
		post.setAuthor(author);
		post.setContent(postEntity.getContent());
		post.setDate(new Date(System.currentTimeMillis()));
			
		return postService.create(post);
	}
	
	@DELETE
	@Path("/delete/{id}")
	public void delete(@PathParam("id") Long postId) throws AppException{
		PostEntity post = new PostEntity();
		post = postRepository.get(postId);
		
		postService.delete(post);
	}

}
