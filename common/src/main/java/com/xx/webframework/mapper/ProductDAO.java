package com.xx.webframework.mapper;

import com.xx.webframework.domain.Product;
import com.xx.webframework.domain.ProductExample;

/**
 * ProductDAO继承基类
 */
public interface ProductDAO extends MyBatisBaseDao<Product, Integer, ProductExample> {
}