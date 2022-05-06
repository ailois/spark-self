package com.ailois.java_spark.jobs;

import com.ailois.java_spark.executor.SparkInit;
import com.google.common.collect.ImmutableList;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.apache.spark.sql.catalyst.expressions.GenericRow;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.collection.JavaConverters;

import java.util.*;

import static org.apache.spark.sql.types.DataTypes.*;

public class DataframeMapType {

    static final SparkSession spark = new SparkInit().javaSparkInit();

    public static void main(String[] args) {
        getMapDF().select("key", "value", "sub", "psv").show(false);
    }

    protected static Dataset<Row> getMapDF() {
        Dataset<Row> originDF = createOriginDF();
        String[] columns = originDF.columns();
        StructType schema = originDF.schema();
        MapType mapType = createMapType(StringType, StringType, true);
        schema = schema.add("sub", StringType);
        schema = schema.add("psv", mapType);
        return originDF.map((MapFunction<Row, Row>) row -> {
            List<Object> list = new ArrayList<>(Arrays.asList(row.mkString(",").split(",")));
            String key1 = columns[2].split("_", 2)[1];
            String value1 = row.getString(2);
            String key2 = columns[3].split("_", 2)[1];
            String value2 = row.getString(3);
            list.add("Def");
            Map<String, String> defMap = new HashMap<>();
            defMap.put(key1, value1);
            defMap.put(key2, value2);
            list.add(JavaConverters.mapAsScalaMap(defMap));
            return new GenericRow(list.toArray());
        }, RowEncoder.apply(schema));
    }

    protected static Dataset<Row> createOriginDF() {
        StructType structType = DataTypes.createStructType(
                new StructField[]{
                        createStructField("key", StringType, false),
                        createStructField("value", StringType, false),
                        createStructField("Def_first", StringType, false),
                        createStructField("Def_second", StringType, false),
                });
        List<String> one = new ArrayList<>();
        one.add("id_1");
        one.add("a");
        one.add("");
        one.add("1");
        Row oneRow = RowFactory.create(one.toArray());
        List<String> two = new ArrayList<>();
        two.add("id_2");
        two.add("b");
        two.add("");
        two.add("2");
        Row twoRow = new GenericRow(two.toArray());
        List<Row> rowList = ImmutableList.of(oneRow, twoRow, RowFactory.create("id_3", "c", "1", "3"));
        return spark.createDataFrame(rowList, structType);
    }


}
