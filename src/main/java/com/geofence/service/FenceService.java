package com.geofence.service;

import com.geofence.dao.FenceDao;
import com.geofence.model.Fence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wmonks on 3/23/2016.
 */
@Service
public class FenceService {

    @Autowired
    private FenceDao fenceDao;

    public List<Fence> getFences() {
        return fenceDao.getFences();
    }

    public List<Fence> getMatchingFences(BigDecimal latitude, BigDecimal longitude, BigDecimal radius) {
        return fenceDao.getMatchingFences(latitude, longitude, radius);
    }

    public void submitFence(Fence f) {
        fenceDao.insertFence(f);
    }

    public void setFenceDao(FenceDao fenceDao) {
        this.fenceDao = fenceDao;
    }


}
