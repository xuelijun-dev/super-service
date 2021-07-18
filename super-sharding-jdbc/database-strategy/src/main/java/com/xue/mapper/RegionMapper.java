package com.xue.mapper;


import com.xue.entity.Region;

import java.util.List;

/**
 * 线路电压配置 数据层
 * 
 * @author bc3000
 * @date 2019-08-16
 */
public interface RegionMapper {

	public Region selectRegionById(Long id);

	public List<Region> selectRegionList(Region region);

	public int insertRegion(Region region);

	public int updateRegion(Region region);

	public int deleteRegionById(Long id);

}