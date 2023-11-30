package com.mu.module.sys.model.request;

import cc.uncarbon.framework.core.constant.HelioConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cc.uncarbon.framework.core.enums.YesOrNoEnum;
import cc.uncarbon.framework.core.enums.EnabledStatusEnum;


/**
 * 后台管理-分页列表 DTO
 */
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminApiInterfaceListDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "接口名称或标识(关键词)")
    private String name;

    @ApiModelProperty(value = "接口路径(关键词)")
    private String path;

    @ApiModelProperty(value = "请求方法(关键词)")
    private String method;

    @ApiModelProperty(value = "请求参数，包括参数名称、数据类型、是否必需、默认值等信息(关键词)")
    private String requestParameters;

    @ApiModelProperty(value = "返回数据结构，包括返回字段、数据类型、示例值等信息(关键词)")
    private String responseData;

    @ApiModelProperty(value = "接口示例(关键词)")
    private String description;

    @ApiModelProperty(value = "接口所需的权限或角色(关键词)")
    private String permissions;

    @ApiModelProperty(value = "接口版本号(关键词)")
    private String version;

    @ApiModelProperty(value = "时间区间起")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = HelioConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = HelioConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime beginAt;

    @ApiModelProperty(value = "时间区间止")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = HelioConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = HelioConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime endAt;

}
