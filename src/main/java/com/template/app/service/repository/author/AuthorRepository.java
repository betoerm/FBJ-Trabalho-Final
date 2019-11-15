package com.template.app.service.repository.author;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.template.app.entity.AuthorEntity;
import com.template.app.entity.PostEntity;
import com.template.app.exception.AppException;
import com.template.app.messages.AppBeanMessages;

@Stateless
@Local
public class AuthorRepository {
	
	@PersistenceContext(unitName = "author-persistence-unit")
	private EntityManager entityManager;

	private EntityManager getEntityManager()
	{
		return entityManager;
	}	
	
	public List<AuthorEntity> retrieveAll(String relationships) {
		try {
			
			String namedQuery = "AuthorEntity.retrieveAll";
			
			Query query = getEntityManager().createNamedQuery(namedQuery);

			List<AuthorEntity> list = (List<AuthorEntity>)query.getResultList( );
			return list;		

		} catch (AppException e) {			
			throw e;
		} catch (Exception e) {			
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}
	
	public AuthorEntity get(Long id, String relationships) {
		try {
			CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery  criteriaQuery = criteriaBuilder.createQuery(AuthorEntity.class);
			Root root = criteriaQuery.from(AuthorEntity.class);
			root.fetch("posts", JoinType.LEFT);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

			AuthorEntity authorEntity = (AuthorEntity)getEntityManager().createQuery(criteriaQuery).getSingleResult();	

			return authorEntity;

		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}

	public AuthorEntity persist(AuthorEntity authorEntity) {
		try {
			getEntityManager().persist(authorEntity);
			return authorEntity;
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
		
	}

	public void delete(AuthorEntity authorEntity) {
		try {
			getEntityManager().remove(authorEntity);
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
		
	}
	
	public AuthorEntity get(Long id) {		
		return get(id, "");
	}
	
	
	public List<PostEntity> getPosts(Long id) {
		try{
			Query query = getEntityManager().createNamedQuery("AuthorEntity.getPosts");
			query.setParameter("id", id);
			List<PostEntity> list = (List<PostEntity>)query.getResultList( );
			return list;
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}
	
	
}
