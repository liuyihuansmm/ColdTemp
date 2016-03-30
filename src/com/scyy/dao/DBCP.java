package com.scyy.dao;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by LYH on 2016-03-26.
 * 说明：DBCP框架下的连接池
 */
public class DBCP {

    private static DataSource DS;
    private static final String configFile= "/com/scyy/dao/dbcp.properties";

    public DBCP(){
        initDbcp();
    }

    private static void initDbcp(){
        Properties pop = new Properties();
        try {
            pop.load(Object.class.getResourceAsStream(configFile));
            DS = BasicDataSourceFactory.createDataSource(pop);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getCon(){
        Connection con = null;
        if(DS != null){
            try {
                con = DS.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                con.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return con;
        }

        return con;
    }
}
