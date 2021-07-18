package com.xue.service;

import com.xue.entity.ProductInfo;

import java.util.List;

/**
 * Created by Administrator.
 */
public interface ProductService {
    //添加商品
    public void createProduct(ProductInfo product);

    public List<ProductInfo> queryProductList(ProductInfo productInfo);


    public List<ProductInfo> queryProductListWithStoreAndDescription(ProductInfo productInfo);

    //查询商品
    //public List<ProductInfo> queryProduct(int page, int pageSize);
}
