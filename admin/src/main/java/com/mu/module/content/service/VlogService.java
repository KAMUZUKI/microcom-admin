package com.mu.module.content.service;

import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.core.exception.BusinessException;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cc.uncarbon.framework.crud.service.impl.HelioBaseServiceImpl;
import com.mu.module.content.entity.VlogEntity;
import com.mu.module.content.mapper.VlogMapper;
import com.mu.module.content.model.request.AdminVlogInsertOrUpdateDTO;
import com.mu.module.content.model.request.AdminVlogListDTO;
import com.mu.module.content.model.response.VlogBO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * 
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VlogService extends HelioBaseServiceImpl<VlogMapper, VlogEntity> {

    /**
     * 后台管理-分页列表
     */
    public PageResult<VlogBO> adminList(PageParam pageParam, AdminVlogListDTO dto) {
        Page<VlogEntity> entityPage = this.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<VlogEntity>()
                        .lambda()
                        // 
                        .eq(ObjectUtil.isNotNull(dto.getCreateId()), VlogEntity::getCreateId, dto.getCreateId())
                        // 作者名
                        .like(StrUtil.isNotBlank(dto.getAuthor()), VlogEntity::getAuthor, StrUtil.cleanBlank(dto.getAuthor()))
                        // 
                        .like(StrUtil.isNotBlank(dto.getTitle()), VlogEntity::getTitle, StrUtil.cleanBlank(dto.getTitle()))
                        // 
                        .like(StrUtil.isNotBlank(dto.getLabel()), VlogEntity::getLabel, StrUtil.cleanBlank(dto.getLabel()))
                        // 
                        .like(StrUtil.isNotBlank(dto.getText()), VlogEntity::getText, StrUtil.cleanBlank(dto.getText()))
                        // 
                        .like(StrUtil.isNotBlank(dto.getImg()), VlogEntity::getImg, StrUtil.cleanBlank(dto.getImg()))
                        // 
                        .eq(ObjectUtil.isNotNull(dto.getTime()), VlogEntity::getTime, dto.getTime())
                        // 
                        .eq(ObjectUtil.isNotNull(dto.getStatus()), VlogEntity::getStatus, dto.getStatus())
                        // 时间区间
                        .between(ObjectUtil.isNotNull(dto.getBeginAt()) && ObjectUtil.isNotNull(dto.getEndAt()), VlogEntity::getCreatedAt, dto.getBeginAt(), dto.getEndAt())
                        // 排序
                        .orderByDesc(VlogEntity::getCreatedAt)
        );

        return this.entityPage2BOPage(entityPage);
    }

    /**
     * 根据 ID 取详情
     *
     * @param id 主键ID
     * @return null or BO
     */
    public VlogBO getOneById(Long id) {
        return this.getOneById(id, false);
    }

    /**
     * 根据 ID 取详情
     *
     * @param id 主键ID
     * @param throwIfInvalidId 是否在 ID 无效时抛出异常
     * @return null or BO
     */
    public VlogBO getOneById(Long id, boolean throwIfInvalidId) throws BusinessException {
        VlogEntity entity = this.getById(id);
        if (throwIfInvalidId && entity == null) {
            throw new BusinessException(400, "无效ID");
        }

        return this.entity2BO(entity);
    }

    /**
     * 后台管理-新增
     */
    @Transactional(rollbackFor = Exception.class)
    public Long adminInsert(AdminVlogInsertOrUpdateDTO dto) {
        log.info("[后台管理--新增] >> DTO={}", dto);
        this.checkExistence(dto);

        dto.setId(null);
        VlogEntity entity = new VlogEntity();
        BeanUtil.copyProperties(dto, entity);

        this.save(entity);

        return entity.getId();
    }

    /**
     * 后台管理-编辑
     */
    @Transactional(rollbackFor = Exception.class)
    public void adminUpdate(AdminVlogInsertOrUpdateDTO dto) {
        log.info("[后台管理--编辑] >> DTO={}", dto);
        this.checkExistence(dto);

        VlogEntity entity = new VlogEntity();
        BeanUtil.copyProperties(dto, entity);

        this.updateById(entity);
    }

    /**
     * 后台管理-删除
     */
    @Transactional(rollbackFor = Exception.class)
    public void adminDelete(Collection<Long> ids) {
        log.info("[后台管理--删除] >> ids={}", ids);
        this.removeByIds(ids);
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
    private VlogBO entity2BO(VlogEntity entity) {
        if (entity == null) {
            return null;
        }

        VlogBO bo = new VlogBO();
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
    private List<VlogBO> entityList2BOs(List<VlogEntity> entityList) {
        if (CollUtil.isEmpty(entityList)) {
            return Collections.emptyList();
        }

        // 深拷贝
        List<VlogBO> ret = new ArrayList<>(entityList.size());
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
    private PageResult<VlogBO> entityPage2BOPage(Page<VlogEntity> entityPage) {
        return new PageResult<VlogBO>()
            .setCurrent(entityPage.getCurrent())
            .setSize(entityPage.getSize())
            .setTotal(entityPage.getTotal())
            .setRecords(this.entityList2BOs(entityPage.getRecords()))
            ;
    }

    /**
     * 检查是否已存在同名数据
     *
     * @param dto DTO
     */
    private void checkExistence(AdminVlogInsertOrUpdateDTO dto) {
        /*
        可以根据自己业务需要，解禁这段代码，修改判断条件和文案

        VlogEntity existingEntity = this.getOne(
                new QueryWrapper<VlogEntity>()
                        .lambda()
                        .select(VlogEntity::getId)
                        .eq(VlogEntity::getTitle, dto.getTitle())
                        .last(HelioConstant.CRUD.SQL_LIMIT_1)
        );

        if (existingEntity != null && !existingEntity.getId().equals(dto.getId())) {
            throw new BusinessException(400, "已存在相同，请重新输入");
        }
        */
    }

}
