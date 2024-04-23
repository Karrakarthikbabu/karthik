package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Student")
@Data
public class Students {
	@Id
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;

	@Column(name = "resume", columnDefinition = "LONGBLOB")
	@Lob
	byte[] resume;

	@Column(name = "fileName")
	String fileName;

}
