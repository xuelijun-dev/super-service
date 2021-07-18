package com.xue.mapper;

import com.xue.entity.StoreInfo;

import java.util.List;

/**
 * Created by Administrator.
 */
public interface StoreInfoMapper {


    /*List<Map> selectStoreInfoByStoreIds(@Param("storeIds") List<Long> storeIds);

    List<Map> selectStoreInfoByCode(@Param("regionCodes") List<String> regionCodes);*/

    int insertStoreInfo(StoreInfo storeInfo);

    public StoreInfo selectStoreInfoById(Long id);

    public List<StoreInfo> selectStoreInfoList(StoreInfo storeInfo);


    public List<StoreInfo> selectStoreInfoWithRegionList(StoreInfo storeInfo);





    public int updateStoreInfo(StoreInfo storeInfo);

    public int deleteStoreInfoById(Long id);



}
