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
import com.template.app.exception.AppException;
import com.template.app.messages.AppBeanMessages;

@Stateless
@Local
public class AuthorRepository {
	
	@PersistenceContext(unitName = "blog-persistence-unit")
	private EntityManager entityManager;

	private EntityManager getEntityManager()
	{
		return entityManager;
	}	
	
	public List<AuthorEntity> retrieveAll() {
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
	
	public AuthorEntity get(Long id) {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery q = cb.createQuery(AuthorEntity.class);
			Root o = q.from(AuthorEntity.class);
			o.fetch("listPostEntity", JoinType.LEFT);
			q.select(o);
			q.where(cb.equal(o.get("id"), id));

			AuthorEntity a = (AuthorEntity)getEntityManager().createQuery(q).getSingleResult();	

			return a;

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
	
}
