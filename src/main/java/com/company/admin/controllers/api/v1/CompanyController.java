package com.company.admin.controllers.api.v1;

import com.company.admin.models.Company;
import com.company.admin.services.CompanyService;
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
@RestController("companyController")
@RequestMapping("/api/v1/companies")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  @ApiOperation(value = "Search Company", notes = "Searches a company by name. If the pagination values PAGE and SIZE were empty, " +
      "they will assume the respective default values: 0, 10. If the name param is empty, it will search for all the companies.")
  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public Page<Company> search(@RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
    log.info("Find All companies by {}, page {}, size {}.", name, page, size);
    Pageable pageable = new PageRequest(page, validSize(size));
    return companyService.findAllByName(name, pageable);
  }

  @ApiOperation(value = "Show Company", notes = "Searches a company by id.")
  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @ResponseBody
  public Company show(@PathVariable("id") long id) {
    log.info("Show company id: {}", id);

    return companyService.findById(id);
  }

  @ApiOperation(value = "Save Company", notes = "Saves a new company.")
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Company save(@RequestBody Company company) {
    companyService.save(company);
    log.info("Saved company -> {}", company);

    return company;
  }

  @ApiOperation(value = "Update Company", notes = "Updates a company.")
  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Company update(@PathVariable("id") long id, @RequestBody Company company) {
    log.info("Update company id: {} -> {}", id, company);
    companyService.save(company);

    return company;
  }

  @ApiOperation(value = "Delete Company", notes = "Delete a company.")
  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public void delete(@PathVariable("id") long id) {
    log.info("Delete company {}.", id);
    companyService.delete(id);
  }

  private int validSize(int size) {
    return (size > companyService.maxPage()) ? companyService.maxPage() : size;
  }
}
