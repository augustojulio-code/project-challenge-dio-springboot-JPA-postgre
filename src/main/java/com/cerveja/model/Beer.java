package com.cerveja.model;

import java.io.Serializable;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity(name="tb_beer")
public class Beer implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@NotNull 
	@Size(min=3, max =255)
	private String name;
	@NotNull
	@Size(min=3, max =255)
	private String style;
	@NotNull
	@Size(min=3, max =255)
	private String producer;
	@NotNull
	@Size(min=3, max =255)
	private String country;
	@NotNull
	@Email
	private String producerEmail;
}
