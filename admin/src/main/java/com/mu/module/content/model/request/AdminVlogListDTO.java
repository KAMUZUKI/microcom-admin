package com.mu.module.content.model.request;

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
public class AdminVlogListDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "")
    private Integer createId;

    @ApiModelProperty(value = "作者名(关键词)")
    private String author;

    @ApiModelProperty(value = "(关键词)")
    private String title;

    @ApiModelProperty(value = "(关键词)")
    private String label;

    @ApiModelProperty(value = "(关键词)")
    private String text;

    @ApiModelProperty(value = "(关键词)")
    private String img;

    @ApiModelProperty(value = "")
    private LocalDateTime time;

    @ApiModelProperty(value = "")
    private EnabledStatusEnum status;

    @ApiModelProperty(value = "时间区间起")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = HelioConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = HelioConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime beginAt;

    @ApiModelProperty(value = "时间区间止")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = HelioConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = HelioConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime endAt;

}
