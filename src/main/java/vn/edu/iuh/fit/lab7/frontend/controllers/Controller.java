package vn.edu.iuh.fit.lab7.frontend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/home")
    public String myPage(){
        return "/index";
    }
    @GetMapping("/listproduct")
    public String productPage(){
        return "admin/product/list-product";
    }
    @GetMapping("/add-product")
    public String addProductPage() {
        return "admin/product/addProduct";
    }
}
