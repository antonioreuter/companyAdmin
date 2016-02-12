package com.company.admin.services;

import com.company.admin.controllers.exceptions.ResourceNotFoundException;
import com.company.admin.controllers.exceptions.UnprocessableEntityException;
import com.company.admin.models.Employee;
import com.company.admin.repositories.EmployeeRepository;
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
@Service("employeeService")
public class EmployeeService {

  @Value("${employee.page.max}")
  private Integer maxPage;

  @Autowired
  private EmployeeRepository employeeRepository;


  public Employee findById(Long id) {
    Employee employee = employeeRepository.findOne(id);

    if (employee == null)
      throw new ResourceNotFoundException();

    return employee;
  }

  public Page<Employee> findByCompanyId(Long companyId, Pageable pageable) {
    return employeeRepository.findByCompanyId(companyId, pageable);
  }

  public Page<Employee> findAllByName(String name, Pageable pageable) {
    Page<Employee> result = null;

    if (StringUtils.isEmpty(name)) {
      result = employeeRepository.findAll(pageable);
    } else {
      result = employeeRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    return result;
  }

  @Transactional
  public void save(Employee employee) {
    try {
      employeeRepository.save(employee);
    } catch (ConstraintViolationException cvex) {
      throw new UnprocessableEntityException(cvex.getMessage(), cvex);
    }
  }

  @Transactional
  public void delete(Long id) {
    employeeRepository.delete(id);
  }

  public Integer maxPage() {
    return maxPage;
  }
}
