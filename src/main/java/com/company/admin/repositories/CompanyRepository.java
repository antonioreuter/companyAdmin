package com.company.admin.repositories;

import com.company.admin.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aandra1 on 10/02/16.
 */

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

  Page<Company> findAll(Pageable pageable);

  List<Company> findByName(@Param("name") String name);
}
