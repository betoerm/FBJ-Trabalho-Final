package com.template.app.service.repository.comment;

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

import com.template.app.entity.CommentEntity;
import com.template.app.exception.AppException;
import com.template.app.messages.AppBeanMessages;

@Stateless
@Local
public class CommentRepository {
	
	@PersistenceContext(unitName = "comment-persistence-unit")
	private EntityManager entityManager;

	private EntityManager getEntityManager()
	{
		return entityManager;
	}	
	
	public List<CommentEntity> retrieveAll() {
		try {
			
			String namedQuery = "CommentEntity.retrieveAll";
			
			Query query = getEntityManager().createNamedQuery(namedQuery);

			List<CommentEntity> list = (List<CommentEntity>)query.getResultList( );
			return list;		

		} catch (AppException e) {			
			throw e;
		} catch (Exception e) {			
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}
	
	public CommentEntity get(Long id) {
		try {
			CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery  criteriaQuery = criteriaBuilder.createQuery(CommentEntity.class);
			Root root = criteriaQuery.from(CommentEntity.class);
			root.fetch("posts", JoinType.LEFT);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

			CommentEntity commentEntity = (CommentEntity)getEntityManager().createQuery(criteriaQuery).getSingleResult();	

			return commentEntity;

		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}

	public CommentEntity persist(CommentEntity commentEntity) {
		try {
			getEntityManager().persist(commentEntity);
			return commentEntity;
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
		
	}

	public void delete(CommentEntity commentEntity) {
		try {
			getEntityManager().remove(commentEntity);
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
		
	}
	
	
}
