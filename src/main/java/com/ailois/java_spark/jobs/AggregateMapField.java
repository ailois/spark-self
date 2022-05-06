package com.ailois.java_spark.jobs;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.MapGroupsFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.apache.spark.sql.catalyst.expressions.GenericRow;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.collection.JavaConverters;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.spark.sql.types.DataTypes.*;

public class AggregateMapField {
    public static void main(String[] args) {
        StructType structType = DataTypes.createStructType(
                new StructField[]{
                        createStructField("key", StringType, false),
                        createStructField("map", DataTypes.createMapType(StringType, IntegerType, false), false),
                });
        Dataset<Row> mapDF = DataframeMapType.getMapDF()
                .withColumn("psv", functions.to_json(functions.col("psv")))
                .select("sub", "psv");
        Dataset<Row> psv = mapDF.groupByKey((MapFunction<Row, String>) value -> value.getString(0), Encoders.STRING())
                .mapGroups((MapGroupsFunction<String, Row, Row>) (key, values) -> {
                    List<Object> res = new ArrayList<>();
                    List<HashMap<String, String>> maps = new ArrayList<>();
                    res.add(key);
                    while (values.hasNext()) {
                        // value can not be null
                        maps.add(new Gson().fromJson(values.next().getString(1),
                                new TypeToken<HashMap<String, String>>(){}.getType()));
                    }
                    Map<String, Integer> collect = maps.stream().map(HashMap::entrySet)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toMap(Map.Entry::getKey, x -> Integer.valueOf(x.getValue()), Integer::sum));
                    res.add(JavaConverters.mapAsScalaMap(collect));
                    return new GenericRow(res.toArray());
                }, RowEncoder.apply(structType));
        psv.show(false);
    }

}
