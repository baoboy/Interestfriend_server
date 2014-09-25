package com.interestfriend.Idao;

import java.util.List;

import com.interestfriend.bean.GrowthImage;

public interface GrowthImageDao {
	boolean insertGrowthImageToDB(List<GrowthImage> listImages);

	List<GrowthImage> getImagesByGrowthID(int cid, int growth_id);
}
