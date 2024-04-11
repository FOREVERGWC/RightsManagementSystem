package com.example.system.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.example.common.constant.CacheConstant;
import com.example.common.constant.UserConstant;
import com.example.common.utils.AddressUtils;
import com.example.common.utils.RedisUtils;
import com.example.common.utils.ServletUtils;
import com.example.common.domain.model.LoginUser;
import com.example.system.service.ISysTokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 令牌 服务实现类
 * </p>
 */
@Service
public class SysTokenServiceImpl implements ISysTokenService {
    /**
     * 令牌剩余时间
     */
    private static final Long TOKEN_REMAINDER = 20 * 60 * 1000L;
    /**
     * 令牌自定义标识
     */
    @Value("${token.header}")
    private String header;
    /**
     * 令牌秘钥
     */
    @Value("${token.secret}")
    private String SECRET;
    /**
     * 发行人
     */
    @Value("${token.iss}")
    private String ISSUER;
    /**
     * 主题
     */
    @Value("${token.sub}")
    private String SUBJECT;
    /**
     * 令牌有效期
     */
    @Value("${token.ttl}")
    private Integer TTL;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public String createToken(LoginUser loginUser) {
        String uuid = UUID.fastUUID().toString();
        loginUser.setToken(uuid);
        setUserAgent(loginUser);
        refreshToken(loginUser);
        DateTime now = DateTime.now();
        Map<String, Object> map = new HashMap<>();
        map.put(JWTPayload.ISSUER, ISSUER); // 发行人
        map.put(JWTPayload.SUBJECT, SUBJECT); // 主题
        map.put(JWTPayload.AUDIENCE, loginUser.getUsername()); // 用户
        map.put(JWTPayload.ISSUED_AT, now); // 签发时间
        map.put(JWTPayload.NOT_BEFORE, now); // 生效时间
        map.put(JWTPayload.EXPIRES_AT, now.offsetNew(DateField.MINUTE, TTL)); // 过期时间
        map.put(UserConstant.JWT_UUID, uuid);
        redisUtils.setCacheObject(getTokenKey(uuid), loginUser, TTL, TimeUnit.MINUTES);
        return JWTUtil.createToken(map, SECRET.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getAuthorization(request);
        if (StrUtil.isNotBlank(token)) {
            JWT jwt = JWTUtil.parseToken(token);
            String uuid = (String) jwt.getPayload(UserConstant.JWT_UUID);
            String key = getTokenKey(uuid);
            return redisUtils.getCacheObject(key);
        }
        return null;
    }

    @Override
    public void verifyToken(LoginUser user) {

    }

    @Override
    public void delLoginUser(String token) {

    }

    /**
     * 获取Redis中登录用户键值
     *
     * @param uuid 唯一标识
     * @return 键值
     */
    private String getTokenKey(String uuid) {
        return CacheConstant.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 获取请求令牌
     *
     * @param request 请求对象
     * @return 令牌
     */
    private String getAuthorization(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StrUtil.isNotBlank(token) && token.startsWith(UserConstant.TOKEN_PREFIX)) {
            token = token.replace(UserConstant.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    private void setUserAgent(LoginUser loginUser) {
        UserAgent ua = ServletUtils.getUserAgent();
        String ip = ServletUtils.getUserIp();
        loginUser.setLoginIp(ip) //
                .setLoginLocation(AddressUtils.getRealAddressByIP(ip)) //
                .setBrowser(ua.getBrowser().getName()) //
                .setOs(ua.getOs().getName());
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    private void refreshToken(LoginUser loginUser) {
        long loginTime = System.currentTimeMillis();
        loginUser.setLoginTime(loginTime);
        loginUser.setExpireTime(loginTime + TTL * 60 * 1000);
        redisUtils.setCacheObject(getTokenKey(loginUser.getToken()), loginUser, TTL, TimeUnit.MINUTES);
    }
}
