package com.web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@SequenceGenerator(name="CATEGORY_SEQ_GENERATOR", sequenceName="CATEGORY_SEQ", allocationSize = 1)
@Table(name = "CATEGORY")
public class Category{

	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ_GENERATOR" )
	private Long Id;
	
	private String categoryName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parent")
	private Category parent;
	
	@Column(name = "depth")
	private Long depth;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> children = new ArrayList<>();
	
}
