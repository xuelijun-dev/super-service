package com.xue.dao;//package com.xue.dao;
//
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Administrator.
// */
//@Mapper
//@Component
//public interface ProductDescriptDao {
//
//    /**
//     * 根据id列表查询商品详情
//     * @param productIds 商品id列表
//     * @return
//     */
//    @Select({"<script>",
//            " select",
//            " * ",
//            " from product_descript t ",
//            " where t.product_info_id in",
//            "<foreach collection='productIds' item='id' open='(' separator=',' close=')'>",
//            "#{id}",
//            "</foreach>",
//            "</script>"
//    })
//    List<Map> selectProductDescriptbyProductIds(@Param("productIds") List<Long> productIds);
//
//
//
//
//}
