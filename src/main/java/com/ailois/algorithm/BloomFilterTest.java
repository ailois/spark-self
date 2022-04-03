package com.ailois.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class BloomFilterTest {

    private static final Logger logger = LoggerFactory.getLogger(BloomFilterTest.class);

    public static void main(String[] args) {
        val bloomFilter = BloomFilter.create(Funnels.longFunnel(), 3000000, 0.01);
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put((long) i);
        }
        int count = 0;
        for (int i = 0; i < 1000000; i++) {
            if (!bloomFilter.mightContain((long) i)){
                count++;
                logger.info("{} 误判了！", i);
            }
        }
        logger.info("总共的误判数: {}", count);
    }

}
