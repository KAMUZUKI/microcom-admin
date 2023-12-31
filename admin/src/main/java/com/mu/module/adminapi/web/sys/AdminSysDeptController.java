package com.mu.module.adminapi.web.sys;


import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.web.model.request.IdsDTO;
import cc.uncarbon.framework.web.model.response.ApiResult;
import com.mu.module.adminapi.constant.AdminApiConstant;
import com.mu.module.sys.annotation.SysLog;
import com.mu.module.sys.constant.SysConstant;
import com.mu.module.sys.model.request.AdminInsertOrUpdateSysDeptDTO;
import com.mu.module.sys.model.response.SysDeptBO;
import com.mu.module.sys.service.SysDeptService;
import com.mu.module.sys.util.AdminStpUtil;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@SaCheckLogin(type = AdminStpUtil.TYPE)
@Slf4j
@Api(value = "部门管理接口", tags = {"部门管理接口"})
@RequestMapping(value = {
        // 兼容旧的API路由前缀
        SysConstant.SYS_MODULE_CONTEXT_PATH + HelioConstant.Version.HTTP_API_VERSION_V1,
        AdminApiConstant.HTTP_API_URL_PREFIX + "/api/v1"
})
@RestController
public class AdminSysDeptController {

    private static final String PERMISSION_PREFIX = "SysDept:";

    private final SysDeptService sysDeptService;


    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "列表")
    @GetMapping(value = "/sys/depts")
    public ApiResult<List<SysDeptBO>> list() {
        return ApiResult.data(sysDeptService.adminList());
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "详情")
    @GetMapping(value = "/sys/depts/{id}")
    public ApiResult<SysDeptBO> getById(@PathVariable Long id) {
        return ApiResult.data(sysDeptService.getOneById(id, true));
    }

    @SysLog(value = "新增部门")
    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.CREATE)
    @ApiOperation(value = "新增")
    @PostMapping(value = "/sys/depts")
    public ApiResult<?> insert(@RequestBody @Valid AdminInsertOrUpdateSysDeptDTO dto) {
        sysDeptService.adminInsert(dto);

        return ApiResult.success();
    }

    @SysLog(value = "编辑部门")
    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.UPDATE)
    @ApiOperation(value = "编辑")
    @PutMapping(value = "/sys/depts/{id}")
    public ApiResult<?> update(@PathVariable Long id, @RequestBody @Valid AdminInsertOrUpdateSysDeptDTO dto) {
        dto.setId(id);
        sysDeptService.adminUpdate(dto);

        return ApiResult.success();
    }

    @SysLog(value = "删除部门")
    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.DELETE)
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/sys/depts")
    public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
        sysDeptService.adminDelete(dto.getIds());

        return ApiResult.success();
    }

}
