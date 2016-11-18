package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.UserRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.ProductRepository;
import fi.haagahelia.course.domain.Product;
import fi.haagahelia.course.domain.CategoryRepository;
import fi.haagahelia.course.domain.Category;

@SpringBootApplication
public class MakeupStoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MakeupStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MakeupStoreApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner productDemo(ProductRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of products");
			crepository.save(new Category("Lipstick"));
			crepository.save(new Category("Foundation"));
			crepository.save(new Category("Eyeliner"));
			
			repository.save(new Product("Fabulous", "Maybelline","red",250,"1001", crepository.findByName("Lipstick").get(0)));
			repository.save(new Product("Infailible", "L'Oreal", "ivory", 300, "1002", crepository.findByName("Foundation").get(0)));	
			
			// Create users: admin/admin user/user
						User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@gmail.com", "USER");
						User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@gmail.com", "ADMIN");
						urepository.save(user1);
						urepository.save(user2);
			
			log.info("fetch all products");
			for (Product product : repository.findAll()) {
				log.info(product.toString());
			}

		};
	}
}
