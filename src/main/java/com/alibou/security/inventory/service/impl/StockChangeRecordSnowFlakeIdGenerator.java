package com.alibou.security.inventory.service.impl;

import com.alibou.security.inventory.service.SnowFlakeIdGenerator;
import org.springframework.stereotype.Component;

@Component(value = "stockChangeRecordSnowFlakeIdGenerator")
public class StockChangeRecordSnowFlakeIdGenerator implements SnowFlakeIdGenerator {

    private static final long OFFSET_EPOCH = 1728834116000L;
    private static final long SEQUENCE_BITS = 19L;
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    @Override
    public synchronized long generateId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }
        if (timestamp == lastTimestamp) {
            sequence++;
            long seq = sequence & SEQUENCE_MASK;
            if (seq == 0) {
                timestamp = waitForNextMillis(lastTimestamp);
                sequence = 0;
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        return ((timestamp - OFFSET_EPOCH) << SEQUENCE_BITS)
                | sequence;
    }

    @Override
    public long extractEpochSecondsFromSnowflakeId(long snowflakeId) {
        long timestampPart = snowflakeId >> SEQUENCE_BITS;
        long actualTimestamp = timestampPart + OFFSET_EPOCH;
        return actualTimestamp / 1000;
    }

    @Override
    public long getThresholdIdForSecondsAgo(long seconds) {
        long sixMonthsAgoTimestamp = System.currentTimeMillis() - (seconds * 1000);
        long adjustedTimestamp = sixMonthsAgoTimestamp - OFFSET_EPOCH;
        return adjustedTimestamp << SEQUENCE_BITS;
    }

    private long waitForNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for next millisecond", e);
            }
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
