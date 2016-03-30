package com.scyy.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by LYH on 2016-03-24.
 * 说明：工具类，封装了get post方法
 */
public class HttpUtil {

    public static final String URL_TOKEN = "http://115.29.169.221:8090/auth/sy";
    public static final String URL_TEMP = "http://115.29.169.221:8090/data/";


    public static String post(String url,List<NameValuePair> param) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(param));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponse response;
        String result = null;
        try {
            response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result;
    }

    public static String get(String url,String param) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = null;
        if (param != null) {
            httpGet = new HttpGet(url + param);
        }
        HttpResponse response;
        String result = null;
        try {
           response = client.execute(httpGet);
           result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
