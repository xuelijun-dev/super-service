package com.xue.mapper;

import com.xue.entity.ProductInfo;

import java.util.List;

public interface ProductMapper {


    public ProductInfo selectProductById(Long id);


    public List<ProductInfo> selectProductList(ProductInfo productInfo);

    public List<ProductInfo> selectProductListWithStoreAndDescription(ProductInfo productInfo);


    public int insertProduct(ProductInfo productInfo);


    public int updateProduct(ProductInfo productInfo);

    public int deleteProductById(Long id);

}