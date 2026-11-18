package py.com.prestosoftware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import java.util.TimeZone;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import py.com.prestosoftware.ui.main.MainController;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class StockApplication  {

	private static final Logger LOGGER=LoggerFactory.getLogger(StockApplication.class);

	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(5000);
		requestFactory.setReadTimeout(120000);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		// jasperreports trae jackson-dataformat-xml al classpath y RestTemplate prioriza XML sobre JSON.
		// En XML los null viajan como elementos vacíos ("" u objetos sin id), lo que rompe el backend.
		// Forzamos JSON en todas las llamadas.
		restTemplate.getMessageConverters().removeIf(c -> c instanceof MappingJackson2XmlHttpMessageConverter
				|| c instanceof Jaxb2RootElementHttpMessageConverter);
		// Fechas sin zona ("2026-11-12") se interpretan en hora local, no en UTC
		// (en UTC quedaban un día antes al mostrarlas en Paraguay).
		restTemplate.getMessageConverters().stream()
				.filter(c -> c instanceof MappingJackson2HttpMessageConverter)
				.forEach(c -> ((MappingJackson2HttpMessageConverter) c).getObjectMapper().setTimeZone(TimeZone.getDefault()));
		return restTemplate;
	}
	
	public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(StockApplication.class)
        		.web(WebApplicationType.NONE)
        		.headless(false)
        		.run(args);
        MainController mainMenuController = context.getBean(MainController.class);
        mainMenuController.prepareAndOpenFrame();
        LOGGER.info("Simple log statement with inputs {}, {} and {}", 1,2,3);
	}	
}