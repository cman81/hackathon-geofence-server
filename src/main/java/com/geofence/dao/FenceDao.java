package com.geofence.dao;

import com.geofence.model.Fence;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wmonks on 3/23/2016.
 */
public interface FenceDao {
    List<Fence> getFences();

    List<Fence> getMatchingFences(BigDecimal latitude, BigDecimal longitude, BigDecimal radius);

    void insertFence(Fence f);
}
