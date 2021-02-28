package com.hshop.configuration;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties(value = "application.security.config")
public class PropertiesConfiguration {
  private String secretKey;
  private String tokenPrefix;
  private String tokenExpirationAfterDays;
  private String authorizationHeader;
  private String listPermit;
}
