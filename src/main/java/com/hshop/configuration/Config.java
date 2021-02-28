package com.hshop.configuration;

import com.hshop.configuration.filter.JwtUsernamePasswordAuthenticationFilter;
import com.hshop.configuration.filter.TokenVerifierFilter;
import com.hshop.configuration.userdetail.UserDetailService;
import com.hshop.enums.RoleType;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Config extends WebSecurityConfigurerAdapter {
  private final boolean isJwtToken = true;
  @Autowired
  private UserDetailService userDetailService;
  @Autowired
  private PropertiesConfiguration propertiesConfiguration;
  @Autowired
  private SecretKey secretKey;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService);
    super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    if (isJwtToken) {
      http
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), propertiesConfiguration, secretKey))
          .addFilterAfter(new TokenVerifierFilter(propertiesConfiguration, secretKey), JwtUsernamePasswordAuthenticationFilter.class);
    }
      for (String str : toArray(propertiesConfiguration.getListPermit())){
        http.authorizeRequests()
            .antMatchers(str).permitAll();
      }
    http.csrf().disable()
        .authorizeRequests()
        .anyRequest().hasAnyRole(RoleType.ADMIN.getName(),RoleType.SELLER.name());
  }

  private String[] toArray(final String source) {
    return source.split(",");
  }
}

