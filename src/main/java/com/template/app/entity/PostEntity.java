package com.template.app.entity;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "POST")
@SequenceGenerator(name = "POST_ID_GENERATOR", sequenceName = "SE_POST", allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@NamedQueries({
	@NamedQuery(name = "PostEntity.retrieveAll", query = "Select distinct p from PostEntity p ")
})

public class PostEntity implements IEntity<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_ID_GENERATOR")
	private Long id;
	
	@ManyToOne(targetEntity = AuthorEntity.class)
	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
	@XmlTransient
	private AuthorEntity authorEntity;
	
	@NotNull
	@Size(max = 100)
	@Column
	private String content;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;	
	
	public AuthorEntity geAuthorEntity() {
		return authorEntity;
	}
	
	public void setAuthorEntity(AuthorEntity authorEntity) {
		this.authorEntity = authorEntity;
	}
	
	public PostEntity() {
		
	}	
	
	public PostEntity(Long id, AuthorEntity authorEntity, String content, Date date) {
		this.id = id;
		this.authorEntity = authorEntity;
		this.content = content;	
		this.date = date;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date date() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
}