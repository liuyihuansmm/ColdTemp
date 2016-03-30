package com.scyy.dao;

import com.scyy.beans.Tempa;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.SQLException;

import java.util.List;

/**
 * Created by LYH on 2016-03-28.
 */
public class TempDAO {

    private Connection con;
    private PreparedStatement ptst;

    public TempDAO() {
        init();
    }

    private void init() {
        DBCP dbcp = new DBCP();
        con = dbcp.getCon();
    }

    public void insertTempa(List<Tempa> tempaLists) {

        String sql = "insert into gps_gsp_test(gps_time,temp1,temp2,vehicle_number,entry_time) values(?,?,?,?,?)";
        try {
            /**
             * 这里不能con.prepareStatement(sqlStr,Statement.RETURN_GENERATED_KEYS)赋值
             * Statement.RETURN_GENERATED_KEYS是获取最大主键值，若数据库表没有主键，批量处理会有问题！
             */
            ptst = con.prepareStatement(sql);
            for(Tempa temp : tempaLists) {
                ptst.setTimestamp(1,new Timestamp(temp.getCollectTime().getTime()));
                ptst.setDouble(2,temp.getTemp1());
                ptst.setDouble(3,temp.getTemp2());
                ptst.setString(4,temp.getCarNo());
                ptst.setTimestamp(5,new Timestamp(temp.getEntryTime().getTime()));
                ptst.addBatch();
               // ptst.executeUpdate();
            }
            ptst.executeBatch();
            con.commit();
            ptst.clearBatch();
            System.out.println("数据插入成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
                System.out.println("数据插入失败！");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
}