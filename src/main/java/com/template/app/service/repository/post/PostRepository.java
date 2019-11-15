package com.template.app.service.repository.post;

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

import com.template.app.entity.PostEntity;
import com.template.app.exception.AppException;
import com.template.app.messages.AppBeanMessages;

@Stateless
@Local
public class PostRepository {
	
	@PersistenceContext(unitName = "post-persistence-unit")
	private EntityManager entityManager;

	private EntityManager getEntityManager()
	{
		return entityManager;
	}	
	
	public List<PostEntity> retrieveAll() {
		try {
			
			String namedQuery = "PostEntity.retrieveAll";
			
			Query query = getEntityManager().createNamedQuery(namedQuery);

			List<PostEntity> list = (List<PostEntity>)query.getResultList( );
			return list;		

		} catch (AppException e) {			
			throw e;
		} catch (Exception e) {			
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}
	public PostEntity get(Long id) {
		try {
			CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery  criteriaQuery = criteriaBuilder.createQuery(PostEntity.class);
			Root root = criteriaQuery.from(PostEntity.class);
			root.fetch("authors", JoinType.LEFT);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

			PostEntity postEntity = (PostEntity)getEntityManager().createQuery(criteriaQuery).getSingleResult();	

			return postEntity;

		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}

	public PostEntity persist(PostEntity postEntity) {
		try {
			getEntityManager().persist(postEntity);
			return postEntity;
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
		
	}

	public void delete(PostEntity postEntity) {
		try {
			getEntityManager().remove(postEntity);
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
		
	}
	
	
}
