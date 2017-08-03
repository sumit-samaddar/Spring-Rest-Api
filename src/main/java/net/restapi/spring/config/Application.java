package net.restapi.spring.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author sumit
 *
 */
@SpringBootApplication(scanBasePackages={"net.restapi.spring"})
public class Application extends SpringBootServletInitializer  {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
			
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
