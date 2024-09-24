package com.miniapp.wechat.wechat_mini_backend.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HttpClientServiceImpl implements HttpClientService, InitializingBean {

    private static final int TIMEOUT = 5 * 1000;

    private CloseableHttpClient httpClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        httpClient = HttpClients.createDefault();
    }

    @Override
    public Map<String, String> get(String url, Map<String, String> paramMap) {
        Map<String, String> resMap = new HashMap<>();
        CloseableHttpResponse response = null;
        try{
            URIBuilder builder = new URIBuilder(url);
            if(paramMap != null){
                for (String key : paramMap.keySet()) {
                    builder.addParameter(key,paramMap.get(key));
                }
            }
            URI uri = builder.build();
            //创建GET请求
            HttpGet httpGet = new HttpGet(uri);
            //发送请求
            response = httpClient.execute(httpGet);
            //判断响应状态
            if(response.getStatusLine().getStatusCode() == 200){
                String resStr = EntityUtils.toString(response.getEntity(),"UTF-8");
                Map<String, Object> tmp = JSONObject.parseObject(resStr, Map.class);
                for(Map.Entry<String, Object> e : tmp.entrySet()) {
                    resMap.put(e.getKey(), String.valueOf(e.getValue()));
                }
                resMap.put("resultString",resStr);
            }
            return resMap;
        }catch (Exception e){
            return resMap;
        }finally {
            try {
                response.close();
            } catch (IOException e) {
            }
        }
    }

    @Override
    public Map<String, String> post(String url, Map<String, String> paramMap) {
        CloseableHttpResponse response = null;
        Map<String, String> resMap = new HashMap<>();
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (paramMap != null) {
                List<NameValuePair> paramList = new ArrayList();
                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            httpPost.setConfig(builderRequestConfig());
            // 执行http请求
            response = httpClient.execute(httpPost);
            String resStr = EntityUtils.toString(response.getEntity(),"UTF-8");
            Map<String, Object> tmp = JSONObject.parseObject(resStr, Map.class);
            for(Map.Entry<String, Object> e : tmp.entrySet()) {
                resMap.put(e.getKey(), String.valueOf(e.getValue()));
            }
            resMap.put("resultString", resStr);
            return resMap;
        } catch (Exception e) {
            return new HashMap<>();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
            }
        }
    }

    private RequestConfig builderRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(TIMEOUT)
                .setSocketTimeout(TIMEOUT).build();
    }
}
