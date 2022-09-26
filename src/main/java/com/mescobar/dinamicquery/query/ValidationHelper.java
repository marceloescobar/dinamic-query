package com.mescobar.dinamicquery.query;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ValidationHelper {

	public static List<ValidatorMetaData> getListOfValidatorMetaData(Object data) {

		if (data == null)
			return null;

		List<Field> fields = Arrays.asList(data.getClass().getDeclaredFields());

		List<ValidatorMetaData> fieldDefinitions = fields.stream().map(field -> {
			return getValidationMetaData(field, data);
		})
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

		return fieldDefinitions;
	}

	private static ValidatorMetaData getValidationMetaData(Field field, Object data) {
		field.setAccessible(true);

		if (field.isAnnotationPresent(Validate.class)) {
			Validate annotation = field.getAnnotation(Validate.class);
			String fieldName = annotation.columnName().isEmpty() ? field.getName() : annotation.columnName();
			String jsonFieldName = annotation.jsonField().isEmpty() ? field.getName() : annotation.jsonField();

			Object value = null;

			try {
				value = field.get(data);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			if (Objects.nonNull(value)) {
				return new ValidatorMetaData(fieldName, jsonFieldName, value);
			}
		}
		return null;
	}

}
