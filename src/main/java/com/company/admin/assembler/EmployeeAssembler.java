package com.company.admin.assembler;

import com.company.admin.controllers.api.v1.CompanyController;
import com.company.admin.controllers.api.v1.EmployeeController;
import com.company.admin.controllers.exceptions.ResourceNotFoundException;
import com.company.admin.models.Employee;
import com.company.admin.resources.EmployeeResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by aandra1 on 10/02/16.
 */
@Component
public class EmployeeAssembler extends ResourceAssemblerSupport<Employee, EmployeeResource> {

  public EmployeeAssembler() {
    super(EmployeeController.class, EmployeeResource.class);
  }

  @Override
  public EmployeeResource toResource(Employee employee) {
    if (employee == null)
      throw new ResourceNotFoundException();

    EmployeeResource resource = EmployeeResource.builder()
        .employeeId(employee.getId())
        .name(employee.getName())
        .email(employee.getEmail())
        .company(employee.getCompany().getName())
        .build();

    Link company = linkTo(CompanyController.class).slash(employee.getCompany().getId())
        .withRel("company");
    Link self = linkTo(EmployeeController.class).slash(employee.getId()).withSelfRel();

    resource.add(company);
    resource.add(self);

    return resource;
  }
}
