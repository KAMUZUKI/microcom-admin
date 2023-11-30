package com.mu.module.content.model.response;

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
 *  BO
 */
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VlogBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
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

    @ApiModelProperty(value = "")
    private EnabledStatusEnum status;

}
