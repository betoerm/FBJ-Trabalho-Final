package com.template.app.entity;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
	@NamedQuery(name = "PostEntity.retrieveAll", query = "Select distinct p from PostEntity p order by p.date")	
})

public class PostEntity implements IEntity<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_ID_GENERATOR")
	private Long id;	
	
	@ManyToOne(targetEntity = AuthorEntity.class)
	@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
	@XmlTransient
	private AuthorEntity author;
	
	@NotNull
	@Size(max = 1000)
	@Column
	private String content;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
		
	@OneToMany (targetEntity = CommentEntity.class, cascade=CascadeType.ALL, mappedBy="post")
	private List<CommentEntity> listCommentEntity;
	
	public PostEntity() {
		
	}
	
	public PostEntity(Long id, AuthorEntity author, String content, Date date, List<CommentEntity> listCommentEntity) {
		this.id = id;
		this.author = author;		
		this.content = content;	
		this.date = date;
		this.listCommentEntity = listCommentEntity;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public AuthorEntity getAuthor() {
		return author;
	}
	
	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}
	
	public List<CommentEntity> getListCommentEntity() {
		return listCommentEntity;
	}

	public void setListCommentEntity(List<CommentEntity> listCommentEntity) {
		this.listCommentEntity = listCommentEntity;
	}			

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date2) {
		this.date = date2;
	}	
}