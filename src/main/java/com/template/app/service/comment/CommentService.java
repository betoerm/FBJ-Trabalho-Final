package com.template.app.service.comment;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.template.app.entity.CommentEntity;
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
		CommentEntity commentEntity = commentRepository.get(id);
		return commentEntity;
	}
	
}

