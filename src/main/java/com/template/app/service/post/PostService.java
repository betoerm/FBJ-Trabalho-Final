package com.template.app.service.post;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.template.app.entity.AuthorEntity;
import com.template.app.entity.PostEntity;
import com.template.app.service.repository.post.PostRepository;

@Stateless
@Local

public class PostService{
	
	@Inject
	private PostRepository postRepository;
	
	public List<PostEntity> retrieveAll(){
		List<PostEntity> postList = postRepository.retrieveAll();
		return postList;
	}
	
	public PostEntity get(Long id) {
		PostEntity post = postRepository.get(id);
		return post;
	}
	
	public List<PostEntity> getByAuthor(Long id) {
		List<PostEntity> postList = postRepository.getByAuthor(id);
		return postList;
	}
	
	public PostEntity create(PostEntity postEntity) {
		return postRepository.persist(postEntity);
	}
	
	public void delete(PostEntity postEntity) {
		postRepository.delete(postEntity);
	}
	
}

