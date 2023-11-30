package com.mu.test;

import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.core.context.TenantContext;
import cc.uncarbon.framework.core.context.UserContext;
import cc.uncarbon.framework.core.context.UserContextHolder;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cn.hutool.core.collection.CollUtil;
import com.mu.module.sys.model.request.AdminApiInterfaceInsertOrUpdateDTO;
import com.mu.module.sys.model.request.AdminApiInterfaceListDTO;
import com.mu.module.sys.model.response.ApiInterfaceBO;
import com.mu.module.sys.service.ApiInterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//                      👇 这里需要改成相应启动类 👇
@SpringBootTest(classes = ApiInterfaceUnitTest.class)
class ApiInterfaceUnitTest {

    @Resource
    private ApiInterfaceService apiInterfaceService;


    /**
     * 单元测试-初始化
     */
    @BeforeEach
    public void init() {
        // 可以在这里进行初始化操作, 如设置用户上下文等
        UserContextHolder.setUserContext(
                UserContext.builder()
                        .userId(1L)
                        .userName("admin")
                        .build()
        );

        // 指定租户
        /*TenantContextHolder.setTenantContext(
                new TenantContext()
                        .setTenantId()
                        .setTenantName()
        );*/
    }

    /**
     * 单元测试-分页列表
     */
    @Test
    void testAdminList() {
        PageResult<ApiInterfaceBO> pageResult = apiInterfaceService.adminList(
                new PageParam(1, 10),
                AdminApiInterfaceListDTO.builder()

                        .build()
        );

        log.info("\n\n\n分页列表成功 >> 结果={}", pageResult);
    }

    /**
     * 单元测试-后台新增
     */
    @Test
    void testAdminInsert() {
        Long entityId = apiInterfaceService.adminInsert(
                AdminApiInterfaceInsertOrUpdateDTO.builder()
                        // 接口名称或标识
                        .name(null)
                        // 接口路径
                        .path(null)
                        // 请求方法
                        .method(null)
                        // 请求参数，包括参数名称、数据类型、是否必需、默认值等信息
                        .requestParameters(null)
                        // 返回数据结构，包括返回字段、数据类型、示例值等信息
                        .responseData(null)
                        // 接口示例
                        .description(null)
                        // 接口所需的权限或角色
                        .permissions(null)
                        // 接口版本号
                        .version(null)
                        .build()
        );

        log.info("\n\n\n新增成功 >> 新主键ID={}", entityId);
    }

    /**
     * 单元测试-后台更新
     */
    @Test
    void testAdminUpdate() {
        AdminApiInterfaceInsertOrUpdateDTO dto = AdminApiInterfaceInsertOrUpdateDTO.builder()
                // 
                .id(null)
                // 接口名称或标识
                .name(null)
                // 接口路径
                .path(null)
                // 请求方法
                .method(null)
                // 请求参数，包括参数名称、数据类型、是否必需、默认值等信息
                .requestParameters(null)
                // 返回数据结构，包括返回字段、数据类型、示例值等信息
                .responseData(null)
                // 接口示例
                .description(null)
                // 接口所需的权限或角色
                .permissions(null)
                // 接口版本号
                .version(null)
                .build();
        apiInterfaceService.adminUpdate(dto);

        log.info("\n\n\n更新完成 >> dto={}", dto);
    }

    /**
     * 单元测试-后台删除
     */
    @Test
    void testAdminDelete() {
        // 主键ID列表
        ArrayList<Long> ids = CollUtil.newArrayList(1L, 2L, 3L);
        apiInterfaceService.adminDelete(ids);

        log.info("\n\n\n删除完成 >> ids={}", ids);
    }
}
