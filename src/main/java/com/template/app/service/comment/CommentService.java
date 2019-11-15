package com.template.app.service.comment;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.template.app.entity.CommentEntity;
import com.template.app.service.repository.comment.CommentRepository;

@Stateless
@Local
public class CommentService {
	
	@Inject
	private CommentRepository commentRepository;
	
	public List<CommentEntity> retrieveAll() {
		List<CommentEntity> listComments = commentRepository.retrieveAll();
		return listComments;
	}
	
	public CommentEntity get(Long id) {
		CommentEntity commentEntity = commentRepository.get(id);
		return commentEntity;
	}
	
	public CommentEntity create(CommentEntity commentEntity) {		
		return commentRepository.persist(commentEntity);
	}
	
	public void delete(CommentEntity commentEntity) {
		commentRepository.delete(commentEntity);
	}
	
}