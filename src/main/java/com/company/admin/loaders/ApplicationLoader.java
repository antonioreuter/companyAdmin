package com.company.admin.loaders;

import com.company.admin.models.Company;
import com.company.admin.models.Employee;
import com.company.admin.repositories.CompanyRepository;
import com.company.admin.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by aandra1 on 10/02/16.
 */
@Slf4j
@Component
public class ApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    Company microsoft = Company.builder()
        .name("Microsoft")
        .address("Richmond ville")
        .city("Seattle")
        .country("United States")
        .email("microsoft@microsoft.com")
        .phoneNumber("98273947293").build();
    companyRepository.save(microsoft);
    log.info("Company saved: " + microsoft);

    Employee emp1 = Employee.builder().name("John Doe")
        .email("johndoe@microsoft.com").phoneNumber("928374928347")
        .company(microsoft).build();
    employeeRepository.save(emp1);
    log.info("Employee saved: " + emp1);

    Employee emp2 = Employee.builder().name("May Simpson")
        .email("m.simpson@microsoft.com").phoneNumber("88878347958")
        .company(microsoft).build();
    employeeRepository.save(emp2);
    log.info("Employee saved: " + emp2);


    Company google = Company.builder()
        .name("Google")
        .address("Santa Monica ville")
        .city("Palo Alto")
        .country("United States")
        .email("google@google.com")
        .phoneNumber("98273492388").build();
    companyRepository.save(google);
    log.info("Company saved: " + google);

    Employee emp3 = Employee.builder().name("Kevin Krugsman")
        .email("kevin.krugs@google.com").phoneNumber("77764872683")
        .company(google).build();
    employeeRepository.save(emp3);
    log.info("Employee saved: " + emp3);

    Employee emp4 = Employee.builder().name("Clarice Lars")
        .email("c.lars@google.com").phoneNumber("6678794564")
        .company(google).build();
    employeeRepository.save(emp4);
    log.info("Employee saved: " + emp4);
  }
}
