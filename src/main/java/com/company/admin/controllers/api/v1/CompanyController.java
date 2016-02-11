package com.company.admin.controllers.api.v1;

import com.company.admin.assembler.CompanyAssembler;
import com.company.admin.models.Company;
import com.company.admin.repositories.CompanyRepository;
import com.company.admin.resources.CompanyResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aandra1 on 10/02/16.
 */

@Slf4j
@RestController("companyController")
@RequestMapping("/api/v1/companies")
public class CompanyController {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private CompanyAssembler assembler;

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public List<CompanyResource> search() {
    List<Company> result = (ArrayList) companyRepository.findAll();

    return assembler.toResources(result);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @ResponseBody
  public CompanyResource show(@PathVariable("id") long id) {
    log.info("Show company id: {id}", id);
    Company result = companyRepository.findOne(id);

    return assembler.toResource(result);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public CompanyResource save(@RequestBody Company company) {
    companyRepository.save(company);
    log.info("Saved company -> {company}", company);

    return assembler.toResource(company);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CompanyResource update(@PathVariable("id") long id, @RequestBody Company company) {
    log.info("Update companu id: {id} -> {company}", id, company);
    companyRepository.save(company);

    return assembler.toResource(company);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public void delete(@PathVariable("id") long id) {
    log.info("Delete company {id}.", id);
    companyRepository.delete(id);
  }
}
