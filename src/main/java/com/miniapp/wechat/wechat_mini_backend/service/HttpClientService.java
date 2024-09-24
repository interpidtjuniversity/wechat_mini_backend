package com.miniapp.wechat.wechat_mini_backend.service;

import java.util.Map;

public interface HttpClientService {

    Map<String, String> get(String url,Map<String,String> paramMap);

    Map<String, String> post(String url,Map<String,String> paramMap);
}
