package com.mu.module.sys.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cc.uncarbon.framework.core.enums.YesOrNoEnum;
import cc.uncarbon.framework.core.enums.EnabledStatusEnum;


/**
 * 后台管理-新增/编辑 DTO
 */
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminApiInterfaceInsertOrUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
    private Long id;

    @ApiModelProperty(value = "接口名称或标识", required = true)
    @Size(min = 1, max = 255, message = "【接口名称或标识】长度须在 1 至 255 位之间")
    @NotBlank(message = "接口名称或标识不能为空")
    private String name;

    @ApiModelProperty(value = "接口路径", required = true)
    @Size(min = 1, max = 255, message = "【接口路径】长度须在 1 至 255 位之间")
    @NotBlank(message = "接口路径不能为空")
    private String path;

    @ApiModelProperty(value = "请求方法", required = true)
    @Size(min = 1, max = 10, message = "【请求方法】长度须在 1 至 10 位之间")
    @NotBlank(message = "请求方法不能为空")
    private String method;

    @ApiModelProperty(value = "请求参数，包括参数名称、数据类型、是否必需、默认值等信息")
    private String requestParameters;

    @ApiModelProperty(value = "返回数据结构，包括返回字段、数据类型、示例值等信息")
    private String responseData;

    @ApiModelProperty(value = "接口示例")
    private String description;

    @ApiModelProperty(value = "接口所需的权限或角色")
    private String permissions;

    @ApiModelProperty(value = "接口版本号")
    private String version;

}
