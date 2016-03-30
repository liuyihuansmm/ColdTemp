package com.scyy.test;

import com.scyy.beans.Tempa;
import com.scyy.dao.TempDAO;
import com.scyy.utils.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by LYH on 2016-03-25.
 */
public class StartRunTest {

    @Test
    public void testMain() throws Exception {


        //先获取token
        List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        postParams.add(new BasicNameValuePair("pwd", "pwd"));
        String token = HttpUtil.post(HttpUtil.URL_TOKEN, postParams);
        System.out.println(token);

        //请求温度
        ArrayList<String> carList = new ArrayList<String>();
        carList.add("川A1S1V5");
        carList.add("川A5R1G1");
        carList.add("川A6795Q");
        carList.add("川A5527Q");
        JSONArray resultToJA;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        /**
         * 这里实际上才是真正调度的东西
         */
        for(String car : carList) {
            /**
             * 构造好get请求的参数getParams
             */
            StringBuilder getParams = new StringBuilder();
            Calendar calendarNow = Calendar.getInstance();
            getParams.append(car + "/");
            getParams.append(String.valueOf(calendarNow.get(Calendar.YEAR)) + "/");
            //Calendar.MONTH是从0-11算月份的，所以这里加1
            getParams.append(String.valueOf(calendarNow.get(Calendar.MONTH) + 1) + "/");
            getParams.append(String.valueOf(calendarNow.get(Calendar.DATE)) + "/");
            //由于GPS上传不是有延迟，所以这里小时减1
            getParams.append(String.valueOf(calendarNow.get(Calendar.HOUR_OF_DAY)-2) + "/");
            getParams.append(token);

            /**
             * get返回后的结果转成JSONArray
             */
            String result = HttpUtil.get(HttpUtil.URL_TEMP, getParams.toString());
            resultToJA = JSONArray.fromObject(result);
            System.out.println(car+":   "+resultToJA);

            /**
             * 获取车某小时内的传回数据

            List<Tempa>  tempaList = new ArrayList<Tempa>();
            //根据传回数据大小：有数据说明发车了，才进行数据库交互；没发车的不用.
            if(resultToJA.size()>0) {
                for (int k = 0; k < resultToJA.size(); k++) {
                    JSONObject jsonObject = resultToJA.getJSONObject(k);
                    Tempa tempa = new Tempa();
                    tempa.setCarNo(car);
                    tempa.setCollectTime(dateFormat.parse(jsonObject.getString("collectTime")));
                    tempa.setEntryTime(dateFormat.parse(jsonObject.getString("entryTime")));
                    tempa.setTemp1(jsonObject.getDouble("temp1"));
                    tempa.setTemp2(jsonObject.getDouble("temp2"));
                    tempaList.add(tempa);
                }
                TempDAO tempDAO = new TempDAO();
                tempDAO.insertTempa(tempaList);
            }
             */
        }

    }

    @Test
    public  void Show(){
        Calendar now = Calendar.getInstance();
        System.out.println(now.get(Calendar.YEAR));
        System.out.println(now.get(Calendar.MONTH)+1);
        System.out.println(now.get(Calendar.DATE));
        System.out.println(now.get(Calendar.HOUR_OF_DAY));
    }

}