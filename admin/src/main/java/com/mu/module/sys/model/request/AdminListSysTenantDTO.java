package com.mu.module.sys.model.request;

import cc.uncarbon.framework.core.enums.EnabledStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 后台管理-分页列表系统租户
 * @author Uncarbon
 */
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminListSysTenantDTO implements Serializable {

    @ApiModelProperty(value = "租户名(关键词)")
    private String tenantName;

    @ApiModelProperty(value = "租户ID(纯数字)")
    private Long tenantId;

    @ApiModelProperty(value = "状态")
    private EnabledStatusEnum status;

}
