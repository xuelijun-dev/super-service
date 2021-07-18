package com.xue.mapper;

import com.xue.entity.ProductDescript;

import java.util.List;

public interface ProductDescriptMapper {

    public ProductDescript selectProductById(Long id);


    public List<ProductDescript> selectProductList(ProductDescript productInfo);


    public int insertProduct(ProductDescript productInfo);


    public int updateProduct(ProductDescript productInfo);


    public int deleteProductById(Long id);

}