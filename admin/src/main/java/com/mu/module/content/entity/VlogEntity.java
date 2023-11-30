package com.mu.module.content.entity;

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
@TableName(value = "vlog")
public class VlogEntity extends HelioBaseEntity<Long> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "")
    @TableField(value = "create_id")
    private Integer createId;

    @ApiModelProperty(value = "作者名")
    @TableField(value = "author")
    private String author;

    @ApiModelProperty(value = "")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty(value = "")
    @TableField(value = "label")
    private String label;

    @ApiModelProperty(value = "")
    @TableField(value = "text")
    private String text;

    @ApiModelProperty(value = "")
    @TableField(value = "img")
    private String img;

    @ApiModelProperty(value = "")
    @TableField(value = "time")
    private LocalDateTime time;

    @ApiModelProperty(value = "")
    @TableField(value = "status")
    private EnabledStatusEnum status;

}
