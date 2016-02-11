package com.company.admin.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aandra1 on 10/02/16.
 */

@RestController("versionController")
@RequestMapping("/version")
public class VersionController {

  @Value("${application.version}")
  private String version;

  @RequestMapping(method = RequestMethod.GET)
  public String healthcheck() {
    return version;
  }
}
