package com.company.admin.services;

import com.company.admin.exceptions.ResourceNotFoundException;
import com.company.admin.exceptions.UnprocessableEntityException;
import com.company.admin.models.Company;
import com.company.admin.repositories.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;


/**
 * Created by aandra1 on 16/02/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

  @Mock
  CompanyRepository companyRepository;

  @Spy
  @InjectMocks
  CompanyService subject;

  @Test(expected = UnprocessableEntityException.class)
  public void whenTryToSaveWithConstraintViolationShouldThrowAnException() {
    Company company = Company.builder()
        .name("Twitter")
        .address("1355 Market St #900")
        .city("San Francisco")
        .email("twitter@twitter.com")
        .phoneNumber("+1 415-222-9670")
        .build();

    doThrow(ConstraintViolationException.class).when(companyRepository).save(company);

    subject.save(company);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void whenThereIsNoCompanyForId() {
    when(companyRepository.findOne(anyLong())).thenReturn(null);

    subject.findById(10L);
  }

  @Test
  public void findAllByNameWhenNameIsEmpty() {
    String name = "";
    Pageable pageable = new PageRequest(0, 10);
    Page<Company> result = new PageImpl<>(Collections.emptyList(), pageable, 0);

    when(companyRepository.findAll(pageable)).thenReturn(result);

    subject.findAllByName(name, pageable);
    verify(companyRepository, atLeastOnce()).findAll(pageable);
    verify(companyRepository, never()).findAllByNameContainingIgnoreCase(name, pageable);
  }

  @Test
  public void findAllByNameWhenNameNotEmpty() {
    String name = "Goo";
    Pageable pageable = new PageRequest(0, 10);
    Page<Company> result = new PageImpl<>(Collections.emptyList(), pageable, 0);

    when(companyRepository.findAll(pageable)).thenReturn(result);

    subject.findAllByName(name, pageable);
    verify(companyRepository, atLeastOnce()).findAllByNameContainingIgnoreCase(name, pageable);
    verify(companyRepository, never()).findAll(pageable);
  }
}
