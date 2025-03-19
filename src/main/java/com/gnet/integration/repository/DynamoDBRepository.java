package com.gnet.integration.repository;

import java.util.List;

public interface DynamoDBRepository<T> {
    /**
     * Saves an entity to the DynamoDB table with the given table name
     *
     * @param entity    the entity to save
     * @param tableName the suffix for the DynamoDB table name
     * @return the saved entity
     */
    T save(T entity, String tableName);

    /**
     * Saves an entity to the DynamoDB table with the given table name
     *
     * @param entity    the entity to save
     * @param tableName the suffix for the DynamoDB table name
     */
    void saveV2(T entity, String tableName);

    /**
     * Loads an entity from the DynamoDB table with the given table name.
     *
     * @param clazz     the class of the entity to load
     * @param hashKey   the hash key of the entity
     * @param rangeKey  the range key of the entity
     * @param tableName the suffix for the DynamoDB table name
     * @return the loaded entity, or null if not found
     */
    T load(Class<T> clazz, Object hashKey, Object rangeKey, String tableName);

    /**
     * Queries the DynamoDB table by hash key.
     *
     * @param clazz              the class of the entities to query
     * @param partitionKeyObject the partition key object
     * @param tableName          the suffix for the DynamoDB table name
     * @return a list of matching entities
     */
    List<T> queryByHashKey(Class<T> clazz, T partitionKeyObject, String tableName);

}
