package com.company.admin.controllers.api.v1;

import com.company.admin.models.Employee;
import com.company.admin.services.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aandra1 on 10/02/16.
 */

@Slf4j
@RestController("employeeController")
@RequestMapping("/api/v1/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @ApiOperation(value = "Show Employee", notes = "Searches an employee by id.")
  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @ResponseBody
  public Employee show(@PathVariable("id") long id) {
    log.info("Find employee by ID: {}.", id);
    return employeeService.findById(id);
  }

  @ApiOperation(value = "Search Employee by name", notes = "Searches an employee by name. If the pagination values PAGE and SIZE were empty, " +
      "they will assume the respective default values: 0, 10. If the name param is empty, it will search for all the employees.")
  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public Page<Employee> employees(@RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "10") int size) {
    log.info("Find All employees by {}, page {}, size {}.", name, page, size);
    Pageable pageable = new PageRequest(page, validSize(size));

    return employeeService.findAllByName(name, pageable);
  }

  @ApiOperation(value = "Search Employee by Company", notes = "Searches an employee by company. If the pagination values PAGE and SIZE were empty, " +
      "they will assume the respective default values: 0, 10.")
  @RequestMapping(method = RequestMethod.GET, value = "/company/{companyId}")
  @ResponseBody
  public Page<Employee> employeesByCompany(@PathVariable("companyId") long companyId,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "10") int size) {
    log.info("Find All employees by company id {}, page {}, size {}.", companyId, page, size);
    Pageable pageable = new PageRequest(page, validSize(size));

    return employeeService.findByCompanyId(companyId, pageable);
  }

  @ApiOperation(value = "Save Employee", notes = "Saves a new employee.")
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Employee save(@RequestBody Employee employee) {
    employeeService.save(employee);
    log.info("Employee saved -> {}.", employee);

    return employee;
  }

  @ApiOperation(value = "Update Employee", notes = "Updates an employee.")
  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Employee update(@PathVariable("id") long id, @RequestBody Employee employee) {
    log.info("Employee updated id: {} -> {}", id, employee);
    employeeService.save(employee);

    return employee;
  }

  @ApiOperation(value = "Delete Employee", notes = "Delete an employee.")
  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public void delete(@PathVariable("id") long id) {
    log.info("Employee deleted id: {}", id);
    employeeService.delete(id);
  }

  private int validSize(int size) {
    return (size > employeeService.maxPage()) ? employeeService.maxPage() : size;
  }
}
