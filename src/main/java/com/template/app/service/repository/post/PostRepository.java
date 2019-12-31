package com.template.app.service.repository.post;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.template.app.entity.AuthorEntity;
import com.template.app.entity.PostEntity;
import com.template.app.exception.AppException;
import com.template.app.messages.AppBeanMessages;
import com.template.app.service.repository.author.AuthorRepository;

@Stateless
@Local

public class PostRepository{
	
	@PersistenceContext(unitName = "blog-persistence-unit")
	private EntityManager entityManager;
	
	private EntityManager getEntityManager() {
		return entityManager;
	}

	public List<PostEntity> retrieveAll(){
		try {
			String namedQuery = "PostEntity.retrieveAll";
			
			Query query = getEntityManager().createNamedQuery(namedQuery);
			
			List<PostEntity> list = (List<PostEntity>)query.getResultList();
			return list;	
			
		} catch(AppException e) {
			throw e;
		} catch(Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}
	
	public PostEntity get(Long id) {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery q = cb.createQuery(AuthorEntity.class);
			Root o = q.from(PostEntity.class);
			o.fetch("listCommentEntity", JoinType.LEFT);
			q.select(o);
			q.where(cb.equal(o.get("id"), id));
			
			PostEntity a = (PostEntity)getEntityManager().createQuery(q).getSingleResult();	

			return a;

		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}
	

	public List<PostEntity> getByAuthor(Long id) {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery q = cb.createQuery(PostEntity.class);
			Root o = q.from(PostEntity.class);
			o.fetch("author", JoinType.LEFT);
			q.select(o);
			q.where(cb.equal(o.get("author"), id));
			List<PostEntity> list = (List<PostEntity>)getEntityManager().createQuery(q).getResultList();
			return list;
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

