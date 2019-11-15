package com.template.app.service.author;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.template.app.entity.AuthorEntity;
import com.template.app.entity.PostEntity;
import com.template.app.service.repository.author.AuthorRepository;

@Stateless
@Local
public class AuthorService {
	
	@Inject
	private AuthorRepository authorRepository;
	
	//@PersistenceContext(unitName = "author-persistence-unit")
	//private EntityManager entityManager;
	
	public List<AuthorEntity> retrieveAll(String relationships) {
		List<AuthorEntity> listAuthors = authorRepository.retrieveAll(relationships);
		
		List<AuthorEntity> newAuthorList = new ArrayList<AuthorEntity>();
		
		for(AuthorEntity a : listAuthors) {
			AuthorEntity newA = getAuthorDTO(relationships, a);	
			newAuthorList.add(newA);
		}
		
		return newAuthorList;
	}
	
	
	protected AuthorEntity getAuthorDTO(String relationships, AuthorEntity a) {
		
		AuthorEntity newA = new AuthorEntity();
		newA.setId(a.getId());
		newA.setName(a.getName());
		
		
		if (relationships!=null && relationships.equals("i")) {
			newA.setPosts(a.getPosts());
		}

		return newA;
	}
	
	public AuthorEntity get(Long id) {
		AuthorEntity authorEntity = authorRepository.get(id);
		return authorEntity;
	}
	
	public AuthorEntity get(Long id, String relationships) {
		//LOGGER.info("AuthorService.get(relationships)");
		AuthorEntity a = authorRepository.get(id, relationships);
		if (a!=null) { 
			AuthorEntity newA = getAuthorDTO(relationships, a);
			//LOGGER.info("AuthorService.get(relationships): " + newA);
			return newA;
		} else {
			//LOGGER.info("AuthorService.get(relationships): null");
			return null;
		}
		
	}
	
	public AuthorEntity create(AuthorEntity authorEntity) {		
		return authorRepository.persist(authorEntity);
	}
	
	public void delete(AuthorEntity authorEntity) {
		authorRepository.delete(authorEntity);
	}	
	
	public List<PostEntity> getPosts(Long id) {
		//LOGGER.info("AuthorService.getPosts");
		List<PostEntity> listPosts = authorRepository.getPosts(id);
		//LOGGER.info("AuthorService.getPosts: "+ listPosts);
		return listPosts;
	}
	
}