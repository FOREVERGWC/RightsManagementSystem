package com.example.system.security;

import com.example.system.security.filter.JwtAuthorizationFilter;
import com.example.system.security.handler.LoginAuthenticationHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] URL_WHITE_LIST = {"/login", "/register", "/logout", "/captcha", "/password", "/image/**"};
    //    @Resource
//    private LoginFailureHandler loginFailureHandler;
    @Resource
    private LoginAuthenticationHandler loginAuthenticationHandler;
//    @Resource
//    private LoginAccessDefineHandler loginAccessDefineHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    /**
//     * 跨域配置
//     *
//     * @return CorsConfigurationSource
//     */
//    CorsConfigurationSource configurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
//        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
//        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
//        corsConfiguration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http //
                .csrf(AbstractHttpConfigurer::disable) // 关闭CSRF
                .sessionManagement((item) -> item.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 基于令牌，无需Session
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(URL_WHITE_LIST).permitAll() // 放行白名单
                        .anyRequest().authenticated()) // 鉴权认证
                .exceptionHandling((item) -> item.authenticationEntryPoint(loginAuthenticationHandler)) // 认证失败处理类
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class) // 令牌过滤器类
                .build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http    // 使用自己自定义的过滤器 去过滤接口请求
//
//                .formLogin((formLogin) ->
//                        // 这里更改SpringSecurity的认证接口地址，这样就默认处理这个接口的登录请求了
//                        formLogin
//                                //　自定义的登录验证成功或失败后的去向
//                                .failureHandler(loginFailureHandler)
//                )
//                // 禁用了 CSRF 保护。
//                .csrf(AbstractHttpConfigurer::disable)
//                // 配置了会话管理策略为 STATELESS（无状态）。在无状态的会话管理策略下，应用程序不会创建或使用 HTTP 会话，每个请求都是独立的，服务器不会在请求之间保留任何状态信息。
//                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeRequests((authorizeRequests) ->
//                        // 这里过滤一些 不需要token的接口地址
//                        authorizeRequests
//                                .requestMatchers("/api/v1/test/getTestInfo").permitAll()
//                                .requestMatchers("/v3/**", "/profile/**", "/swagger-ui.html",
//                                        "/swagger-resources/**",
//                                        "/v2/api-docs",
//                                        "/v3/api-docs",
//                                        "/webjars/**", "/swagger-ui/**", "/v2/**", "/favicon.ico", "/webjars/springfox-swagger-ui/**", "/static/**", "/webjars/**", "/v2/api-docs", "/v2/feign-docs",
//                                        "/swagger-resources/configuration/ui",
//                                        "/test/user",
//                                        "/swagger-resources", "/swagger-resources/configuration/security",
//                                        "/swagger-ui.html", "/webjars/**").permitAll()
//                                .requestMatchers("/login", "/api/v1/user/getImageCode").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .exceptionHandling((exceptionHandling) -> exceptionHandling
//                        .authenticationEntryPoint(loginAuthenticationHandler) // 匿名处理
//                        .accessDeniedHandler(loginAccessDefineHandler)  // 无权限处理
//                )
//                .addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .cors((cors) -> cors.configurationSource(configurationSource()))
//                .headers((headers) -> headers.frameOptions((HeadersConfigurer.FrameOptionsConfig::disable)))
//                .headers((headers) -> headers.frameOptions((HeadersConfigurer.FrameOptionsConfig::sameOrigin)));
//        // 构建过滤链并返回
//        return http.build();
//    }
}
