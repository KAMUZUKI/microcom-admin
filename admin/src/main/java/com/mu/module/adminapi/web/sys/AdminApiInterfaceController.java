package com.mu.module.adminapi.web.sys;

import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cc.uncarbon.framework.web.model.request.IdsDTO;
import cc.uncarbon.framework.web.model.response.ApiResult;
import com.mu.module.adminapi.constant.AdminApiConstant;
import com.mu.module.sys.model.request.AdminApiInterfaceInsertOrUpdateDTO;
import com.mu.module.sys.model.request.AdminApiInterfaceListDTO;
import com.mu.module.sys.model.response.ApiInterfaceBO;
import com.mu.module.sys.service.ApiInterfaceService;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mu.module.sys.util.AdminStpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 后台管理-管理接口
 */
@RequiredArgsConstructor
@SaCheckLogin(type = AdminStpUtil.TYPE)
@Slf4j
@Api(value = "管理接口", tags = {"管理接口"})
@RequestMapping(AdminApiConstant.HTTP_API_URL_PREFIX + "/api/v1")
@RestController
public class AdminApiInterfaceController {

    // 功能权限串前缀
    private static final String PERMISSION_PREFIX = "ApiInterface:";

    private final ApiInterfaceService apiInterfaceService;


    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "分页列表")
    @GetMapping("/api-interfaces")
    public ApiResult<PageResult<ApiInterfaceBO>> list(PageParam pageParam, AdminApiInterfaceListDTO dto) {
        return ApiResult.data(apiInterfaceService.adminList(pageParam, dto));
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "详情")
    @GetMapping(value = "/api-interfaces/{id}")
    public ApiResult<ApiInterfaceBO> getById(@PathVariable Long id) {
        return ApiResult.data(apiInterfaceService.getOneById(id, true));
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.CREATE)
    @ApiOperation(value = "新增")
    @PostMapping("/api-interfaces")
    public ApiResult<?> insert(@RequestBody @Valid AdminApiInterfaceInsertOrUpdateDTO dto) {
        apiInterfaceService.adminInsert(dto);

        return ApiResult.success();
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.UPDATE)
    @ApiOperation(value = "编辑")
    @PutMapping(value = "/api-interfaces/{id}")
    public ApiResult<?> update(@PathVariable Long id, @RequestBody @Valid AdminApiInterfaceInsertOrUpdateDTO dto) {
        dto.setId(id);
        apiInterfaceService.adminUpdate(dto);

        return ApiResult.success();
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.DELETE)
    @ApiOperation(value = "删除")
    @DeleteMapping("/api-interfaces")
    public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
        apiInterfaceService.adminDelete(dto.getIds());

        return ApiResult.success();
    }

}
