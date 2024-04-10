package com.example.common.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressUtils {
    private static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip={}&json=true";
    private static final String UNKNOWN = "XX XX XX";

    public static String getRealAddressByIP(String ip) {
        if (NetUtil.isInnerIP(ip)) {
            return "内网IP";
        }
        try (HttpResponse response = HttpUtil.createGet(StrUtil.format(IP_URL, ip)).execute()) {
            String body = response.body();
            if (StrUtil.isBlank(body)) {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            JSONObject object = JSONUtil.parseObj(body);
            String region = object.getStr("pro");
            String city = object.getStr("city");
            String address = object.getStr("addr");
            return StrUtil.format("%s %s %s", region, city, address);
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", ip);
        }
        return UNKNOWN;
    }
}
