package com.mu.module.test;

import cc.uncarbon.framework.core.constant.HelioConstant;
import cc.uncarbon.framework.core.context.TenantContext;
import cc.uncarbon.framework.core.context.UserContext;
import cc.uncarbon.framework.core.context.UserContextHolder;
import cc.uncarbon.framework.core.page.PageParam;
import cc.uncarbon.framework.core.page.PageResult;
import cn.hutool.core.collection.CollUtil;
import com.mu.module.HelioBootApplication;
import com.mu.module.content.model.request.AdminVlogInsertOrUpdateDTO;
import com.mu.module.content.model.request.AdminVlogListDTO;
import com.mu.module.content.model.response.VlogBO;
import com.mu.module.content.service.VlogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//                      👇 这里需要改成相应启动类 👇
@SpringBootTest(classes = HelioBootApplication.class)
class VlogUnitTest {

    @Resource
    private VlogService vlogService;


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
        PageResult<VlogBO> pageResult = vlogService.adminList(
                new PageParam(1, 10),
                AdminVlogListDTO.builder()

                        .build()
        );

        log.info("\n\n\n分页列表成功 >> 结果={}", pageResult);
    }

    /**
     * 单元测试-后台新增
     */
    @Test
    void testAdminInsert() {
        Long entityId = vlogService.adminInsert(
                AdminVlogInsertOrUpdateDTO.builder()
                        // 
                        .createId(null)
                        // 作者名
                        .author(null)
                        // 
                        .title(null)
                        // 
                        .label(null)
                        // 
                        .text(null)
                        // 
                        .img(null)
                        // 
                        .time(null)
                        // 
                        .status(null)
                        .build()
        );

        log.info("\n\n\n新增成功 >> 新主键ID={}", entityId);
    }

    /**
     * 单元测试-后台更新
     */
    @Test
    void testAdminUpdate() {
        AdminVlogInsertOrUpdateDTO dto = AdminInsertVlogDTO.builder()
                // 
                .id(null)
                // 
                .createId(null)
                // 作者名
                .author(null)
                // 
                .title(null)
                // 
                .label(null)
                // 
                .text(null)
                // 
                .img(null)
                // 
                .time(null)
                // 
                .status(null)
                .build();
        vlogService.adminUpdate(dto);

        log.info("\n\n\n更新完成 >> dto={}", dto);
    }

    /**
     * 单元测试-后台删除
     */
    @Test
    void testAdminDelete() {
        // 主键ID列表
        ArrayList<Long> ids = CollUtil.newArrayList(1L, 2L, 3L);
        vlogService.adminDelete(ids);

        log.info("\n\n\n删除完成 >> ids={}", ids);
    }
}
