package com.mu.module.adminapi.web.oss;

import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cc.uncarbon.framework.web.model.request.IdsDTO;
import cc.uncarbon.framework.web.model.response.ApiResult;
import com.mu.module.adminapi.constant.AdminApiConstant;
import com.mu.module.oss.model.request.AdminListOssFileInfoDTO;
import com.mu.module.oss.model.response.OssFileInfoBO;
import com.mu.module.oss.service.OssFileInfoService;
import com.mu.module.sys.constant.SysConstant;
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
@Api(value = "后台管理-上传文件信息管理接口", tags = {"后台管理-上传文件信息管理接口"})
@RequestMapping(value = {
        // 兼容旧的API路由前缀
        SysConstant.SYS_MODULE_CONTEXT_PATH + HelioConstant.Version.HTTP_API_VERSION_V1,
        AdminApiConstant.HTTP_API_URL_PREFIX + "/api/v1"
})
@RestController
public class AdminOssFileInfoController {

    // 功能权限串前缀
    private static final String PERMISSION_PREFIX = "OssFileInfo:";

    private final OssFileInfoService ossFileInfoService;


    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "分页列表")
    @GetMapping(value = "/oss/file/infos")
    public ApiResult<PageResult<OssFileInfoBO>> list(PageParam pageParam, AdminListOssFileInfoDTO dto) {
        return ApiResult.data(ossFileInfoService.adminList(pageParam, dto));
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.RETRIEVE)
    @ApiOperation(value = "详情")
    @GetMapping(value = "/oss/file/infos/{id}")
    public ApiResult<OssFileInfoBO> getById(@PathVariable Long id) {
        return ApiResult.data(ossFileInfoService.getOneById(id, true));
    }

    @SaCheckPermission(type = AdminStpUtil.TYPE, value = PERMISSION_PREFIX + HelioConstant.Permission.DELETE)
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/oss/file/infos")
    public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
        ossFileInfoService.adminDelete(dto.getIds());

        return ApiResult.success();
    }

}
