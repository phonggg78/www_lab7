package vn.edu.iuh.fit.lab7.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab7.backend.models.Product;
import vn.edu.iuh.fit.lab7.backend.repositories.ProductRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortby, String sortDerection ){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDerection),sortby);
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        return productRepository.findAll(pageable);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public void delete(long id){
        productRepository.deleteById(id);
    }
    public Product findProductbyID(long id){
        return productRepository.findById(id).get();
    }
    public Page<Product> findAll(int pageNo, int pageSize, String sortBy, String
            sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return productRepository.findAll(pageable);
    }
    public Page<Product> findPaginat(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;
        List<Product> candidates = productRepository.findAll();
        if (candidates.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, candidates.size());
            list = candidates.subList(startItem, toIndex);
        }
        Page<Product> productPage
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                candidates.size());
        return productPage;
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
}
