package com.xue.service.impl;

import com.xue.entity.ProductDescript;
import com.xue.entity.ProductInfo;
import com.xue.mapper.ProductDescriptMapper;
import com.xue.mapper.ProductMapper;
import com.xue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    private ProductDescriptMapper descriptMapper;


    //添加商品
    @Override
    @Transactional
    public void createProduct(ProductInfo productInfo) {
        ProductDescript productDescript =new ProductDescript();
        //设置商品描述 信息
        productDescript.setDescript(productInfo.getDescript());
        //调用dao向商品信息表
        productMapper.insertProduct(productInfo);
        //将商品信息id设置到productDescript
        productDescript.setProductInfoId(productInfo.getProductInfoId());
        //设置店铺id
        productDescript.setStoreInfoId(productInfo.getStoreInfoId());
        //向商品描述信息表插入数据
        descriptMapper.insertProduct(productDescript);
    }

    @Override
    public List<ProductInfo> queryProductList(ProductInfo productInfo) {
        return productMapper.selectProductList(productInfo);
    }

    @Override
    public List<ProductInfo> queryProductListWithStoreAndDescription(ProductInfo productInfo) {
        return null;
    }

   /* @Override
    public List<ProductInfo> queryProduct(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return productDao.selectProductList(start,pageSize);
    }*/
}
