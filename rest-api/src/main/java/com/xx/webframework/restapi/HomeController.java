package com.xx.webframework.restapi;

import com.github.pagehelper.Page;
import com.xx.webframework.domain.Product;
import com.xx.webframework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET,value = "/product/list")
    public List<Product> getProductList(){
//        return productService.findAll();
        int pageNum = 1;
        int pageSize = 10;
        return productService.getProductPage(new Page(pageNum,pageSize));
    }
}
