package com.revature.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.revature.dtos.AuthorDTO;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String genre;
	
	@NotBlank
	private String title;
	
	@NotNull
	@Column(name="author_id")
	private int authorId;
	
	@Positive
	@Column(name="page_count")
	private int pageCount;
	
	@Column(name="release_date")
	private LocalDate releaseDate;
	
	
	/*
	 * DTO - Data Transfer Object
	 * Represents a form for data to be transfered in.
	 */
	@Transient
	private AuthorDTO author;
}
