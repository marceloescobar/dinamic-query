package com.mescobar.dinamicquery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mescobar.dinamicquery.model.Person;
import com.mescobar.dinamicquery.query.PersonSearchParameter;
import com.mescobar.dinamicquery.query.ValidationHelper;
import com.mescobar.dinamicquery.query.ValidatorMetaData;
import com.mescobar.dinamicquery.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository repository;

	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}

	public List<Person> getPersonWithDynamicParam(PersonSearchParameter personSearchQuery) {

		List<ValidatorMetaData> metaDataList = ValidationHelper.getListOfValidatorMetaData(personSearchQuery);

		if (CollectionUtils.isEmpty(metaDataList)) {
			return new ArrayList<>();
		}

		return getSearchResultByDynamicItems(metaDataList);
	}

	private List<Person> getSearchResultByDynamicItems(List<ValidatorMetaData> metaDataList) {

		List<Person> userEntities = repository
				.findAll((Specification<Person>) (root, criteriaQuery, criteriaBuilder) -> {
					List<Predicate> predicates = new ArrayList<>();

					if (!CollectionUtils.isEmpty(metaDataList)) {

						for (ValidatorMetaData field : metaDataList) {
							if (Objects.nonNull(field.getValue())) {
								predicates.add(criteriaBuilder.equal(root.get(field.getFieldName()), field.getValue()));
							}
						}
					}
					return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
				});
		return userEntities;
	}

}
