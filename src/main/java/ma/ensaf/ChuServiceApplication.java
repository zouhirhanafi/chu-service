package ma.ensaf;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ChuServiceApplication {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${spring.profiles.active:dev}")
	private String activeProfiles;
	@Value("${server.error.include-stacktrace}")
	private String errorInclude;


    @Autowired
    private Environment environment;

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ChuServiceApplication.class, args);
	}

	void testLog(Logger log) {
		log.trace("trace message");
		log.debug("debug message");
		log.info("info message");
		log.warn("warn message");
		log.error("error message");
	}

	@Profile("dev")
	@Bean 
	CommandLineRunner runner() {
		return (String... args) -> {
			System.out.println("===***---### Debut exécution runner ...");
			for (String profileName : environment.getActiveProfiles()) {
				System.out.println("Currently active profile - " + profileName);
			}

			System.out.println("active profiles : " + activeProfiles);
			System.out.println("errorInclude : " + errorInclude);

			testLog(logger);
			testLog(LoggerFactory.getLogger("com"));
			testLog(LoggerFactory.getLogger("com.ensaf"));
			testLog(LoggerFactory.getLogger("com.ensaf.ekili"));

			logger.debug("client " + 34 + " créé avec succès");
			logger.debug("client {} créé avec succès {}", 34, "x");

			System.out.println("===***---### Fin exécution runner.");
		};
	}
}
