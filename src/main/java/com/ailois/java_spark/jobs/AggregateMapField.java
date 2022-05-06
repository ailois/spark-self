package com.ailois.java_spark.jobs;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.api.java.function.MapGroupsFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.apache.spark.sql.catalyst.expressions.GenericRow;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.collection.JavaConverters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.spark.sql.types.DataTypes.*;

public class AggregateMapField {
    public static void main(String[] args) {
        StructType structType = getAfterAggSchema();
        Dataset<Row> mapDF = DataframeMapType.getMapDF().select("sub", "psv");
        Dataset<Row> psv = distinctMapWithGroupByKey(mapDF, structType);
        psv.show(false);
    }

    private static StructType getAfterAggSchema() {
        return DataTypes.createStructType(
                new StructField[]{
                        createStructField("key", StringType, false),
                        createStructField("map", DataTypes.createMapType(StringType, IntegerType, false), false),
                });
    }

    private static Dataset<Row> distinctMapWithGroupByKey(Dataset<Row> dataset, StructType structType) {
        return dataset.groupByKey((MapFunction<Row, String>) row -> row.getAs("sub"), Encoders.STRING())
                .mapGroups((MapGroupsFunction<String, Row, Row>) (key, values) -> {
                    List<Object> res = new ArrayList<>();
                    List<Map<String, String>> maps = new ArrayList<>();
                    res.add(key);
                    while (values.hasNext()) {
                        // value can not be null
                        maps.add(JavaConverters.mapAsJavaMap(values.next().getAs("psv")));
                    }
                    Map<String, Integer> collect = maps.stream().map(Map::entrySet)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toMap(Map.Entry::getKey,
                                    x -> "".equals(x.getValue()) ? 0 : Integer.parseInt(x.getValue()),
                                    Integer::sum));
                    res.add(JavaConverters.mapAsScalaMap(collect));
                    return new GenericRow(res.toArray());
                }, RowEncoder.apply(structType));
    }

}
