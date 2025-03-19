package com.gnet.integration.repository.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.gnet.integration.repository.DynamoDBRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class DynamoDBRepositoryImpl<T> implements DynamoDBRepository<T> {

    @Autowired
    private DynamoDBMapper mapper;

    private DynamoDBMapperConfig getConfig(String tableName) {
        return new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName))
                .build();
    }

    private DynamoDBMapperConfig getConfigV2(String tableName) {
        return new DynamoDBMapperConfig.Builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName))
                .build();
    }

    @Override
    public T save(T entity, String tableName) {
        try {
            log.info("Saving entity to table-------------------->{}", tableName);
            mapper.save(entity, getConfig(tableName));
            log.info("Entity successfully saved to table-------------------->{}", tableName);
        } catch (Exception e) {
            log.error("An exception occurred while saving entity to table -------------------->{}-------{}------{}",
                    tableName, e.getMessage(), e.getClass().getName());
        }
        return entity;
    }

    @Override
    public void saveV2(T entity, String tableName) {
        try {
            log.info("Saving entity to table-------------------->{}", tableName);
            mapper.save(entity, getConfigV2(tableName));
            log.info("Entity successfully saved to table-------------------->{}", tableName);
        } catch (Exception e) {
            log.error("An exception occurred while saving entity to table -------------------->{}-------{}------{}",
                    tableName, e.getMessage(), e.getClass().getName());
        }
    }

    @Override
    public T load(Class<T> clazz, Object hashKey, Object rangeKey, String tableName) {
        try {
            log.info("Loading entity from table-------------------->{}", tableName);
            T entity = mapper.load(clazz, hashKey, rangeKey, getConfig(tableName));

            if (entity != null) {
                log.info("Entity successfully loaded from table-------------------->{}", tableName);
            } else {
                log.warn("No entity found in table-------------------->{} with hashKey: {} and rangeKey: {}", tableName, hashKey, rangeKey);
            }
            return entity;
        } catch (Exception e) {
            log.error("An exception occurred while loading entity from table-------------------->{}-------{}------{}",
                    tableName, e.getMessage(), e.getClass().getName());
            return null;
        }
    }

    @Override
    public List<T> queryByHashKey(Class<T> clazz, T partitionKeyObject, String tableName) {
        try {
            log.info("Querying table-------------------->{} with hash key values: {}", tableName, partitionKeyObject);

            DynamoDBQueryExpression<T> queryExpression = new DynamoDBQueryExpression<T>()
                    .withHashKeyValues(partitionKeyObject)
                    .withConsistentRead(false);

            List<T> results = mapper.query(clazz, queryExpression, getConfig(tableName));
            if (results != null && !results.isEmpty()) {
                log.info("Query successful, found {} entities in table-------------------->{}", results.size(), tableName);
            } else {
                log.warn("No entities found in table-------------------->{} with hash key values: {}", tableName, partitionKeyObject);
            }
            return results;
        } catch (Exception e) {
            log.error("An exception occurred while querying table-------------------->{}-------{}------{}",
                    tableName, e.getMessage(), e.getClass().getName());
            return null;
        }
    }

}
