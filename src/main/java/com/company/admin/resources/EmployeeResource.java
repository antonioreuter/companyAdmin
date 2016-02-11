package com.company.admin.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by aandra1 on 10/02/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResource extends ResourceSupport {

  private Long employeeId;

  private String name;

  private String email;

  private String company;
}
