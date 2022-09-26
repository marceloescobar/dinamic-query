package com.mescobar.dinamicquery.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatorMetaData {

	private String fieldName;
	private String jsonFieldName;
	private Object value;
}
