package com.ailois.java_spark.jobs;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.spark.sql.types.DataTypes.StringType;
import static org.apache.spark.sql.types.DataTypes.createStructField;

public class TestForeachSchema {

    private static final Logger logger = LoggerFactory.getLogger(TestForeachSchema.class);

    private static final StructType STRUCT_TYPE = DataTypes.createStructType(
            new StructField[]{
                    createStructField("key", StringType, false),
                    createStructField("value", StringType, false),
                    createStructField("Def_first", StringType, false),
                    createStructField("Def_second", StringType, false),
            });

    public static void main(String[] args) {
        List<StructField> collect = Arrays.stream(STRUCT_TYPE.fields()).collect(Collectors.toList());
        collect.forEach(x -> logger.info("field name: {}, field type: {}", x.name(), x.dataType()));
    }

}
