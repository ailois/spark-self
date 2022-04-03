package com.ailois.joinery;

import com.ailois.other.HumpNamedUtils;
import joinery.DataFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class TestJoinery {

    private static final Logger logger = LoggerFactory.getLogger(TestJoinery.class);

    public static void main(String[] args) throws IOException {
        String path = "D:\\IdeaProjects\\MyProject1\\src\\main\\resources\\2021_2_2013_48_25.csv";//NOSONAR
        DataFrame<Object> data = DataFrame.readCsv(path);
        logger.info("{}", data.size());
        List<Users> users = transformDataFrameToPojo(Users.class, data);
        DataFrame<Object> lists = transformPojoToDataFrame(Users.class, users);
        logger.info("{}", lists);
    }

    private static <T> DataFrame<Object> transformPojoToDataFrame(Class<T> t, List<T> list) {
        List<String> columns = Arrays.stream(t.getDeclaredFields()).map(x -> HumpNamedUtils.hump2LowerColumnName(x.getName())).collect(Collectors.toList());
        DataFrame<Object> dataFrame = new DataFrame<>(columns);
        for (T u : list) {
            List<Object> row = new ArrayList<>();
            for (String col : columns) {
                try {
                    Field field = t.getDeclaredField(HumpNamedUtils.column2LowerHumpName(col));
                    Object o = field.get(u);
                    Type type = field.getAnnotatedType().getType();
                    Object cast;
                    try {
                        cast = Class.forName(type.getTypeName()).cast(o);
                        row.add(cast);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            dataFrame.append(row);
        }
        return dataFrame;
    }


    private static <T> List<T> transformDataFrameToPojo(Class<T> t, DataFrame<Object> data) {
        List<T> results = new ArrayList<>();
        try {
            for (int i = 0; i < data.size(); i++) {
                T o = t.newInstance();
                Field[] declaredFields = t.getDeclaredFields();
                int finalI = i;
                Arrays.stream(declaredFields).forEach(x -> {
                    x.setAccessible(true);
                    Object value = data.get(finalI, HumpNamedUtils.hump2LowerColumnName(x.getName()));
                    Type type = x.getAnnotatedType().getType();
                    Object cast;
                    try {
                        cast = Class.forName(type.getTypeName()).cast(value);
                        x.set(o, cast);
                    } catch (ClassNotFoundException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
                results.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}
