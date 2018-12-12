package com.test.junity.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Table(name = "Book")
public class Book {
	@Id
	@Column
    @GeneratedValue
    @Getter @Setter private int id;
	@Getter @Setter @Column private String name;
	@Getter @Setter @Column private String author;
	@Getter @Setter @Column private String description;
	@Getter @Setter @Column private String numPages;
	
	
}
