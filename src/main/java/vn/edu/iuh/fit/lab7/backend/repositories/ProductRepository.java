package vn.edu.iuh.fit.lab7.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab7.backend.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
