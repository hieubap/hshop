package com.hshop.configuration;

import com.hshop.configuration.userdetail.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import spring.library.common.config.Config;

public class Configuration extends Config {
  @Autowired
  private UserDetailService userDetailService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
    auth.userDetailsService(userDetailService);
  }
}
