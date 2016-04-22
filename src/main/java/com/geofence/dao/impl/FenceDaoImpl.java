package com.geofence.dao.impl;

import com.geofence.dao.FenceDao;
import com.geofence.model.Fence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wmonks on 3/23/2016.
 */
@Repository
public class FenceDaoImpl implements FenceDao {

    private NamedParameterJdbcTemplate template;

    @Autowired
    public FenceDaoImpl(DataSource ds) {
        template = new NamedParameterJdbcTemplate(ds);
    }

    @Override
    public List<Fence> getFences() {
        Map<String, Object> params = new HashMap<String, Object>();

        String sql = "select name, type, north, south, east, west from fence ";

        List<Fence> result = template.query(sql, params, fenceMapper);

        return result;
    }

    @Override
    public List<Fence> getMatchingFences(BigDecimal latitude, BigDecimal longitude, BigDecimal radius) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("radius", radius);

        String sql = "select name, type, north, south, east, west from fence "
                + "where :latitude > (west - :radius) and :latitude < (east + :radius) "
                + "and :longitude < (north + :radius) and :longitude > (south - :radius) ";

        List<Fence> result = template.query(sql, params, fenceMapper);

        return result;
    }

    @Override
    public void insertFence(Fence f) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", f.getName());
        params.put("type", f.getType());
        params.put("north", f.getNorth());
        params.put("south", f.getSouth());
        params.put("east", f.getEast());
        params.put("west", f.getWest());

        String sql = "insert into fence (name, type, north, south, east, west) "
            + "values (:name, :type, :north, :south, :east, :west)";
        template.update(sql, params);
    }

    //@Override
    //public List<Car> getCarsByMake(String make) {
        //Map<String, Object> params = new HashMap<~>();
        //params.put("make", make);

        //String sql = "select make, model, price from car where make = (:make)";
        //template.update(sql, params);
    //}



    private RowMapper<Fence> fenceMapper = (rs, rowNum) -> {
        Fence f = new Fence();


        f.setName(rs.getString("name"));
        f.setType(rs.getString("type"));
        f.setNorth(rs.getBigDecimal("north"));
        f.setSouth(rs.getBigDecimal("south"));
        f.setEast(rs.getBigDecimal("east"));
        f.setWest(rs.getBigDecimal("west"));

        return f;
    };
}
