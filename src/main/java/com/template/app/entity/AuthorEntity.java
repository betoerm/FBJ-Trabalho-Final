package com.template.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "AUTHOR")
@SequenceGenerator(name = "AUTHOR_ID_GENERATOR", sequenceName = "SE_AUTHOR", allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@NamedQueries({
	@NamedQuery(name = "AuthorEntity.retrieveAll", query = "Select Distinct a from AuthorEntity a")
})

public class AuthorEntity implements IEntity<Long>{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_ID_GENERATOR")
	private Long id;
	
	@NotNull
	@Size(max = 100)
	@Column
	private String name;
	
	@OneToMany (targetEntity = PostEntity.class, cascade=CascadeType.ALL, mappedBy="author")
	private List<PostEntity> listPostEntity;
	
	public AuthorEntity(){
		
	}
	
	public AuthorEntity(Long id, String name, List<PostEntity> listPostEntity) {
		this.id = id;
		this.name = name;
		this.listPostEntity = listPostEntity; 
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