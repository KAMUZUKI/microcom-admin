package com.mu.module.sys.facade;

import com.mu.module.sys.model.request.AdminInsertSysTenantDTO;

/**
 * 系统租户防腐层，用于解决循环依赖
 *
 * @author Uncarbon
 */
public interface SysTenantFacade {

    /**
     * 后台管理-新增
     */
    Long adminInsert(AdminInsertSysTenantDTO dto);

}
