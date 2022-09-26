package com.mescobar.dinamicquery.query;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PersonSearchParameter {

	@Validate(columnName = "id")
	private Long id;
	
	@Validate(columnName = "username")
	private String userName;

	@Validate(columnName = "name")
	private String name;

	@Validate(columnName = "email")
	private String emailAddress;

	@Validate(columnName = "phone")
	private String phoneNumber;

	@Validate(columnName = "type")
	private String userType;

	@Validate(columnName = "userCode")
	private String code;
}
