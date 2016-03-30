package com.scyy.test;

import com.scyy.dao.DBCP;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by LYH on 2016-03-26.
 */
public class DBCPTest {

    @Test
    public void testGetCon() throws Exception {
        DBCP dbcp = new DBCP();
        System.out.println(dbcp.getCon().toString());

    }
}