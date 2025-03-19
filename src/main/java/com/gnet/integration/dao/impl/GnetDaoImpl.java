package com.gnet.integration.dao.impl;

import com.gnet.integration.dao.GnetDao;
import com.gnet.integration.dto.request.bookingreq.ValidVehicleRequest;
import com.gnet.integration.repository.constant.RepositoryConstant;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import smartzi.v2.b2b.res.VehicleGroupB2BVTMapRes;

import java.sql.*;
import java.util.ArrayList;


@Slf4j
@Repository
public class GnetDaoImpl implements GnetDao {

    @Autowired
    @Qualifier("replicationJdbcTemplate")
    private final JdbcTemplate replicationJdbcTemplate;

    @Autowired
    public GnetDaoImpl(@Qualifier("replicationJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.replicationJdbcTemplate = jdbcTemplate;
    }

    @Override
    public VehicleGroupB2BVTMapRes getValidVehicleGroups(ValidVehicleRequest validvehicleRequest) {
        Gson gson = new Gson();
        log.info("getValidVehicleGroups-request------------------>{}", gson.toJson(validvehicleRequest));
        Long startTime = System.currentTimeMillis();
        Connection connection = null;
        CallableStatement callableSt = null;
        ResultSet resultSet = null;
        VehicleGroupB2BVTMapRes vehicleGroup = null;
        int i = 1;
        try {
            System.out.println("vehicle-type-connection------------------>" + replicationJdbcTemplate.getDataSource());

            connection = DataSourceUtils.getConnection(replicationJdbcTemplate.getDataSource());
            System.out.println("vehicle-type-connection------------------>" + connection);
            callableSt = connection.prepareCall(RepositoryConstant.GET_VALID_VEHICLE_GROUPS_FOR_B2B_VEHICLE_TYPES);
            callableSt.setObject(i++, validvehicleRequest.getTravelDateTime(), Types.VARCHAR);
            callableSt.setObject(i++, validvehicleRequest.getSourceTimeZone(), Types.VARCHAR);
            callableSt.setObject(i++, validvehicleRequest.getSourceLat(), Types.NUMERIC);
            callableSt.setObject(i++, validvehicleRequest.getSourceLong(), Types.NUMERIC);
            callableSt.setObject(i++, validvehicleRequest.getSourceLocationId(), Types.BIGINT);
            callableSt.setObject(i++, validvehicleRequest.getB2bAccountDetailId(), Types.BIGINT);
            callableSt.setObject(i++, validvehicleRequest.getB2bVehicleType(), Types.VARCHAR);

            callableSt.execute();
            resultSet = callableSt.getResultSet();
            if (resultSet != null) {
                vehicleGroup = new VehicleGroupB2BVTMapRes();
                while (resultSet.next()) {
                    vehicleGroup.setB2bVehicleTypeId(resultSet.getLong("rB2BVehicleTypeId"));
                    vehicleGroup.setVehicleGroupId(resultSet.getLong("rVehicleGroupId"));
                    vehicleGroup.setB2bVehicleTypeName(resultSet.getString("rB2BVehicleTypeName"));
                    log.info("Vehicle group found: {}", gson.toJson(vehicleGroup));
                }
            }
        } catch (SQLException e) {
            log.info("An error occurred while fetching getValidVehicleGroups , Cause :" + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (callableSt != null) {
                    callableSt.close();
                }
            } catch (SQLException e) {
                log.info("An-SQLException-occured-in-getValidVehicleGroups-------->{}", e.getMessage());
            }
            DataSourceUtils.releaseConnection(connection, replicationJdbcTemplate.getDataSource());
        }
        return vehicleGroup;
    }
}
