package com.mu.module.adminapi.web.sys;

import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cc.uncarbon.framework.web.model.request.IdsDTO;
import cc.uncarbon.framework.web.model.response.ApiResult;
import com.mu.module.adminapi.constant.AdminApiConstant;
import com.mu.module.sys.annotation.SysLog;
import com.mu.module.sys.constant.SysConstant;
import com.mu.module.sys.facade.SysTenantFacade;
import com.mu.module.sys.model.request.AdminInsertSysTenantDTO;
import com.mu.module.sys.model.request.AdminListSysTenantDTO;
import com.mu.module.sys.model.request.AdminUpdateSysTenantDTO;
import com.mu.module.sys.model.response.SysTenantBO;
import com.mu.module.sys.service.SysTenantService;
import com.mu.module.sys.util.AdminStpUtil;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@SaCheckLogin(type = AdminStpUtil.TYPE)
@Slf4j
@Api(value = "系统租户管理接口", tags = {"系统租户管理接口"})
@RequestMapping(value = {
        // 兼容旧的API路由前缀
        SysConstant.SYS_MODULE_CONTEXT_PATH + HelioConstant.Version.HTTP_API_VERSION_V1,
        AdminApiConstant.HTTP_API_URL_PREFIX + "/api/v1"
})
@RestController
public class AdminSysTenantController {

    private static final String PERMISSION_PREFIX = "SysTenant:";

    private final SysTenantService sysTenantService;

    private final SysTenantFacade sysTenantFacade;


    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/sys/tenants")
    public ApiResult<PageResult<SysTenantBO>> list(PageParam pageParam, AdminListSysTenantDTO dto) {
        return ApiResult.data(sysTenantService.adminList(pageParam, dto));
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "详情")
    @GetMapping(value = "/sys/tenants/{id}")
    public ApiResult<SysTenantBO> getById(@PathVariable Long id) {
        return ApiResult.data(sysTenantService.getOneById(id, true));
    }

    @SysLog(value = "新增系统租户")
    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.CREATE)
    @ApiOperation(value = "新增")
    @PostMapping(value = "/sys/tenants")
    public ApiResult<?> insert(@RequestBody @Valid AdminInsertSysTenantDTO dto) {
        sysTenantFacade.adminInsert(dto);

        return ApiResult.success();
    }

    @SysLog(value = "编辑系统租户")
    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.UPDATE)
    @ApiOperation(value = "编辑")
    @PutMapping(value = "/sys/tenants/{id}")
    public ApiResult<?> update(@PathVariable Long id, @RequestBody @Valid AdminUpdateSysTenantDTO dto) {
        dto.setId(id);
        sysTenantService.adminUpdate(dto);

        return ApiResult.success();
    }

    @SysLog(value = "删除系统租户")
    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.DELETE)
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/sys/tenants")
    public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
        sysTenantService.adminDelete(dto.getIds());

        return ApiResult.success();
    }

}
