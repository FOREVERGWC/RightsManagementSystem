package com.example.common.aspect;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.example.common.annotation.Log;
import com.example.common.domain.entity.SysUser;
import com.example.common.domain.model.LoginUser;
import com.example.common.enums.BusinessStatus;
import com.example.common.utils.JsonUtils;
import com.example.common.utils.ServletUtils;
import com.example.common.utils.UserUtils;
import com.example.system.domain.entity.SysOperLog;
import com.example.system.service.ISysOperLogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LogAspect {
    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<>("Cost Time");

    @Resource
    private ISysOperLogService sysOperLogService;

    /**
     * 处理请求前执行
     *
     * @param log 操作日志注解
     */
    @Before(value = "@annotation(log)")
    public void boBefore(Log log) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(log)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log log, Object jsonResult) {
        handleLog(joinPoint, log, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(log)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log log, Exception e) {
        handleLog(joinPoint, log, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
        try {
            SysOperLog sysOperLog = getSysOperLog(joinPoint, controllerLog, e, jsonResult);
            sysOperLogService.recordOper(sysOperLog);
        } catch (Exception exp) {
            log.error("异常信息:{}", exp.getMessage());
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    private SysOperLog getSysOperLog(JoinPoint joinPoint, Log log, Exception e, Object result) {
        LoginUser loginUser = UserUtils.getLoginUser();
        SysOperLog operLog = new SysOperLog();
        operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
        operLog.setOperIp(ServletUtils.getUserIp());
        operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
        if (loginUser != null) {
            operLog.setOperName(loginUser.getUsername());
            SysUser currentUser = loginUser.getUser();
            if (currentUser != null) {
                currentUser.getDeptId();
                // TODO: 2024/4/13 查询部门名称
                operLog.setDeptName("");
            }
        }

        if (e != null) {
            operLog.setStatus(BusinessStatus.FAIL.ordinal());
            operLog.setErrorMsg(e.getMessage());
        }
        // 设置方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        operLog.setMethod(className + "." + methodName + "()");
        operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
        operLog.setBusinessType(log.businessType().ordinal());
        operLog.setTitle(log.title());
        operLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog, log.excludeParamNames());
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData()) {
            // 去除敏感字段
            operLog.setJsonResult(JsonUtils.toJsonStr(result));
        }
        operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
        return operLog;
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog, String[] excludeParamNames) {
        Map<String, String> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
        String requestMethod = operLog.getRequestMethod();
        // 使用数组判断请求方法包含requestMethod
        if (MapUtil.isEmpty(paramsMap) && (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))) {
            String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
            String str = JsonUtils.toJsonStr(joinPoint.getArgs());
            // TODO: 2024/4/13 忽略敏感字段
            operLog.setOperParam(params);
        } else {
            operLog.setOperParam(JSONUtil.toJsonStr(paramsMap));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
        String params = "";
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                if (!isFilterObject(o)) {
                    try {
                        // TODO: 2024/4/13 排除敏感字段
                        String jsonObj = JSONUtil.toJsonStr(o);
                        params += jsonObj + " ";
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.trim();
    }

//    /**
//     * 忽略敏感属性
//     */
//    public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames) {
//        return new PropertyPreExcludeFilter().addExcludes(ArrayUtils.addAll(EXCLUDE_PROPERTIES, excludeParamNames));
//    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof BindingResult;
    }
}
