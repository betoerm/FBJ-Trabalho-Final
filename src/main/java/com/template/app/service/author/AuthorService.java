package com.template.app.service.author;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.template.app.entity.AuthorEntity;
import com.template.app.service.repository.author.AuthorRepository;

@Stateless
@Local

public class AuthorService {
	
	@Inject
	private AuthorRepository authorRepository;
	
	public List<AuthorEntity> retrieveAll(){
		List<AuthorEntity> authorList = authorRepository.retrieveAll();
		return authorList;
	}
	
	public AuthorEntity get(Long id) {
		AuthorEntity authorEntity = authorRepository.get(id);
		return authorEntity;
	}
	
	public AuthorEntity create(AuthorEntity authorEntity) {
		return authorRepository.persist(authorEntity);
	}
	
	public void delete(AuthorEntity authorEntity) {
		authorRepository.delete(authorEntity);
	}
}