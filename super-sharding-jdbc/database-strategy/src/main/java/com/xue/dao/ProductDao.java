package com.xue.dao;

import com.xue.entity.ProductDescript;
import com.xue.entity.ProductInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator.
 */
@Mapper
@Component
public interface ProductDao {

    //添加商品基本信息
    @Insert("insert into product_info(store_info_id,product_name,spec,region_code,price) " +
            " values (#{storeInfoId},#{productName},#{spec},#{regionCode},#{price})")
    @Options(useGeneratedKeys = true,keyProperty = "productInfoId",keyColumn = "product_info_id")
    int insertProductInfo(ProductInfo productInfo);

    //添加商品描述信息
    @Insert("insert into product_descript(product_info_id,descript,store_info_id) " +
            " value(#{productInfoId},#{descript},#{storeInfoId})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertProductDescript(ProductDescript productDescript);

    @Select("select i.*,d.descript,r.region_name placeOfOrigin from product_info i join product_descript d on i.product_info_id = d.product_info_id " +
            " join region r on i.region_code = r.region_code order by product_info_id desc limit #{start},#{pageSize}")
    List<ProductInfo> selectProductList(@Param("start")int start, @Param("pageSize") int pageSize);

    //商品总数
    @Select("select count(1) from product_info")
    int selectCount();

    //商品分组统计
    @Select("select t.region_code,count(1) as num from product_info t group by t.region_code having num > 1 order by region_code ")
    List<Map> selectProductGroupList();


    /**
     * 根据id列表查询商品详情
     * @param productIds 商品id列表
     * @return
     */
    @Select({"<script>",
            " select",
            " * ",
            " from product_info t ",
            " where t.product_info_id in",
            "<foreach collection='productIds' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Map> selectProductbyProductIds(@Param("productIds") List<Long> productIds);


    /**
     * 根据店铺ID列表查询商品列表
     * @param storeIds 店铺id列表
     * @return
     */
    @Select({"<script>",
            " select",
            " * ",
            " from product_info t ",
            " where t.store_info_id in",
            "<foreach collection='storeIds' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Map> selectProductByStoreIds(@Param("storeIds") List<Long> storeIds);
}
