package dao;

import utils.JdbcUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {

    public Integer executeUpdate(String sql, Object... objs) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objs.length; i++) {
            preparedStatement.setObject(i + 1, objs[i]);
        }

        int row = preparedStatement.executeUpdate();

        preparedStatement.close();
        if(connection.getAutoCommit()) {
            JdbcUtil.release();
        }

        return row;
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... objs) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objs.length; i++) {
            preparedStatement.setObject(i + 1, objs[i]);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = preparedStatement.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<T> list = new ArrayList<>();

        while (resultSet.next()) {
            T t = clazz.getConstructor().newInstance();
            for (int i = 1; i <= columnCount; i++) {
                // Le query devono esporre alias compatibili con i nomi dei campi Java.
                String fieldName = metaData.getColumnLabel(i);
                Object value = resultSet.getObject(fieldName);

                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(t, value);
            }
            list.add(t);
        }

        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()) {
            JdbcUtil.release();
        }

        return list;

    }

    public <T> T executeQueryBean(Class<T> clazz, String sql, Object... objs) throws Exception {
        List<T> list = executeQuery(clazz, sql, objs);
        return list.isEmpty() ? null : list.get(0);
    }
}
