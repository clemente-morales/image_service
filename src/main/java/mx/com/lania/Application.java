package mx.com.lania;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import mx.com.lania.ImageServiceProperties;
import mx.com.lania.models.storage.StorageProperties;
import mx.com.lania.models.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties({ImageServiceProperties.class, StorageProperties.class})
public class Application {
	private ImageServiceProperties properties;

	@Autowired
	public Application(ImageServiceProperties properties) {
		this.properties = properties;
	}
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
            storageService.deleteAll();
            storageService.init();
		};
	}
	
	@Bean
	@ConfigurationProperties(prefix = "imageService.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			System.out.println("Starting this application :"+properties.getDescription());
			System.out.println("Getting this user :"+properties.getSecurity().getUser());

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

}