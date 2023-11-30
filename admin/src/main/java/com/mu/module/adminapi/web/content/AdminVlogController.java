package com.mu.module.adminapi.web.content;

import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cc.uncarbon.framework.web.model.request.IdsDTO;
import cc.uncarbon.framework.web.model.response.ApiResult;
import com.mu.module.adminapi.constant.AdminApiConstant;
import com.mu.module.content.model.request.AdminVlogInsertOrUpdateDTO;
import com.mu.module.content.model.request.AdminVlogListDTO;
import com.mu.module.content.model.response.VlogBO;
import com.mu.module.content.service.VlogService;
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
public class AdminVlogController {

    // 功能权限串前缀
    private static final String PERMISSION_PREFIX = "Vlog:";

    private final VlogService vlogService;


    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "分页列表")
    @GetMapping("/vlogs")
    public ApiResult<PageResult<VlogBO>> list(PageParam pageParam, AdminVlogListDTO dto) {
        return ApiResult.data(vlogService.adminList(pageParam, dto));
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "详情")
    @GetMapping(value = "/vlogs/{id}")
    public ApiResult<VlogBO> getById(@PathVariable Long id) {
        return ApiResult.data(vlogService.getOneById(id, true));
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.CREATE)
    @ApiOperation(value = "新增")
    @PostMapping("/vlogs")
    public ApiResult<?> insert(@RequestBody @Valid AdminVlogInsertOrUpdateDTO dto) {
        vlogService.adminInsert(dto);

        return ApiResult.success();
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.UPDATE)
    @ApiOperation(value = "编辑")
    @PutMapping(value = "/vlogs/{id}")
    public ApiResult<?> update(@PathVariable Long id, @RequestBody @Valid AdminVlogInsertOrUpdateDTO dto) {
        dto.setId(id);
        vlogService.adminUpdate(dto);

        return ApiResult.success();
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.DELETE)
    @ApiOperation(value = "删除")
    @DeleteMapping("/vlogs")
    public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
        vlogService.adminDelete(dto.getIds());

        return ApiResult.success();
    }

}
