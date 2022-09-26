package com.mescobar.dinamicquery.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Person {

	@Id
	private Long id;

	private String username;

	private String name;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private String password;

	private String type;

	private String userCode;

	private String country;

	private String city;

	private String state;

	private String zipCode;

	private String nationalId;
}
