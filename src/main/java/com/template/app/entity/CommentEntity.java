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
@Table(name = "COMMENT")
@SequenceGenerator(name = "COMMENT_ID_GENERATOR", sequenceName = "SE_COMMENT", allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@NamedQueries({
	@NamedQuery(name = "CommentEntity.retrieveAll", query = "Select distinct c from CommentEntity c order by c.date")
})
public class CommentEntity implements IEntity<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_ID_GENERATOR")
	private Long id;
	
	@ManyToOne(targetEntity = PostEntity.class)
	@JoinColumn(name = "POST_ID", referencedColumnName = "ID")
	@XmlTransient
	private PostEntity post;
	
	@NotNull
	@Size(max = 100)
	@Column
	private String content;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;	
		
	public CommentEntity() {
		
	}	
	
	public CommentEntity(Long id, PostEntity post, String content, Date date) {
		this.id = id;
		this.post = post;
		this.content = content;		
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
	
	public PostEntity getPost() {
		return post;
	}
	
	public void setPost(PostEntity post) {
		this.post = post;
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
	
	public void setDate(Date date2) {
		this.date = date2;
	}	
}