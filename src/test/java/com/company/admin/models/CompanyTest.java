package com.company.admin.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


/**
 * Created by aandra1 on 16/02/16.
 */
public class CompanyTest {

  private Validator validator;

  @Before
  public void init() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  @Test
  public void validCompanyWhenCountryIsEmpty() {
    Company company = Company.builder()
        .name("Twitter")
        .address("1355 Market St #900")
        .city("San Francisco")
        .email("twitter@twitter.com")
        .phoneNumber("+1 415-222-9670")
        .build();

    Set<ConstraintViolation<Company>> violations = this.validator.validate(company);
    Assert.assertEquals(false, violations.isEmpty());
  }

  @Test
  public void validCompanyWhenEmailIsEmpty() {
    Company company = Company.builder()
        .name("Twitter")
        .address("1355 Market St #900")
        .city("San Francisco")
        .country("United States")
        .phoneNumber("+1 415-222-9670")
        .build();

    Set<ConstraintViolation<Company>> violations = this.validator.validate(company);
    Assert.assertEquals(true, violations.isEmpty());
  }
}
