package com.mu.module.sys.entity;

import cc.uncarbon.framework.crud.entity.HelioBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cc.uncarbon.framework.core.enums.YesOrNoEnum;
import cc.uncarbon.framework.core.enums.EnabledStatusEnum;


/**
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "api_interface")
public class ApiInterfaceEntity extends HelioBaseEntity<Long> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "接口名称或标识")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "接口路径")
    @TableField(value = "path")
    private String path;

    @ApiModelProperty(value = "请求方法")
    @TableField(value = "method")
    private String method;

    @ApiModelProperty(value = "请求参数，包括参数名称、数据类型、是否必需、默认值等信息")
    @TableField(value = "request_parameters")
    private String requestParameters;

    @ApiModelProperty(value = "返回数据结构，包括返回字段、数据类型、示例值等信息")
    @TableField(value = "response_data")
    private String responseData;

    @ApiModelProperty(value = "接口示例")
    @TableField(value = "description")
    private String description;

    @ApiModelProperty(value = "接口所需的权限或角色")
    @TableField(value = "permissions")
    private String permissions;

    @ApiModelProperty(value = "接口版本号")
    @TableField(value = "version")
    private String version;

}
