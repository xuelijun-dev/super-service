package com.xue.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerUtil {

    public static void test1() throws URISyntaxException, UnsupportedEncodingException {
        //1. 打开浏览器，创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址，发起get请求创建HttpGet对象
        //案例1
        /*URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        //设置参数
        uriBuilder.setParameter("keys","Java");
        HttpGet httpGet = new HttpGet(uriBuilder.build());*/
        //案例2
        //HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        //案例3
        //HttpPost httpGet = new HttpPost("http://www.itcast.cn/");
        //案例4
        //封装参数对
        HttpPost request = new HttpPost("http://yun.itheima.com/search");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keys","Java"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");
        request.setEntity(formEntity);
        System.out.println("发起请求的信息:"+request);
        //3.按回车，发起请求，返回响应
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            //4.解释响应
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf8");
                System.out.println("获取内容长度"+content.length());
//                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void pooling(PoolingHttpClientConnectionManager cm) throws UnsupportedEncodingException {
        //从连接池中获取client对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpPost request = new HttpPost("http://yun.itheima.com/search");
        RequestConfig config = RequestConfig.custom()
                //请求超时设置
                .setConnectionRequestTimeout(50)
                //创建连接超时设置
                .setConnectTimeout(1000)
                //数据传输超时设置
                .setSocketTimeout(10 * 1000)
                .build();
        request.setConfig(config);
        //参数设置
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keys","Java"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");
        request.setEntity(formEntity);
        System.out.println("发起请求的信息:"+request);
        //3.按回车，发起请求，返回响应
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            //4.解释响应
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf8");
                System.out.println("获取内容长度"+content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) throws Exception {
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//        //最大连接数
//        cm.setMaxTotal(100);
//        //单机最大连接数
//        cm.setDefaultMaxPerRoute(10);
//        test1();
//    }
}


