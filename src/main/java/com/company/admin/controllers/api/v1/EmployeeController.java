package com.company.admin.controllers.api.v1;

import com.company.admin.assembler.EmployeeAssembler;
import com.company.admin.models.Employee;
import com.company.admin.repositories.EmployeeRepository;
import com.company.admin.resources.EmployeeResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aandra1 on 10/02/16.
 */

@Slf4j
@RestController("employeeController")
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  public static final Integer PAGE_MAX = 20;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeeAssembler assembler;

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @ResponseBody
  public EmployeeResource show(@PathVariable("id") long id) {
    log.info("Find All employees.");
    Employee result = employeeRepository.findOne(id);

    return assembler.toResource(result);
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<EmployeeResource> employees() {
    log.info("Find All employees.");
    List<Employee> result = (ArrayList) employeeRepository.findAll();

    return assembler.toResources(result);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/company/{companyId}/{page}/{size}")
  @ResponseBody
  public Page<Employee> employeesByCompany(@PathVariable("companyId") long companyId, @PathVariable("page") int page,
                                           @PathVariable("size") int size) {
    log.info("Find All employees by company id: {companyId}, Page {page}, Total per Page: {size}", companyId, page, size);
    Pageable pageable = new PageRequest(page, size);
    Page<Employee> result = employeeRepository.findByCompanyId(companyId, pageable);

    return result;
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public EmployeeResource save(@RequestBody Employee employee) {
    employeeRepository.save(employee);
    log.info("Saved employee -> {employee}.", employee);

    return assembler.toResource(employee);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public EmployeeResource update(@PathVariable("id") long id, @RequestBody Employee employee) {
    log.info("Update employee id: {id} -> {employee}", id, employee);
    employeeRepository.save(employee);

    return assembler.toResource(employee);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public void delete(@PathVariable("id") long id) {
    log.info("Delete employee id: {id}", id);
    employeeRepository.delete(id);
  }
}
