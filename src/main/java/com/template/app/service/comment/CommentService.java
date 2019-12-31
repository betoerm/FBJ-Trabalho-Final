package com.template.app.service.comment;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.template.app.entity.CommentEntity;
import com.template.app.entity.PostEntity;
import com.template.app.service.repository.comment.CommentRepository;


@Stateless
@Local

public class CommentService{
	
	@Inject
	private CommentRepository commentRepository;
	
	public List<CommentEntity> retrieveAll(){
		List<CommentEntity> commentList = commentRepository.retrieveAll();
		return commentList;
	}
	
	public CommentEntity get(Long id) {
		CommentEntity comment = commentRepository.get(id);
		return comment;
	}
	
	public List<CommentEntity> getByPosts(Long id) {
		List<CommentEntity> commentList = commentRepository.getByPosts(id);
		return commentList;
	}
	
	public CommentEntity create(CommentEntity commentEntity) {
		return commentRepository.persist(commentEntity);
	}
	
	public void delete(CommentEntity commentEntity) {
		commentRepository.delete(commentEntity);
	}
	
}

