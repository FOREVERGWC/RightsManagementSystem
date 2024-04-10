package com.example.system.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.common.constant.CacheConstant;
import com.example.common.constant.UserConstant;
import com.example.common.utils.RedisUtils;
import com.example.system.domain.model.LoginUser;
import com.example.system.service.ISysTokenService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        return null;
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
}
