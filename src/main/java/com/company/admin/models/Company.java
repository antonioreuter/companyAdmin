package com.company.admin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by aandra1 on 10/02/16.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "name", "email"})
@ToString(of = {"id", "name", "email"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(unique = true)
  @NotEmpty
  private String name;

  @NotEmpty
  private String address;

  @NotEmpty
  private String city;

  @NotEmpty
  private String country;

  @Email
  private String email;
  
  private String phoneNumber;

  @JsonIgnore
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Employee> employees;

  public void addEmployee(Employee employee) {
    if (employee == null)
      throw new IllegalArgumentException("you cannot add an empty employee!");

    if (CollectionUtils.isEmpty(employees))
      employees = new ArrayList<>();

    employees.add(employee);
  }

  public Collection<Employee> getEmployees() {
    if (CollectionUtils.isEmpty(employees))
      return Collections.EMPTY_LIST;

    return Collections.unmodifiableCollection(employees);
  }

}
