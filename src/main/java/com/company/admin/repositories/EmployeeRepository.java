package com.company.admin.repositories;

import com.company.admin.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aandra1 on 10/02/16.
 */
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

  List<Employee> findByCompanyId(Long id);

  Page<Employee> findByCompanyId(Long id, Pageable pageable);
}
