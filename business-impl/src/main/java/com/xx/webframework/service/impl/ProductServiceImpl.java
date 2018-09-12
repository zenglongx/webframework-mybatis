package com.xx.webframework.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xx.webframework.domain.Product;
import com.xx.webframework.domain.ProductExample;
import com.xx.webframework.mapper.ProductDAO;
import com.xx.webframework.mapper.ProductDAOSelf;
import com.xx.webframework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductDAOSelf productDAOSelf;

    @Override
    public List<Product> findAll() {
        return productDAO.selectByExample(new ProductExample());
    }

    @Override
    public List<Product> getProductPage(Page page) {
        ProductExample productExample = new ProductExample();
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        return productDAO.selectByExample(productExample);
    }
}
