package com.alibou.security.inventory.service;

public interface SnowFlakeIdGenerator {
    long generateId();

    long extractEpochSecondsFromSnowflakeId(long snowflakeId);

    long getThresholdIdForSecondsAgo(long seconds);
}
