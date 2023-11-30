package com.mu.module.sys.service;

import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.core.exception.BusinessException;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cc.uncarbon.framework.crud.service.impl.HelioBaseServiceImpl;
import com.mu.module.sys.entity.ApiInterfaceEntity;
import com.mu.module.sys.mapper.ApiInterfaceMapper;
import com.mu.module.sys.model.request.AdminApiInterfaceInsertOrUpdateDTO;
import com.mu.module.sys.model.request.AdminApiInterfaceListDTO;
import com.mu.module.sys.model.response.ApiInterfaceBO;
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
public class ApiInterfaceService extends HelioBaseServiceImpl<ApiInterfaceMapper, ApiInterfaceEntity> {

    /**
     * 后台管理-分页列表
     */
    public PageResult<ApiInterfaceBO> adminList(PageParam pageParam, AdminApiInterfaceListDTO dto) {
        Page<ApiInterfaceEntity> entityPage = this.page(
                new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
                new QueryWrapper<ApiInterfaceEntity>()
                        .lambda()
                        // 接口名称或标识
                        .like(StrUtil.isNotBlank(dto.getName()), ApiInterfaceEntity::getName, StrUtil.cleanBlank(dto.getName()))
                        // 接口路径
                        .like(StrUtil.isNotBlank(dto.getPath()), ApiInterfaceEntity::getPath, StrUtil.cleanBlank(dto.getPath()))
                        // 请求方法
                        .like(StrUtil.isNotBlank(dto.getMethod()), ApiInterfaceEntity::getMethod, StrUtil.cleanBlank(dto.getMethod()))
                        // 请求参数，包括参数名称、数据类型、是否必需、默认值等信息
                        .like(StrUtil.isNotBlank(dto.getRequestParameters()), ApiInterfaceEntity::getRequestParameters, StrUtil.cleanBlank(dto.getRequestParameters()))
                        // 返回数据结构，包括返回字段、数据类型、示例值等信息
                        .like(StrUtil.isNotBlank(dto.getResponseData()), ApiInterfaceEntity::getResponseData, StrUtil.cleanBlank(dto.getResponseData()))
                        // 接口示例
                        .like(StrUtil.isNotBlank(dto.getDescription()), ApiInterfaceEntity::getDescription, StrUtil.cleanBlank(dto.getDescription()))
                        // 接口所需的权限或角色
                        .like(StrUtil.isNotBlank(dto.getPermissions()), ApiInterfaceEntity::getPermissions, StrUtil.cleanBlank(dto.getPermissions()))
                        // 接口版本号
                        .like(StrUtil.isNotBlank(dto.getVersion()), ApiInterfaceEntity::getVersion, StrUtil.cleanBlank(dto.getVersion()))
                        // 时间区间
                        .between(ObjectUtil.isNotNull(dto.getBeginAt()) && ObjectUtil.isNotNull(dto.getEndAt()), ApiInterfaceEntity::getCreatedAt, dto.getBeginAt(), dto.getEndAt())
                        // 排序
                        .orderByDesc(ApiInterfaceEntity::getCreatedAt)
        );

        return this.entityPage2BOPage(entityPage);
    }

    /**
     * 根据 ID 取详情
     *
     * @param id 主键ID
     * @return null or BO
     */
    public ApiInterfaceBO getOneById(Long id) {
        return this.getOneById(id, false);
    }

    /**
     * 根据 ID 取详情
     *
     * @param id 主键ID
     * @param throwIfInvalidId 是否在 ID 无效时抛出异常
     * @return null or BO
     */
    public ApiInterfaceBO getOneById(Long id, boolean throwIfInvalidId) throws BusinessException {
        ApiInterfaceEntity entity = this.getById(id);
        if (throwIfInvalidId && entity == null) {
            throw new BusinessException(400, "无效ID");
        }

        return this.entity2BO(entity);
    }

    /**
     * 后台管理-新增
     */
    @Transactional(rollbackFor = Exception.class)
    public Long adminInsert(AdminApiInterfaceInsertOrUpdateDTO dto) {
        log.info("[后台管理--新增] >> DTO={}", dto);
        this.checkExistence(dto);

        dto.setId(null);
        ApiInterfaceEntity entity = new ApiInterfaceEntity();
        BeanUtil.copyProperties(dto, entity);

        this.save(entity);

        return entity.getId();
    }

    /**
     * 后台管理-编辑
     */
    @Transactional(rollbackFor = Exception.class)
    public void adminUpdate(AdminApiInterfaceInsertOrUpdateDTO dto) {
        log.info("[后台管理--编辑] >> DTO={}", dto);
        this.checkExistence(dto);

        ApiInterfaceEntity entity = new ApiInterfaceEntity();
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
    private ApiInterfaceBO entity2BO(ApiInterfaceEntity entity) {
        if (entity == null) {
            return null;
        }

        ApiInterfaceBO bo = new ApiInterfaceBO();
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
    private List<ApiInterfaceBO> entityList2BOs(List<ApiInterfaceEntity> entityList) {
        if (CollUtil.isEmpty(entityList)) {
            return Collections.emptyList();
        }

        // 深拷贝
        List<ApiInterfaceBO> ret = new ArrayList<>(entityList.size());
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
    private PageResult<ApiInterfaceBO> entityPage2BOPage(Page<ApiInterfaceEntity> entityPage) {
        return new PageResult<ApiInterfaceBO>()
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
    private void checkExistence(AdminApiInterfaceInsertOrUpdateDTO dto) {
        /*
        可以根据自己业务需要，解禁这段代码，修改判断条件和文案

        ApiInterfaceEntity existingEntity = this.getOne(
                new QueryWrapper<ApiInterfaceEntity>()
                        .lambda()
                        .select(ApiInterfaceEntity::getId)
                        .eq(ApiInterfaceEntity::getTitle, dto.getTitle())
                        .last(HelioConstant.CRUD.SQL_LIMIT_1)
        );

        if (existingEntity != null && !existingEntity.getId().equals(dto.getId())) {
            throw new BusinessException(400, "已存在相同，请重新输入");
        }
        */
    }

}
