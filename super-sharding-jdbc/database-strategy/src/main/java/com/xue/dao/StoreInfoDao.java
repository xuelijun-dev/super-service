package com.xue.dao;

import com.xue.entity.StoreInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator.
 */
@Mapper
@Component
public interface StoreInfoDao {

    /**
     * 根据id列表查询店铺详情
     * @param storeIds 商品id列表
     * @return
     */
    @Select({"<script>",
            " select",
            " * ",
            " from store_info t ",
            " where t.id in",
            "<foreach collection='storeIds' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Map> selectStoreInfoByStoreIds(@Param("storeIds") List<Long> storeIds);


    /**
     * 根据地区编码查询店铺
     * @param regionCodes 地区编码列表
     * @return
     */
    @Select({"<script>",
            " select",
            " * ",
            " from store_info t ",
            " where t.region_code in",
            "<foreach collection='regionCodes' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Map> selectStoreInfoByCode(@Param("regionCodes") List<String> regionCodes);


    //添加店铺
    @Insert("insert into store_info(id, store_name, reputation, region_code) " +
            " values (#{id},  #{storeName}, #{reputation}, #{regionCode})")
    int insertStoreInfo(StoreInfo storeInfo);

}
