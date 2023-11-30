-- 菜单
    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title,
                          parent_id, type, permission, icon, sort, status, component)
    VALUES (20231124150715774, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '管理', 0, 1,
                             'ApiInterface', 'ant-design:flag-outlined', 100, 1, '/sys/ApiInterface/index');

-- 子按钮
    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title, parent_id,
                          type, permission, icon, sort, status)
    VALUES (20231124150715775, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '查询', 20231124150715774,
                             2,
                             'ApiInterface:retrieve', NULL, 1, 1);

    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title, parent_id,
                          type,
                          permission, icon,
                          sort, status)
    VALUES (20231124150715776, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '新增', 20231124150715774,
                             2,
                             'ApiInterface:create', NULL, 2, 1);

    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title, parent_id,
                          type,
                          permission, icon,
                          sort, status)
    VALUES (20231124150715777, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '编辑', 20231124150715774,
                             2,
                             'ApiInterface:update', NULL, 4, 1);

    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title, parent_id,
                          type,
                          permission, icon,
                          sort, status)
    VALUES (20231124150715778, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '删除', 20231124150715774,
                             2,
                             'ApiInterface:delete', NULL, 3, 1);

