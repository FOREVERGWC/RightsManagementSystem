package com.example.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 操作日志记录表
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@TableName("sys_oper_log")
@Schema(name = "OperLog", description = "操作日志记录表")
public class SysOperLog implements Serializable {
    @Serial
    private static final long serialVersionUID = -2933441298685038055L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;
    /**
     * 模块标题
     */
    @Schema(description = "模块标题")
    private String title;
    /**
     * 业务类型(0其它、1新增、2修改、3删除)
     */
    @Schema(description = "业务类型(0其它、1新增、2修改、3删除)")
    private Integer businessType;
    /**
     * 方法名称
     */
    @Schema(description = "方法名称")
    private String method;
    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    private String requestMethod;
    /**
     * 操作类别(0其它、1后台用户、2手机端用户)
     */
    @Schema(description = "操作类别(0其它、1后台用户、2手机端用户)")
    private Integer operatorType;
    /**
     * 操作人员
     */
    @Schema(description = "操作人员")
    private String operName;
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;
    /**
     * 请求URL
     */
    @Schema(description = "请求URL")
    private String operUrl;
    /**
     * 主机地址
     */
    @Schema(description = "主机地址")
    private String operIp;
    /**
     * 操作地点
     */
    @Schema(description = "操作地点")
    private String operLocation;
    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String operParam;
    /**
     * 返回参数
     */
    @Schema(description = "返回参数")
    private String jsonResult;
    /**
     * 操作状态(0正常、1异常)
     */
    @Schema(description = "操作状态(0正常、1异常)")
    private Integer status;
    /**
     * 错误消息
     */
    @Schema(description = "错误消息")
    private String errorMsg;
    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    private Date operTime;
    /**
     * 消耗时间
     */
    @Schema(description = "消耗时间")
    private Long costTime;
}
