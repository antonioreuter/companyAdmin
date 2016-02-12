package com.company.admin.repositories;

import com.company.admin.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aandra1 on 10/02/16.
 */
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
  Page<Employee> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

  Page<Employee> findByCompanyId(Long id, Pageable pageable);
}
