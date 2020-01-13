package com.wang.wechat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @Author ful
 * @Description:
 * @Date in 16:11 2018/10/17
 * @Modify By:
 */
public class HttpUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> httpGet(String url) {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        try {
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Map<String, Object> result_map = JSON.parseObject(result);
        return result_map;
    }


    /**
     * http请求工具类，post请求
     *
     * @param url    url
     * @param params 参数值 仅支持String和list两种类型
     * @return
     * @throws Exception
     */
    public static Map<String, Object> httpPost(String url, Map<String, Object> params) {
        DefaultHttpClient defaultHttpClient = null;
        BufferedReader bufferedReader = null;
        try {
            defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            if (params != null) {
                //转换为json格式并打印，不需要的你们可以不要
                String jsonParams = objectMapper.writeValueAsString(params);

                HttpEntity httpEntity = new StringEntity(jsonParams, "utf-8");
                httpPost.setEntity(httpEntity);
            }
            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                String errorLog = "请求失败，errorCode:" + httpResponse.getStatusLine().getStatusCode();


            }
            //读取返回信息
            String output;
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            while ((output = bufferedReader.readLine()) != null) {
                stringBuilder.append(output);
            }
            Map<String, Object> result_map = JSON.parseObject(stringBuilder.toString());
            return result_map;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (defaultHttpClient != null)
                defaultHttpClient.getConnectionManager().shutdown();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * http请求工具类，post请求
     *
     * @param url url
     * @return
     * @throws Exception
     */
    public static Map<String, Object> httpPost2(String url, String jsonString) {
        DefaultHttpClient defaultHttpClient = null;
        BufferedReader bufferedReader = null;
        try {
            defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            HttpEntity httpEntity = new StringEntity(jsonString, "utf-8");
            httpPost.setEntity(httpEntity);
            HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                String errorLog = "请求失败，errorCode:" + httpResponse.getStatusLine().getStatusCode();


            }
            //读取返回信息
            String output;
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            while ((output = bufferedReader.readLine()) != null) {
                stringBuilder.append(output);
            }
            Map<String, Object> result_map = JSON.parseObject(stringBuilder.toString());
            return result_map;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (defaultHttpClient != null)
                defaultHttpClient.getConnectionManager().shutdown();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /**
     * 描述: 发起https请求并获取结果
     *
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outputStr
     *            提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject json = null;
        try {
            URL getUrl = new URL(requestUrl);
            HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
            http.setRequestMethod(requestMethod);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            OutputStream outputStream = http.getOutputStream();
            // 注意编码格式，防止中文乱码
            outputStream.write(outputStr.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] b = new byte[size];
            is.read(b);
            String message = new String(b, "UTF-8");
            json = JSONObject.parseObject(message);
            is.close();
            return json;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
