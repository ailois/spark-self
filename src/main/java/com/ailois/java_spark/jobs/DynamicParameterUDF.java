package com.ailois.java_spark.jobs;

import org.apache.spark.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


public class DynamicParameterUDF {

    private static final Logger logger = LoggerFactory.getLogger(DynamicParameterUDF.class);

    public static void main(String[] args) {
        logger.info("start test dynamic parameter udf...");

        Column test = functions.when(functions.col("key").endsWith("_1"),
                        functions.when(functions.col("value").isInCollection(Arrays.asList("b", "a")),
                                functions.lit("test")).otherwise(functions.lit("test2")))
                .otherwise(functions.lit("test3"));
        logger.info("column log test: {}", test);

        Dataset<Row> originDF = DataframeMapType.createOriginDF();
        originDF = originDF.withColumn("test", test);
        originDF.show(false);
    }

}
