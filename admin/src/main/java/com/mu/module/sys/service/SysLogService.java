package com.mu.module.sys.service;

import cc.uncarbon.framework.core.exception.BusinessException;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cc.uncarbon.framework.crud.service.impl.HelioBaseServiceImpl;
import com.mu.module.sys.entity.SysLogEntity;
import com.mu.module.sys.enums.SysErrorEnum;
import com.mu.module.sys.mapper.SysLogMapper;
import com.mu.module.sys.model.request.AdminInsertSysLogDTO;
import com.mu.module.sys.model.request.AdminListSysLogDTO;
import com.mu.module.sys.model.response.SysLogBO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 系统日志
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysLogService extends HelioBaseServiceImpl<SysLogMapper, SysLogEntity> {

    /**
     * 后台管理-分页列表
     */
    public PageResult<SysLogBO> adminList(PageParam pageParam, AdminListSysLogDTO dto) {
        Page<SysLogEntity> entityPage = this.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<SysLogEntity>()
                        .lambda()
                        // 仅返回给前端少量字段
                        .select(SysLogEntity::getCreatedAt, SysLogEntity::getUsername, SysLogEntity::getOperation,
                                SysLogEntity::getIp, SysLogEntity::getStatus, SysLogEntity::getUserAgent,
                                SysLogEntity::getIpLocationRegionName, SysLogEntity::getIpLocationProvinceName,
                                SysLogEntity::getIpLocationCityName, SysLogEntity::getIpLocationDistrictName)
                        // 用户账号
                        .like(StrUtil.isNotBlank(dto.getUsername()), SysLogEntity::getUsername, StrUtil.cleanBlank(dto.getUsername()))
                        // 操作内容
                        .like(StrUtil.isNotBlank(dto.getOperation()), SysLogEntity::getOperation, StrUtil.cleanBlank(dto.getOperation()))
                        // 状态
                        .eq(ObjectUtil.isNotNull(dto.getStatus()), SysLogEntity::getStatus, dto.getStatus())
                        // 时间区间
                        .between(ObjectUtil.isNotNull(dto.getBeginAt()) && ObjectUtil.isNotNull(dto.getEndAt()), SysLogEntity::getCreatedAt, dto.getBeginAt(), dto.getEndAt())
                        // 排序
                        .orderByDesc(SysLogEntity::getCreatedAt)
        );

        return this.entityPage2BOPage(entityPage);
    }

    /**
     * 根据 ID 取详情
     *
     * @param id 主键ID
     * @return null or BO
     */
    public SysLogBO getOneById(Long id) {
       return this.getOneById(id, false);
    }

    /**
     * 根据 ID 取详情
     *
     * @param id 主键ID
     * @param throwIfInvalidId 是否在 ID 无效时抛出异常
     * @return null or BO
     */
    public SysLogBO getOneById(Long id, boolean throwIfInvalidId) throws BusinessException {
        SysLogEntity entity = this.getById(id);
        if (throwIfInvalidId) {
            SysErrorEnum.INVALID_ID.assertNotNull(entity);
        }

        return this.entity2BO(entity);
    }

    /**
     * 后台管理-新增
     */
    @Transactional(rollbackFor = Exception.class)
    public Long adminInsert(AdminInsertSysLogDTO dto) {
        log.info("[后台管理-新增操作日志] >> 入参={}", dto);

        SysLogEntity entity = new SysLogEntity();
        BeanUtil.copyProperties(dto, entity);

        int USER_AGENT_MAX_LENGTH = 255;
        if (StrUtil.length(entity.getUserAgent()) > USER_AGENT_MAX_LENGTH) {
            // 超长度截断
            entity.setUserAgent(
                    StrUtil.subPre(entity.getUserAgent(), USER_AGENT_MAX_LENGTH)
            );
        }

        this.save(entity);

        return entity.getId();
    }

    /*
    ----------------------------------------------------------------
                        私有方法 private methods
    ----------------------------------------------------------------
     */

    /**
     * 实体转 BO
     *
     * @param entity 实体
     * @return BO
     */
    private SysLogBO entity2BO(SysLogEntity entity) {
        if (entity == null) {
            return null;
        }

        SysLogBO bo = new SysLogBO();
        BeanUtil.copyProperties(entity, bo);

        // 可以在此处为BO填充字段

        return bo;
    }

    /**
     * 实体 List 转 BO List
     *
     * @param entityList 实体 List
     * @return BO List
     */
    private List<SysLogBO> entityList2BOs(List<SysLogEntity> entityList) {
        if (CollUtil.isEmpty(entityList)) {
            return Collections.emptyList();
        }

        // 深拷贝
        List<SysLogBO> ret = new ArrayList<>(entityList.size());
        entityList.forEach(
                entity -> ret.add(this.entity2BO(entity))
        );

        return ret;
    }

    /**
     * 实体分页转 BO 分页
     *
     * @param entityPage 实体分页
     * @return BO 分页
     */
    private PageResult<SysLogBO> entityPage2BOPage(Page<SysLogEntity> entityPage) {
        return new PageResult<SysLogBO>()
                .setCurrent(entityPage.getCurrent())
                .setSize(entityPage.getSize())
                .setTotal(entityPage.getTotal())
                .setRecords(this.entityList2BOs(entityPage.getRecords()))
                ;
    }

}
