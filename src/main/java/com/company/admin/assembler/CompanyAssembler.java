package com.company.admin.assembler;

import com.company.admin.controllers.api.v1.CompanyController;
import com.company.admin.controllers.api.v1.EmployeeController;
import com.company.admin.controllers.exceptions.ResourceNotFoundException;
import com.company.admin.models.Company;
import com.company.admin.resources.CompanyResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by aandra1 on 10/02/16.
 */
@Component
public class CompanyAssembler extends ResourceAssemblerSupport<Company, CompanyResource> {

  public CompanyAssembler() {
    super(CompanyController.class, CompanyResource.class);
  }

  @Override
  public CompanyResource toResource(Company company) {
    if (company == null)
      throw new ResourceNotFoundException();

    CompanyResource resource = CompanyResource.builder()
        .companyId(company.getId())
        .name(company.getName())
        .email(company.getEmail())
        .address(company.getAddress())
        .city(company.getCity())
        .country(company.getCountry())
        .build();

    Link employees = linkTo(EmployeeController.class)
        .slash("company")
        .slash(company.getId()).slash(0).slash(EmployeeController.PAGE_MAX)
        .withRel("employees");
    Link self = linkTo(CompanyController.class).slash(company.getId()).withSelfRel();

    resource.add(employees);
    resource.add(self);

    return resource;
  }
}
