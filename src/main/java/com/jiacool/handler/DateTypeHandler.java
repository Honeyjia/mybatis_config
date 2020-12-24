package com.jiacool.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/*
将java中的Date类型转化为数据库中的毫秒类型

1.定义转换类继承类BaseTypeHandler<T>

2.覆盖4个未实现的方法，其中setNonNullParameter为java程序设置数据到数据库的回调方法，
getNullableResult为查询时 mysql的字符串类型转换成 java的Type类型的方法

 */
public class DateTypeHandler extends BaseTypeHandler<Date> {
    //java 类型转化成 数据库 所需要的类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();//转化为long型
        preparedStatement.setLong(i, time);
    }

    //将数据库中的类型 转化成 java所需要的类型
    /*
    resultSet参数:查询出的结果集
    String参数:属性名称
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //获得结果集中的需要的数据（long）
        long aLong = resultSet.getLong(s);
        //转化成Date 类型返回
        Date date = new Date(aLong);
        return date;
    }

    //将数据库中的类型 转化成 java所需要的类型
    // i 参数:字段的位置
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }

    //将数据库中的类型 转化成 java所需要的类型
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
