package com.xx.webframework.service;

import com.github.pagehelper.Page;
import com.xx.webframework.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> getProductPage(Page page);

}
