package com.mu.module.content.model.request;

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
public class AdminVlogInsertOrUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
    private Long id;

    @ApiModelProperty(value = "")
    private Integer createId;

    @ApiModelProperty(value = "作者名")
    private String author;

    @ApiModelProperty(value = "")
    private String title;

    @ApiModelProperty(value = "")
    private String label;

    @ApiModelProperty(value = "")
    private String text;

    @ApiModelProperty(value = "")
    private String img;

    @ApiModelProperty(value = "")
    private LocalDateTime time;

    @ApiModelProperty(value = "", required = true)
    @NotNull(message = "不能为空")
    private EnabledStatusEnum status;

}
