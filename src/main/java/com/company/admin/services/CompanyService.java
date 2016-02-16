package com.company.admin.services;

import com.company.admin.exceptions.ResourceNotFoundException;
import com.company.admin.exceptions.UnprocessableEntityException;
import com.company.admin.models.Company;
import com.company.admin.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolationException;

/**
 * Created by aandra1 on 11/02/16.
 */
@Service("companyService")
public class CompanyService {

  @Value("${company.page.max}")
  private Integer maxPage;

  @Autowired
  private CompanyRepository companyRepository;

  @Transactional
  public void save(Company company) {
    try {
      companyRepository.save(company);
    } catch (ConstraintViolationException cvex) {
      throw new UnprocessableEntityException(cvex.getMessage(), cvex);
    }
  }

  @Transactional
  public void delete(Long id) {
    companyRepository.delete(id);
  }

  public Page<Company> findAllByName(String name, Pageable pageable) {
    Page<Company> result = null;

    if (StringUtils.isEmpty(name)) {
      result = companyRepository.findAll(pageable);
    } else {
      result = companyRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    return result;
  }

  public Company findById(Long id) {
    Company company = companyRepository.findOne(id);

    if (company == null)
      throw new ResourceNotFoundException();

    return company;
  }

  public Integer maxPage() {
    return maxPage;
  }
}
