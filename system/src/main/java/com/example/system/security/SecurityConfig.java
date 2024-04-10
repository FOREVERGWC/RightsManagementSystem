package com.example.system.security;

import com.example.system.security.filter.CheckTokenFilter;
import com.example.system.security.handler.LoginAccessDefineHandler;
import com.example.system.security.handler.LoginAuthenticationHandler;
import com.example.system.security.handler.LoginFailureHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private CheckTokenFilter checkTokenFilter;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private LoginAuthenticationHandler loginAuthenticationHandler;
    @Resource
    private LoginAccessDefineHandler loginAccessDefineHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 跨域配置
     *
     * @return CorsConfigurationSource
     */
    CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("启动鉴权模块");
        http    // 使用自己自定义的过滤器 去过滤接口请求

                .formLogin((formLogin) ->
                        // 这里更改SpringSecurity的认证接口地址，这样就默认处理这个接口的登录请求了
                        formLogin
                                //　自定义的登录验证成功或失败后的去向
                                .failureHandler(loginFailureHandler)
                )
                // 禁用了 CSRF 保护。
                .csrf(AbstractHttpConfigurer::disable)
                // 配置了会话管理策略为 STATELESS（无状态）。在无状态的会话管理策略下，应用程序不会创建或使用 HTTP 会话，每个请求都是独立的，服务器不会在请求之间保留任何状态信息。
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests((authorizeRequests) ->
                        // 这里过滤一些 不需要token的接口地址
                        authorizeRequests
                                .requestMatchers("/api/v1/test/getTestInfo").permitAll()
                                .requestMatchers("/v3/**", "/profile/**", "/swagger-ui.html",
                                        "/swagger-resources/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/webjars/**", "/swagger-ui/**", "/v2/**", "/favicon.ico", "/webjars/springfox-swagger-ui/**", "/static/**", "/webjars/**", "/v2/api-docs", "/v2/feign-docs",
                                        "/swagger-resources/configuration/ui",
                                        "/test/user",
                                        "/swagger-resources", "/swagger-resources/configuration/security",
                                        "/swagger-ui.html", "/webjars/**").permitAll()
                                .requestMatchers("/login", "/api/v1/user/getImageCode").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .authenticationEntryPoint(loginAuthenticationHandler) // 匿名处理
                        .accessDeniedHandler(loginAccessDefineHandler)  // 无权限处理
                )
                .addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .cors((cors) -> cors.configurationSource(configurationSource()))
                .headers((headers) -> headers.frameOptions((HeadersConfigurer.FrameOptionsConfig::disable)))
                .headers((headers) -> headers.frameOptions((HeadersConfigurer.FrameOptionsConfig::sameOrigin)));
        // 构建过滤链并返回
        return http.build();
    }
}
