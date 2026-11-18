package py.com.prestosoftware.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import py.com.prestosoftware.data.repository.SafeJpaRepository;

@Configuration
@ComponentScan(basePackages = "py.com.prestosoftware")
@EnableJpaRepositories(basePackages = "py.com.prestosoftware.data.repository",
		repositoryBaseClass = SafeJpaRepository.class)
public class ApplicationConfiguration {
}
