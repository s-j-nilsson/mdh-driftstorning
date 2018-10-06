package se.mdh.driftstorning.admin.Configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {
  @Bean
  public LayoutDialect layoutDialect() {
    return new LayoutDialect();
  }
}
