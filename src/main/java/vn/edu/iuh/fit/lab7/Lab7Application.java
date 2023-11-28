package vn.edu.iuh.fit.lab7;

import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.lab7.backend.enums.ProductStatus;
import vn.edu.iuh.fit.lab7.backend.models.Product;
import vn.edu.iuh.fit.lab7.backend.repositories.ProductRepository;

import java.util.Random;

@SpringBootApplication
public class Lab7Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab7Application.class, args);
    }
    @Autowired
    private ProductRepository productRepository;
    @Bean
    CommandLineRunner createSampleProduct(){
        return  args -> {
            Faker faker = new Faker();
            Random random = new Random();
            Device device = faker.device();
            for (int i = 0; i<200; i++){
                Product product = new Product(
                        device.modelName(),
                        faker.lorem().paragraph(30),
                        random.nextInt(10)%2==0?"Kg":"piece",
          //             "piece",
                        device.manufacturer(),
                        ProductStatus.ACTIVE
                );
                productRepository.save(product);
            }
        };

    }

}
