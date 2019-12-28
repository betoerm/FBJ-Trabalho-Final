package com.template.app.service.repository.comment;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.template.app.entity.CommentEntity;
import com.template.app.exception.AppException;
import com.template.app.messages.AppBeanMessages;

@Stateless
@Local

public class CommentRepository{
	
	@PersistenceContext(unitName = "blog-persistence-unit")
	private EntityManager entityManager;
	
	private EntityManager getEntityManager() {
		return entityManager;
	}
	
	public List<CommentEntity> retrieveAll(){
		try {
			String namedQuery = "CommentEntity.retrieveAll";
			
			Query query = getEntityManager().createNamedQuery(namedQuery);
			
			List<CommentEntity> list = (List<CommentEntity>)query.getResultList();
			return list;
			
			
		} catch(AppException e) {
			throw e;
		} catch(Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}
	
	public CommentEntity get(Long id) {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery q = cb.createQuery(CommentEntity.class);
			Root o = q.from(CommentEntity.class);
			o.fetch("postEntity", JoinType.LEFT);
			q.select(o);
			q.where(cb.equal(o.get("id"), id));

			CommentEntity c = (CommentEntity)getEntityManager().createQuery(q).getSingleResult();	

			return c;

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

