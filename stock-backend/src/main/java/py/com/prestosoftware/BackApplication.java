package py.com.prestosoftware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan
public class BackApplication  {

	private static final Logger LOGGER=LoggerFactory.getLogger(BackApplication.class);
	
	public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
        LOGGER.info("Agroprogreso backend started on the configured server port");
	}	
}
